/* JFlex example: partial Java language lexer specification */
package org.ifn660.jflexer;

import java_cup.runtime.*;

/**
 * This class is a simple example lexer.
 */
 
%%

%public
%class Lexer
%cup    // switches to CUP compatibility mode to interface with a CUP generated parser.
%line   //switches line counting on (the current line number can be accessed via the variable yyline)
%column //switches column counting on (the current column is accessed via yycolumn)
%state STRING

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*



%%


/* keywords */
<YYINITIAL> "abstract"           { return symbol(sym.ABSTRACT, yytext()); }
<YYINITIAL> "assert"             { return symbol(sym.ASSERT); }
<YYINITIAL> "boolean"            { return symbol(sym.BOOLEAN); }
<YYINITIAL> "break"              { return symbol(sym.BREAK); }
<YYINITIAL> "byte"               { return symbol(sym.BYTE); }
<YYINITIAL> "case"               { return symbol(sym.CASE); }
<YYINITIAL> "catch"              { return symbol(sym.CATCH); }
<YYINITIAL> "char"               { return symbol(sym.CHAR); }
<YYINITIAL> "class"              { return symbol(sym.CLASS); }
<YYINITIAL> "const"              { return symbol(sym.CONST); }
<YYINITIAL> "continue"           { return symbol(sym.CONTINUE); }
<YYINITIAL> "default"            { return symbol(sym.DEFAULT); }
<YYINITIAL> "do"                 { return symbol(sym.DO); }
<YYINITIAL> "double"             { return symbol(sym.DOUBLE); }
<YYINITIAL> "else"               { return symbol(sym.ELSE); }
<YYINITIAL> "enum"               { return symbol(sym.ENUM); }
<YYINITIAL> "extends"            { return symbol(sym.EXTENDS); }
<YYINITIAL> "final"              { return symbol(sym.FINAL, yytext()); }
<YYINITIAL> "finally"            { return symbol(sym.FINALLY); }
<YYINITIAL> "float"              { return symbol(sym.FLOAT); }
<YYINITIAL> "for"                { return symbol(sym.FOR); }
<YYINITIAL> "goto"               { return symbol(sym.GOTO); }
<YYINITIAL> "if"                 { return symbol(sym.IF); }
<YYINITIAL> "implements"         { return symbol(sym.IMPLEMENTS); }
<YYINITIAL> "import"             { return symbol(sym.IMPORT); }
<YYINITIAL> "instanceof"         { return symbol(sym.INSTANCEOF); }
<YYINITIAL> "int"                { return symbol(sym.INT, yytext()); }
<YYINITIAL> "interface"          { return symbol(sym.INTERFACE); }
<YYINITIAL> "long"               { return symbol(sym.LONG); }
<YYINITIAL> "native"             { return symbol(sym.NATIVE); }
<YYINITIAL> "new"                { return symbol(sym.NEW); }
<YYINITIAL> "package"            { return symbol(sym.PACKAGE); }
<YYINITIAL> "private"            { return symbol(sym.PRIVATE, yytext()); }
<YYINITIAL> "protected"          { return symbol(sym.PROTECTED, yytext()); }
<YYINITIAL> "public"             { return symbol(sym.PUBLIC, yytext()); }
<YYINITIAL> "return"             { return symbol(sym.RETURN); }
<YYINITIAL> "short"              { return symbol(sym.SHORT); }
<YYINITIAL> "static"             { return symbol(sym.STATIC, yytext()); }
<YYINITIAL> "strictfp"           { return symbol(sym.STRICTFP, yytext()); }
<YYINITIAL> "super"              { return symbol(sym.SUPER); }
<YYINITIAL> "switch"             { return symbol(sym.SWITCH); }
<YYINITIAL> "synchronized"       { return symbol(sym.SYNCHRONIZED); }
<YYINITIAL> "this"               { return symbol(sym.THIS); }
<YYINITIAL> "throw"              { return symbol(sym.THROW); }
<YYINITIAL> "throws"             { return symbol(sym.THROWS); }
<YYINITIAL> "transient"          { return symbol(sym.TRANSIENT); }
<YYINITIAL> "try"                { return symbol(sym.TRY); }
<YYINITIAL> "void"               { return symbol(sym.VOID, yytext()); }
<YYINITIAL> "volatile"           { return symbol(sym.VOLATILE); }
<YYINITIAL> "while"              { return symbol(sym.WHILE); }


