package com.epam.lab.intouch.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.epam.lab.intouch.service.history.HistoryServiceTest;
import com.epam.lab.intouch.service.member.MemberServiceTest;
import com.epam.lab.intouch.service.project.ProjectServiceTest;
import com.epam.lab.intouch.service.skill.SkillServiceTest;
import com.epam.lab.intouch.service.team.TeamServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ HistoryServiceTest.class, MemberServiceTest.class, ProjectServiceTest.class, SkillServiceTest.class, TeamServiceTest.class })
public class AllTests {

}
