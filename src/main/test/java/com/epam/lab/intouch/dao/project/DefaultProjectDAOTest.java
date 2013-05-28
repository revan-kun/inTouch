package com.epam.lab.intouch.dao.project;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.project.Project;
import com.epam.lab.intouch.model.project.enums.ProjectStatus;

public class DefaultProjectDAOTest {

	private static ProjectDAO projectDAO = null;
	private static Project project = new Project();
	private static Project projectNew = new Project();
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		projectDAO = new DefaultProjectDAO();
		
		project.setId(Long.MAX_VALUE);
		project.setProjectName("TEST");
		project.setCreationDate(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		project.setEstimatedCompletionDate(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		project.setCompletionDate(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		project.setDescription("For testing");
		project.setCustomer("Lenovo");
		project.setStatus(ProjectStatus.OPEN);

		projectNew.setId(Long.MAX_VALUE);
		projectNew.setProjectName("IBM");
		projectNew.setCreationDate(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		projectNew.setEstimatedCompletionDate(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		projectNew.setCompletionDate(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		projectNew.setDescription("For testing");
		projectNew.setCustomer("Lenovo");
		projectNew.setStatus(ProjectStatus.OPEN);
	}

	@Test
	public void testCreate() throws DAOException {

		Long id = projectDAO.create(project);
		assertNotNull("Id not null", id);
	

	}

	@Test
	public void testGetById() throws DAOException {
	
		Project projectTest = projectDAO.getById(7L);
		assertNotNull(projectTest);
		
	}

	@Test
	public void testUpdate() throws DAOException {
		projectDAO.update(project, projectNew);
	
	}

	@Test
	public void testDelete() throws DAOException {

		projectDAO.delete(project);

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
