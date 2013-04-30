package com.epam.lab.intouch.service.skill;

import static com.epam.lab.intouch.dao.util.FieldName.ID;
import static com.epam.lab.intouch.dao.util.FieldName.NAME;
import static com.epam.lab.intouch.dao.util.FieldName.TYPE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.model.member.info.skill.SkillType;

public class SkillService {


	public Long create(Skill skill) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	public Skill getById(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	public void update(Skill oldSkill, Skill newSkill) throws DAOException {
		// TODO Auto-generated method stub

	}


	public void delete(Skill skill) throws DAOException {
		// TODO Auto-generated method stub

	}






	public List<Skill> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Skill> getAllFromSearch(String query) throws DAOException {

		return null;
	}


}
