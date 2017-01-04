package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.logging.log4j.Logger;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.utilities.MyLoggerFactory;

public class ParserSyntaxTestWithSyntaxErrors {
	private static String testProgramsDirectoryPath = "MJTestPrograms/SyntaxAnalysisWithErrors";
	private static Logger logger = MyLoggerFactory.getLogger(ParserSyntaxTestWithSyntaxErrors.class);
	
	public static void main(String[] args) {
		PrintWriter out = null;
		if (args.length == 2 && "-f".equals(args[0])) {
			File outputFile = new File("output/" + args[1]);
			try {
				if (outputFile.exists() || outputFile.createNewFile()) {
					out = new PrintWriter(new FileOutputStream(outputFile));
				}
			} catch (IOException e) {
		
			} 
		} 
		
		File file = new File(testProgramsDirectoryPath);
		File[] testFiles = file.listFiles();
		
		for (int i = 0; i < testFiles.length; i++) {
			
			if (out != null) {
				out.println("======================================================================");
				out.println("FILE: " + testFiles[i].getPath());
				out.println("======================================================================");
			}
			
			logger.info("======================================================================");
			logger.info("FILE: " + testFiles[i].getPath());
			logger.info("======================================================================");
			
			try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(testFiles[i])));) {
				Symbol symbol = null;
				Lexer lexer = new Lexer(in);
				
				try {
					Parser parser = new Parser(new Lexer(in));
					symbol = parser.parse();
					logger.info("SYMBOL IS: " + symbol.sym);
					logger.info("GLOBAL VARIABLE COUNT: " + parser.action_obj.getGlobalVariableCount());
					logger.info("MAIN VARIABLE COUNT: " + parser.action_obj.getMainVariableCount());
					logger.info("CONSTANT COUNT: " + parser.action_obj.getConstantCount());
					logger.info("GLOBAL ARRAY DECLARTION: " + parser.action_obj.getGlobalArrayCount());
					logger.info("CLASS STATIC METHOD COUNT: " + parser.action_obj.getStaticMethodCount());
					logger.info("GLOBAL METHOD COUNT: " + parser.action_obj.getGlobalMethodCount());
					logger.info("CODE BLOCK COUNT: " + parser.action_obj.getCodeBlockCount());
					logger.info("MAIN METHOD CALL COUNT: " + parser.action_obj.getMainMethodCalls());
					logger.info("FORMAL ARGUMENTS COUNT: " + parser.action_obj.getFormalArgumentsCount());
					logger.info("CLASS DEFINITION COUNT: " + parser.action_obj.getClassDefinitionCount());
					logger.info("CLASS NON STATIC METHOD COUNT: " + parser.action_obj.getNonStaticMethodCount());
					logger.info("CLASS VARIABLE COUNT: " + parser.action_obj.getClassVariableCount());
				} catch (LexerException le) {
					if (out != null) {
						out.println(le);
					}
					logger.info(le);
				} catch (Exception e) {
					logger.info(e);
				}
			} catch (FileNotFoundException fnfe) {
				// TODO: handle exception
			} catch (IOException ioe) {
				// TODO: handle exception
			} 
		}
		
		out.close();
	}
}