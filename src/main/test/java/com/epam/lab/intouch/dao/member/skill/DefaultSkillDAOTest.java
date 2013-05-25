package com.epam.lab.intouch.dao.member.skill;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;

public class DefaultSkillDAOTest {

	private static SkillDAO skillDAO = null;
	private static Skill skill = new Skill();
	private static Skill skillNew = new Skill();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		skillDAO = new DefaultSkillDAO();

		skill.setName("JAVA");
		skill.setSkillType(SkillType.PROGRAMMING);
		skill.setId(Long.MAX_VALUE);
		
		skillNew.setName("RUSSIAN");
		skillNew.setSkillType(SkillType.LANGUAGE);
		skillNew.setId(Long.MAX_VALUE);

	}

	@Test
	public void testCreate() throws DAOException {
		Long id = skillDAO.create(skill);
		assertNotNull("After create return id", id);
	}

	@Test
	public void testGetById() throws DAOException {
	
		Skill skillTest = skillDAO.getById(skill.getId());
		assertNotNull(skillTest);
		
	}

	@Test
	public void testUpdate() throws DAOException {

		skillDAO.update(skill, skillNew);
	}

	@Test
	public void testDelete() throws DAOException {
		
		skillDAO.delete(skillNew);
	}

	@Test
	public void testGetAll() throws DAOException {
		Collection<Skill> skills = skillDAO.getAll();
		assertNotNull(skills);
	}

	@Test
	public void testGetAllFromSearch() throws DAOException {
		String query = "SELECT * FROM Skills WHERE type = 'PROGRAMMING'";
		Collection<Skill> skills = skillDAO.getAllFromSearch(query);
		assertNotNull(skills);
	}

}
