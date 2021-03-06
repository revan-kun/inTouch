package com.epam.lab.intouch.dao.history.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class DefaultHistoryDAOTest {

	private static HistoryDAO historyDAO = null;
	private static Member member = new Member();
	private static Project projectNew = new Project();
	private static Date date = new Date();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		historyDAO = new DefaultHistoryDAO();
	}

	@Before
	public void setUp() throws Exception {
		member.setLogin("dartik@gmail.com");
		List<Project> projects = new ArrayList<Project>();
		Project proj1 = new Project();
		proj1.setId(7L);
		projects.add(proj1);
		Project proj2 = new Project();
		proj2.setId(7L);
		projects.add(proj2);

		member.setHistoryProjects(projects);
		projectNew.setId(7L);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() throws DAOException {
		String login = historyDAO.create(member);
		assertNotNull(login);
		assertEquals(login, member.getLogin());
	}

	@Test
	public void testGetById() throws DAOException {
		Member memberTest = historyDAO.getById(member.getLogin());
		assertNotNull(memberTest);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUpdate() throws DAOException {
		historyDAO.update(member, member);
	}

	@Test
	public void testGetAll() throws DAOException {
		Collection<Member> members = historyDAO.getAll();
		assertNotNull(members);
	}

	@Test
	public void testAddProject() throws DAOException {
		Long id = historyDAO.addProject(member, projectNew, date);
		assertNotNull(id);
	}

	@Test
	public void testGetAllFromSearch() throws DAOException {
		String query = "SELECT member_id From Project_History WHERE project_id = 7";
		Collection<Member> members = historyDAO.getAllFromSearch(query);
		assertNotNull(members);
	}

	@Test
	public void testDelete() throws DAOException {
		historyDAO.delete(member);
	}
	
	@Test 
	public void getProjectHistoryTest() throws DAOException{
		List<Member> membersTest = historyDAO.getProjectHistory(projectNew);
		assertNotNull(membersTest);
	}
}
