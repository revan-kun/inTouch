package com.epam.lab.intouch.service.member;

import java.util.Collection;

import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.member.MemberDAOImpl;
import com.epam.lab.intouch.model.member.Member;

public class MemberService {

	private MemberDAO memberDAO;

	public MemberService() {
		setMemberDAO(new MemberDAOImpl());
	}

	public void create(Member member) {

	}

	public Member getById(String id) {
		return null;
	}

	public void update(Member oldMember, Member newMember) {

	}

	public void delete(Member member) {

	}

	public Collection<Member> getAll() {
		return null;
	}

	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
}
