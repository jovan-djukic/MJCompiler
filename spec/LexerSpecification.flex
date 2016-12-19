/* User code section of lexer specification, copied verbatim */

// import java_cup.runtime because we are using the symbol calss from CUP

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
	private Symbol newSymbol(in type) {
		return new Symbol(tipe, yyline, yycolumn);
	}
	
	private Symbol newSymbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
%}

%eofval{
	return newSymbol(sym.EOF);
%eofval}

newLine = \r\n
whiteSpace = " "|\t|newLine|\b|\f
digit = [0-9]
leter = [a-zA-Z]

identifier = letter(letter|digit|_)
numericalConstant = digit*
booleanConstant = ("true"|"false")


%xstate COMMENT, CHAR_CONSTANT

%debug

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

{identifier}		{ return newSymbol(sym.IDENTIFIER, yytext()); }
{numericalConstant}	{ return newSymbol(sym.NUMBERICAL_CONSTANT, new Integer(yytext())); }
{booleanConstant}	{ return newSymbol(sym.BOOLEAN_CONSTANT, new Boolean(yytext())); }

\" 	{ 
		currentString.setLength(0);
		yybegin(CHAR_CONSTANT);
	}

<CHAR_CONSTANT> {
	.\"	{
			yybegin(YYINITIAL);
			return newSymbol(sym.CHAR_CONSTANT, new Character(yytext.charAt(0)));
		}
}

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

.	{ throw new LexerException("Error. Unkown token: " + yytext() + " at line: " + (yyline + 1) + ", at column: " + (yycolumn + 1)); }









