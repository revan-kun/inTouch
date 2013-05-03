package com.epam.lab.intouch.controller.util.query.join;

import com.epam.lab.intouch.controller.util.query.Writable;
import com.epam.lab.intouch.controller.util.query.select.Column;
import com.epam.lab.intouch.controller.util.query.where.WhereOperand;

public class JoinCondition implements Writable, WhereOperand {
	private Column field;
	private Column valueColumn;
	private String operator;

	public JoinCondition(Column field, String operator, Column value) {
		this.field = field;
		this.valueColumn = value;
		this.operator = operator;
	}

	public Column getField() {
		return field;
	}

	public void setField(Column field) {
		this.field = field;
	}

	public Column getValue() {
		return valueColumn;
	}

	public void setValue(Column value) {
		this.valueColumn = value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((valueColumn == null) ? 0 : valueColumn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof JoinCondition))
			return false;
		JoinCondition other = (JoinCondition) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (valueColumn == null) {
			if (other.valueColumn != null)
				return false;
		} else if (!valueColumn.equals(other.valueColumn))
			return false;
		return true;
	}

	@Override
	public String write() {

		StringBuilder queryBuilder = new StringBuilder();

		if (field != null && operator != null && valueColumn != null) {
			queryBuilder.append(field.write()).append(operator).append(valueColumn.write());
		}

		return queryBuilder.toString();
	}
}
