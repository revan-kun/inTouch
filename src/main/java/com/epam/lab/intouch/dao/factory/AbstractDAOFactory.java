package com.epam.lab.intouch.dao.factory;

import com.epam.lab.intouch.dao.history.project.HistoryDAO;
import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.member.like.LikeDAO;
import com.epam.lab.intouch.dao.member.skill.MemberSkillsDAO;
import com.epam.lab.intouch.dao.member.skill.SkillDAO;
import com.epam.lab.intouch.dao.project.ProjectDAO;
import com.epam.lab.intouch.dao.team.TeamDAO;

public abstract class AbstractDAOFactory {
	public static final String MS_SERVER = "msSQL";

	// public static final String XML_DATA_SOURCE="xml";

	public static AbstractDAOFactory getInstance(String type) {
		AbstractDAOFactory factory = null;

		if (type == MS_SERVER) {
			factory = new SQLServerDAOFactory();
		}

		return factory;
	}

	public abstract MemberDAO getMemberDAO();

	public abstract HistoryDAO getHistoryDAO();

	public abstract LikeDAO getLikeDAO();

	public abstract MemberSkillsDAO getMemberSkillsDAO();

	public abstract ProjectDAO getProjectDAO();

	public abstract TeamDAO getTeamDAO();

	public abstract SkillDAO getSkillDAO();
}
