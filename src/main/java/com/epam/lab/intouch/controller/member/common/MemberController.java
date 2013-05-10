package com.epam.lab.intouch.controller.member.common;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.service.member.MemberService;

public class MemberController {
	private final static Logger LOG = LogManager.getLogger(MemberController.class);

	private MemberService memberService;

	public MemberController() {
		memberService = new MemberService();
	}

	private Boolean checkMemberIfExists(Member member) throws DataAccessingException {
		Boolean exists = false;

		try {
			Member memberInDB = memberService.getById(member.getLogin());

			if (memberInDB != null) {
				exists = true;
			}

		} catch (DAOException e) {
			LOG.error("Cannot access data: ", e);
			throw new DataAccessingException(e);
		}

		return exists;
	}

	public Member authorizeMember(String login, String password) throws DataAccessingException {

		Member member = new Member();
		member.setLogin(login);
		member.setPassword(password);

		return authorizeMember(member);
	}

	public Member authorizeMember(Member member) throws DataAccessingException {
		Member authorizedMember = null;

		if (member != null && member.getLogin() != null && member.getPassword() != null) {

			try {
				Member memberInDB = memberService.getById(member.getLogin());

				if (memberInDB != null) {

					if (member.getPassword().equals(memberInDB.getPassword())) {
						authorizedMember = memberInDB;
					}
				}

			} catch (DAOException e) {
				LOG.error("Cannot access data: ", e);
				throw new DataAccessingException(e);
			}

		}

		return authorizedMember;
	}

	public void updateProfile(Member oldMember, Member newMember) throws DataAccessingException {
		Boolean memberExists = checkMemberIfExists(oldMember);

		if (memberExists) {
			try {
				memberService.update(oldMember, newMember);
			} catch (DAOException e) {
				LOG.error("Cannot access data for updating member profile: ", e);
				throw new DataAccessingException(e);
			}
		}
	}

	public Boolean registerNewMember(Member member) throws DataAccessingException {
		Boolean result = false;
		Boolean memberExists = checkMemberIfExists(member);

		if (!memberExists) {
			try {
				member.setRegistrationDate(new Date());
				memberService.create(member);
				result = true;
			} catch (DAOException e) {
				LOG.error("Cannot access data holder for new user registration: ", e);
				throw new DataAccessingException(e);
			}
		}

		return result;
	}

}
