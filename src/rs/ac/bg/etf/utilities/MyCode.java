package rs.ac.bg.etf.utilities;

import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class MyCode extends Code {
	public static void putTrueJump(int op, int adr) {
		put(jcc + op); put2(adr - pc + 1);
	}
	
	public static void putCall(int adr) {
		put(call); put2(adr - pc + 1);
	}
}
