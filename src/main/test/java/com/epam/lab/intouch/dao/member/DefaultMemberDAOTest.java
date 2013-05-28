package com.epam.lab.intouch.dao.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;

public class DefaultMemberDAOTest {

	private static MemberDAO memberDAO = new DefaultMemberDAO();;
	private static Member member = new Member();
	private static Member memberNew = new Member();
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void setUpAfterClass() throws Exception {
		memberDAO.delete(member);
		memberDAO.delete(memberNew);
	}

	@Before
	public void setUp() throws Exception {

		member.setLogin("test@gmail.com");
		member.setPassword("test");
		member.setFirstName("Name");
		member.setLastName("Douw");
		member.setBirthday(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		member.setSex(Sex.MALE);
		member.setQualificationLevel(QualificationLevel.JUNIOR);
		member.setExperience(5D);
		member.setPhotoLink("test\test.jpg");
		member.setAdditionalInfo("I am the test unit");
		member.setRole(Role.TESTER);

		memberNew.setLogin("testik@gmail.com");
		memberNew.setPassword("newpassword");
		memberNew.setFirstName("Name");
		memberNew.setLastName("Douw");
		memberNew.setBirthday(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		memberNew.setSex(Sex.MALE);
		memberNew.setQualificationLevel(QualificationLevel.JUNIOR);
		memberNew.setExperience(5D);
		memberNew.setPhotoLink("test\test.jpg");
		memberNew.setAdditionalInfo("I am the test unit");
		memberNew.setRole(Role.DEVELOPER);
		memberNew.setRating(12);

	}

	@After
	public void tearDown() throws Exception {

		memberDAO.delete(memberNew);

	}

	@Test
	public void testCreate() throws DAOException {

		String login = memberDAO.create(member);
		assertNotNull("Login not null", login);

	}

	@Test
	public void testGetByID() throws DAOException {

		String login = memberDAO.create(memberNew);
		Member memberTest = memberDAO.getById(login);
		assertNotNull("Member not null", memberTest);
		assertEquals(memberTest.getLastName(), memberNew.getLastName());
		assertEquals(memberTest.getExperience(), memberNew.getExperience());
		assertEquals(memberTest.getLogin(), memberNew.getLogin());

	}

	@Test
	public void testUpdate() throws DAOException {

		memberDAO.update(member, memberNew);
		Member testMember = memberDAO.getById(memberNew.getLogin());
		assertEquals(testMember.getLogin(), memberNew.getLogin());
		assertNotSame(testMember, member);

	}

	@Test
	public void testGetAll() throws DAOException {
		List<Member> membersTest = (List<Member>) memberDAO.getAll();
		assertNotNull("members must be not null", membersTest);

	}

	@Test
	public void testGetAllFronSearch() throws DAOException {
		String queryReadAll = "SELECT * FROM Member INNER JOIN Member_Skills ON Member.login=Member_Skills.member_id WHERE sex = 'MALE'";
		Collection<Member> members = memberDAO.getAllFromSearch(queryReadAll);
		assertNotNull(members);

	}

	@Test
	public void testUpdateRating() throws DAOException {
		member.setRating(12);
		memberDAO.updateRating(member);
	}

	@Test
	public void testDelete() throws DAOException {

		memberDAO.delete(memberNew);
		assertNull(memberDAO.getById(memberNew.getLogin()));

	}

}
