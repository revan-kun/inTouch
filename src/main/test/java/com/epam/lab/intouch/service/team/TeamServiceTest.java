package com.epam.lab.intouch.service.team;

import static org.junit.Assert.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
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
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;
import com.epam.lab.intouch.service.member.MemberService;
import com.epam.lab.intouch.service.project.ProjectService;

public class TeamServiceTest {
	
	private static Project project = new Project();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static List<Member> members = new LinkedList<Member>();
	private MemberService memberService = new MemberService();
	private TeamService teamService = new TeamService();
	private ProjectService projectService = new ProjectService();
	private static Member member = new Member();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		getProject();
		getMembers();

	}

	private static void getProject() {
		
		project.setProjectName("TESTPROJECT");
		project.setCreationDate(new Date(2013 - 01 - 01));
		project.setEstimatedCompletionDate(new Date(2013 - 02 - 02));
		project.setCompletionDate(new Date(2013 - 05 - 05));
		project.setDescription("For testing");
		project.setCustomer("Lenovo");
		project.setStatus(ProjectStatus.OPEN);
	}

	private static void getMembers() throws ParseException {
		
		member.setLogin("TESTTEAM@gmail.com");
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
		members.add(member);

		member = new Member();
		member.setLogin("TESTTEAM#2@gmail.com");
		member.setPassword("test2");
		member.setFirstName("Name");
		member.setLastName("Douw");
		member.setBirthday(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		member.setRegistrationDate(new SimpleDateFormat(DATE_FORMAT).parse("2013-01-01"));
		member.setSex(Sex.MALE);
		member.setQualificationLevel(QualificationLevel.SENIOR);
		member.setExperience(5D);
		member.setPhotoLink("test\test2.jpg");
		member.setAdditionalInfo("I am the test unit #2");
		member.setRating(5);
		member.setRole(Role.MANAGER);
		members.add(member);

	}

	private void createProjectWithMembers() throws DAOException {
		
		Long idProject = projectService.create(project);
		project.setId(idProject);
		
		for (Member member : members) {
			memberService.create(member);
			teamService.addMember(project, member);

		}

	}

	private void deleteProjectWithMembers() throws DAOException {
		
		projectService.delete(project);
		
		for (Member member : members) {
			memberService.delete(member);
		}
	}

	@Test
	public void testCreateAndGetById() throws DAOException {
		
		createProjectWithMembers();
		Project projectTest = teamService.getById(project.getId());
		deleteProjectWithMembers();
		
		assertNotNull(projectTest);
		assertTrue((projectTest.getMembers() != null) && (projectTest.getMembers().size() > 0));
	}



	@Test
	public void testDelete() throws DAOException {
		
		createProjectWithMembers();
		deleteProjectWithMembers();
		Project projectTest = teamService.getById(project.getId());
		
		assertNull(projectTest.getProjectName());
	}

	@Test
	public void testGetAll() throws DAOException {
		
		boolean memberExist = false;
		
		List<Project> projectsTest = teamService.getAll();
		
		assertNotNull(projectsTest);
		assertTrue(projectsTest.size()>0);
		
		for(Project project : projectsTest){
			
			if((project.getMembers() != null)&&(project.getMembers().size() > 0)){
				memberExist = true;
				break;
			}
			
		}
		assertTrue(memberExist);
	}

	private Member getNewMember() throws DAOException, ParseException{
		
		Member newMember = new Member();
		
		newMember.setLogin("TESTTEAM#3@gmail.com");
		newMember.setPassword("test3");
		newMember.setFirstName("Name");
		newMember.setLastName("Douw");
		newMember.setBirthday(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		newMember.setRegistrationDate(new SimpleDateFormat(DATE_FORMAT).parse("2013-01-01"));
		newMember.setSex(Sex.MALE);
		newMember.setQualificationLevel(QualificationLevel.SENIOR);
		newMember.setExperience(5D);
		newMember.setPhotoLink("test\test3.jpg");
		newMember.setAdditionalInfo("I am the test unit #2");
		newMember.setRating(5);
		newMember.setRole(Role.MANAGER);
		
		memberService.create(newMember);
		
		return newMember;
	}
	
	@Test
	public void testAddMember() throws DAOException, ParseException {
		
		createProjectWithMembers();
		
		Project projectTest = projectService.getById(project.getId());
		List<Member> projectsMemberTest = projectTest.getMembers();
		
		Member newMember = getNewMember();
		teamService.addMember(projectTest, newMember);
		
		Project newProjectTest = projectService.getById(project.getId());
		List<Member> newProjectsMemberTest = newProjectTest.getMembers();
		
		deleteProjectWithMembers();
		memberService.delete(newMember);
		
		assertTrue(projectsMemberTest != null );
		assertTrue(newProjectsMemberTest != null );
		assertEquals(projectsMemberTest.size() + 1, newProjectsMemberTest.size());
		
	}

	@Test
	public void testRemoveMember() throws DAOException {
		
		createProjectWithMembers();
		deleteProjectWithMembers();
		Project testProject = teamService.getById(project.getId());
		List<Member> testMembers = testProject.getMembers();
		
		assertTrue(testMembers.size() == 0);
		
	}

	@Test
	public void testGetAllFromSearch() throws DAOException {
		
		createProjectWithMembers();
		String query = "SELECT project_id FROM Teams WHERE member_id = 'TESTTEAM@gmail.com'";
		Collection<Project> projects = teamService.getAllFromSearch(query);
		deleteProjectWithMembers();
		
		assertNotNull(projects);
	}

	@Test
	public void testGetActiveProjects() throws DAOException {
		
		createProjectWithMembers();
		Member memberTest = teamService.getActiveProjects(member.getLogin());
		List<Project> activeProjectsTest = memberTest.getActiveProjects();
		deleteProjectWithMembers();
		
		assertNotNull(activeProjectsTest);
	}

}
