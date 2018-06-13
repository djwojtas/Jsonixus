package pl.edu.agh;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import pl.edu.agh.generated.edujavaLexer;
import pl.edu.agh.generated.edujavaParser;

public class Main {
    public static void main(String[] args) {
        ANTLRInputStream input = new ANTLRInputStream(
                "void main() { }");

        edujavaLexer lexer = new edujavaLexer(input);
        edujavaParser parser = new edujavaParser(new CommonTokenStream(lexer));
        parser.addParseListener(new Test());

        // Start parsing
        parser.root();
    }
}
