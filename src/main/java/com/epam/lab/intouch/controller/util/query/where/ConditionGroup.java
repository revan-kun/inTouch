package com.epam.lab.intouch.controller.util.query.where;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ConditionGroup is set of conditions separated by operator. Example: TableName.SomeColumn='SomeValue' OR TableName.SomeColumn2='SomeValue' OR etc.
 * 
 * @author Zatorsky D.B
 * 
 */
public class ConditionGroup implements Conditional {
	private final static Logger LOG = LogManager.getLogger(ConditionGroup.class);

	private List<Conditional> conditions;
	String operator;

	public ConditionGroup() {
		conditions = new ArrayList<Conditional>();
	}

	public List<Conditional> getConditions() {
		return conditions;
	}

	public void setConditions(List<Conditional> conditions) {
		this.conditions = conditions;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * adds condition or condition group to WHERE clause
	 * 
	 * @param cond
	 *            condition or conditionGroup
	 * @return true if condition were added
	 */
	public Boolean addCondition(Conditional cond) {
		return conditions.add(cond);
	}

	/**
	 * removes condition or condition group from WHERE clause
	 * 
	 * @param cond
	 *            condition or conditionGroup
	 * @return true if condition were deleted
	 */
	public Boolean removeCondition(Conditional cond) {
		return conditions.remove(cond);
	}

	/**
	 * @return amount of valid condition. Valid condition is condition without null (or empty) elements (field, operator or value)
	 */
	private Integer amountOfValidConditions() {
		Integer validCondAmount = 0;
		for (Conditional cond : conditions) {
			if (cond.isValid()) {
				validCondAmount++;
			}
		}

		return validCondAmount;
	}

	/**
	 * Valid condition is condition without null (or empty) elements (field, operator or value)
	 * 
	 * @see com.epam.lab.intouch.controller.util.query.where.Conditional#isValid()
	 */
	@Override
	public Boolean isValid() {
		return amountOfValidConditions() > 0;
	}

	/**
	 * @see com.epam.lab.intouch.controller.util.query.Writable#toString()
	 */
	@Override
	public String toString() {
		StringBuilder queryBuilder = new StringBuilder();
		int validConditionsAmount = amountOfValidConditions();

		if (this.isValid()) {
			queryBuilder.append(" (");

			int i = 0;
			for (Conditional conditional : conditions) {
				queryBuilder.append(conditional);
				if (i < validConditionsAmount - 1 && conditional.isValid()) {
					queryBuilder.append(" ").append(operator).append(" ");
					i++;
				}

			}

			queryBuilder.append(") ");
		}

		LOG.debug(queryBuilder.toString());

		return queryBuilder.toString();

	}
}
