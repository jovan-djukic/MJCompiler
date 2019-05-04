package optionDriver;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import java_cup.runtime.Symbol;
import lexer.Lexer;
import parser.Parser;
import rs.etf.pp1.mj.runtime.Code;

import java.io.*;

@Parameters (
		commandNames       = { Compile.name },
		commandDescription = "Compilation command, if specified both input and output files need to be specified as well"
)
public class Compile extends CommandDriver {
	public static final String name = "compile";
	
	@Parameter (
			names       = { "-i", "--input" },
			description = "Path to input file containing a valid micro java program",
			converter   = FileConverter.class,
			required    = true
	)
	private File inputFile;
	
	@Parameter (
			names       = { "-o", "--output" },
			description = "Path to output file",
			converter   = FileConverter.class,
			required    = true
	)
	private File outputFile;
	
	public Compile (  ) {
		super ( Compile.name );
	}
	
	@Override
	public void executeCommand ( ) {
		try (
				InputStream inputStream = new FileInputStream ( inputFile )
		) {
			Parser parser = new Parser ( new Lexer ( inputStream ) );
			Symbol symbol = parser.parse ( );
			
			parser.dump ( );
			
			if ( parser.isErrorFound ( ) ) {
				System.out.println ( "Error found, no code generated" );
			} else {
				if ( this.outputFile.exists ( ) ) {
					this.outputFile.delete ( );
				}
				Code.write ( new FileOutputStream ( this.outputFile ) );
			}
		} catch ( FileNotFoundException e ) {
			e.printStackTrace ( );
		} catch ( IOException e ) {
			e.printStackTrace ( );
		} catch ( Exception e ) {
			e.printStackTrace ( );
		}
	}
}
