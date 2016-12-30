package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SymbolTable extends Tab{
	public static final Struct boolType = new Struct(Struct.Bool);;
	public static final Struct voidType = new Struct(Struct.None);;

	public static void init() {
		Tab.init();
		currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
	
	private static int currentLevel = -1;
	
	public static int getCurrentLevel() {
		return currentLevel;
	}
	

	public static void openScope() {
		Tab.openScope();
		currentLevel++;
	}
	
	public static void closeScope() {
		Tab.closeScope();
		currentLevel--;
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
				return "void";
			}
			default: {
				return "";
			}
		}
	}
}
