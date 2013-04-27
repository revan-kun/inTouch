package com.epam.lab.intouch.dao.skill.language;

import com.epam.lab.intouch.dao.BaseDAO;
import com.epam.lab.intouch.model.member.Member;

public interface LanguageSkillDAO extends BaseDAO<Member, String>{
	
	String addLanguageSkill(Member member);
	
	void removeLanguageSkill(Member member);

}
