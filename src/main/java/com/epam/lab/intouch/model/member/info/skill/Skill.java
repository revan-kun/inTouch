package com.epam.lab.intouch.model.member.info.skill;

public class Skill {

	private Long id;
	private String name;
	private SkillType skillType;
	private Integer level;
	private Double experience;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getExperience() {
		return experience;
	}

	public void setExperience(Double experience) {
		this.experience = experience;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public SkillType getSkillType() {
		return skillType;
	}

	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Skill [id=");
		builder.append(id);
		builder.append(", \n name=");
		builder.append(name);
		builder.append(", \n skillType=");
		builder.append(skillType);
		builder.append(", \n level=");
		builder.append(level);
		builder.append(", \n experience=");
		builder.append(experience);
		builder.append(", \n description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
