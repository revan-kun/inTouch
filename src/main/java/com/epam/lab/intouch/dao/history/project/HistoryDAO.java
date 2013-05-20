package com.epam.lab.intouch.dao.history.project;

import java.sql.Date;
import java.util.List;

import com.epam.lab.intouch.dao.BaseDAO;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public interface HistoryDAO extends BaseDAO<Member, String> {

	Long addProject(Member member, Project project, Date date) throws DAOException;

	List<Member> getProjectHistory(Project project) throws DAOException;

}
