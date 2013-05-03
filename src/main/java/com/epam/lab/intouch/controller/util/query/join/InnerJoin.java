package com.epam.lab.intouch.controller.util.query.join;

import com.epam.lab.intouch.controller.util.query.from.FromOperand;
import com.epam.lab.intouch.controller.util.query.from.Table;

public class InnerJoin implements JoinOperand, FromOperand {
	private Table leftTable;
	private Table rightTable;
	private JoinCondition condition;

	public InnerJoin(Table leftTable, Table rightTable, JoinCondition condition) {
		this.leftTable = leftTable;
		this.rightTable = rightTable;
		this.condition = condition;
	}

	public JoinCondition getCondition() {
		return condition;
	}

	public void setCondition(JoinCondition condition) {
		this.condition = condition;
	}

	@Override
	public Table getLeftTable() {
		return leftTable;
	}

	public void setLeftTable(Table leftTable) {
		this.leftTable = leftTable;
	}

	@Override
	public Table getRightTable() {
		return rightTable;
	}

	public void setRightTable(Table rightTable) {
		this.rightTable = rightTable;
	}

	@Override
	public String write() {

		StringBuilder joinBuilder = new StringBuilder();
		joinBuilder.append(leftTable.write()).append(" ").append("JOIN").append(" ").append(rightTable.write()).append(" ").append("ON").append(" ")
				.append(condition.write());

		return joinBuilder.toString();
	}

}
