package com.epam.lab.intouch.controller.util.query.where;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.util.query.Writable;
import com.epam.lab.intouch.controller.util.query.select.Column;

public class Condition implements Writable {
	private final static Logger LOG = LogManager.getLogger(Condition.class);
	
	private Boolean isFilter;
	private String field;
	private String operator;
	private String value;

	public Condition(Column field, String operator, String value, Boolean isFilter) {
		this.field = field.toString();
		this.operator = operator;
		this.value = value;
		this.isFilter = isFilter;
	}

	public Condition(Column leftKey, String operator, Column rightKey) {
		this.field = leftKey.toString();
		this.operator = operator;
		this.value = rightKey.toString();
		isFilter = false;
	}

	public Boolean isFilter() {
		return isFilter;
	}

	public void setFilter(Boolean isFilter) {
		this.isFilter = isFilter;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		LOG.debug("field: "+field+"\n operator: "+operator+"\nvalue: "+value);
		
		StringBuilder builder = new StringBuilder();

		if (field != null && operator != null && operator != null) {
			builder.append(field).append(" ").append(operator).append(" ");

			if (isFilter) {
				builder.append("'").append(value).append("'");
			} else {
				builder.append(value);
			}
			
		}

		return builder.toString();
	}
}
