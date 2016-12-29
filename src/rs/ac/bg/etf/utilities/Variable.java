package rs.ac.bg.etf.utilities;

public class Variable {
	private String name = "";
	private boolean isArray = false;
	
	public Variable(String name, boolean isArray) {
		super();
		this.name = name;
		this.isArray = isArray;
	}

	public boolean isArray() {
		return isArray;
	}

	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
