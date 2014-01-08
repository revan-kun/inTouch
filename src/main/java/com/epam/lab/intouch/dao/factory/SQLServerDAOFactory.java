package com.epam.lab.intouch.dao.factory;

import com.epam.lab.intouch.dao.history.project.DefaultHistoryDAO;
import com.epam.lab.intouch.dao.history.project.HistoryDAO;
import com.epam.lab.intouch.dao.member.DefaultMemberDAO;
import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.member.like.DefaultLikeDAO;
import com.epam.lab.intouch.dao.member.like.LikeDAO;
import com.epam.lab.intouch.dao.member.skill.DefaultMemberSkillsDAO;
import com.epam.lab.intouch.dao.member.skill.DefaultSkillDAO;
import com.epam.lab.intouch.dao.member.skill.MemberSkillsDAO;
import com.epam.lab.intouch.dao.member.skill.SkillDAO;
import com.epam.lab.intouch.dao.project.DefaultProjectDAO;
import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.team.DefaultTeamDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;

public class SQLServerDAOFactory extends AbstractDAOFactory{

	@Override
	public MemberDAO getMemberDAO() {
		return new DefaultMemberDAO();
	}

	@Override
	public HistoryDAO getHistoryDAO() {
		return new DefaultHistoryDAO();
	}

	@Override
	public LikeDAO getLikeDAO() {
		return new DefaultLikeDAO();
	}

	@Override
	public MemberSkillsDAO getMemberSkillsDAO() {
		return new DefaultMemberSkillsDAO();
	}

	@Override
	public ProjectDAO getProjectDAO() {
		return new DefaultProjectDAO();
	}

	@Override
	public TeamDAO getTeamDAO() {
		return new DefaultTeamDAO();
	}

	@Override
	public SkillDAO getSkillDAO() {
		return new DefaultSkillDAO();
	}

}
