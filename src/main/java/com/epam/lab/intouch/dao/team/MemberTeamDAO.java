package com.epam.lab.intouch.dao.team;

import java.util.List;

import com.epam.lab.intouch.dao.BaseDAO;
import com.epam.lab.intouch.model.member.Member;

public interface MemberTeamDAO extends BaseDAO<Member, String> {
	
	List<Long> getProjectID(String login);

}
