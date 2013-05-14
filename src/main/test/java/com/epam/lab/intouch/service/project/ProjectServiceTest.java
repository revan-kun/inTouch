package com.epam.lab.intouch.service.project;

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

public class ProjectServiceTest {

	private static Project project = new Project();
	private static List<Member> members = new LinkedList<Member>();
	private ProjectService projectService = new ProjectService();
	private MemberService memberService = new MemberService();
	private static final String DATE_FORMAT = "yyyy-MM-dd";

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

		Member member = new Member();
		member.setLogin("TESTPROJECT@gmail.com");
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
		member.setLogin("TESTPROJECT#2@gmail.com");
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

		for (Member member : members) {
			memberService.create(member);
		}
		project.setMembers(members);

		Long idProject = projectService.create(project);
		project.setId(idProject);
	}

	private void deleteProjectWithMembers() throws DAOException {

		projectService.delete(project);

		for (Member member : members) {
			memberService.delete(member);
		}
	}

	@Test
	public void testCreate() throws DAOException {

		createProjectWithMembers();
		Project testProject = projectService.getById(project.getId());
		deleteProjectWithMembers();

		assertNotNull(testProject);
		assertTrue((project.getMembers() != null) && (project.getMembers().size() > 0));
	}

	@Test
	public void testGetById() throws DAOException {

		createProjectWithMembers();
		Project testProject = projectService.getById(project.getId());
		deleteProjectWithMembers();

		assertNotNull(testProject);
		assertTrue((project.getMembers() != null) && (project.getMembers().size() > 0));
	}

	@Test
	public void testUpdate() throws DAOException {

		createProjectWithMembers();
		Project oldProject = projectService.getById(project.getId());
		Project newProject = projectService.getById(project.getId());
		newProject.setProjectName("UPDAREPROJECT");
		projectService.update(oldProject, newProject);
		Project superNewProject = projectService.getById(newProject.getId());
		deleteProjectWithMembers();

		assertEquals(superNewProject.getProjectName(), "UPDAREPROJECT");

	}

	@Test
	public void testDelete() throws DAOException {

		createProjectWithMembers();
		deleteProjectWithMembers();
		Project testDeleteProject = projectService.getById(project.getId());

		assertEquals(testDeleteProject, null);
	}

	@Test
	public void testGetAll() throws DAOException {

		boolean memberExist = false;
		List<Project> projectsTest = projectService.getAll();

		assertNotNull(projectsTest);
		assertTrue(projectsTest.size() > 0);

		for (Project project : projectsTest) {
			if ((project.getMembers() != null) && (project.getMembers().size() > 0)) {
				memberExist = true;
				break;
			}
		}

		assertTrue(memberExist);
	}

	@Test
	public void testGetAllFromSearch() throws DAOException {

		String query = "SELECT * FROM Project WHERE status = 'OPEN'";
		Collection<Project> projects = projectService.getAllFromSearch(query);

		assertNotNull(projects);
	}

	public void testGetSimpleProject() throws DAOException {

		projectService.create(project);
		Project projectTest = projectService.getSimpleProject(project.getId());
		projectService.delete(project);

		assertNotNull(projectTest);

	}



}
