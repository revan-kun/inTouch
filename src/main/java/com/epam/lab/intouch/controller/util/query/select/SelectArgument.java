package com.epam.lab.intouch.controller.util.query.select;

import com.epam.lab.intouch.controller.util.query.from.Table;


public class SelectArgument implements SelectOperand {
	private Table table;
	private SelectOperand selectOperand;

	public SelectArgument(Table table, SelectOperand selectOperand) {
		this.table = table;
		this.selectOperand = selectOperand;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public SelectOperand getSelectOperand() {
		return selectOperand;
	}

	public void setSelectOperand(SelectOperand selectOperand) {
		this.selectOperand = selectOperand;
	}

	@Override
	public String write() {
		return table.write() + "." + selectOperand.write();
	}
}
