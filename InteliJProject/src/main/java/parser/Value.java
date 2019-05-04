package parser;

import rs.etf.pp1.symboltable.concepts.Struct;

public class Value {
	private Struct type;
	private int    value;
	
	public Value ( Struct type, int value ) {
		super ( );
		this.type  = type;
		this.value = value;
	}
	
	public Struct getType ( ) {
		return this.type;
	}
	
	public void setType ( Struct type ) {
		this.type = type;
	}
	
	public int getValue ( ) {
		return this.value;
	}
	
	public void setValue ( int value ) {
		this.value = value;
	}
	
	public String toString ( ) {
		switch ( this.type.getKind ( ) ) {
			case Struct.Int: {
				return Integer.toString ( this.value );
			}
			
			case Struct.Char: {
				return Character.toString ( ( char ) this.value );
			}
			
			case Struct.Bool: {
				return this.value == 0 ? "false" : "true";
			}
			
			default: {
				return "";
			}
		}
	}
}
