package parser;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class MyDumpSymbolTableVisitor extends DumpSymbolTableVisitor {
	
	private       StringBuilder output        = new StringBuilder ( );
	private final String        indent        = "   ";
	private       StringBuilder currentIndent = new StringBuilder ( );
	
	protected void nextIndentationLevel ( ) {
		this.currentIndent.append ( this.indent );
	}
	
	protected void previousIndentationLevel ( ) {
		if ( this.currentIndent.length ( ) > 0 ) {
			this.currentIndent.setLength ( this.currentIndent.length ( ) - this.indent.length ( ) );
		}
	}
	
	@Override
	public void visitObjNode ( Obj objectToVisit ) {
		this.output.append ( "[" );
		
		switch ( objectToVisit.getKind ( ) ) {
			case Obj.Con: {
				this.output.append ( "Con " );
				break;
			}
			case Obj.Var: {
				this.output.append ( "Var " );
				break;
			}
			case Obj.Type: {
				this.output.append ( "Type " );
				break;
			}
			case Obj.Meth: {
				this.output.append ( "Meth " );
				break;
			}
			case Obj.Fld: {
				this.output.append ( "Fld " );
				break;
			}
			case Obj.Prog: {
				this.output.append ( "Prog " );
				break;
			}
			case MyObj.Static: {
				this.output.append ( "StaticMeth " );
				break;
			}
			case MyObj.Global: {
				this.output.append ( "Global " );
				break;
			}
		}
		
		this.output.append ( objectToVisit.getName ( ) );
		this.output.append ( ": " );
		
		if ( ( Obj.Var == objectToVisit.getKind ( ) ) && "this".equalsIgnoreCase ( objectToVisit.getName ( ) ) ) {
			this.output.append ( "THIS_POINTER" );
		} else if ( objectToVisit.getKind ( ) == MyObj.Type ) {
			objectToVisit.getType ( ).accept ( this );
		} else {
			this.output.append ( SymbolTable.getTypeName ( objectToVisit.getType ( ) ) );
		}
		
		if ( objectToVisit.getKind ( ) == Obj.Type && objectToVisit.getType ( ).getKind ( ) == Struct.Class ) {
			this.output.append ( ", " );
			this.output.append ( objectToVisit.getType ( ).getNumberOfFields ( ) );
		}
		
		this.output.append ( ", " );
		this.output.append ( objectToVisit.getAdr ( ) );
		this.output.append ( ", " );
		this.output.append ( objectToVisit.getLevel ( ) + " " );
		
		if ( objectToVisit.getKind ( ) == Obj.Prog || objectToVisit.getKind ( ) == Obj.Meth || objectToVisit.getKind ( ) == MyObj.Static || objectToVisit.getKind ( ) == MyObj.Global ) {
			this.output.append ( "\n" );
			this.nextIndentationLevel ( );
		}
		
		
		for ( Obj o : objectToVisit.getLocalSymbols ( ) ) {
			this.output.append ( this.currentIndent.toString ( ) );
			o.accept ( this );
			this.output.append ( "\n" );
		}
		
		if ( objectToVisit.getKind ( ) == Obj.Prog || objectToVisit.getKind ( ) == Obj.Meth || objectToVisit.getKind ( ) == MyObj.Static || objectToVisit.getKind ( ) == MyObj.Global ) {
			this.previousIndentationLevel ( );
			this.output.append ( this.currentIndent.toString ( ) );
		}
		
		this.output.append ( "]" );
		
	}
	
	@Override
	public void visitScopeNode ( Scope scope ) {
		for ( Obj o : scope.values ( ) ) {
			o.accept ( this );
			this.output.append ( "\n" );
		}
	}
	
	
	public String getOutput ( ) {
		return this.output.toString ( );
	}
	
	@Override
	public void visitStructNode ( Struct structToVisit ) {
		switch ( structToVisit.getKind ( ) ) {
			case Struct.None: {
				this.output.append ( "void" );
				break;
			}
			case Struct.Int: {
				this.output.append ( "int" );
				break;
			}
			case Struct.Char: {
				this.output.append ( "char" );
				break;
			}
			case Struct.Bool: {
				this.output.append ( "bool" );
				break;
			}
			case Struct.Array: {
				this.output.append ( "Arr of " );
				
				switch ( structToVisit.getElemType ( ).getKind ( ) ) {
					case Struct.None: {
						this.output.append ( "notype" );
						break;
					}
					case Struct.Int: {
						this.output.append ( "int" );
						break;
					}
					case Struct.Char: {
						this.output.append ( "char" );
						break;
					}
					case Struct.Bool: {
						this.output.append ( "bool" );
						break;
					}
					case Struct.Class: {
						this.output.append ( "Class" );
						break;
					}
				}
				
				break;
			}
			case Struct.Class: {
				this.output.append ( "Class" );
				if ( structToVisit.getElemType ( ) != null ) {
					this.output.append ( ", parent is " + SymbolTable.getClassTypeName ( structToVisit.getElemType ( ) ) + " " );
				}
				this.output.append ( "[\n" );
				this.nextIndentationLevel ( );
				
				for ( Obj obj : structToVisit.getMembers ( ) ) {
					this.output.append ( this.currentIndent.toString ( ) );
					obj.accept ( this );
					this.output.append ( "\n" );
				}
				this.previousIndentationLevel ( );
				this.output.append ( this.currentIndent.toString ( ) + "]" );
				break;
			}
		}
		
	}
}
