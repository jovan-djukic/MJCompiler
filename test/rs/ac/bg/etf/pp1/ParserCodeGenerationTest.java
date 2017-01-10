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
import rs.ac.bg.etf.utilities.MyDumpSymbolTableVisitor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.mj.runtime.Run;
import rs.etf.pp1.mj.runtime.disasm;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class ParserCodeGenerationTest {
	private static String testProgramsDirectoryPath = "MJTestPrograms/CodeGeneration";
	private static Logger logger = MyLoggerFactory.getLogger(ParserCodeGenerationTest.class);
	
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
				
				try {
					Parser parser = new Parser(new Lexer(in));
					symbol = parser.parse();
					
					logger.info("SYMBOL IS: " + symbol.sym);
					SymbolTable.dump(new MyDumpSymbolTableVisitor());
					
					if (parser.isErrorFound()) {
						logger.info("Error found, no code generated");
					} else {
						File objectFile = new File("output/MJProgram");
						if (objectFile.exists()) {
							objectFile.delete();
						}
						Code.write(new FileOutputStream(objectFile));
						disasm.main(new String[] { "output/MJProgram" });
						
						logger.info("======================================================================");
						logger.info("RUNNING FILE: " + testFiles[i].getPath());
						logger.info("======================================================================");
						
						Run.main(new String[] { "output/MJProgram" });
						System.out.println();
						
						logger.info("======================================================================");
						logger.info("END OF FILE: " + testFiles[i].getPath());
						logger.info("======================================================================");
					}
				} catch (LexerException le) {
					if (out != null) {
						out.println(le);
					}
					logger.info(le);
				} catch (Exception e) {
					logger.info(e);
					e.printStackTrace();
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
