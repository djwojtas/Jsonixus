grammar edujava;

root
    :   functionDeclaration
    ;

functionDeclaration
    :   returnType name '(' variables? ')' '{' implementation? '}'
    ;

functionCall
    :   name '(' variables? ')'
    ;

dataType
    :   DOUBLESYM
    |   STRINGSYM
    |   BOOLEANSYM
    |   INTSYM
    ;

returnType
    :   DOUBLESYM
    |   STRINGSYM
    |   BOOLEANSYM
    |   VOIDSYM
    |   INTSYM
    ;

variables
    :   variable
    |   variables ',' variable
    ;

variable
    :   dataType name
    ;

name
    :   NAME
    ;

codeExpression
    :   declaration ';'
    |   loop
    ;

implementation
    :   codeExpression
    |   implementation codeExpression
    ;


declaration
    :   dataType? name assignmentExpression?
    ;

assignmentExpression
    :   '=' data operator data
    |   '=' functionCall
    |   '=' data
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

data
    :   INT
    |   DOUBLE
    |   STRING
    |   BOOLEAN
    ;

getData
    :   name
    |   functionCall
    ;

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
NAME : [a-zA-Z_]?[a-zA-Z0-9_]+;
STRING : '"' [^"]* '"';


Whitespace
    :   [ \t\r\n]+
        -> skip
    ;