package com.epam.lab.intouch.controller.util.query.builder;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.intouch.controller.util.query.Writable;
import com.epam.lab.intouch.controller.util.query.from.Table;
import com.epam.lab.intouch.controller.util.query.select.Selectable;
import com.epam.lab.intouch.controller.util.query.where.Condition;
import com.epam.lab.intouch.controller.util.query.where.Conditional;

public class QueryBuilder {
	private Boolean isDistinct;

	private final StringBuilder queryBuilder;
	private List<Conditional> conditions;

	public QueryBuilder() {
		queryBuilder = new StringBuilder();
		conditions = new ArrayList<Conditional>();
		isDistinct = false;
	}

	public Boolean isDistinct() {
		return isDistinct;
	}

	public void setDistinct(Boolean isDistinct) {
		this.isDistinct = isDistinct;
	}

	private void appendSring(String... args) {
		for (int i = 0; i < args.length; i++) {
			queryBuilder.append(args[i]);
			if (i != (args.length - 1)) {
				queryBuilder.append(", ");
			}
		}
	}

	private void appendWritable(Writable... args) {
		for (int i = 0; i < args.length; i++) {
			queryBuilder.append(args[i]);
			if (i != (args.length - 1)) {
				queryBuilder.append(", ");
			}
		}
	}

	public QueryBuilder select(String... args) {
		queryBuilder.append("SELECT").append(" ");

		if (isDistinct) {
			queryBuilder.append(" ").append("DISTINCT").append(" ");
		}

		appendSring(args);
		return this;
	}

	public QueryBuilder select(Selectable... args) {
		queryBuilder.append("SELECT").append(" ");

		if (isDistinct) {
			queryBuilder.append(" ").append("DISTINCT").append(" ");
		}

		appendWritable(args);
		return this;
	}

	public QueryBuilder inerJoin(Table table, Condition key) {
		if (table != null && key != null) {
			queryBuilder.append(" ").append("JOIN").append(" ").append(table.toString()).append(" ").append("ON").append(" ").append(key.toString());
		}
		return this;
	}

	public QueryBuilder from(String... args) {
		queryBuilder.append(" ").append("FROM").append(" ");
		appendSring(args);
		return this;
	}

	public QueryBuilder from(Table... args) {
		queryBuilder.append(" ").append("FROM").append(" ");
		appendWritable(args);
		return this;
	}

	public QueryBuilder where(Conditional cond) {
		if (cond.isValid()) {
			queryBuilder.append(" ").append("WHERE").append(" ");
			conditions.add(cond);
		}

		return this;
	}

	@Override
	public String toString() {

		for (Conditional cond : conditions) {
			queryBuilder.append(" ").append(cond).append(" ");
			System.out.println(cond);
		}

		return queryBuilder.toString();
	}

}
