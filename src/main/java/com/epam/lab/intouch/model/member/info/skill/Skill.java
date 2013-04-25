package com.epam.lab.intouch.model.member.info.skill;

public class Skill {
	
	private Long id;
	private String name;
	private SkillLevel level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SkillLevel getLevel() {
		return level;
	}

	public void setLevel(SkillLevel level) {
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
