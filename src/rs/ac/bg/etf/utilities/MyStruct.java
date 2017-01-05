package rs.ac.bg.etf.utilities;

import java.util.Map;

import rs.ac.bg.etf.pp1.SymbolTable;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.HashTableDataStructure;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class MyStruct extends Struct {

	public MyStruct(int kind, Struct elemType) {
		super(kind, elemType);
		// TODO Auto-generated constructor stub
		if (elemType.getKind() == Struct.Class) {
			this.setElementType(elemType);
		}
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
	
	@Override
	public void setMembers(SymbolDataStructure symbols) {
		SymbolDataStructure currentMembers = super.getMembersTable();
		SymbolDataStructure newMembers = new HashTableDataStructure();
		
		//First add fields
		for (Obj object : currentMembers.symbols()) {
			if (object.getKind() == MyObj.Fld) {
				newMembers.insertKey(object);
			}
		}
		
		if (symbols != null) {
			for (Obj object : symbols.symbols()) {
				if (object.getKind() == MyObj.Fld) {
					newMembers.insertKey(object);
				}
 			}
		}
		
		//Set new members with fields only so you have an exact field number
		super.setMembers(newMembers);
		newMembers = super.getMembersTable();
		
		//Add static and non-static methods
		for (Obj object : currentMembers.symbols()) {
			if (object.getKind() != MyObj.Fld) {
				newMembers.insertKey(object);
			}
		}
		
		if (symbols != null) {
			for (Obj object : symbols.symbols()) {
				if (object.getKind() != MyObj.Fld) {
					newMembers.insertKey(object);
				}
 			}
		}
	}
	
	@Override
	public boolean equals(Struct other) {
		if (SymbolTable.getTypeName(other).equals(SymbolTable.getTypeName(this))) {
			return true;
		} else if (this.getKind() == Array) {
			return other.getKind() == Array && this.getElemType().equals(other.getElemType());
		} else {
			return this == other;
		}
	}
	
}
