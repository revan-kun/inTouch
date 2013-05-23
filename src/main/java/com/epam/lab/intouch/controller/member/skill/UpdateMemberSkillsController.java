package com.epam.lab.intouch.controller.member.skill;

import java.nio.channels.SeekableByteChannel;
import java.util.List;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.util.cache.SkillCache;
import com.epam.lab.intouch.dao.exception.DAOException;
import com.epam.lab.intouch.model.member.Member;
import com.epam.lab.intouch.model.member.info.skill.Skill;
import com.epam.lab.intouch.service.skill.BaseSkillService;
import com.epam.lab.intouch.service.skill.SkillService;

public class UpdateMemberSkillsController {

	private final BaseSkillService skillService;

	public UpdateMemberSkillsController() {
		skillService = new SkillService();
	}

	public void updateMemberSkills(List<Skill> parsedSkills, Member sessionMember) {

	    List<Skill> skills = sessionMember.getSkills();
		sessionMember.getSkills().removeAll(sessionMember.getSkills());

		try {
			SkillCache scillCache = SkillCache.getInstance();
			for (int i = 0; i < parsedSkills.size(); i++) {
				for (Skill skill : scillCache.getSkills()) {
					if (skill.getName().equalsIgnoreCase(parsedSkills.get(i).getName())) {
						sessionMember.getSkills().add(skill);
					}
				}
			}
			skillService.updateMemberSkills(sessionMember);
		} catch (DataAccessingException | DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
