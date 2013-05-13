package com.epam.lab.intouch.controller.util.query.where;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConditionGroup implements Conditional {
	private final static Logger LOG = LogManager.getLogger(ConditionGroup.class);

	private List<Conditional> conditions;
	String operator;

	public ConditionGroup() {
		conditions = new ArrayList<Conditional>();
	}

	@Override
	public Boolean isValid() {
		// Boolean result = false;

		// int invalidCondAmount = 0;
		// for (Conditional cond : conditions) {
		// if (!cond.isValid()) {
		// invalidCondAmount++;
		// }
		// }
		//
		// if (conditions.size() > invalidCondAmount) {
		// result = true;
		// }

		return amountOfValidConditions() > 0;
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

	public Boolean addCondition(Conditional cond) {
		return conditions.add(cond);
	}

	public Boolean removeCondition(Conditional cond) {
		return conditions.remove(cond);
	}

	private Integer amountOfValidConditions() {
		Integer validCondAmount = 0;
		for (Conditional cond : conditions) {
			if (cond.isValid()) {
				validCondAmount++;
			}
		}

		return validCondAmount;

	}

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
