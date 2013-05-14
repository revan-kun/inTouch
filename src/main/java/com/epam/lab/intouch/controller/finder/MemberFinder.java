package com.epam.lab.intouch.controller.finder;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.service.member.BaseMemberService;
import com.epam.lab.intouch.service.member.MemberService;

public class MemberFinder {
	private final static Logger LOG = LogManager.getLogger(MemberFinder.class);

	public List<Member> gerAllMembers() throws DataAccessingException {
		BaseMemberService service = new MemberService();
		List<Member> members = new ArrayList<Member>();

		try {
			members = service.getAll();
		} catch (DAOException e) {
			LOG.error("Data cannot be accessed!: ", e);
			throw new DataAccessingException();
		}
		return members;
	}

	public List<Member> findMembers(String query) throws DataAccessingException {
		BaseMemberService service = new MemberService();
		List<Member> members = new ArrayList<Member>();

		try {
			members = service.getAllFromSearch(query);
		} catch (DAOException e) {
			LOG.error("Data cannot be accessed!: ", e);
			throw new DataAccessingException();
		}
		return members;
	}

}
