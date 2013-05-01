package com.epam.lab.intouch.dao.member;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;

public class DefaultMemberDAOTest {
	
	private static MemberDAO memberDAO = null;
	private static Member member = new Member();
	private static Member memberNew = new Member();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		memberDAO = new DefaultMemberDAO();
		
		member.setLogin("test@gmail.com");
		member.setPassword("test");
		member.setFirstName("Name");
		member.setLastName("Douw");
		member.setBirthday(new Date(2001-02-02));
		member.setRegistrationDate(new Date(2013-01-01));
		member.setSex(Sex.MALE);
		member.setQualificationLevel(QualificationLevel.JUNIOR);
		member.setExperience(5D);
		member.setPhotoURI("test\test.jpg");
		member.setAdditionalInfo("I am the test unit");
		member.setRole(Role.DEVELOPER);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		memberNew.setLogin("test@gmail.com");
		memberNew.setPassword("newpassword");
		memberNew.setFirstName("Name");
		memberNew.setLastName("Douw");
		memberNew.setBirthday(new Date(2001-02-02));
		memberNew.setRegistrationDate(new Date(2013-01-01));
		memberNew.setSex(Sex.MALE);
		memberNew.setQualificationLevel(QualificationLevel.JUNIOR);
		memberNew.setExperience(5D);
		memberNew.setPhotoURI("test\test.jpg");
		memberNew.setAdditionalInfo("I am the test unit");
		memberNew.setRole(Role.DEVELOPER);

	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testCreate() throws DAOException {
		
		String login = memberDAO.create(member);
		assertNotNull("Login not null", login);

	}
	
	@Test
	public void testGetByID() throws DAOException{
		Member memberTest = memberDAO.getById(member.getLogin());
		assertNotNull("Member not null", memberTest);
		assertEquals(memberTest.getLastName(), member.getLastName());
		assertEquals(memberTest.getBirthday(), member.getBirthday());
	}
	
	@Test
	public void testGetAll() throws DAOException{
		List<Member> membersTest = (List<Member>) memberDAO.getAll();
		assertNotNull("members must be not null", membersTest);
		
	}
	
	@Test
	public void testUpdate() throws DAOException{
		memberDAO.update(member, memberNew);
		
		assertNotSame("New member", member, memberNew);
	}
	
	@Test 
	public void testDelete() throws DAOException{
		memberDAO.delete(memberNew);
		
	}

}
