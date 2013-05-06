package com.epam.lab.intouch.controller.util.query.where;

import java.util.LinkedHashSet;
import java.util.Set;

public class ConditionGroup implements WhereOperand {
	private Set<WhereOperand> conditions;
	private String groupOperator;

	public ConditionGroup(String groupOperator) {
		conditions = new LinkedHashSet<WhereOperand>();
		this.groupOperator = groupOperator;
	}

	public Set<WhereOperand> get—onditions() {
		return conditions;
	}

	public void set—onditions(Set<WhereOperand> Òonditions) {
		this.conditions = Òonditions;
	}

	public String getGroupOperator() {
		return groupOperator;
	}

	public void setGroupOperator(String groupOperator) {
		this.groupOperator = groupOperator;
	}

	public void addWhereOperand(WhereOperand WhereOperand) {
		conditions.add(WhereOperand);
	}

	public Boolean removeWhereOperand(WhereOperand WhereOperand) {
		return conditions.remove(WhereOperand);
	}

	@Override
	public String write() {

		StringBuilder queryBuilder = new StringBuilder();

		int i = 1;
		for (WhereOperand whereOperand : conditions) {
			if (i != 1) {
				queryBuilder.append(groupOperator).append(" ");
			}
			queryBuilder.append(whereOperand.write());
			i++;
		}

		return queryBuilder.toString();
	}

}
