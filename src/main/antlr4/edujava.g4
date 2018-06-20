grammar edujava;

@header {
    package pl.edu.agh.generated;
}

root
    :   functionDeclarations catchEOF
    ;

catchEOF
    :   EOF
    ;

functionDeclarations
    :   functionDeclaration
    |   functionDeclarations functionDeclaration
    ;

semicolon
    : ';'
    ;

functionDeclaration
    :   mainFunction
    |   returnType name '(' variables? ')' '{' implementation? '}'
    ;

mainFunction //todo zmienic main na mainsym?
    :   VOIDSYM 'main' '()' '{' implementation? '}'
    ;

functionCall
    :   name '(' names? ')'
    ;

dataType
    :   arrayType
    |   singleDataType
    ;

singleDataType
    :   DOUBLESYM
    |   STRINGSYM
    |   BOOLEANSYM
    |   INTSYM
    ;

returnType
    :   dataType
    |   VOIDSYM
    ;

arrayType
    :   singleDataType '[]'
    ;

variables
    :   variable
    |   variables ',' variable
    ;

variable
    :   dataType name
    ;

names
    :   name
    |   names ',' name
    ;

name
    :   NAME
    ;

codeExpression
    :   ifstatement
    |   operation semicolon
    |   declaration semicolon
    |   loop
    |   returnStatement semicolon
    ;

returnStatement
    :   RETURNSYM getOrCalculateData
    ;

implementation
    :   codeExpression
    |   implementation codeExpression
    ;


declaration
    :   dataType? name assignmentExpression?
    ;

operation
    :   elementName assignmentExpression
    ;

assignmentExpression
    :   '=' multipleParenthesis
    |   '=' getData
    ;



operator
    :   PLUSSYM
    |   MINUSSYM
    |   DZIELENIESYM
    |   RAZYSYM
    ;

loop
    :   loopFor
    ;

loopFor
    :   FORSYM '(' declaration? semicolon condition? semicolon forUpdate? ')' '{' implementation? '}'
    ;

condition
    :   lessThen
    |   moreThen
    |   equal
    |   diffrent
    |   BOOLEAN
    ;
    //todo badz rowne

lessThen
    :   getOrCalculateData WIEKSZYSYM getOrCalculateData
    ;

moreThen
    :   getOrCalculateData MNIEJSZYSYM getOrCalculateData
    ;

equal
    :   getOrCalculateData ROWNOSCSYM getOrCalculateData
    ;

diffrent
    :   getOrCalculateData ROZNYSYM getOrCalculateData
    ;

forUpdate
    :   functionCall
    |   name assignmentExpression
    ;

arrayElement
    :   name '[' multipleParenthesis ']'
    ;

elementName
    :   arrayElement
    |   name
    ;

data
    :   INT #dataInt
    |   DOUBLE #dataDbl
    |   STRING #dataString
    |   BOOLEAN #dataBool
    ;

multipleParenthesis
    :   getOrCalculateData
    |   nestedParentheses
    |   nestedParentheses parenthesableOperation multipleParenthesis
    ;

parenthesableOperation
    :   operator
    |   MNIEJSZYSYM
    |   WIEKSZYSYM
    |   ROWNOSCSYM
    |   ROZNYSYM
    ;

nestedParentheses
    :   '(' ( multipleParenthesis | getOrCalculateData )* ')'
    ;

getOrCalculateData
    :   calculations
    ;

getData
    :   name
    |   functionCall
    |   data
    |   elementName
    ;

calculations
    :   getData
    |   calculation
    |   calculation calculationSym calculations
    ;

calculation
    :   plusMinusCalculations
    |   timesDivideCalculations
    ;

plusMinusCalculations
    :   getData op=(PLUSSYM | MINUSSYM) getData
    ;

timesDivideCalculations
    :   getData op=(RAZYSYM | DZIELENIESYM) getData
    ;

calculationSym
    :   PLUSSYM
    |   MINUSSYM
    |   RAZYSYM
    |   DZIELENIESYM
    ;

ifstatement
    :   IFSYM '(' condition ')' '{' implementation? '}'
    ;



ARRAYSYM : '[]';
INTSYM : 'int';
IFSYM : 'if';
RETURNSYM  : 'return';
ANDSYM  : 'and';
ORSYM  : 'or';
NOTSYM  : 'not';
DOUBLESYM : 'double';
STRINGSYM : 'string';
BOOLEANSYM : 'boolean';
VOIDSYM : 'void';
WHILESYM : 'while';
CONTINUESYM : 'continue';
FORSYM : 'for';
NEWSYM : 'new';
BREAKSYM : 'break';
ELSESYM : 'else';
FINALSYM : 'final';
STATICSYM : 'static';
PRZYPISANIESYM : '=';
ROWNOSCSYM : '==';
PLUSSYM : '+';
MINUSSYM : '-';
RAZYSYM : '*';
DZIELENIESYM : '/';
WIEKSZYSYM : '>';
MNIEJSZYSYM : '<';
ROZNYSYM : '!=';
NAWIASOKRAGLYSYM : '(|)';
NAWIASKWADRATOWYSYM : '[|]';
KONIECLININISYM : 'EOF';
MNIEJSZYLUBROWNYSYM : '<=';
WIEKSZYLUBROWNYSYM : '>=';
KOMENTARZSYM : '//';

BOOLEAN : 'true' | 'false';
INT : [0-9]+;
DOUBLE : [0-9]+ '.' [0-9]+;
NAME : [a-zA-Z_][a-zA-Z0-9_]*;
STRING : '"' [^"]* '"';

Whitespace
    :   [ \t\r\n]+
        -> skip
    ;