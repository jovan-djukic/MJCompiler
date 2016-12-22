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
import rs.ac.bg.etf.pp1.Lexer;
import rs.ac.bg.etf.pp1.LexerException;
import rs.ac.bg.etf.pp1.sym_lexer;
import rs.ac.bg.etf.pp1.utilities.MyLoggerFactory;

public class LexerTest {
	
	private static String testProgramsDirectoryPath = "MJTestPrograms";
	private static Logger logger = MyLoggerFactory.getLogger(LexerTest.class);
	
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
				
				while (true) {
					try {
						symbol = lexer.next_token();
						if (symbol.sym == sym_lexer.EOF) {
							break;
						} else {
							if (out != null) {
								out.println("TYPE: " + symbol.sym + ", SIMBOL: " + symbol.value);
							}
							logger.info("TYPE: " + symbol.sym + ", SIMBOL: " + symbol.value);
						}
					} catch (LexerException le) {
						if (out != null) {
							out.println(le);
						}
						logger.info(le);
					}
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
