package rs.ac.bg.etf.utilities;

import rs.etf.pp1.symboltable.concepts.Struct;

public class Value {
	private Struct type = null;
	private int value = 0;
	
	public Value(Struct type, int value) {
		super();
		this.type = type;
		this.value = value;
	}
	
	public Struct getType() {
		return type;
	}
	
	public void setType(Struct type) {
		this.type = type;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString() {
		switch (type.getKind()) {
			case Struct.Int: {
				return Integer.toString(value);
			}
			
			case Struct.Char: {
				return Character.toString((char) value);
			}
			
			case Struct.Bool: {
				return value == 0 ? "false" : "true";
			}
			
			default: {
				return "";
			}
		}
	}
}
