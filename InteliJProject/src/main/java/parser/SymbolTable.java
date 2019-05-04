package parser;


import java.util.Iterator;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.HashTableDataStructure;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class SymbolTable extends Tab {
	public static final Struct boolType   = new MyStruct ( Struct.Bool );
	public static final Struct voidType   = new MyStruct ( Struct.None );
	public static final Obj    voidObject = new Obj ( Obj.Var, "voidObject", voidType );
	
	public static String CHR = "chr";
	public static String ORD = "ord";
	public static String LEN = "len";
	
	private static void addPredefinedFunction ( String name ) {
		Obj predefinedFunction = SymbolTable.find ( name );
		Tab.currentScope ( ).getLocals ( ).deleteKey ( predefinedFunction.getName ( ) );
		MyObj               newPredefinedFunction = new MyObj ( MyObj.Global, predefinedFunction.getName ( ), predefinedFunction.getType ( ), predefinedFunction.getAdr ( ), predefinedFunction.getLevel ( ) );
		SymbolDataStructure dataStructure         = new HashTableDataStructure ( );
		for ( Obj parameter : predefinedFunction.getLocalSymbols ( ) ) {
			dataStructure.insertKey ( parameter );
		}
		newPredefinedFunction.setLocals ( dataStructure );
		Tab.currentScope.addToLocals ( newPredefinedFunction );
	}
	
	public static void init ( ) {
		Tab.init ( );
		
		//change the type of predefined functions
		SymbolTable.addPredefinedFunction ( SymbolTable.CHR );
		//Obj chr = SymbolTable.find ( CHR );
		//SymbolTable.Tab.currentScope ( ).getLocals ( ).deleteKey ( chr.getName ( ) );
		//MyObj               newChr           = new MyObj ( MyObj.Global, chr.getName ( ), chr.getType ( ), chr.getAdr ( ), chr.getLevel ( ) );
		//SymbolDataStructure chrDataStructure = new HashTableDataStructure ( );
		//for ( Obj parameter : chr.getLocalSymbols ( ) ) {
		//	chrDataStructure.insertKey ( parameter );
		//}
		//newChr.setLocals ( chrDataStructure );
		//Tab.currentScope.addToLocals ( newChr );
		
		SymbolTable.addPredefinedFunction ( ORD );
		//Obj ord = SymbolTable.find ( ORD );
		//SymbolTable.Tab.currentScope ( ).getLocals ( ).deleteKey ( ord.getName ( ) );
		//MyObj               newOrd           = new MyObj ( MyObj.Global, ord.getName ( ), ord.getType ( ), ord.getAdr ( ), ord.getLevel ( ) );
		//SymbolDataStructure ordDataStructure = new HashTableDataStructure ( );
		//for ( Obj parameter : ord.getLocalSymbols ( ) ) {
		//	ordDataStructure.insertKey ( parameter );
		//}
		//newOrd.setLocals ( ordDataStructure );
		//Tab.currentScope.addToLocals ( newOrd );
		
		SymbolTable.addPredefinedFunction ( LEN );
		//Obj len = SymbolTable.find ( LEN );
		//SymbolTable.Tab.currentScope ( ).getLocals ( ).deleteKey ( len.getName ( ) );
		//MyObj               newLen           = new MyObj ( MyObj.Global, len.getName ( ), len.getType ( ), len.getAdr ( ), len.getLevel ( ) );
		//SymbolDataStructure lenDataStructure = new HashTableDataStructure ( );
		//for ( Obj parameter : len.getLocalSymbols ( ) ) {
		//	lenDataStructure.insertKey ( parameter );
		//}
		//newLen.setLocals ( lenDataStructure );
		//Tab.currentScope.addToLocals ( newLen );
		
		Tab.currentScope.addToLocals ( new Obj ( Obj.Type, "bool", boolType ) );
		Tab.currentScope.addToLocals ( new Obj ( Obj.Type, "void", voidType ) );
	}
	
	private static int currentLevel = -1;
	
	public static int getCurrentLevel ( ) {
		return SymbolTable.currentLevel;
	}
	
	
	public static void openScope ( ) {
		Tab.openScope ( );
		SymbolTable.currentLevel++;
	}
	
	public static void closeScope ( ) {
		Tab.closeScope ( );
		SymbolTable.currentLevel--;
	}
	
	public static String getBasicTypeName ( Struct type ) {
		switch ( type.getKind ( ) ) {
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
				String name = "Array of ";
				switch ( type.getElemType ( ).getKind ( ) ) {
					case Struct.Int: {
						return name + "int";
					}
					case Struct.Char: {
						return name + "char";
					}
					case Struct.Bool: {
						return name + "bool";
					}
					case Struct.Class: {
						return name + SymbolTable.getClassTypeName ( type.getElemType ( ) );
					}
					case Struct.None: {
						return name + "void";
					}
					default: {
						return "";
					}
				}
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
	
	public static String getClassTypeName ( Struct classType ) {
		for ( Scope scope = Tab.currentScope; scope != null; scope = scope.getOuter ( ) ) {
			for ( Obj topObject : scope.values ( ) ) {
				if ( topObject.getType ( ) == classType && topObject.getKind ( ) == Obj.Type ) {
					return topObject.getName ( );
				}
				if ( topObject.getLocalSymbols ( ).size ( ) > 0 ) {
					Iterator<Obj> iterator = topObject.getLocalSymbols ( ).iterator ( );
					while ( iterator.hasNext ( ) ) {
						Obj currentObject = iterator.next ( );
						if ( currentObject.getType ( ) == classType && currentObject.getKind ( ) == Obj.Type ) {
							return currentObject.getName ( );
						}
					}
				}
			}
		}
		
		return "";
	}
	
	public static String getTypeName ( Struct typeNode ) {
		if ( typeNode.getKind ( ) == Struct.Class ) {
			return SymbolTable.getClassTypeName ( typeNode );
		} else {
			return SymbolTable.getBasicTypeName ( typeNode );
		}
	}
	
	
	public static Obj insert ( int kind, String name, Struct type ) {
		// create a new Object node with kind, name, type
		Obj newObj = new MyObj ( kind, name, type, 0, ( ( SymbolTable.currentLevel != 0 ) ? 1 : 0 ) );
		
		// append the node to the end of the symbol list
		if ( !Tab.currentScope.addToLocals ( newObj ) ) {
			Obj res = Tab.currentScope.findSymbol ( name );
			return ( res != null ) ? res : noObj;
		} else { return newObj; }
	}
}
