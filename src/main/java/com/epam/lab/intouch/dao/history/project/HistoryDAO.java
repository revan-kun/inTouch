package com.epam.lab.intouch.dao.history.project;

import com.epam.lab.intouch.dao.BaseDAO;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.project.Project;

public interface HistoryDAO extends BaseDAO<Member, String> {

	Long addProject(Member member, Project project) throws DAOException;

}
