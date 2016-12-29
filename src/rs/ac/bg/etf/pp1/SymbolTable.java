package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SymbolTable extends Tab{
	public static Struct boolType = null;
	
	public static void init() {
		Tab.init();
		boolType = new Struct(Struct.Bool);
		currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
	
	public static String getBasicTypeName(int basicType) {
		switch (basicType) {
			case Struct.Int: {
				return "int";
			}
			case Struct.Char: {
				return "char";
			}
			case Struct.Bool: {
				return "bool";
			}
			case Struct.Array: {
				return "array";
			}
			case Struct.Class: {
				return "class";
			}
			case Struct.None: {
				return "none";
			}
			default: {
				return "";
			}
		}
	}
}
