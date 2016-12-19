package rs.ac.bg.etf.pp1.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java_cup.runtime.Symbol;

public class LexerTest {
	
	private static String testProgramsDirectoryPath = "MJTestPrograms";
	
	public static void main(String[] args) {
		PrintWriter out = null;
		if (args.length == 2 && "-f".equals(args[0])) {
			File outputFile = new File("output/" + args[1]);
			try {
				if (!outputFile.exists() && !outputFile.createNewFile()) {
					out = new PrintWriter(System.out);
				} else {
					out = new PrintWriter(new FileOutputStream(outputFile));
				}
			} catch (IOException e) {
				out = new PrintWriter(System.out);
			} 
		} else {
			out = new PrintWriter(System.out);
		}
		File file = new File(testProgramsDirectoryPath);
		File[] testFiles = file.listFiles();
		
		for (int i = 0; i < testFiles.length; i++) {
			
			out.println("======================================================================");
			out.println("FILE: " + testFiles[i].getPath());
			out.println("======================================================================");
			
			try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(testFiles[i])));) {
				Symbol symbol = null;
				Lexer lexer = new Lexer(in);
				try {
					while ((symbol = lexer.next_token()).sym != sym.EOF) {
						out.println("TYPE: " + symbol.sym + ", SIMBOL: " + symbol.value);
					}
				} catch (LexerException le) {
					out.println(le);
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
