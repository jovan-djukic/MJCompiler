package parser;

public class Constant {
	private String name;
	private Value  value;
	
	public Constant ( String name, Value value ) {
		this.name  = name;
		this.value = value;
	}
	
	public String getName ( ) {
		return name;
	}
	
	public void setName ( String name ) {
		this.name = name;
	}
	
	public Value getValue ( ) {
		return value;
	}
	
	public void setValue ( Value value ) {
		this.value = value;
	}
	
	public String toString ( ) {
		return name + " " + value;
	}
}
