package com.epam.lab.intouch.controller.util.query.join;

import com.epam.lab.intouch.controller.util.query.Writable;
import com.epam.lab.intouch.controller.util.query.from.Table;


public interface JoinOperand extends Writable {
	Table getLeftTable();
	Table getRightTable();
	JoinCondition getCondition();
}
