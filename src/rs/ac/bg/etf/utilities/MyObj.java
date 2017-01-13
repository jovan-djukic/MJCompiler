package rs.ac.bg.etf.utilities;

import java.util.Iterator;
import java.util.List;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.HashTableDataStructure;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class MyObj extends Obj {
	public static final int Static = 7;
	public static final int Global = 8;
	
	public MyObj(int kind, String name, Struct type, int adr, int level) {
		super(kind, name, type, adr, level);
		// TODO Auto-generated constructor stub
	}

	public MyObj(int kind, String name, Struct type) {
		super(kind, name, type);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return true;
		} else if(o instanceof Obj) {
			Obj other = (Obj) o;
			if (other.getName().equals(this.getName()) && other.getKind() == MyObj.Meth && this.getKind() == MyObj.Meth && other.getLevel() == this.getLevel()) {
				Iterator<Obj> otherIterator = other.getLocalSymbols().iterator();
				Iterator<Obj> thisIterator = other.getLocalSymbols().iterator();

				//Skip this
				otherIterator.next();
				thisIterator.next();
				
				for (int i = 0; i < this.getLevel() - 1; i++) {
					if(!otherIterator.next().getType().equals(thisIterator.next().getType())) {
						return false;
					}
				}
				
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
