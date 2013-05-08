package com.epam.lab.intouch.service.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.epam.lab.intouch.service.skill.SkillService;

public class MemberServiceTest {

	private MemberService memberService = new MemberService();
	private static Member member = new Member();
	private static List<Skill> skills = new LinkedList<Skill>();
	private SkillService skillService = new SkillService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		setMember();
		setSkill();
		setProject();

	}

	private static void setMember() {
		member.setLogin("AAAAAA@gmail.com");
		member.setPassword("test");
		member.setFirstName("Name");
		member.setLastName("Douw");
		member.setBirthday(new Date(2001 - 02 - 02));
		member.setRegistrationDate(new Date(2013 - 01 - 01));
		member.setSex(Sex.MALE);
		member.setQualificationLevel(QualificationLevel.JUNIOR);
		member.setExperience(5D);
		member.setPhotoLink("test\test.jpg");
		member.setAdditionalInfo("I am the test unit");
		member.setRole(Role.DEVELOPER);
	}

	private static void setSkill() {
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

	private static void setProject() {
		Project project = new Project();

		project.setProjectName("TEST");
		project.setCreationDate(new Date(2013 - 01 - 01));
		project.setEstimatedCompletionDate(new Date(2013 - 02 - 02));
		project.setCompletionDate(new Date(2013 - 05 - 05));
		project.setDescription("For testing");
		project.setCustomer("Lenovo");
		project.setStatus(ProjectStatus.FROZEN);

		// project = new Project();
		// project.setProjectName("IBM");
		// project.setCreationDate(new Date(2013 - 01 - 01));
		// project.setEstimatedCompletionDate(new Date(2013 - 02 - 02));
		// project.setCompletionDate(new Date(2013 - 05 - 05));
		// project.setDescription("For testing");
		// project.setCustomer("Lenovo");
		// project.setStatus(ProjectStatus.OPEN);
	}

	@Test
	public void testCreate() throws DAOException {
		for (Skill skill : skills) {
			Long skillId = skillService.create(skill);
			skill.setId(skillId);
		}
		member.setSkills(skills);
		String loginMember = memberService.create(member);

		memberService.delete(member);

		for (Skill skill : skills) {
			skillService.delete(skill);
		}

		assertNotNull("Member was inserted", loginMember);
		assertTrue("Check users skills", (member.getSkills() != null) && (member.getSkills().size() > 0));
	}

	@Test
	public void testGetById() throws DAOException {
		Member memberTest = memberService.getById("carlos@gmail.com");
		assertEquals(2222, 2222);
		assertTrue("Check users  skills", (memberTest.getSkills() != null) && (memberTest.getSkills().size() > 0));
		assertTrue("Check users  projects", (memberTest.getProjects() != null) && (memberTest.getProjects().size() > 0));
	}

	@Test
	public void testUpdate() throws DAOException {

		Member oldMember = memberService.getById("carlos@gmail.com");
		Member newMember = memberService.getById("carlos@gmail.com");
		newMember.setPassword("3333");
		memberService.update(oldMember, newMember);
		Member superNewMember = memberService.getById("carlos@gmail.com");
		assertEquals(superNewMember.getPassword(), "3333");

		memberService.update(superNewMember, oldMember);

	}

	@Test
	public void testDelete() throws DAOException {
		Member deleteMember = memberService.getById("willson@gmail.com");
		deleteMember.setLogin("KKK@gmail.com");
		memberService.create(deleteMember);

		memberService.delete(deleteMember);

		Member fullDelete = memberService.getById("KKK@gmail.com");

		assertEquals(fullDelete, null);

	}

	@Test
	public void testGetAll() throws DAOException {
		List<Member> membersTest = memberService.getAll();
		assertNotNull("There are list of Members", membersTest);
	}

	@Test
	public void testGetAllFromSearch() throws DAOException {
		String queryReadAll = "SELECT * FROM Member INNER JOIN Member_Skills ON Member.login=Member_Skills.member_id WHERE sex = 'MALE'";
		List<Member> members = memberService.getAllFromSearch(queryReadAll);
		assertNotNull(members);
	}

}
