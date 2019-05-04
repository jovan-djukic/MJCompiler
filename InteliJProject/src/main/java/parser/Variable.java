package parser;

public class Variable {
	private String  name;
	private boolean isArray;
	
	public Variable ( String name, boolean isArray ) {
		super ( );
		this.name    = name;
		this.isArray = isArray;
	}
	
	public boolean isArray ( ) {
		return this.isArray;
	}
	
	public void setArray ( boolean isArray ) {
		this.isArray = isArray;
	}
	
	public String getName ( ) {
		return this.name;
	}
	
	public void setName ( String name ) {
		this.name = name;
	}
}
