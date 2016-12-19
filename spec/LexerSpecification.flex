/* User code section of lexer specification, copied verbatim */

// import java_cup.runtime because we are using the symbol class from CUP

package rs.ac.bg.etf.pp1.main;
import java_cup.runtime.*;

%%

%class Lexer
%unicode
%cup

%char
%line
%column

%{
	StringBuilder currentString = null;
%}

%init{
	this.currentString = new StringBuilder();
%init}

%{
	private Symbol newSymbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	
	private Symbol newSymbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
%}

%yylexthrow LexerException

%eofval{
	return newSymbol(sym.EOF);
%eofval}

newLine = \r\n|\n
whiteSpaces = " "|\t|{newLine}|\b|\f
digit = [0-9]
letter = [a-zA-Z]

identifier = {letter}({letter}|{digit}|_)*
numericalConstant = {digit}+
booleanConstant = true|false
characterConstant = '.'

%xstate COMMENT, ERROR

%%

{whiteSpaces}	{ }

"program"	{ return newSymbol(sym.PROGRAM, yytext()); }
"break"		{ return newSymbol(sym.BREAK, yytext()); }
"class"		{ return newSymbol(sym.CLASS, yytext()); }
"else"		{ return newSymbol(sym.ELSE, yytext()); }
"const"		{ return newSymbol(sym.CONST, yytext()); }
"if"		{ return newSymbol(sym.IF, yytext()); }
"new"		{ return newSymbol(sym.NEW, yytext()); }
"print"		{ return newSymbol(sym.PRINT, yytext()); }
"read"		{ return newSymbol(sym.READ, yytext()); }
"return"	{ return newSymbol(sym.READ, yytext()); }
"void" 		{ return newSymbol(sym.VOID, yytext()); }
"for"		{ return newSymbol(sym.FOR, yytext()); }
"extends"	{ return newSymbol(sym.EXTENDS, yytext()); }
"static"	{ return newSymbol(sym.STATIC, yytext()); }

"+"		{ return newSymbol(sym.PLUS, yytext()); }
"-"		{ return newSymbol(sym.MINUS, yytext()); }
"*"		{ return newSymbol(sym.TIMES, yytext()); }
"/"		{ return newSymbol(sym.SOLIDUS, yytext()); }
"%"		{ return newSymbol(sym.PERCENT, yytext()); }
"=="	{ return newSymbol(sym.EQUAL, yytext()); }
"!="	{ return newSymbol(sym.NOT_EQUAL, yytext()); }
">"		{ return newSymbol(sym.GREATER_THAN, yytext()); }
">="	{ return newSymbol(sym.GREATER_OR_EQUAL_THAN, yytext()); }
"<"		{ return newSymbol(sym.LESS_THAN, yytext()); }
"<="	{ return newSymbol(sym.LESS_OR_EQUAL_THAN, yytext()); }
"&&"	{ return newSymbol(sym.AND, yytext()); }
"||"	{ return newSymbol(sym.OR, yytext()); }
"="		{ return newSymbol(sym.EQUALS, yytext()); }
"+="	{ return newSymbol(sym.PLUS_EQUALS, yytext()); }
"-="	{ return newSymbol(sym.MINUS_EQUALS, yytext()); }
"*="	{ return newSymbol(sym.TIMES_EQUALS, yytext()); }
"/="	{ return newSymbol(sym.SOLIDUS_EQUALS, yytext()); }
"%="	{ return newSymbol(sym.PERCENT_EQUALS, yytext()); }
"++"	{ return newSymbol(sym.INCREMENT, yytext()); }
"--"	{ return newSymbol(sym.DECREMENT, yytext()); }
";"		{ return newSymbol(sym.SEMICOLON, yytext()); }
","		{ return newSymbol(sym.COMA, yytext()); }
"."		{ return newSymbol(sym.DOT, yytext()); }
"("		{ return newSymbol(sym.LEFT_PARENTHESIS, yytext()); }
")"		{ return newSymbol(sym.RIGHT_PARENTHESIS, yytext()); }
"["		{ return newSymbol(sym.LEFT_BRACKET, yytext()); }
"]"		{ return newSymbol(sym.RIGHT_BRACKET, yytext()); }
"{"		{ return newSymbol(sym.LEFT_BRACE, yytext()); }
"}"		{ return newSymbol(sym.RIGHT_BRACE, yytext()); }

"//"	{ yybegin(COMMENT); }

<COMMENT> {
	.*{newLine} { yybegin(YYINITIAL); }
}

{numericalConstant}	{ return newSymbol(sym.NUMERICAL_CONSTANT, new Integer(yytext())); }
{booleanConstant}	{ return newSymbol(sym.BOOLEAN_CONSTANT, new Boolean(yytext())); }
{characterConstant}	{ return newSymbol(sym.CHARACTER_CONSTANT, Character.toString(yytext().charAt(2))); }
{identifier}		{ return newSymbol(sym.IDENTIFIER, yytext()); }

.	{ yybegin(ERROR); }

<ERROR> {
	.*{whiteSpaces} 	{ 
							yybegin(YYINITIAL);
							throw new LexerException("Error. Unknown token: " + yytext().substring(0, yytext().length() - 1) + " at line: " + (yyline + 1) + ", at column: " + (yycolumn + 1)); 
						}
}









