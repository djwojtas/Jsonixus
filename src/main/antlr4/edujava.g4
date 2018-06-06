grammar edujava;

root
    :   functionDeclaration
    ;

functionDeclaration
    :   returnType name '(' variables? ')' '{' implementation? '}'
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
    :   '=' getData operator getData
    |   '=' functionCall
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
    |   equal
    |   diffrent
    |   BOOLEAN
    ;
    //todo badz rowne

lessThen
    :   getData WIEKSZYSYM getData
    ;

moreThen
    :   getData MNIEJSZYSYM getData
    ;

equal
    :   getData ROWNOSCSYM getData
    ;

diffrent
    :   getData ROZNYSYM getData
    ;

forUpdate
    :   functionCall
    |   name assignmentExpression
    ;

arrayElement
    :   name '[' getOrCalculateData ']'
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

getOrCalculateData
    :   getData
    |   calculations
    ;

getData
    :   name
    |   functionCall
    |   data
    |   arrayType
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