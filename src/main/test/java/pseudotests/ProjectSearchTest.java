package pseudotests;

import com.epam.lab.intouch.controller.util.query.builder.QueryBuilder;
import com.epam.lab.intouch.controller.util.query.from.Table;
import com.epam.lab.intouch.controller.util.query.select.Wildcard;
import com.epam.lab.intouch.controller.util.query.where.Condition;
import com.epam.lab.intouch.controller.util.query.where.ConditionGroup;
import com.epam.lab.intouch.controller.util.query.where.Operator;
import com.epam.lab.intouch.util.db.metadata.FieldName;
import com.epam.lab.intouch.util.db.metadata.TableName;

public class ProjectSearchTest {
	private Table project;

	String createdLowerBound = null;
	String createdUpperBound = "2013-11-10";

	String estimatedLowerBound = "2012-11-10";
	String estimatedUpperBound = null;

	String completedLowerBound = "2012-11-10";
	String completedUpperBound = "2013-11-10";

	String customer = "Google";

	String[] statuses = new String[] { "Open", "Closed" };

	public ProjectSearchTest() {
		project = new Table(TableName.PROJECT);
	}

	private ConditionGroup getBoundedConditionGroup(String field, String lowerBound, String upperBound) {

		Condition lowerBoundCond = new Condition(project.getColumn(field), Operator.GREATER_OR_EQUAL, lowerBound, true);
		Condition upperBoundCond = new Condition(project.getColumn(field), Operator.LESS_OR_EQUAL, upperBound, true);

		ConditionGroup condGroup = new ConditionGroup();
		condGroup.setOperator(Operator.AND);

		condGroup.addCondition(lowerBoundCond);
		condGroup.addCondition(upperBoundCond);

		return condGroup;
	}

	private Condition getCustomerCondition() {
		return new Condition(project.getColumn(FieldName.CUSTOMER), Operator.EQUALS, customer, true);
	}

	private ConditionGroup getCreatedCondGroup() {
		return getBoundedConditionGroup(FieldName.CREATED, createdLowerBound, createdUpperBound);
	}

	private ConditionGroup getEstimatedCompletionCondGroup() {
		return getBoundedConditionGroup(FieldName.ESTIMATED_COMPLETION, estimatedLowerBound, estimatedUpperBound);
	}

	private ConditionGroup getCompletedDateCondGroup() {
		return getBoundedConditionGroup(FieldName.COMPLETED, completedLowerBound, completedUpperBound);
	}

	private ConditionGroup getStatusesCondGroup() {

		ConditionGroup condGroup = new ConditionGroup();
		condGroup.setOperator(Operator.OR);

		if (statuses != null) {
			for (int i = 0; i < statuses.length; i++) {
				Condition cond = new Condition(project.getColumn(FieldName.STATUS), Operator.EQUALS, statuses[i], true);
				condGroup.addCondition(cond);
			}
		}

		return condGroup;
	}

	public String getQuery() {

		ConditionGroup conditionGroup = new ConditionGroup();
		conditionGroup.setOperator(Operator.AND);

		conditionGroup.addCondition(getCustomerCondition());
		conditionGroup.addCondition(getCreatedCondGroup());
		conditionGroup.addCondition(getEstimatedCompletionCondGroup());
		conditionGroup.addCondition(getCompletedDateCondGroup());
		conditionGroup.addCondition(getStatusesCondGroup());

		// if (!getSkillCondGroup(request).isValid()) {
		// skills = null;
		// memberSkills = null;
		// }

		QueryBuilder builder = new QueryBuilder();
		builder.select(new Wildcard()).from(project).where(conditionGroup);

		return builder.toString();
	}

	public static void main(String[] args) {
		ProjectSearchTest test = new ProjectSearchTest();
		System.out.println(test.getQuery());

	}

}
