package rs.ac.bg.etf.utilities;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class MyObj extends Obj {
	public static final int Static = 6;

	public MyObj(int kind, String name, Struct type, int adr, int level) {
		super(kind, name, type, adr, level);
		// TODO Auto-generated constructor stub
	}

	public MyObj(int kind, String name, Struct type) {
		super(kind, name, type);
		// TODO Auto-generated constructor stub
	}
}
