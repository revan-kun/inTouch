package com.epam.lab.intouch.service.member;

import java.util.Collection;

import com.epam.lab.intouch.dao.member.MemberDAO;
import com.epam.lab.intouch.dao.member.MemberDAOImpl;
import com.epam.lab.intouch.model.member.Member;

public class MemberProvider {
	
	private MemberDAO memberDAO;
	
	public MemberProvider(){
		setMemberDAO(new MemberDAOImpl());
	}

	
	public void create(Member member){
		// TODO Auto-generated method stub

	}

	
	public Member getById(String id){
		// TODO Auto-generated method stub
		return null;
	}

	
	public void update(Member oldEntity, Member newEntity){
		// TODO Auto-generated method stub

	}

	
	public void delete(Member member){
		// TODO Auto-generated method stub

	}

	
	public Collection<Member> getAll(){
		// TODO Auto-generated method stub
		return null;
	}


	public MemberDAO getMemberDAO() {
		return memberDAO;
	}


	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

}
