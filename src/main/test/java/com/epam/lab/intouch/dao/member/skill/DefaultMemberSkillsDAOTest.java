package com.epam.lab.intouch.dao.member.skill;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class DefaultMemberSkillsDAOTest {

	private static MemberSkillsDAO memberSkillsDAO = null;
	private static Member member = new Member();
	private static Skill skillNew = new Skill();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		memberSkillsDAO = new DefaultMemberSkillsDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		member.setLogin("dartik@gmail.com");
		List<Skill> skills = new ArrayList<Skill>();
		for (int i = 1; i < 4; i++) {
			Skill skill = new Skill();
			skill.setId(30L + i);
			skill.setExperience(i + 1.0);
			skill.setDescription("it is skill number " + i);
			skill.setLevel(i);
			skills.add(skill);
		}
		member.setSkills(skills);

		skillNew.setId(30L);

	}

	@Test
	public void testCreate() throws DAOException {
		String login = memberSkillsDAO.create(member);
		assertNotNull(login);
	}

	@Test
	public void testGetById() throws DAOException {
		Member memberTest = memberSkillsDAO.getById(member.getLogin());
		assertNotNull(memberTest);

	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUpdate() throws DAOException {
		memberSkillsDAO.update(member, member);
	}

	@Test
	public void testGetAll() throws DAOException {
		Collection<Member> members = memberSkillsDAO.getAll();
		assertNotNull(members);
	}

	@Test
	public void testGetAllFromSearch() throws DAOException {
		String query = "SELECT member_id FROM Member_Skills WHERE skill_id = 11";
		Collection<Member> members = memberSkillsDAO.getAllFromSearch(query);
		assertNotNull(members);

	}

	@Test
	public void testAddSkill() throws DAOException {
		String memberLogin = memberSkillsDAO.addSkill(member, skillNew);
		assertNotNull(memberLogin);
	}

	@Test
	public void testRemoveSkill() throws DAOException {
		memberSkillsDAO.removeSkill(member, skillNew);
	}

	@Test
	public void testDelete() throws DAOException {
		memberSkillsDAO.delete(member);
	}
}
