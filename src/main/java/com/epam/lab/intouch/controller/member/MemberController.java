package com.epam.lab.intouch.controller.member;

import java.util.Collection;

import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.service.member.MemberService;

public class MemberController {
	private MemberService service;

	public MemberController() {
		service = new MemberService();
	}

	public MemberService getService() {
		return service;
	}

	public void setService(MemberService service) {
		this.service = service;
	}

	public void create(Member member) throws DAOException {
		service.create(member);
	}

	public Member getById(String id) throws DAOException {
		return service.getById(id);
	}

	public void update(Member oldMember, Member newMember) throws DAOException {
		service.update(oldMember, newMember);
	}

	public void delete(Member member) throws DAOException {
		service.delete(member);
	}

	public Collection<Member> getAll() throws DAOException {
		return service.getAll();
	}
}
