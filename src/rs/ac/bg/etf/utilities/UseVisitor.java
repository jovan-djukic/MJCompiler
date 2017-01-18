package rs.ac.bg.etf.utilities;

import rs.ac.bg.etf.pp1.SymbolTable;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class UseVisitor {
	
	
	public static String visitObjNode(Obj objToVisit) {
		StringBuilder output = new StringBuilder();
		
		output.append("[");
		switch (objToVisit.getKind()) {
		case Obj.Con:  output.append("Con "); break;
		case Obj.Var:  output.append("Var "); break;
		case Obj.Type: output.append("Type "); break;
		case Obj.Meth: output.append("Meth "); break;
		case Obj.Fld:  output.append("Fld "); break;
		case Obj.Elem: output.append("Elem "); break;
		case MyObj.Static: output.append("StaticMeth "); break;
		case MyObj.Global: output.append("Global "); break;
		}
		
		output.append(objToVisit.getName());
		output.append(": ");
		
		output.append(SymbolTable.getTypeName(objToVisit.getType()));
		
		if (objToVisit.getKind() == Obj.Type && objToVisit.getType().getKind() == Struct.Class) {
			output.append(", ");
			output.append(objToVisit.getType().getNumberOfFields());
		}
		
		output.append(", ");
		output.append(objToVisit.getAdr());
		output.append(", ");
		output.append(objToVisit.getLevel() + " ");
				
		/*if (objToVisit.getKind() == Obj.Prog || objToVisit.getKind() == Obj.Meth || objToVisit.getKind() == MyObj.Static || objToVisit.getKind() == MyObj.Global) {
			output.append("\n");
			nextIndentationLevel();
		}
		

		for (Obj o : objToVisit.getLocalSymbols()) {
			output.append(currentIndent.toString());
			o.accept(this);
			output.append("\n");
		}
		
		if (objToVisit.getKind() == Obj.Prog || objToVisit.getKind() == Obj.Meth || objToVisit.getKind() == MyObj.Static || objToVisit.getKind() == MyObj.Global) {
			previousIndentationLevel();
			output.append(currentIndent.toString());
		}*/

		output.append("]");
		
		return output.toString();
	}

}
