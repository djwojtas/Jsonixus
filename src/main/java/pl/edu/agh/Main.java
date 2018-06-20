package pl.edu.agh;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import pl.edu.agh.generated.MyVisitor;
import pl.edu.agh.generated.edujavaLexer;
import pl.edu.agh.generated.edujavaParser;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static boolean czylubiePlacki = true;

    public static void main(String[] args) {


//        ANTLRInputStream input = new ANTLRInputStream(
//                "test");


        try {

            ANTLRInputStream input = new ANTLRInputStream(
                    new FileInputStream("/Users/iza/Desktop/jsonixusTest/Jsonixus/src/main/java/pl/edu/agh/inputTest"));


            edujavaLexer lexer = new edujavaLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            //passing tokens to parser
            edujavaParser parser = new edujavaParser(tokens);
            ParseTree tree = parser.root();
            //parser.addParseListener(new Test());
            MyVisitor visitor = new MyVisitor();
            visitor.visit(tree);

        } catch (IOException e) {
            e.printStackTrace();
        }
        ////edujavaParser.FormsContext formsSentenceContext = parser.forms();

//        int xd = kiedyIzeBoliBrzuch();
//        System.out.println(xd);



//        edujavaParser parser = new edujavaParser(new CommonTokenStream(lexer));
//        parser.addParseListener(new Test());
        // ParseTree tree = parser.parse
//        edu
//        eduJavaVisitor visitor = new MyVisitor();
//
//        parser.root();
//        visitor.visitData(t)
    }
}
