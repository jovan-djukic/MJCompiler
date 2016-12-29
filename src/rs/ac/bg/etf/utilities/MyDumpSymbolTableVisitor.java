package rs.ac.bg.etf.utilities;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class MyDumpSymbolTableVisitor extends DumpSymbolTableVisitor {
	@Override
	public void visitObjNode(Obj objToVisit) {
		output.append("[");
		super.visitObjNode(objToVisit);
		output.append("]");
	}
}
