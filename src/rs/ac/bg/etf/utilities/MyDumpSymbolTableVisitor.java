package rs.ac.bg.etf.utilities;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class MyDumpSymbolTableVisitor extends DumpSymbolTableVisitor {
	@Override
	public void visitObjNode(Obj objToVisit) {
		output.append("[");
		super.visitObjNode(objToVisit);
		if (objToVisit.getKind() == Obj.Meth) {
			output.append(currentIndent.toString());
		}
		output.append("]");
	}
	
	@Override
	public void visitStructNode(Struct structToVisit) {
		switch (structToVisit.getKind()) {
		case Struct.None:
			output.append("void");
			break;
		case Struct.Int:
			output.append("int");
			break;
		case Struct.Char:
			output.append("char");
			break;
		case Struct.Bool: 
			output.append("bool");
			break;
		case Struct.Array:
			output.append("Arr of ");
			
			switch (structToVisit.getElemType().getKind()) {
			case Struct.None:
				output.append("notype");
				break;
			case Struct.Int:
				output.append("int");
				break;
			case Struct.Char:
				output.append("char");
				break;
			case Struct.Bool: 
				output.append("bool");
				break;
			case Struct.Class:
				output.append("Class");
				break;
			}
			break;
		case Struct.Class:
			output.append("Class [\n");
			nextIndentationLevel();
			for (Obj obj : structToVisit.getMembers()) {
				output.append(currentIndent.toString());
				obj.accept(this);
				output.append("\n");
			}
			previousIndentationLevel();
			output.append(currentIndent.toString() + "]");
			break;
		}

	}
}
