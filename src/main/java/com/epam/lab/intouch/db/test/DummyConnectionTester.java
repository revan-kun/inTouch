package com.epam.lab.intouch.db.test;

import com.epam.lab.intouch.db.util.*;

public class DummyConnectionTester {
	public static void main(String[] args) {
		ConnectionManager.getInstance().setDBType(DBType.MSSQL);
		//TODO place for future db connection check
		ConnectionManager.getInstance().close();
		//simple comment
		//my first comment
	}
}
