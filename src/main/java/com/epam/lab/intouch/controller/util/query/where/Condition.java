package com.epam.lab.intouch.controller.util.query.where;

import com.epam.lab.intouch.controller.util.query.select.Column;

/**
 * This class exists for creating condition in WHERE section
 * 
 * @author Zatorsky D.B
 * 
 */
public class Condition implements Conditional {

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

	/**
	 * @see com.epam.lab.intouch.controller.util.query.where.Conditional#isValid()
	 */
	public Boolean isValid() {
		return isNotNull() && isNotEmpty();
	}

	/**
	 * @return true if all three components (field, operator and value) of condition is not empty
	 */
	private Boolean isNotEmpty() {
		return !field.isEmpty() && !operator.isEmpty() && !value.isEmpty();
	}

	/**
	 * @return true if all three components (field, operator and value) of condition is not null
	 */
	private Boolean isNotNull() {
		return field != null && operator != null && value != null;
	}

	/**
	 * There are two type of conditions: filter and key. When we suppose that condition is a filter, that's mean that the value of condition user enters
	 * dynamically so the value of this condition should be in single brackets. When we suppose that condition is a filter, that's mean that this condition is
	 * static, and used for joins and other similar operations. 
	 * Example of filter: TableName.SomeColumn='SomeValue' 
	 * Example of key: TableName1.SomeColumn1=TableName2.SomeColumn2
	 * 
	 * @return true if condition is filter
	 */
	public Boolean isFilter() {
		return isFilter != null && isFilter;
	}

	/**
	 * @param isFilter
	 *            defines a filter type condition
	 */
	public void setFilter(Boolean isFilter) {
		this.isFilter = isFilter;
	}

	/**
	 * @return field of condition
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            set field to this condition
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return operator of condition
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            set operator for condition
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return value of condition
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            set value for conditions
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Renders condition depending on the type of it
	 * 
	 * @see @see com.epam.lab.intouch.controller.util.query.Writable#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		if (isValid()) {

			builder.append(field).append(" ").append(operator).append(" ");

			if (isFilter()) {
				builder.append("N'").append(value).append("'");
			} else {
				builder.append(value);
			}

		}

		return builder.toString();
	}
}
