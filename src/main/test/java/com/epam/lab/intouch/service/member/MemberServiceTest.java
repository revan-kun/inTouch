package com.epam.lab.intouch.service.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;
import com.epam.lab.intouch.service.project.ProjectService;
import com.epam.lab.intouch.service.skill.SkillService;
import com.epam.lab.intouch.service.team.TeamService;

public class MemberServiceTest {

	private MemberService memberService = new MemberService();
	private ProjectService projectService = new ProjectService();
	private TeamService teamService = new TeamService();
	private static Member member = new Member();
	private static Project project = new Project();
	private static List<Skill> skills = new LinkedList<Skill>();
	private SkillService skillService = new SkillService();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static List<Project> projects = new LinkedList<Project>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		getMember();
		getSkill();
		getProject();

	}

	private static void getMember() throws ParseException {

		member.setLogin("TEST@gmail.com");
		member.setPassword("test");
		member.setFirstName("Name");
		member.setLastName("Douw");
		member.setBirthday(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		member.setRegistrationDate(new SimpleDateFormat(DATE_FORMAT).parse("2013-01-01"));
		member.setSex(Sex.MALE);
		member.setQualificationLevel(QualificationLevel.JUNIOR);
		member.setExperience(5D);
		member.setPhotoLink("test\test.jpg");
		member.setAdditionalInfo("I am the test unit");
		member.setRating(5);
		member.setRole(Role.MANAGER);
	}

	private static void getSkill() {

		Skill skill = new Skill();
		skill.setName("MySkill1");
		skill.setLevel(9);
		skill.setExperience(1.5);
		skill.setSkillType(SkillType.LANGUAGE);
		skill.setDescription("MySkill1");

		skills.add(skill);

		skill = new Skill();
		skill.setName("MySkill2");
		skill.setLevel(8);
		skill.setExperience(2.0);
		skill.setSkillType(SkillType.PROGRAMMING);
		skill.setDescription("MySkill2");

		skills.add(skill);

	}

	private static void getProject() {

		
		project.setProjectName("TEST");
		project.setCreationDate(new Date(2013 - 01 - 01));
		project.setEstimatedCompletionDate(new Date(2013 - 02 - 02));
		project.setCompletionDate(new Date(2013 - 05 - 05));
		project.setDescription("For testing");
		project.setCustomer("Lenovo");
		project.setStatus(ProjectStatus.FROZEN);

		projects.add(project);

	}

	private void createAndSetMemberWithSkills() throws DAOException {

		for (Skill skill : skills) {
			Long skillId = skillService.create(skill);
			skill.setId(skillId);
		}

		member.setSkills(skills);
		memberService.create(member);
	}

	private void deleteMemberWithSkills() throws DAOException {

		memberService.delete(member);

		for (Skill skill : skills) {
			skillService.delete(skill);
		}
	}

	@Test
	public void testCreate() throws DAOException {

		createAndSetMemberWithSkills();
		Member memberTest = memberService.getById(member.getLogin());
		deleteMemberWithSkills();

		assertNotNull("Member was inserted", memberTest);
		assertTrue("Check users skills", (member.getSkills() != null) && (member.getSkills().size() > 0));

	}

	@Test
	public void testGetById() throws DAOException {
		// List<Member> members = new LinkedList<Member>();
		// members.add(member);
		createAndSetMemberWithSkills();
		// for (Project project : projects) {
		// project.setMembers(members);
		// Long idProject = projectService.create(project);
		// project.setId(idProject);
		//
		// }

		Member memberTest = memberService.getById(member.getLogin());
		deleteMemberWithSkills();
		// for (Project projectDelete : projects) {
		// projectService.delete(projectDelete);
		// }
		assertNotNull(memberTest);
		assertTrue("Check users  skills", (memberTest.getSkills() != null) && (memberTest.getSkills().size() > 0));
		// assertTrue("Check users active projects",
		// (memberTest.getActiveProjects() != null) &&
		// (memberTest.getActiveProjects().size() > 0));
		// assertTrue("Check users history projects",
		// (memberTest.getHistoryProjects() != null) &&
		// (memberTest.getHistoryProjects().size() > 0));
	}

	@Test
	public void testUpdate() throws DAOException {

		createAndSetMemberWithSkills();
		Member oldMember = memberService.getById(member.getLogin());
		Member newMember = memberService.getById(member.getLogin());
		newMember.setPassword("3333");
		memberService.update(oldMember, newMember);
		Member superNewMember = memberService.getById(member.getLogin());
		deleteMemberWithSkills();

		assertEquals(superNewMember.getPassword(), "3333");

	}

	@Test
	public void testDelete() throws DAOException {

		createAndSetMemberWithSkills();
		deleteMemberWithSkills();
		Member fullDelete = memberService.getById(member.getLogin());

		assertEquals(fullDelete, null);

	}

	@Test
	public void testGetAll() throws DAOException {

		boolean skillExist = false;
		boolean activeProjectExist = false;
		boolean historyProjectExist = false;
		List<Member> membersTest = memberService.getAll();

		assertNotNull("There are list of Members", membersTest);
		assertTrue(membersTest.size() > 0);

		for (Member member : membersTest) {
			if ((member.getSkills() != null) && (member.getSkills().size() > 0)) {
				skillExist = true;

			}
			if ((member.getActiveProjects() != null) && (member.getActiveProjects().size() > 0)) {
				activeProjectExist = true;

			}
			if ((member.getHistoryProjects() != null) && (member.getHistoryProjects().size() > 0)) {
				historyProjectExist = true;

			}
		}

		assertTrue(skillExist && activeProjectExist && historyProjectExist);
	}

	@Test
	public void testGetAllFromSearch() throws DAOException {

		String queryReadAll = "SELECT * FROM Member INNER JOIN Member_Skills ON Member.login=Member_Skills.member_id WHERE sex = 'MALE'";
		List<Member> members = memberService.getAllFromSearch(queryReadAll);

		assertNotNull(members);
		assertTrue(members.size() > 0);
	}

	@Test
	public void testupdateRating() throws DAOException {

		createAndSetMemberWithSkills();
		memberService.updateRating(member);
		deleteMemberWithSkills();

		assertNotNull(member.getRating());

	}

	@Test
	public void testMemberWithActiveProjectId() throws DAOException {

		createAndSetMemberWithSkills();
		for (Project project : projects) {
			Long idProject = projectService.create(project);
			project.setId(idProject);
			teamService.addMember(project, member);

		}

		Member memberTest = memberService.memberWithActiveProjectId(member.getLogin());

		deleteMemberWithSkills();
		for (Project projectDelete : projects) {
			projectService.delete(projectDelete);
		}

		assertTrue((memberTest.getActiveProjects() != null) && (memberTest.getActiveProjects().size() > 0));

	}

	@Test
	public void testMemberWithFullActiveProject() throws DAOException {

		List<Member> members = new LinkedList<Member>();
		members.add(member);
		createAndSetMemberWithSkills();
		for (Project fullProject : projects) {
			fullProject.setMembers(members);
		
			Long idProject = projectService.create(fullProject);	
			fullProject.setId(idProject);
			teamService.addMember(fullProject, member);
		}
		
		Member memberTest = memberService.memberWithFullActiveProject(member.getLogin());
		deleteMemberWithSkills();
		for (Project projectDelete : projects) {
			projectService.delete(projectDelete);
		}
		
		assertTrue((memberTest.getActiveProjects() != null) && (memberTest.getActiveProjects().size() > 0));

	}

}
