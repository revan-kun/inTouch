package com.epam.lab.intouch.service.member.trash;

import java.util.HashMap;
import java.util.Map;

import com.epam.lab.intouch.model.member.SimpleMember;


/**
 * @author Revan
 *
 */
public class SimpleMemberService {

	private Map<String, SimpleMember> members;

	public SimpleMemberService() {
		this.members = new HashMap<String, SimpleMember>();
		this.members.put("member", new SimpleMember("member", "1111"));

	}

	public SimpleMember login(final String login, final String password) {
		return this.members.get(login);
	}

}
