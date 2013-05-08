package com.epam.lab.intouch.dao.member.skill;

import static org.junit.Assert.assertEquals;
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

		skillNew.setName("RUSSIAN");
		skillNew.setSkillType(SkillType.LANGUAGE);

	}

	@Test
	public void testCreate() throws DAOException {

		Long id = skillDAO.create(skill);
		assertNotNull("After create return id", id);
	}

	@Test
	public void testGetById() throws DAOException {
		skill.setId(8L);
		Skill skillTest = skillDAO.getById(skill.getId());
		assertNotNull(skillTest);
		assertEquals(skillTest.getSkillType(), skill.getSkillType());
	}

	@Test
	public void testUpdate() throws DAOException {
		skill.setId(9L);
		skillNew.setId(7L);
		skillDAO.update(skill, skillNew);
		// add get by id and checked
	}

	@Test
	public void testDelete() throws DAOException {
		skillNew.setId(11L);
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
