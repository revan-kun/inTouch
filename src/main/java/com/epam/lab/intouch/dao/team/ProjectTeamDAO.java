package com.epam.lab.intouch.dao.team;

import java.util.List;

import com.epam.lab.intouch.dao.BaseDAO;
import com.epam.lab.intouch.model.project.Project;

public interface ProjectTeamDAO extends BaseDAO<Project, Long> {

	List<String> getMemberID(Long idProject);

}
