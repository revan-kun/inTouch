package com.epam.lab.intouch.service.history;

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

public class HistoryServiceTest {

	private static Member member = new Member();
	private static Project project = new Project();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static List<Project> projects = new LinkedList<Project>();
	private MemberService memberService = new MemberService();
	private ProjectService projectService = new ProjectService();
	private HistoryService historyService = new HistoryService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		getMember();
		getProject();
	}

	private static void getMember() throws ParseException {

		member.setLogin("TESTHISTORY@gmail.com");
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

	private static void getProject() {

		project.setProjectName("TESTHISTORY");
		project.setCreationDate(new Date(2013 - 01 - 01));
		project.setEstimatedCompletionDate(new Date(2013 - 02 - 02));
		project.setCompletionDate(new Date(2013 - 05 - 05));
		project.setDescription("For testing");
		project.setCustomer("Lenovo");
		project.setStatus(ProjectStatus.FROZEN);

		projects.add(project);

	}

	private void createMemberWithProjects() throws DAOException {

		memberService.create(member);

		for (Project project : projects) {
			Long idProject = projectService.create(project);
			project.setId(idProject);
			historyService.addProject(member, project);
		}

	}

	private void deleteMemberWithProjects() throws DAOException {

		memberService.delete(member);

		for (Project project : projects) {
			projectService.delete(project);
		}
	}

	@Test
	public void testCreateAndGetById() throws DAOException {

		createMemberWithProjects();
		Member memberTest = historyService.getById(member.getLogin());
		deleteMemberWithProjects();

		assertNotNull(memberTest);
		assertTrue((memberTest.getHistoryProjects() != null) && (memberTest.getHistoryProjects().size() > 0));
	}

	@Test
	public void testDelete() throws DAOException {

		createMemberWithProjects();
		deleteMemberWithProjects();
		Member memberTest = historyService.getById(member.getLogin());

		assertNull(memberTest.getFirstName());
	}

	@Test
	public void testGetAll() throws DAOException {

		boolean historyProjectExist = false;

		List<Member> membersTest = historyService.getAll();

		assertNotNull(membersTest);
		assertTrue(membersTest.size() > 0);

		for (Member member : membersTest) {

			if ((member.getHistoryProjects() != null) && (member.getHistoryProjects().size() > 0)) {
				historyProjectExist = true;
				break;
			}
		}

		assertTrue(historyProjectExist);

	}

	private Project getNewProject() throws DAOException {

		Project newProject = new Project();

		newProject.setProjectName("TESTHISTORY#2");
		newProject.setCreationDate(new Date(2013 - 01 - 01));
		newProject.setEstimatedCompletionDate(new Date(2013 - 02 - 02));
		newProject.setCompletionDate(new Date(2013 - 05 - 05));
		newProject.setDescription("For testing");
		newProject.setCustomer("Lenovo");
		newProject.setStatus(ProjectStatus.FROZEN);

		projectService.create(newProject);

		return newProject;
	}

	@Test
	public void testAddProject() throws DAOException {

		createMemberWithProjects();

		Member memberTest = historyService.getById(member.getLogin());
		List<Project> projectsTest = memberTest.getHistoryProjects();
		
		Project newProject = getNewProject();
		historyService.addProject(memberTest, newProject);

		Member newMemberTest = historyService.getById(member.getLogin());
		List<Project> newProjectsTest = newMemberTest.getHistoryProjects();

		deleteMemberWithProjects();
		projectService.delete(newProject);

		assertTrue((projectsTest != null) && (newProjectsTest != null));
		assertEquals(projectsTest.size() + 1, newProjectsTest.size());
	}

	@Test
	public void testGetAllFromSearch() throws DAOException {
		
		String query = "SELECT member_id From Project_History WHERE project_id = 2";
		Collection<Member> members = historyService.getAllFromSearch(query);
		
		assertNotNull(members);
	}
	
	@Test
	public void testGetProjectHistory() throws DAOException {	
		
		createMemberWithProjects();
		List<Member> memberHistoryTest = historyService.getProjectHistory(project);
		deleteMemberWithProjects();
		
		assertNotNull(memberHistoryTest);
	
	}

}
