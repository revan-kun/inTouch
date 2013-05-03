package com.epam.lab.intouch.controller.util.query.builder;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.epam.lab.intouch.controller.util.query.Writable;
import com.epam.lab.intouch.controller.util.query.from.FromOperand;
import com.epam.lab.intouch.controller.util.query.join.JoinOperand;
import com.epam.lab.intouch.controller.util.query.select.SelectOperand;
import com.epam.lab.intouch.controller.util.query.where.ConditionGroup;
import com.epam.lab.intouch.controller.util.query.where.WhereOperand;

public class SelectQuery implements Writable {
	private Set<SelectOperand> selectOperands;
	private Set<FromOperand> fromOperands;
	private ConditionGroup conditionGroup;

	public SelectQuery() {
		selectOperands = new LinkedHashSet<SelectOperand>();
		fromOperands = new LinkedHashSet<FromOperand>();
	}

	public ConditionGroup getConditionGroup() {
		return conditionGroup;
	}

	public void setConditionGroup(ConditionGroup conditionGroup) {
		this.conditionGroup = conditionGroup;
	}

	public void addJoinOperand(JoinOperand joinOperand) {
		fromOperands.add(joinOperand.getLeftTable());
		fromOperands.add(joinOperand.getRightTable());
		conditionGroup.addWhereOperand(joinOperand.getCondition());
	}

	public Set<SelectOperand> getSelectOperands() {
		return selectOperands;
	}

	public void setSelectOperands(Set<SelectOperand> selectOperands) {
		this.selectOperands = selectOperands;
	}

	public Set<WhereOperand> getWhereOperands() {
		return conditionGroup.get—onditions();
	}

	public void addSelectOperand(SelectOperand selectOperand) {
		selectOperands.add(selectOperand);
	}

	public void addFromOperand(FromOperand fromOperand) {
		fromOperands.add(fromOperand);
	}

	public void addWhereOperand(WhereOperand whereOperand) {
		conditionGroup.addWhereOperand(whereOperand);
	}

	public Boolean removeWhereOperand(WhereOperand whereOperand) {
		return conditionGroup.removeWhereOperand(whereOperand);
	}

	public Boolean removeSelectOperand(SelectOperand selectOperand) {
		return selectOperands.remove(selectOperand);
	}

	public Boolean removeFromOperand(FromOperand fromOperand) {
		return fromOperands.remove(fromOperand);
	}

	private String appendQueryPart(Collection<? extends Writable> queryPart, String separator) {
		StringBuilder queryBuilder = new StringBuilder();

		Iterator<? extends Writable> iterator = queryPart.iterator();

		boolean hasNext = iterator.hasNext();

		while (hasNext) {
			Writable curr = (Writable) iterator.next();
			hasNext = iterator.hasNext();
			queryBuilder.append(curr.write());

			if (hasNext) {
				queryBuilder.append(separator).append(" ");
			}

		}

		queryBuilder.append(" ");

		return queryBuilder.toString();
	}

	@Override
	public String write() {
		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append("SELECT").append(" ");

		queryBuilder.append(appendQueryPart(selectOperands, ", "));

		queryBuilder.append("FROM").append(" ").append(appendQueryPart(fromOperands, ", "));

		Set<WhereOperand> whereOperands = conditionGroup.get—onditions();

		if (!whereOperands.isEmpty()) {
			queryBuilder.append("WHERE").append(" ");

			queryBuilder.append(appendQueryPart(whereOperands, " " + conditionGroup.getGroupOperator() + " "));

		}

		return queryBuilder.toString();
	}

}
