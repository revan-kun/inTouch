package com.epam.lab.intouch.controller.util.query.builder;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.intouch.controller.util.query.Writable;
import com.epam.lab.intouch.controller.util.query.from.Table;
import com.epam.lab.intouch.controller.util.query.select.Selectable;
import com.epam.lab.intouch.controller.util.query.where.Condition;
import com.epam.lab.intouch.controller.util.query.where.Conditional;

/**
 * This class exists for building queries in runtime
 * 
 * @author Zatorsky D.B
 * 
 */

public class QueryBuilder {
	private Boolean isDistinct;

	private final StringBuilder queryBuilder;
	private List<Conditional> conditions;

	public QueryBuilder() {
		queryBuilder = new StringBuilder();
		conditions = new ArrayList<Conditional>();
		isDistinct = false;
	}

	/**
	 * Check query if it is DISTINCT
	 * 
	 * @return true if result query should have DISTINCT clause
	 */
	public Boolean isDistinct() {
		return isDistinct;
	}

	/**
	 * Creates query with DISTINCT clause
	 * 
	 * @param determines
	 *            DISTINCT clause in the query
	 */
	public void setDistinct(Boolean isDistinct) {
		this.isDistinct = isDistinct;
	}

	/**
	 * @param args
	 *            variety of strings that should be added to the query
	 */
	private void appendSring(String... args) {
		for (int i = 0; i < args.length; i++) {
			queryBuilder.append(args[i]);
			if (i != (args.length - 1)) {
				queryBuilder.append(", ");
			}
		}
	}

	/**
	 * @param args
	 *            variety of objects that implement Writable interface and should be added to the query
	 */
	private void appendWritable(Writable... args) {
		for (int i = 0; i < args.length; i++) {
			queryBuilder.append(args[i]);
			if (i != (args.length - 1)) {
				queryBuilder.append(", ");
			}
		}
	}

	/**
	 * Appends SELECT query part to the whole query
	 * 
	 * @param args
	 *            variety of strings that should be added to the query
	 * @return QueryBuilder reference (reference to itself)
	 */
	public QueryBuilder select(String... args) {
		queryBuilder.append("SELECT").append(" ");

		if (isDistinct) {
			queryBuilder.append(" ").append("DISTINCT").append(" ");
		}

		appendSring(args);
		return this;
	}

	/**
	 * Appends SELECT query part to the whole query
	 * 
	 * @param args
	 *            variety of objects that implement Selectable interface and should be added to the query
	 * @return QueryBuilder reference (reference to itself)
	 */
	public QueryBuilder select(Selectable... args) {
		queryBuilder.append("SELECT").append(" ");

		if (isDistinct) {
			queryBuilder.append(" ").append("DISTINCT").append(" ");
		}

		appendWritable(args);
		return this;
	}

	/**
	 * Appends INNER JOIN clause in the FROM section of result query
	 * 
	 * @param table
	 *            table which will be added
	 * @param key
	 *            key which means ON clause
	 * @return QueryBuilder reference (reference to itself)
	 */
	public QueryBuilder inerJoin(Table table, Condition key) {
		if (table != null && key != null) {
			queryBuilder.append(" ").append("JOIN").append(" ").append(table.toString()).append(" ").append("ON").append(" ").append(key.toString());
		}
		return this;
	}

	/**
	 * Appends FROM clause to the result query
	 * 
	 * @param args
	 *            names of tables that are sources from where we will receive data
	 * @return QueryBuilder reference (reference to itself)
	 */
	public QueryBuilder from(String... args) {
		queryBuilder.append(" ").append("FROM").append(" ");
		appendSring(args);
		return this;
	}

	/**
	 * Appends FROM clause to the result query
	 * 
	 * @param args
	 *            table objects that are sources from where we will receive data
	 * @return QueryBuilder reference (reference to itself)
	 */
	public QueryBuilder from(Table... args) {
		queryBuilder.append(" ").append("FROM").append(" ");
		appendWritable(args);
		return this;
	}

	/**
	 * Appends WHERE clause to the result query
	 * 
	 * @param cond
	 *            condition or condition group that should be added to the WHERE section
	 * @return QueryBuilder reference (reference to itself)
	 */
	public QueryBuilder where(Conditional cond) {
		if (cond.isValid()) {
			queryBuilder.append(" ").append("WHERE").append(" ");
			conditions.add(cond);
		}

		return this;
	}

	/**
	 * This method renders query
	 * 
	 * @see com.epam.lab.intouch.controller.util.query.Writable#toString()
	 */
	@Override
	public String toString() {

		for (Conditional cond : conditions) {
			queryBuilder.append(" ").append(cond).append(" ");
		}

		return queryBuilder.toString();
	}

}
