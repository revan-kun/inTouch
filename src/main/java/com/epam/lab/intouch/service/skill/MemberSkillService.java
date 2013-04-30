package com.epam.lab.intouch.service.skill;

import static com.epam.lab.intouch.dao.util.FieldName.DESCRIPTION;
import static com.epam.lab.intouch.dao.util.FieldName.EXPERIENCE;
import static com.epam.lab.intouch.dao.util.FieldName.MEMBER_ID;
import static com.epam.lab.intouch.dao.util.FieldName.SELF_ASSESSED_LEVEL;
import static com.epam.lab.intouch.dao.util.FieldName.SKILL_ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.intouch.dao.exception.DAOCreateException;
import com.epam.lab.intouch.dao.exception.DAODeleteException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.dao.exception.DAOReadException;
import com.epam.lab.intouch.db.exception.DBConnectionException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;

public class MemberSkillService {


	public String create(Member member) throws DAOCreateException {



		return null;
	}

	
	public Member getById(String login) throws DAOReadException {
		

		
		return null;
	}


	public void update(Member oldMember, Member newMember) throws DAOException {
		
		throw new UnsupportedOperationException("You can't update member skill");

	}


	public void delete(Member member) throws DAODeleteException {



	}

	
	public List<Member> getAll() throws DAOReadException {


		return null;
	}

	



	public List<Member> getAllFromSearch(String query) throws DAOReadException {
		


		return null;
	}

	
	public String addSkill(Member member, Skill skill) throws DAOCreateException{


		return null;
	}

	
	public void removeSkill(Member member, Skill skill) throws DAODeleteException{

		
	}

}
