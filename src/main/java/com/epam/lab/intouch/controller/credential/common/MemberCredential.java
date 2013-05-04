package com.epam.lab.intouch.controller.credential.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.service.member.MemberService;

public class MemberCredential {
	private final static Logger LOG = LogManager.getLogger(MemberCredential.class);

	private MemberService memberService;

	public MemberCredential() {
		memberService = new MemberService();
	}

	public Boolean checkMemberIfExists(Member member) throws DataAccessingException {
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

	public Member authorizeMember(Member member) throws DataAccessingException   {
		Member authorizedMember = null;

		if (member != null && member.getLogin() != null) {

			try {
				authorizedMember = memberService.getById(member.getLogin());
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
