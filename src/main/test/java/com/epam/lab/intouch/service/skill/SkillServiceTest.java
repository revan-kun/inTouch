package com.epam.lab.intouch.service.skill;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.enums.QualificationLevel;
import com.epam.lab.intouch.model.member.enums.Role;
import com.epam.lab.intouch.model.member.enums.Sex;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;
import com.epam.lab.intouch.service.member.MemberService;

public class SkillServiceTest {

	private static Member member = new Member();
	private static List<Skill> skills = new LinkedList<Skill>();
	private SkillService skillService = new SkillService();
	private MemberService memberService = new MemberService();
	private static Skill skill = new Skill();

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setMember();
		setMemberSkill();
		setSkill();

	}

	private static void setMember() throws ParseException {
		member.setLogin("TESTSKILL@gmail.com");
		member.setPassword("test");
		member.setFirstName("Name");
		member.setLastName("Douw");
		member.setBirthday(new SimpleDateFormat(DATE_FORMAT).parse("2001-02-02"));
		member.setRegistrationDate(new SimpleDateFormat(DATE_FORMAT).parse("2013-01-01"));
		member.setSex(Sex.MALE);
		member.setQualificationLevel(QualificationLevel.JUNIOR);
		member.setExperience(5D);
		member.setPhotoLink("test\test.jpg");
		member.setAdditionalInfo("I am the test unit");
		member.setRating(5);
		member.setRole(Role.MANAGER);
	}

	private static void setMemberSkill() {
		Skill skill = new Skill();
		skill.setName("MemberSkill1");
		skill.setLevel(9);
		skill.setExperience(1.5);
		skill.setSkillType(SkillType.LANGUAGE);
		skill.setDescription("MemberSkill1");
		skills.add(skill);

		skill = new Skill();
		skill.setName("MemberSkill2");
		skill.setLevel(8);
		skill.setExperience(2.0);
		skill.setSkillType(SkillType.PROGRAMMING);
		skill.setDescription("MemberSkill2");
		skills.add(skill);
	}

	private static void setSkill() {

		skill.setName("DEUTCH");
		skill.setSkillType(SkillType.LANGUAGE);
	}

	private void createAndSetMemberWithSkills() throws DAOException {
		memberService.create(member);
		for (Skill skill : skills) {

			Long skillId = skillService.create(skill);
			skill.setId(skillId);

			skillService.addSkill(member, skill);
		}

	}

	private void deleteMemberWithSkills() throws DAOException {
		memberService.delete(member);

		for (Skill skill : skills) {
			skillService.delete(skill);
			// skillService.removeSkill(member, skill);
		}

	}

	@Test
	public void testCreate() throws DAOException {
		Long idSkill = skillService.create(skill);
		Skill skill = skillService.getById(idSkill);
		skillService.delete(skill);
		assertNotNull(skill);

	}

	@Test
	public void testGetById() throws DAOException {
		createAndSetMemberWithSkills();
		Member memberWithSkill = skillService.getById(member.getLogin());
		deleteMemberWithSkills();
		assertNotNull(memberWithSkill);
		assertTrue("Check users skills", (memberWithSkill.getSkills() != null) && (memberWithSkill.getSkills().size() > 0));

	}

	//
	// @Test
	// public void testUpdate() {
	// fail("Not yet implemented");
	// }

	@Test
	public void testDeleteMember() throws DAOException {
		createAndSetMemberWithSkills();
		deleteMemberWithSkills();
		Member memberWithSkill = skillService.getById(member.getLogin());

		assertEquals(memberWithSkill.getFirstName(), null);

	}

	@Test
	public void testDeleteSkill() throws DAOException {
		Long idSkill = skillService.create(skill);
		skillService.delete(skill);
		Skill skill = skillService.getById(idSkill);
		assertEquals(skill, null);

	}

	@Test
	public void testGetAll() throws DAOException {
		boolean skillExist = false;
		List<Member> memberWithSkill = skillService.getAll();
		assertNotNull(memberWithSkill);
		assertTrue(memberWithSkill.size() > 0);
		for (Member member : memberWithSkill) {
			if ((member.getSkills() != null) && (member.getSkills().size() > 0)) {
				skillExist = true;
			}
			assertTrue(skillExist);
		}
	}

	@Test
	public void testGetAllSkills() throws DAOException {
		List<Skill> skills = skillService.getAllSkills();
		assertTrue((skills != null) && (skills.size()) > 0);
	}

	@Test
	public void testGetAllSkillsType() throws DAOException {
		List<SkillType> skillsType = skillService.getAllSkillsType();
		assertTrue((skillsType != null) && (skillsType.size()) > 0);

	}

	@Test
	public void testGetAllFromSearch() throws DAOException {
		String queryReadAll = "SELECT * FROM Member INNER JOIN Member_Skills ON Member.login=Member_Skills.member_id WHERE sex = 'MALE'";
		Collection<Member> members = skillService.getAllFromSearch(queryReadAll);
		assertNotNull(members);
	}

	@Test
	public void testAddSkill() throws DAOException {
		createAndSetMemberWithSkills();
		Member memberTest = skillService.getById(member.getLogin());
		deleteMemberWithSkills();
		assertNotNull(memberTest);
		assertTrue("Check users skills", (memberTest.getSkills() != null) && (memberTest.getSkills().size() > 0));

	}

	@Test
	public void testRemoveSkill() throws DAOException {
		createAndSetMemberWithSkills();
		skillService.removeSkill(member, skill);
		skillService.delete(skill);
		Skill skillTest = skillService.getById(skill.getId());

		assertNull(skillTest);

	}

}
