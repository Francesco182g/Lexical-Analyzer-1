// Circuit.lex
//
// Description of lexer for circuit description language.
//
// Ian Stark

import java_cup.runtime.Symbol; 		//This is how we pass tokens to the parser

%%

										// Declarations for JFlex
%unicode 								// We wish to read text files
%cupsym CircuitSym
%cup 									// Declare that we expect to use Java CUP

										// Abbreviations for regular expressions
whitespace = [ \n\r\t]
digit = [0-9]
test = "/*" [^*] 
keyword = (if)|(then)|(else)|(while)|(for)
letter = [a-zA-Z]
number = {digit}+
value1 = {number}("."{number})?
id = {letter}+(({number}*)({letter}*))*
relop = <|>|<=|>=|=|<--
delimiters = "("|")"|"["|"]"

%%

										// Now for the actual tokens and assocated actions
"seq" 		{ return new Symbol(CircuitSym.SEQ); }
"par" 		{ return new Symbol(CircuitSym.PAR); }
"end" 		{ return new Symbol(CircuitSym.END); }
{keyword}	{ return new Symbol(CircuitSym.KEYWORD,yytext()); }
{id}	{ return new Symbol(CircuitSym.ID,yytext()); }
{relop} 	{ return new Symbol(CircuitSym.RELOP,yytext()); }
{delimiters} { return new Symbol(CircuitSym.DELIMITERS,yytext()); }
{value1} 	{ return new Symbol(CircuitSym.RESISTOR,yytext()); }
{whitespace} { /* ignore */ }
[^]			{ return new Symbol(CircuitSym.error);}

<<EOF>> {return new Symbol(CircuitSym.EOF);} 

