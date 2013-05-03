package com.epam.lab.intouch.controller.util.query.where;

import java.util.LinkedHashSet;
import java.util.Set;

public class ConditionGroup implements WhereOperand {
	private Set<WhereOperand> �onditions;
	private String groupOperator;

	public ConditionGroup(String groupOperator) {
		�onditions = new LinkedHashSet<WhereOperand>();
		this.groupOperator = groupOperator;
	}

	public Set<WhereOperand> get�onditions() {
		return �onditions;
	}

	public void set�onditions(Set<WhereOperand> �onditions) {
		this.�onditions = �onditions;
	}

	public String getGroupOperator() {
		return groupOperator;
	}

	public void setGroupOperator(String groupOperator) {
		this.groupOperator = groupOperator;
	}

	public void addWhereOperand(WhereOperand WhereOperand) {
		�onditions.add(WhereOperand);
	}

	public Boolean removeWhereOperand(WhereOperand WhereOperand) {
		return �onditions.remove(WhereOperand);
	}

	@Override
	public String write() {

		StringBuilder queryBuilder = new StringBuilder();

		int i = 1;
		for (WhereOperand whereOperand : �onditions) {
			if (i != 1) {
				queryBuilder.append(groupOperator).append(" ");
			}
			queryBuilder.append(whereOperand.write());
			i++;
		}

		return queryBuilder.toString();
	}

}
