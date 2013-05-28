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
import static com.epam.lab.intouch.web.util.RequestParser.changeEncoding;

public class ProjectSearchParser {
	private static final String CUSTOMER = "customer";
	private static final String LOWER_BOUND_CREATION_DATE = "createdLowerBound";
	private static final String UPPER_BOUND_CREATION_DATE = "createdUpperBound";

	private static final String LOWER_BOUND_ESTIMATED_DATE = "estimatedLowerBound";
	private static final String UPPER_BOUND_ESTIMATED_DATE = "estimatedUpperBound";

	private static final String LOWER_BOUND_COMPLETION_DATE = "completedLowerBound";
	private static final String UPPER_BOUND_COMPLETION_DATE = "completedUpperBound";

	private static final String STATUS = "status";
	private static final String ALL_PARAMETERS = "all";

	private static final String PROJECT_OPEN = "open";
	private static final String PROJECT_CLOSED = "closed";

	private Table project;

	public ProjectSearchParser() {
		project = new Table(TableName.PROJECT);
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

	private ConditionGroup getCustomerCondGroup(HttpServletRequest request) {
		String customer = changeEncoding(request.getParameter(CUSTOMER));

		ConditionGroup conditionGroup = new ConditionGroup();
		conditionGroup.setOperator(Operator.OR);

		for (String token : PatternUtils.splitPunctuationMatch(customer)) {
			Condition cond = new Condition(project.getColumn(FieldName.CUSTOMER), Operator.LIKE, token, true);
			conditionGroup.addCondition(cond);
		}

		return conditionGroup;

	}

	private ConditionGroup getCreatedCondGroup(HttpServletRequest request) {
		String createdLowerBound = request.getParameter(LOWER_BOUND_CREATION_DATE);
		String createdUpperBound = request.getParameter(UPPER_BOUND_CREATION_DATE);

		return getBoundedConditionGroup(project.getColumn(FieldName.CREATED), createdLowerBound, createdUpperBound);
	}

	private ConditionGroup getEstimatedCompletionCondGroup(HttpServletRequest request) {
		String estimatedLowerBound = request.getParameter(LOWER_BOUND_ESTIMATED_DATE);
		String estimatedUpperBound = request.getParameter(UPPER_BOUND_ESTIMATED_DATE);

		return getBoundedConditionGroup(project.getColumn(FieldName.ESTIMATED_COMPLETION), estimatedLowerBound, estimatedUpperBound);
	}

	private ConditionGroup getCompletedDateCondGroup(HttpServletRequest request) {
		String completedLowerBound = request.getParameter(LOWER_BOUND_COMPLETION_DATE);
		String completedUpperBound = request.getParameter(UPPER_BOUND_COMPLETION_DATE);

		return getBoundedConditionGroup(project.getColumn(FieldName.COMPLETED), completedLowerBound, completedUpperBound);
	}

	private ConditionGroup getStatusesCondGroup(HttpServletRequest request) {

		String status = request.getParameter(STATUS);
		ConditionGroup condGroup = new ConditionGroup();
		condGroup.setOperator(Operator.OR);

		if (ALL_PARAMETERS.equals(status)) {

			Condition openCond = new Condition(project.getColumn(FieldName.STATUS), Operator.EQUALS, PROJECT_OPEN, true);
			Condition closedCond = new Condition(project.getColumn(FieldName.STATUS), Operator.EQUALS, PROJECT_CLOSED, true);

			condGroup.addCondition(openCond);
			condGroup.addCondition(closedCond);

		} else {
			Condition chosenCond = new Condition(project.getColumn(FieldName.STATUS), Operator.EQUALS, status, true);
			condGroup.addCondition(chosenCond);
		}

		return condGroup;
	}

	public String getQuery(HttpServletRequest request) {

		ConditionGroup conditionGroup = new ConditionGroup();
		conditionGroup.setOperator(Operator.AND);

		conditionGroup.addCondition(getCustomerCondGroup(request));
		conditionGroup.addCondition(getCreatedCondGroup(request));
		conditionGroup.addCondition(getEstimatedCompletionCondGroup(request));
		conditionGroup.addCondition(getCompletedDateCondGroup(request));
		conditionGroup.addCondition(getStatusesCondGroup(request));

		QueryBuilder builder = new QueryBuilder();
		builder.setDistinct(true);

		builder.select(project.getWildcard()).from(project).where(conditionGroup);

		return builder.toString();
	}
}
