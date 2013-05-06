package com.epam.lab.intouch.controller.util.query.where;

import com.epam.lab.intouch.controller.util.query.select.Column;

public class Condition implements WhereOperand {
	private Column field;
	private String value;
	private String operator;

	public Condition(Column field, String operator, String value) {
		this.field = field;
		this.value = value;
		this.operator = operator;
	}

	public Column getField() {
		return field;
	}

	public void setField(Column field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Condition))
			return false;
		Condition other = (Condition) obj;
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
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String write() {

		StringBuilder queryBuilder = new StringBuilder();

		if (field != null && operator != null && value != null) {
			queryBuilder.append(field.write()).append(" ").append(operator).append(" '").append(value).append("'");
		}

		return queryBuilder.toString();
	}

}
