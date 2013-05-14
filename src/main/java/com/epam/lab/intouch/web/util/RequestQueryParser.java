package com.epam.lab.intouch.web.util;

import javax.servlet.http.HttpServletRequest;

import com.epam.lab.intouch.controller.util.query.builder.QueryBuilder;
import com.epam.lab.intouch.controller.util.query.from.Table;
import com.epam.lab.intouch.controller.util.query.select.Wildcard;
import com.epam.lab.intouch.controller.util.query.where.Condition;
import com.epam.lab.intouch.controller.util.query.where.ConditionGroup;
import com.epam.lab.intouch.controller.util.query.where.Operator;
import com.epam.lab.intouch.util.db.metadata.FieldName;
import com.epam.lab.intouch.util.db.metadata.TableName;

public class RequestQueryParser {
	private Table member;
	private Table memberSkills;
	private Table skills;

	public RequestQueryParser() {
		member = new Table(TableName.MEMBER);
		memberSkills = new Table(TableName.MEMBER_SKILLS);
		skills = new Table(TableName.SKILLS);
	}

	private Condition getGenderCondition(HttpServletRequest request) {
		String gender = request.getParameter("sex");
		return new Condition(member.getColumn(FieldName.SEX), Operator.EQUALS, gender, true);
	}

	private ConditionGroup getGeneralExperienceCondGroup(HttpServletRequest request) {

		String lowerBound = request.getParameter("expirienceLowerBound");
		String upperBound = request.getParameter("expirienceUpperBound");

		Condition lowerBoundCond = new Condition(member.getColumn(FieldName.EXPERIENCE), Operator.GREATER_OR_EQUAL, lowerBound, true);
		Condition upperBoundCond = new Condition(member.getColumn(FieldName.EXPERIENCE), Operator.LESS_OR_EQUAL, upperBound, true);

		ConditionGroup condGroup = new ConditionGroup();
		condGroup.setOperator(Operator.AND);

		condGroup.addCondition(lowerBoundCond);
		condGroup.addCondition(upperBoundCond);

		return condGroup;
	}

	private ConditionGroup getQualificationCondGroup(HttpServletRequest request) {

		String[] qualifications = request.getParameterValues("qualification");

		ConditionGroup condGroup = new ConditionGroup();
		condGroup.setOperator(Operator.OR);

		if (qualifications != null) {
			for (int i = 0; i < qualifications.length; i++) {
				Condition cond = new Condition(member.getColumn(FieldName.QLEVEL), Operator.EQUALS, qualifications[i], true);
				condGroup.addCondition(cond);
			}
		}

		return condGroup;
	}

	private ConditionGroup getRoleCondGroup(HttpServletRequest request) {

		String[] roles = request.getParameterValues("role");

		ConditionGroup condGroup = new ConditionGroup();
		condGroup.setOperator(Operator.OR);

		if (roles != null) {
			for (int i = 0; i < roles.length; i++) {
				Condition cond = new Condition(member.getColumn(FieldName.ROLE), Operator.EQUALS, roles[i], true);
				condGroup.addCondition(cond);
			}
		}

		return condGroup;
	}

	private Condition getSkillNameCondition(HttpServletRequest request) {
		String skillName = request.getParameter("skillName");
		return new Condition(skills.getColumn(FieldName.NAME), Operator.EQUALS, skillName, true);
	}

	private ConditionGroup getSkillExperienceCondGroup(HttpServletRequest request) {

		String lowerBound = request.getParameter("expirienceLowerBound");
		String upperBound = request.getParameter("skillExperienceUpperBound");

		Condition lowerBoundCond = new Condition(memberSkills.getColumn(FieldName.EXPERIENCE), Operator.GREATER_OR_EQUAL, lowerBound, true);
		Condition upperBoundCond = new Condition(memberSkills.getColumn(FieldName.EXPERIENCE), Operator.LESS_OR_EQUAL, upperBound, true);

		ConditionGroup condGroup = new ConditionGroup();
		condGroup.setOperator(Operator.AND);

		condGroup.addCondition(lowerBoundCond);
		condGroup.addCondition(upperBoundCond);

		return condGroup;
	}

	private ConditionGroup getSkillLevelConditionGroup(HttpServletRequest request) {

		String lowerBound = request.getParameter("lowerBoundLevel");
		String upperBound = request.getParameter("upperBoundLevel");

		Condition lowerBoundCond = new Condition(memberSkills.getColumn(FieldName.SELF_ASSESSED_LEVEL), Operator.GREATER_OR_EQUAL, lowerBound, true);
		Condition upperBoundCond = new Condition(memberSkills.getColumn(FieldName.SELF_ASSESSED_LEVEL), Operator.LESS_OR_EQUAL, upperBound, true);

		ConditionGroup condGroup = new ConditionGroup();
		condGroup.setOperator(Operator.AND);

		condGroup.addCondition(lowerBoundCond);
		condGroup.addCondition(upperBoundCond);

		return condGroup;
	}

	private ConditionGroup getSkillCondGroup(HttpServletRequest request) {
		ConditionGroup skillConditionGroup = new ConditionGroup();
		skillConditionGroup.setOperator(Operator.AND);

		Condition skillNameCond = getSkillNameCondition(request);
		ConditionGroup skillLevelCondGroup = getSkillLevelConditionGroup(request);
		ConditionGroup skillExperienceCondGroup = getSkillExperienceCondGroup(request);

		skillConditionGroup.addCondition(skillNameCond);
		skillConditionGroup.addCondition(skillLevelCondGroup);
		skillConditionGroup.addCondition(skillExperienceCondGroup);

		return skillConditionGroup;
	}

	public String getQuery(HttpServletRequest request) {

		Condition memberSkillsMemberKey = new Condition(member.getColumn(FieldName.LOGIN), Operator.EQUALS, memberSkills.getColumn(FieldName.MEMBER_ID));
		Condition skillsMemberSkillsKey = new Condition(memberSkills.getColumn(FieldName.SKILL_ID), Operator.EQUALS, skills.getColumn(FieldName.ID));

		ConditionGroup conditionGroup = new ConditionGroup();
		conditionGroup.setOperator(Operator.AND);

		conditionGroup.addCondition(getGenderCondition(request));
		conditionGroup.addCondition(getGeneralExperienceCondGroup(request));
		conditionGroup.addCondition(getQualificationCondGroup(request));
		conditionGroup.addCondition(getRoleCondGroup(request));
		conditionGroup.addCondition(getSkillCondGroup(request));

		if (!getSkillCondGroup(request).isValid()) {
			skills = null;
			memberSkills = null;
		}

		QueryBuilder builder = new QueryBuilder();
		builder.select(new Wildcard()).from(member).inerJoin(memberSkills, memberSkillsMemberKey).inerJoin(skills, skillsMemberSkillsKey).where(conditionGroup);

		return builder.toString();

	}
}
