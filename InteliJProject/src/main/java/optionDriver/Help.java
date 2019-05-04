package optionDriver;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameters;

@Parameters (
		commandNames = { Help.name },
		commandDescription = "Help command, prints all options supplied by this program"
)
public class Help extends CommandDriver {
	
	public static final String name = "help";
	
	private JCommander jCommander;
	
	public Help ( JCommander jCommander ) {
		super ( Help.name );
		this.jCommander = jCommander;
	}
	
	@Override
	public void executeCommand ( ) {
		this.jCommander.usage ( );
	}
}
