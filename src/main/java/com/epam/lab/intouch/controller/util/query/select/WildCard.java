package com.epam.lab.intouch.controller.util.query.select;

public class WildCard implements SelectOperand {
	private final String WILDCARD="*";
	
	@Override
	public String write() {
		return WILDCARD;
	}

}
