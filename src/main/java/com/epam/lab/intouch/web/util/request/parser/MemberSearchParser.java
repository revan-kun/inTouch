package com.epam.lab.intouch.web.util.request.parser;

import javax.servlet.http.HttpServletRequest;

import com.epam.lab.intouch.controller.util.query.builder.QueryBuilder;
import com.epam.lab.intouch.controller.util.query.from.Table;
import com.epam.lab.intouch.controller.util.query.select.Column;
import com.epam.lab.intouch.controller.util.query.util.pattern.PatternUtils;
import com.epam.lab.intouch.controller.util.query.where.Condition;
import com.epam.lab.intouch.controller.util.query.where.ConditionGroup;
import com.epam.lab.intouch.controller.util.query.where.Operator;
import com.epam.lab.intouch.util.db.metadata.FieldName;
import com.epam.lab.intouch.util.db.metadata.TableName;

public class MemberSearchParser {
	private static final String QUALIFICATION = "qualification";
	private static final String GENDER = "sex";
	private static final String ALL_PARAMETERS = "all";
	private static final String EXPERIENCE_LOWER_BOUND = "expirienceLowerBound";
	private static final String EXPERIENCE_UPPER_BOUND = "expirienceUpperBound";
	private static final String ROLE = "role";

	private static final String MANAGER = "manager";
	private static final String SKILL_NAME = "skillName";
	private static final String QUERY = "query";

	private Table member;
	private Table memberSkills;
	private Table skills;

	public MemberSearchParser() {
		member = new Table(TableName.MEMBER);
		memberSkills = new Table(TableName.MEMBER_SKILLS);
		skills = new Table(TableName.SKILLS);
	}

	private ConditionGroup getBoundedConditionGroup(Column field, String lowerBound, String upperBound) {
		Condition lowerBoundCond = new Condition(field, Operator.GREATER_OR_EQUAL, lowerBound, true);
		Condition upperBoundCond = new Condition(field, Operator.LESS_OR_EQUAL, upperBound, true);

		ConditionGroup condGroup = new ConditionGroup();
		condGroup.setOperator(Operator.AND);

		condGroup.addCondition(lowerBoundCond);
		condGroup.addCondition(upperBoundCond);

		return condGroup;
	}

	private ConditionGroup getMultipleConditionGroup(String[] parameters, Column field) {
		ConditionGroup condGroup = new ConditionGroup();
		condGroup.setOperator(Operator.OR);

		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				Condition cond = new Condition(field, Operator.EQUALS, parameters[i], true);
				condGroup.addCondition(cond);
			}
		}

		return condGroup;

	}

	private Condition getGenderCondition(HttpServletRequest request) {
		String gender = request.getParameter(GENDER);
		if (ALL_PARAMETERS.equals(gender)) {
			gender = null;
		}

		return new Condition(member.getColumn(FieldName.SEX), Operator.EQUALS, gender, true);
	}

	private ConditionGroup getGeneralExperienceCondGroup(HttpServletRequest request) {
		String lowerBound = request.getParameter(EXPERIENCE_LOWER_BOUND);
		String upperBound = request.getParameter(EXPERIENCE_UPPER_BOUND);

		return getBoundedConditionGroup(member.getColumn(FieldName.EXPERIENCE), lowerBound, upperBound);
	}

	private ConditionGroup getQualificationCondGroup(HttpServletRequest request) {
		String[] qualifications = request.getParameterValues(QUALIFICATION);

		return getMultipleConditionGroup(qualifications, member.getColumn(FieldName.QLEVEL));
	}

	private ConditionGroup getRoleCondGroup(HttpServletRequest request) {
		String[] roles = request.getParameterValues(ROLE);

		return getMultipleConditionGroup(roles, member.getColumn(FieldName.ROLE));
	}

	private Condition getAddMemberSearchRoleCond(HttpServletRequest request) {
		String role = request.getParameter(ROLE);
		Condition cond = null;

		if (MANAGER.equals(role)) {
			cond = new Condition(member.getColumn(FieldName.ROLE), Operator.NOT_EQUAL, role, true);
		} else {
			cond = new Condition(member.getColumn(FieldName.ROLE), Operator.EQUALS, role, true);
		}
		return cond;
	}

	private ConditionGroup getSkillNameCondGroup(HttpServletRequest request) {
		String[] skillNames = request.getParameterValues(SKILL_NAME);

		return getMultipleConditionGroup(skillNames, skills.getColumn(FieldName.NAME));
	}

	private ConditionGroup getMemberInfoCondGroup(HttpServletRequest request) {
		ConditionGroup conditionGroup = new ConditionGroup();
		conditionGroup.setOperator(Operator.OR);

		String name = request.getParameter(QUERY);

		for (String word : PatternUtils.splitPunctuationMatch(name)) {
			Condition nameCondition = new Condition(member.getColumn(FieldName.NAME), Operator.LIKE, word, true);
			Condition surnameCondition = new Condition(member.getColumn(FieldName.SURNAME), Operator.LIKE, word, true);

			conditionGroup.addCondition(nameCondition);
			conditionGroup.addCondition(surnameCondition);
		}
		return conditionGroup;
	}

	private ConditionGroup getResultCondGroup(HttpServletRequest request) {

		ConditionGroup conditionGroup = new ConditionGroup();
		conditionGroup.setOperator(Operator.AND);

		conditionGroup.addCondition(getGenderCondition(request));
		conditionGroup.addCondition(getGeneralExperienceCondGroup(request));
		conditionGroup.addCondition(getQualificationCondGroup(request));
		conditionGroup.addCondition(getSkillNameCondGroup(request));

		return conditionGroup;
	}

	private QueryBuilder getQueryBuilderBody(HttpServletRequest request) {
		Condition memberSkillsMemberKey = new Condition(member.getColumn(FieldName.LOGIN), Operator.EQUALS, memberSkills.getColumn(FieldName.MEMBER_ID));
		Condition skillsMemberSkillsKey = new Condition(memberSkills.getColumn(FieldName.SKILL_ID), Operator.EQUALS, skills.getColumn(FieldName.ID));

		if (!getSkillNameCondGroup(request).isValid()) {
			skills = null;
			memberSkills = null;
		}

		QueryBuilder builder = new QueryBuilder();
		builder.setDistinct(true);

		builder.select(member.getWildcard()).from(member).inerJoin(memberSkills, memberSkillsMemberKey).inerJoin(skills, skillsMemberSkillsKey);

		return builder;
	}

	public String getAddNewMemberQuery(HttpServletRequest request) {
		ConditionGroup conditionGroup = getResultCondGroup(request);
		conditionGroup.addCondition(getAddMemberSearchRoleCond(request));

		QueryBuilder builder = getQueryBuilderBody(request);
		builder.where(conditionGroup);

		return builder.toString();
	}

	public String getQuery(HttpServletRequest request) {
		ConditionGroup conditionGroup = getResultCondGroup(request);
		QueryBuilder builder = getQueryBuilderBody(request);

		conditionGroup.addCondition(getRoleCondGroup(request));

		builder.where(conditionGroup);

		return builder.toString();

	}

	public String getMemberInfoQuery(HttpServletRequest request) {
		ConditionGroup conditionGroup = getMemberInfoCondGroup(request);
		QueryBuilder builder = getQueryBuilderBody(request);

		builder.where(conditionGroup);

		return builder.toString();

	}

}
