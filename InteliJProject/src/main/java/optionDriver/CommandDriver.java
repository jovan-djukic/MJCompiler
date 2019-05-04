package optionDriver;


public abstract class CommandDriver {
	private String  name;
	
	public CommandDriver ( String name )  {
		this.name = name;
	}
	
	public boolean isCommandNameEqual ( String commandName ) {
		return this.name.equals ( commandName );
	}
	
	public abstract void executeCommand ( );
}

