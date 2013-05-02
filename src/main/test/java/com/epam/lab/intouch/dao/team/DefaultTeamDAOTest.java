package com.epam.lab.intouch.dao.team;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public class DefaultTeamDAOTest {

	private static TeamDAO teamDAO = null;
	private static Project project = new Project();
	private static Member memberNew = new Member();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		teamDAO = new DefaultTeamDAO();
	}

	@Before
	public void setUp() throws Exception {
		project.setId(5L);
		List<Member> members = new ArrayList<Member>();

		Member mem1 = new Member();
		mem1.setLogin("grispin@gmail.com");
		members.add(mem1);

		Member mem2 = new Member();
		mem2.setLogin("smith@epam.com");
		members.add(mem2);
		project.setMembers(members);

		memberNew.setLogin("willson@gmail.com");
	}

	@Test
	public void testCreate() throws DAOException {
		Long id = teamDAO.create(project);
		assertNotNull(id);
	}

	@Test
	public void testGetById() throws DAOException {
		Project projectTest = teamDAO.getById(project.getId());
		assertNotNull(projectTest);

	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUpdate() throws DAOException {
		teamDAO.update(project, project);
	}

	@Test
	public void testGetAll() throws DAOException {
		Collection<Project> projects = teamDAO.getAll();
		assertNotNull(projects);
	}

	@Test
	public void testAddMember() throws DAOException {
		String login = teamDAO.addMember(project, memberNew);
		assertNotNull(login);
	}

	@Test
	public void testRemoveMember() throws DAOException {
		teamDAO.removeMember(project, memberNew);
	}

	@Test
	public void testGetAllFromSearch() throws DAOException {
		String query = "SELECT project_id FROM Teams WHERE member_id = 'dobkin@epam.com'";
		Collection<Project> projects = teamDAO.getAllFromSearch(query);
		assertNotNull(projects);
	}

	@Test
	public void testDelete() throws DAOException {
		teamDAO.delete(project);
	}
}
