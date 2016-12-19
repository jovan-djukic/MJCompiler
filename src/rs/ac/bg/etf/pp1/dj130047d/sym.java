package rs.ac.bg.etf.pp1.dj130047d;

/*
 * Names are taken from https://en.wikipedia.org/wiki/List_of_symbols
 */

public class sym {
	
	public static class LexerException extends Exception {
		private String message = null;
		
		public LexerException(String message) {
			this.message = message;
		}
		
		public String toString() {
			return message;
		}
	}

	//Keywords
	public static int PROGRAM = 1;	// program
	public static int BREAK = 2; 	// break
	public static int CLASS = 3; 	// class
	public static int ELSE = 4; 	// else
	public static int CONST = 5; 	// const
	public static int IF = 6; 		// if
	public static int NEW = 7; 		// new
	public static int PRINT = 8;	// print
	public static int READ = 9;		// read
	public static int RETURN = 10;	// return
	public static int VOID = 11;	// void
	public static int FOR = 12;		// for
	public static int EXTENDS = 13; // extends
	public static int CONTINUE = 14;// continue
	public static int STATIC = 15;	// static
	
	//Identifiers
	public static int IDENTIFIER = 16;// any identifier
	
	//Constants
	public static int NUMERICAL_CONSTANT = 17;		// numerical constants like 1235
	public static int CHARARACTER_CONSTANT = 18;	// character constants like "a"
	public static int BOOLEAN_CONSTANT = 19;		// boolean constants, true or false
	
	//Operators
	public static int PLUS = 20;					// +
	public static int MINUS = 21;					// -
	public static int TIMES = 22;					// *
	public static int SOLIDUS = 23; 				// /
	public static int PERCENT = 24; 				// %
	public static int EQUAL = 25;					// ==
	public static int NOT_EQUAL = 26;				// !=					
	public static int GREATER_THAN = 27;			// >
	public static int GREATER_OR_EQUAL_THAN = 28;	// >=
	public static int LESS_THAN = 29;				// <
	public static int LESS_OR_EQUAL_THAN = 30;		// <=
	public static int AND = 31;						// &&
	public static int OR = 32;						// ||
	public static int EQUALS = 33;					// =
	public static int PLUS_EQUALS = 34;				// +=
	public static int MINUS_EQUALS = 35;			// -=
	public static int TIMES_EQUALS = 36;			// *=
	public static int SOLIDUS_EQUALS = 37;			// /=
	public static int PERCENT_EQUALS = 38;			// %=
	public static int INCREMENT = 39;				// ++
	public static int DECREMENT = 40;				// --	
	public static int SEMICOLON = 41;				// ;
	public static int COMA = 42;					// ,
	public static int DOT = 43;						// .
	public static int LEFT_PARENTHESIS = 44;		// (
	public static int RIGHT_PARENTHESIS = 45;		// )
	public static int LEFT_BRACKET = 46;			// [
	public static int RIGHT_BRACKET = 47;			// ]
	public static int LEFT_BRACE = 48;				// {
	public static int RIGHT_BRACE = 49;				// }

	//End of file
	public static int EOF = 50;
}
