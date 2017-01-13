package rs.ac.bg.etf.utilities;

import rs.etf.pp1.mj.runtime.Code;

public class MyCode extends Code {
	public static void putTrueJump(int op, int adr) {
		put(jcc + op); put2(adr - pc + 1);
	}
}
