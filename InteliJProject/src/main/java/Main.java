import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import optionDriver.Compile;
import optionDriver.Help;
import optionDriver.CommandDriver;
import optionDriver.Run;

public class Main {
	
	public static void main ( String[] args ) {
		try {
			JCommander jCommander = JCommander.newBuilder ( )
			                                  .build ( );
			
			CommandDriver commandDrivers[] = {
					new Help ( jCommander ),
					new Compile ( ),
					new Run ( )
			};
			
			jCommander.setProgramName ( "mje" );
			
			for ( CommandDriver commandDriver : commandDrivers ) {
				jCommander.addCommand ( commandDriver );
			}
			
			jCommander.parse ( args );
			
			String parsedCommand = jCommander.getParsedCommand ( );
			
			for ( CommandDriver commandDriver : commandDrivers ) {
				if ( commandDriver.isCommandNameEqual ( parsedCommand ) )  {
					commandDriver.executeCommand ( );
				}
			}
		} catch ( ParameterException parameterException ) {
			System.out.println ( parameterException.getMessage ( ) );
			parameterException.getJCommander ( ).usage ( );
		}
 	}
}
