package com.epam.lab.intouch.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.epam.lab.intouch.dao.history.project.DefaultHistoryDAOTest;
import com.epam.lab.intouch.dao.member.DefaultMemberDAOTest;
import com.epam.lab.intouch.dao.member.skill.DefaultMemberSkillsDAOTest;
import com.epam.lab.intouch.dao.member.skill.DefaultSkillDAOTest;
import com.epam.lab.intouch.dao.project.DefaultProjectDAOTest;
import com.epam.lab.intouch.dao.team.DefaultTeamDAOTest;

@RunWith(Suite.class)
@SuiteClasses({ DefaultMemberDAOTest.class, DefaultProjectDAOTest.class, DefaultSkillDAOTest.class, DefaultMemberSkillsDAOTest.class,
		DefaultHistoryDAOTest.class, DefaultTeamDAOTest.class })
public class AllDAOTests {

}
