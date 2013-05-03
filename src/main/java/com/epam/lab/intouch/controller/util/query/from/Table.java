package com.epam.lab.intouch.controller.util.query.from;


public class Table implements FromOperand {
	private String name;

	public Table(String name) {
		this.name = name;
	}

//	public void join(Table table, Column leftTableKey, Column rightTableKey){
//		StringBuilder joinBuilder=new StringBuilder();
//		joinBuilder.append(name).append(" ").append("JOIN").append(" ")
//		.append(table.append()).append(" ").append("ON").append(" ")
//		.append(leftTableKey.append()).append(Operator.EQUALS).append(rightTableKey.append());
//		
//		setName(joinBuilder.toString());
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String write() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Table))
			return false;
		Table other = (Table) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
