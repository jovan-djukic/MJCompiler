package rs.ac.bg.etf.utilities;

import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class MyStruct extends Struct {

	public MyStruct(int kind, Struct elemType) {
		super(kind, elemType);
		// TODO Auto-generated constructor stub
	}

	public MyStruct(int kind, SymbolDataStructure members) {
		super(kind, members);
		// TODO Auto-generated constructor stub
	}

	public MyStruct(int kind) {
		super(kind);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean assignableTo(Struct dest) {
		if (super.assignableTo(dest)) {
			return true;
		} else { //This for class extension
			for(Struct struct = this.getElemType(); struct != null; struct = struct.getElemType()) {
				if (struct.equals(dest)) {
					return true;
				}
			}
		}
		return false;
	}
}
