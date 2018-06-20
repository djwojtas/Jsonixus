package pl.edu.agh;

//import java.awt.desktop.SystemSleepEvent;
//import java.awt.desktop.SystemSleepEvent;
import org.antlr.v4.runtime.tree.ParseTree;
import pl.edu.agh.generated.edujavaBaseVisitor;
import pl.edu.agh.generated.edujavaParser;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class MyVisitor extends edujavaBaseVisitor<Value> {

    private Map<String, Value> memory = new HashMap<String, Value>();

    private FileUtil out = new FileUtil("C:/Users/djwojtas/gramatyka/src/main/java/pl/edu/agh/output.jxs");

    private void beginWritingToFile(){
        out.write("import testImport.xd \n\n");
    }


    private void write(String s){
        out.write(" " + s );
    }

    private void endWriting(){
        out.close();
    }


    @Override
    public Value visitRoot(edujavaParser.RootContext ctx) {
        beginWritingToFile();
        return super.visitRoot(ctx);
    }

    @Override
    public Value visitPlusMinusCalculations(edujavaParser.PlusMinusCalculationsContext ctx) {

//        Value left = this.visit(ctx.getData(0));
//        Value right = this.visit(ctx.getData(1));
//
//        switch (ctx.op.getType()) {
//            case edujavaParser.PLUSSYM:
//                System.out.println(left.asString());
//                return left.isDouble() && right.isDouble() ?
//                        new Value(Double.valueOf(ctx.getData(0).getText()) + Double.valueOf(ctx.getData(1).getText())) :
//                        new Value(Double.valueOf(ctx.getData(0).getText()) + Double.valueOf(ctx.getData(1).getText()));
//            case edujavaParser.MINUSSYM:
//                System.out.write("minus");
//                new Value(Double.valueOf(ctx.getData(0).getText()) - Double.valueOf(ctx.getData(1).getText()));
//            default:
//                throw new RuntimeException("unknown operator");
//        }
        return super.visitPlusMinusCalculations(ctx);
    }

    @Override
    public Value visitMainFunction(edujavaParser.MainFunctionContext ctx) {

        write("\n\n public static void main(String[] args) { \n\n");

        String funCall = ctx.getText();
        String value = String.valueOf(funCall);
        System.out.println("visitMainFunction detected: " + value);
        return super.visitMainFunction(ctx);
    }


    @Override
    public Value visitFunctionCall(edujavaParser.FunctionCallContext ctx) {
        String funCall = ctx.getText();
        String value = String.valueOf(funCall);
        System.out.println("visitFunctionCall detected: " + value);

        visit(ctx.name());
        write("(");
        
        if (ctx.names() == null) {
            write(")");
        } else {
            visit(ctx.names());
            write(")");
        }
        //return visitChildren(ctx); <- caused visitig tree again and duplicating text
       return null;
    }

    @Override
    public Value visitCatchEOF(edujavaParser.CatchEOFContext ctx) {
        write("\n\n}");
        endWriting();
        return super.visitCatchEOF(ctx);
    }

    @Override
    public Value visitSingleDataType(edujavaParser.SingleDataTypeContext ctx) {
        String value = ctx.getText();
        write(value);
        System.out.println("visitSingleDataType detected: " + value);
       // return visitChildren(ctx);
        return super.visitSingleDataType(ctx);
    }

    @Override
    public Value visitDataType(edujavaParser.DataTypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitFunctionDeclaration(edujavaParser.FunctionDeclarationContext ctx) {
        String value = ctx.getText();
        System.out.println("visitFunctionDeclaration detected: " + value);
        ParseTree tree = ctx.getChild(1);

        if (tree != null) {
            write("public static");
            visit(ctx.returnType());
            visit(ctx.name());
            write("(");
            if (ctx.variables() == null) {
                write(")");
            } else {
                visit(ctx.variables());
                write(")");
            }

            write("{");

            if (ctx.implementation() == null) {
                write("}");
            } else {
                write("\n");
                visit(ctx.implementation());
                write("\n}\n");
            }
            return null;
        }
        
        return super.visitFunctionDeclaration(ctx);
    }

    @Override
    public Value visitReturnType(edujavaParser.ReturnTypeContext ctx) {
        String value = ctx.getText();
        System.out.println("visitReturnType: " + value);
        if (ctx.getChild(2) == null) {
            return visitChildren(ctx.dataType());
        } else {
            write("void");
            return this.visitReturnType(ctx);
        }
    }
    
    
    @Override
    public Value visitFunctionDeclarations(edujavaParser.FunctionDeclarationsContext ctx) {
        String value = ctx.getText();
        System.out.println("visitFunctionDeclarations detected: " + value );
       // return visitChildren(ctx);
        return super.visitFunctionDeclarations(ctx);
    }

    @Override
    public Value visitGetData(edujavaParser.GetDataContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitAssignmentExpression(edujavaParser.AssignmentExpressionContext ctx) {
        String value = ctx.getText();
        System.out.println("visitAssignmentExpression detected: " + value );
        write("=");
        return visitChildren(ctx);
    }

    @Override
    public Value visitDeclaration(edujavaParser.DeclarationContext ctx) {
        String value = ctx.getText();
        System.out.println("visitDeclaration detected: " + value );
        return super.visitDeclaration(ctx);
    }

    @Override
    public Value visitMultipleParenthesis(edujavaParser.MultipleParenthesisContext ctx) {
        String value = ctx.getText();
        System.out.println("visitMultipleParenthesis detected: " + value );
        return visitChildren(ctx);
    }

    @Override
    public Value visitOperation(edujavaParser.OperationContext ctx) {
        String value = ctx.getText();
        System.out.println("visitOperation detected: " + value );
        return super.visitOperation(ctx);
    }

    @Override
    public Value visitCodeExpression(edujavaParser.CodeExpressionContext ctx) {
        String value = ctx.getText();
        System.out.println("visitCodeExpression detected: " + value );
        return visitChildren(ctx);
    }

    @Override
    public Value visitImplementation(edujavaParser.ImplementationContext ctx) {
        String value = ctx.getText();
        System.out.println("visitImplementation detected: " + value );
        return visitChildren(ctx);
    }

    @Override
    public Value visitGetOrCalculateData(edujavaParser.GetOrCalculateDataContext ctx) {
        String value = ctx.getText();
        System.out.println("visitGetOrCalculateData detected: " + value );
        return visitChildren(ctx);
    }

    @Override
    public Value visitDataBool(edujavaParser.DataBoolContext ctx) {
        String value = ctx.getText();
        System.out.println("visitDataBool detected: " + value );
        write(ctx.BOOLEAN().getSymbol().getText());
        return new Value(Boolean.valueOf(ctx.getText()));
    }

    @Override
    public Value visitDataInt(edujavaParser.DataIntContext ctx) {
        String value = ctx.getText();
        System.out.println("visitDataInt detected: " + value );
        write(ctx.INT().getSymbol().getText());
        return new Value(Integer.valueOf(ctx.getText()));
    }

    @Override
    public Value visitDataDbl(edujavaParser.DataDblContext ctx) {
        String value = ctx.getText();
        System.out.println("visitDataDouble detected: " + value );
        write(ctx.DOUBLE().getSymbol().getText());
        return new Value(Double.valueOf(ctx.getText()));
    }

    @Override
    public Value visitDataString(edujavaParser.DataStringContext ctx) {
        String value = ctx.getText();
        System.out.println("visitDataString detected: " + value );
        write(ctx.STRING().getSymbol().getText());
        return new Value(ctx.getText().toString());
    }

    @Override
    public Value visitTimesDivideCalculations(edujavaParser.TimesDivideCalculationsContext ctx) {

        // String token = ctx.RAZYSYM().getText(); todo
        return super.visitTimesDivideCalculations(ctx);
    }

    @Override
    public Value visitReturnStatement(edujavaParser.ReturnStatementContext ctx) {
        String value = ctx.getText();
        System.out.println("visitReturnStatement detected: " + value );
        write("\n" + ctx.RETURNSYM().getSymbol().getText());
        return super.visitReturnStatement(ctx);
    }

    @Override
    public Value visitName(edujavaParser.NameContext ctx) {
        String value = ctx.getText();
        System.out.println("visitName detected: " + value );
        write(value);
        return super.visitName(ctx);
    }

    @Override
    public Value visitSemicolon(edujavaParser.SemicolonContext ctx) {
        String value = ctx.getText();
        write(";\n");
        System.out.println("visitSemicolon detected: " + value );
        return super.visitSemicolon(ctx);
    }
}
