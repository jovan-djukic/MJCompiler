package rs.ac.bg.etf.utilities;

import java.util.ArrayList;

public class Constant {
	private String name = null;
	private Value value = null;
	
	public Constant(String name, Value value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
 	}
	
	public String toString() {
		return name + " " + value;
	}
}