<YYINITIAL> {

/* identifiers */ 
  {Identifier}                   { return symbol(sym.IDENTIFIER, yytext()); }
 
 
/* literals */
  {DecIntegerLiteral}            { return symbol(sym.INTEGER_LITERAL,new Integer(yytext())); }
  \"                             { string.setLength(0); yybegin(STRING); }


/* separators */
  "("                            { return symbol(sym.LPAREN); }
  ")"                            { return symbol(sym.RPAREN); }
  "{"                            { return symbol(sym.LBRACE); }
  "}"                            { return symbol(sym.RBRACE); }
  "["                            { return symbol(sym.LBRACK); }
  "]"                            { return symbol(sym.RBRACK); }
  ";"                            { return symbol(sym.SEMICOLON); }
  ","                            { return symbol(sym.COMMA); }
  "."                            { return symbol(sym.DOT); }


  /* operators */
  "="                            { return symbol(sym.OP_EQ, yytext()); }
  ">"                            { return symbol(sym.OP_GT, yytext()); }
  "<"                            { return symbol(sym.OP_LT, yytext()); }
  "!"                            { return symbol(sym.OP_NOT_EQ, yytext()); }
  "~"                            { return symbol(sym.OP_UNARY, yytext()); } //INVERTS BITS
  "?"                            { return symbol(sym.OP_TERNARY, yytext()); } // BOOLEAN STATEMENT EVALUATION
  ":"                            { return symbol(sym.OP_LOOP_IN, yytext()); } // FOR LOOP OPERATOR, i.e. FOR EACH ELEMENT E "IN" ELEMENTS
  "->"                           { return symbol(sym.OP_LAMBDA, yytext()); }
  "=="                           { return symbol(sym.OP_EQ_EQ, yytext()); }
  ">="                           { return symbol(sym.OP_GT_EQ, yytext()); }
  "<="                           { return symbol(sym.OP_LT_EQ, yytext()); }
  "!="                           { return symbol(sym.OP_NOT_EQ, yytext()); }
  "&&"                           { return symbol(sym.OP_LOGIC_AND, yytext()); }
  "||"                           { return symbol(sym.OP_LOGIC_OR, yytext()); }
  "++"                           { return symbol(sym.OP_POS_INCR, yytext()); }
  "--"                           { return symbol(sym.OP_NEG_INCR, yytext()); }
  "+"                            { return symbol(sym.OP_PLUS, yytext()); }
  "-"                            { return symbol(sym.OP_MINUS, yytext()); } // note: this will need to be differentiated from a negative value
  "*"                            { return symbol(sym.OP_MULTIPLY, yytext()); }
  "/"                            { return symbol(sym.OP_DIVIDE, yytext()); }
  "&"                            { return symbol(sym.OP_AND, yytext()); }
  "|"                            { return symbol(sym.OP_OR, yytext()); }
  "^"                            { return symbol(sym.OP_XOR, yytext()); }
  "%"                            { return symbol(sym.OP_MODULUS, yytext()); }
  "<<"                           { return symbol(sym.OP_BIT_L_SHIFT, yytext()); }
  ">>"                           { return symbol(sym.OP_BIT_R_SHIFT, yytext()); }
  ">>>"                          { return symbol(sym.OP_UNSIGN_R_SHIFT, yytext()); }
  "+="                           { return symbol(sym.OP_ADD_AND, yytext()); }
  "-="                           { return symbol(sym.OP_MINUS_AND, yytext()); }
  "*="                           { return symbol(sym.OP_MULTIPLY_AND, yytext()); }
  "/="                           { return symbol(sym.OP_DIVIDE_AND, yytext()); }
  "&="                           { return symbol(sym.OP_BIT_AND, yytext()); }
  "|="                           { return symbol(sym.OP_BIT_INCL_OR, yytext()); } // BITWISE INCLUSIVE OR
  "^="                           { return symbol(sym.OP_BIT_EXCL_OR, yytext()); }
  "%="                           { return symbol(sym.OP_MODULUS_AND, yytext()); }
  "<<="                          { return symbol(sym.OP_L_SHIFT_AND, yytext()); }
  ">>="                          { return symbol(sym.OP_R_SHIFT_AND, yytext()); }
  ">>>="                         { return symbol(sym.OP_UNSIGN_R_SHIFT_AND, yytext()); }

  /* comments */
  {Comment}                      { /* ignore */ }
 
 
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
  
}


<STRING> {
  \"                             { yybegin(YYINITIAL); 
                                   return symbol(sym.STRING_LITERAL, 
                                   string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }
  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\                             { string.append('\\'); }
}

<<EOF>> {return symbol(sym.EOF);}
/* error fallback */
[^]                              { return symbol(sym.UNKNOWN); }