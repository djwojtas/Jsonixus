grammar edujava;

@header {
    package pl.edu.agh.generated;
}

root
    :   functionDeclaration
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
    |   operation ';'
    |   declaration ';'
    |   loop
    |   returnStatement ';'
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
    :   FORSYM '(' declaration? ';' condition? ';' forUpdate? ')' '{' implementation? '}'
    ;

condition
    :   lessThen
    |   moreThen
    |   moreOrEqual
    |   lessOrEqual
    |   equal
    |   diffrent
    |   BOOLEAN
    ;

lessThen
    :   getOrCalculateData WIEKSZYSYM getOrCalculateData
    ;

moreThen
    :   getOrCalculateData MNIEJSZYSYM getOrCalculateData
    ;

moreOrEqual
    :   getOrCalculateData MNIEJSZYLUBROWNYSYM getOrCalculateData
    ;

lessOrEqual
    :   getOrCalculateData WIEKSZYLUBROWNYSYM getOrCalculateData
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
    :   INT
    |   DOUBLE
    |   STRING
    |   BOOLEAN
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
    :   getData
    |   calculations
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
    :   getData PLUSSYM getData
    |   getData MINUSSYM getData
    ;

timesDivideCalculations
    :   getData RAZYSYM getData
    |   getData DZIELENIESYM getData
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

json
    :   value
    ;

value
    :   data
    |   jsonObj
    |   jsonArray
    |   NULLSYM
    ;

jsonObj
    :   '{' pair (',' pair )* '}'
    |   '{' '}'
    ;

pair
    : STRING ':' value
    ;

jsonArray
    :   '[' value (',' value)* ']'
    |   '[' ']'
    ;

ARRAYSYM : '[]';
INTSYM : 'int';
IFSYM : 'if';
RETURNSYM  : 'return';
ANDSYM  : '&&';
ORSYM  : '||';
NOTSYM  : '!';
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
NULLSYM : 'null';

BOOLEAN : 'true' | 'false';
INT : [0-9]+;
DOUBLE : [0-9]+ '.' [0-9]+;
NAME : [a-zA-Z_][a-zA-Z0-9_]*;
STRING :  '"' ~["]* '"';


Whitespace
    :   [ \t\r\n]+
        -> skip
    ;