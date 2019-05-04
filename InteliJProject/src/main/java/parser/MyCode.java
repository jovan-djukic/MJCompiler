package parser;

import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class MyCode extends Code {
	public static void putTrueJump ( int testOperation, int address ) {
		Code.put ( Code.jcc + testOperation );
		Code.put2 ( address - Code.pc + 1 );
	}
	
	public static void putCall ( int address ) {
		Code.put ( Code.call );
		Code.put2 ( address - Code.pc + 1 );
	}
}
