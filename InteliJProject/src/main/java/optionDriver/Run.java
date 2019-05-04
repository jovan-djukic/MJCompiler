package optionDriver;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.File;

@Parameters (
		commandNames = { Run.name },
		commandDescription = "Run options, executes a valid micro java program"
)
public class Run extends CommandDriver {
	public static final String name = "run";
	
	@Parameter (
			names       = { "-f", "--file" },
			description = "File containing a valid micro java program",
			converter   = FileConverter.class,
			required    = true
	)
	private File microJavaProgramFile;
	
	@Parameter (
			names       = { "-d", "--debug" },
			description = "If specified debugging is turned on"
	)
	private boolean debug;
	
	public Run ( ) {
		super ( Run.name );
	}
	
	@Override
	public void executeCommand ( ) {
		if ( this.debug ) {
			rs.etf.pp1.mj.runtime.Run.main ( new String[] { this.microJavaProgramFile.getAbsolutePath ( ), "-debug" } );
		} else {
			rs.etf.pp1.mj.runtime.Run.main ( new String[] { this.microJavaProgramFile.getAbsolutePath ( ) } );
		}
	}
}
