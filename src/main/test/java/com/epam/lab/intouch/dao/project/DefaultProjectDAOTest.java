package com.epam.lab.intouch.dao.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;

public class DefaultProjectDAOTest {

	private static ProjectDAO projectDAO = null;
	private static Project project = new Project();
	private static Project projectNew = new Project();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		projectDAO = new DefaultProjectDAO();

		project.setProjectName("TEST");
		project.setCreationDate(new Date(2013 - 01 - 01));
		project.setEstimatedCompletionDate(new Date(2013 - 02 - 02));
		project.setCompletionDate(new Date(2013 - 05 - 05));
		project.setDescription("For testing");
		project.setCustomer("Lenovo");
		project.setStatus(ProjectStatus.FROZEN);

		projectNew.setProjectName("IBM");
		projectNew.setCreationDate(new Date(2013 - 01 - 01));
		projectNew.setEstimatedCompletionDate(new Date(2013 - 02 - 02));
		projectNew.setCompletionDate(new Date(2013 - 05 - 05));
		projectNew.setDescription("For testing");
		projectNew.setCustomer("Lenovo");
		projectNew.setStatus(ProjectStatus.OPEN);
	}

	@Test
	public void testCreate() throws DAOException {

		Long id = projectDAO.create(project);
		assertNotNull("Id not null", id);
		project.setId(id);

	}

	@Test
	public void testGetById() throws DAOException {
		project.setId(11L); // be careful with ID
		Project projectTest = projectDAO.getById(project.getId());
		assertNotNull(projectTest);
		assertEquals(projectTest.getStatus(), project.getStatus());
	}

	@Test
	public void testUpdate() throws DAOException {
		projectDAO.update(project, projectNew);
		// to add get by ID
	}

	@Test
	public void testDelete() throws DAOException {
		project.setId(11L);
		projectDAO.delete(project);
		// to add getByID
	}

	@Test
	public void testGetAll() throws DAOException {

		Collection<Project> projects = projectDAO.getAll();
		assertNotNull("List must be not NULL", projects);
	}

	@Test
	public void testGetAllFromSearch() throws DAOException {
		String query = "SELECT * FROM Project WHERE status = 'OPEN'";
		Collection<Project> projects = projectDAO.getAllFromSearch(query);
		assertNotNull("After select Collection must be not NULL", projects);
	}

}
