package com.epam.lab.intouch.dao.member.skill;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		memberSkillsDAO = new DefaultMemberSkillsDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		member.setLogin("dobkin@epam.com");
		List<Skill> skills = new ArrayList<Skill>();
		for(int i = 1; i < 10; i++ ){
			Skill skill = new Skill();
			skill.setId(6L);
			skill.setExperience(i+0.3);
			skill.setDescription("it is skill number " + i);
			skill.setLevel(i);
			skills.add(skill);
		}
		member.setSkills(skills);
		
	}

	@Test
	public void testCreate() throws DAOException {
		String login = memberSkillsDAO.create(member);
		assertNotNull(login);
	}

	@Test
	public void testGetById() {
	}

	@Test
	public void testUpdate() {
	}

	

	@Test
	public void testGetAll() {
	}

	@Test
	public void testGetAllFromSearch() {
	}

	@Test
	public void testAddSkill() {
	}

	@Test
	public void testRemoveSkill() {
	}

	@Test
	public void testDelete() {
	}
}
