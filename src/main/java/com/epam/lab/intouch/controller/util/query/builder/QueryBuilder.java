package com.epam.lab.intouch.controller.util.query.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.intouch.controller.util.query.Writable;
import com.epam.lab.intouch.controller.util.query.from.Table;
import com.epam.lab.intouch.controller.util.query.select.Selectable;
import com.epam.lab.intouch.controller.util.query.where.Condition;
import com.epam.lab.intouch.controller.util.query.where.Conditional;

public class QueryBuilder {
	private final static Logger LOG = LogManager.getLogger(QueryBuilder.class);

	private final StringBuilder queryBuilder;
	private List<Conditional> conditions;

	public QueryBuilder() {
		queryBuilder = new StringBuilder();
		conditions = new ArrayList<Conditional>();
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
		appendSring(args);
		return this;
	}

	public QueryBuilder select(Selectable... args) {
		queryBuilder.append("SELECT").append(" ");
		appendWritable(args);
		return this;
	}

	public QueryBuilder inerJoin(Table table, Condition key) {
		if(table!=null && key!=null){
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
		queryBuilder.append(" ").append("WHERE").append(" ");
		conditions.add(cond);
		return this;
	}

	// public QueryBuilder where(Condition cond) {
	// queryBuilder.append(" ").append("WHERE").append(" ").append(cond);
	// return this;
	// }
	//
	// public QueryBuilder and(Condition cond) {
	// if (cond != null && !cond.toString().isEmpty()) {
	// SecurityFilter filter = new SecurityFilter();
	// cond.setValue(filter.replaceUnsafeSymbols(cond.getValue()));
	// queryBuilder.append(" ").append("AND").append(" ").append(cond);
	// }
	// return this;
	// }
	//
	// public QueryBuilder or(Condition cond) {
	// if (cond != null && !cond.toString().isEmpty()) {
	// SecurityFilter filter = new SecurityFilter();
	// cond.setValue(filter.replaceUnsafeSymbols(cond.getValue()));
	// queryBuilder.append(" ").append("OR").append(" ").append(cond);
	// }
	// return this;
	// }

	@Override
	public String toString() {

		for (Conditional cond : conditions) {
			queryBuilder.append(" ").append(cond).append(" ");
			System.out.println(cond);
		}

		return queryBuilder.toString();
	}

}