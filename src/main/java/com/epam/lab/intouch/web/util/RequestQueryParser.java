package com.epam.lab.intouch.web.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.util.query.builder.QueryBuilder;
import com.epam.lab.intouch.controller.util.query.from.Table;
import com.epam.lab.intouch.controller.util.query.select.Wildcard;
import com.epam.lab.intouch.controller.util.query.where.Condition;
import com.epam.lab.intouch.controller.util.query.where.ConditionGroup;
import com.epam.lab.intouch.controller.util.query.where.Operator;
import com.epam.lab.intouch.util.db.metadata.FieldName;
import com.epam.lab.intouch.util.db.metadata.TableName;

public class RequestQueryParser {
	private final static Logger LOG = LogManager.getLogger(RequestQueryParser.class);

	private Boolean isValid(String parameter) {
		Boolean result = false;
		if (parameter != null && !parameter.isEmpty()) {
			result = true;
		}

		return result;
	}

	public String getQuery(HttpServletRequest request) {
		String gender = request.getParameter("sex");
		String experienceLowerBound = request.getParameter("expirienceLowerBound");
		String experienceUpperBound = request.getParameter("expirienceUpperBound");

		String[] qualifications = request.getParameterValues("qualification");
		String[] roles = request.getParameterValues("role");

		String skillName = request.getParameter("skillName");

		String skillExperienceLowerBound = request.getParameter("expirienceLowerBound");
		String skillExperienceUpperBound = request.getParameter("skillExperienceUpperBound");

		String skillLevelLowerBound = request.getParameter("lowerBoundLevel");
		String skillLevelUpperBound = request.getParameter("upperBoundLevel");

		LOG.debug(" \ngender: " + gender + " \nExpLowBound: " + experienceLowerBound + " \nExpUpBound: " + experienceUpperBound + " \nqualifications: "
				+ qualifications + " \nroles: " + roles + " \nskillName: " + skillName + " \nskillExperienceLowerBound: " + skillExperienceLowerBound
				+ " \nskillExperienceUpperBound: " + skillExperienceUpperBound + " \nskillLevelLowerBound: " + skillLevelLowerBound
				+ " \nskillLevelUpperBound: " + skillLevelUpperBound + "\n");

		Table member = new Table(TableName.MEMBER);
		Table memberSkills = new Table(TableName.MEMBER_SKILLS);
		Table skills = new Table(TableName.SKILLS);

		Condition memberSkillsMemberKey = new Condition(member.getColumn(FieldName.LOGIN), Operator.EQUALS, memberSkills.getColumn(FieldName.MEMBER_ID));
		Condition skillsMemberSkillsKey = new Condition(memberSkills.getColumn(FieldName.SKILL_ID), Operator.EQUALS, skills.getColumn(FieldName.ID));

		ConditionGroup conditionGroup = new ConditionGroup();
		conditionGroup.setOperator(Operator.AND);

		Condition sexCondition = new Condition(member.getColumn(FieldName.SEX), Operator.EQUALS, gender, true);

		ConditionGroup qualificationConditionGroup = new ConditionGroup();
		qualificationConditionGroup.setOperator(Operator.OR);

		ConditionGroup roleConditionGroup = new ConditionGroup();
		roleConditionGroup.setOperator(Operator.OR);

		if (roles != null) {
			for (int i = 0; i < roles.length; i++) {
				Condition cond = new Condition(member.getColumn(FieldName.ROLE), Operator.EQUALS, roles[i], true);
				roleConditionGroup.addCondition(cond);
			}
		}

		Condition lowerBoundExperienceCond = new Condition(member.getColumn(FieldName.EXPERIENCE), Operator.GREATER_OR_EQUAL, experienceLowerBound, true);
		Condition upperBoundExperienceCond = new Condition(member.getColumn(FieldName.EXPERIENCE), Operator.LESS_OR_EQUAL, experienceUpperBound, true);

		if (qualifications != null) {
			for (int i = 0; i < qualifications.length; i++) {
				Condition cond = new Condition(member.getColumn(FieldName.QLEVEL), Operator.EQUALS, qualifications[i], true);
				qualificationConditionGroup.addCondition(cond);
			}
		}

		QueryBuilder builder = new QueryBuilder();

		Condition skillNameCond = new Condition(skills.getColumn(FieldName.NAME), Operator.EQUALS, skillName, true);

		Condition lowerBoundSkillExperienceCond = new Condition(memberSkills.getColumn(FieldName.EXPERIENCE), Operator.GREATER_OR_EQUAL,
				skillExperienceLowerBound, true);
		Condition upperBoundSkillExperienceCond = new Condition(memberSkills.getColumn(FieldName.EXPERIENCE), Operator.LESS_OR_EQUAL,
				skillExperienceUpperBound, true);

		Condition skillLevelLowerBoundCond = new Condition(memberSkills.getColumn(FieldName.SELF_ASSESSED_LEVEL), Operator.GREATER_OR_EQUAL,
				skillLevelLowerBound, true);
		Condition skillLevelUpperBoundCond = new Condition(memberSkills.getColumn(FieldName.SELF_ASSESSED_LEVEL), Operator.LESS_OR_EQUAL, skillLevelUpperBound,
				true);

		conditionGroup.addCondition(sexCondition);
		conditionGroup.addCondition(qualificationConditionGroup);
		conditionGroup.addCondition(roleConditionGroup);
		conditionGroup.addCondition(lowerBoundExperienceCond);
		conditionGroup.addCondition(upperBoundExperienceCond);
		conditionGroup.addCondition(skillNameCond);
		conditionGroup.addCondition(lowerBoundSkillExperienceCond);
		conditionGroup.addCondition(upperBoundSkillExperienceCond);
		conditionGroup.addCondition(skillLevelLowerBoundCond);
		conditionGroup.addCondition(skillLevelUpperBoundCond);

		LOG.debug("\nSexCond: " + sexCondition);
		LOG.debug("\nSkillNameCond: " + skillNameCond);

		if (!isValid(skillLevelUpperBound) && !isValid(skillLevelLowerBound) && !isValid(skillExperienceUpperBound) && !isValid(skillExperienceLowerBound)
				&& !isValid(skillName)) {
			memberSkills = null;
			skills = null;
		}

		builder.select(new Wildcard()).from(member).inerJoin(memberSkills, memberSkillsMemberKey).inerJoin(skills, skillsMemberSkillsKey).where(conditionGroup);

		return builder.toString();

	}
}
