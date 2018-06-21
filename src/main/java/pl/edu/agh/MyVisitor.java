package pl.edu.agh;

//import java.awt.desktop.SystemSleepEvent;
//import java.awt.desktop.SystemSleepEvent;

import org.antlr.v4.runtime.tree.ParseTree;
import pl.edu.agh.generated.edujavaBaseVisitor;
import pl.edu.agh.generated.edujavaParser;

import javax.sound.midi.SysexMessage;
import java.util.HashMap;
import java.util.Map;

public class MyVisitor extends edujavaBaseVisitor<Value> {

    private Map<String, Value> memory = new HashMap<String, Value>();
    FileUtil fileUtil;


    @Override
    public Value visitRoot(edujavaParser.RootContext ctx) {
        fileUtil = new FileUtil("/Users/iza/Desktop/jsonixusTest/Jsonixus/src/main/java/pl/edu/agh/Output.java");
        return super.visitRoot(ctx);
    }

    @Override
    public Value visitPlusMinusCalculations(edujavaParser.PlusMinusCalculationsContext ctx) {

        visit(ctx.getData(0));

        switch (ctx.op.getType()) {
          case edujavaParser.PLUSSYM:
            fileUtil.write(ctx.PLUSSYM().getSymbol().getText());
            visit(ctx.getData(1));
            return new Value("OK");
          case edujavaParser.MINUSSYM:
            fileUtil.write(ctx.MINUSSYM().getSymbol().getText());
            visit(ctx.getData(1));
            return new Value("OK");
          default:
            throw new RuntimeException("unknown operator");
        }
    }

    @Override
    public Value visitIfstatement(edujavaParser.IfstatementContext ctx) {
        String token = ctx.IFSYM().getSymbol().getText();
        fileUtil.write(token);
        fileUtil.write("(");
        visit(ctx.condition());
        fileUtil.write(")" + " {\n");
        visit(ctx.implementation());
        fileUtil.write("\n}" );

        if (ctx.elsestatement() != null) {
            fileUtil.write(ctx.elsestatement().ELSESYM().getSymbol().getText());
            fileUtil.write(" {\n");
            visit(ctx.elsestatement().implementation());
            fileUtil.write(" \n}");
        }

        return new Value("DONE");
        // super.visitIfstatement(ctx);
    }

    @Override
    public Value visitMainFunction(edujavaParser.MainFunctionContext ctx) {

        fileUtil.write("\n\n public static void main(String[] args) { \n\n");

        String funCall = ctx.getText();
        String value = String.valueOf(funCall);
        System.out.println("visitMainFunction detected: " + value);
        return super.visitMainFunction(ctx);
    }

    @Override
    public Value visitLoopFor(edujavaParser.LoopForContext ctx) {
        String forToken = ctx.FORSYM().getSymbol().getText();
        fileUtil.write("\n" + forToken);
        fileUtil.write("(");
        visit(ctx.declaration());
        visit(ctx.loopSemicolon(0));
        visit(ctx.condition());
        visit(ctx.loopSemicolon(1));
        visit(ctx.forUpdate());
        fileUtil.write(")" + " {\n");
        visit(ctx.implementation());
        fileUtil.write("\n}" );
        return new Value("DONE");

    }

    @Override
    public Value visitForUpdate(edujavaParser.ForUpdateContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitFunctionCall(edujavaParser.FunctionCallContext ctx) {
        String funCall = ctx.getText();
        String value = String.valueOf(funCall);
        System.out.println("visitFunctionCall detected: " + value);

        visit(ctx.name());
        fileUtil.write("(");

        if (ctx.names() == null) {
            fileUtil.write(")");
        } else {
            visit(ctx.names());
            fileUtil.write(")");
        }
        //return visitChildren(ctx); <- caused visitig tree again and duplicating text
       return new Value("DONE");
    }

    @Override
    public Value visitCondition(edujavaParser.ConditionContext ctx) {
        visit(ctx.getOrCalculateData(0));

        switch (ctx.sign.getType()) {
            case edujavaParser.WIEKSZYSYM:
                fileUtil.write(ctx.WIEKSZYSYM().getSymbol().getText());
                visit(ctx.getOrCalculateData(1));
                return new Value("DONE");
            case edujavaParser.MNIEJSZYSYM:
                fileUtil.write(ctx.MNIEJSZYSYM().getSymbol().getText());
                visit(ctx.getOrCalculateData(1));
                return new Value("DONE");
            case edujavaParser.ROZNYSYM:
                fileUtil.write(ctx.ROZNYSYM().getSymbol().getText());
                visit(ctx.getOrCalculateData(1));
                return new Value("DONE");
            case edujavaParser.ROWNOSCSYM:
                fileUtil.write(ctx.ROWNOSCSYM().getSymbol().getText());
                visit(ctx.getOrCalculateData(1));
                return new Value("DONE");
            default:
                throw new RuntimeException("unknown operator");
        }
    }

    @Override
    public Value visitCatchEOF(edujavaParser.CatchEOFContext ctx) {
        fileUtil.write("\n\n}");
        fileUtil.close();
        return super.visitCatchEOF(ctx);
    }

    @Override
    public Value visitSingleDataType(edujavaParser.SingleDataTypeContext ctx) {
        String value = ctx.getText();
        fileUtil.write(value);
        System.out.println("visitSingleDataType detected: " + value);
       // return visitChildren(ctx);
        return super.visitSingleDataType(ctx);
    }

//    @Override
//    public Value visitDataType(edujavaParser.DataTypeContext ctx) {
//        return visitChildren(ctx);
//    }


    @Override
    public Value visitSingletype(edujavaParser.SingletypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitArType(edujavaParser.ArTypeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitJsonSymbol(edujavaParser.JsonSymbolContext ctx) {
        //fileUtil.write("\nJson ");
        return new Value("OK");
    }

    @Override
    public Value visitLoop(edujavaParser.LoopContext ctx) {
        return visitChildren(ctx);
    }



    @Override
    public Value visitFunctionDeclaration(edujavaParser.FunctionDeclarationContext ctx) {
        String value = ctx.getText();
        System.out.println("visitFunctionDeclaration detected: " + value);
        ParseTree tree = ctx.getChild(1);

        if (tree != null) {
            fileUtil.write("public static");
            visit(ctx.returnType());
            visit(ctx.name());
            fileUtil.write("(");
            if (ctx.variables() == null) {
                fileUtil.write(")");
            } else {
                visit(ctx.variables());
                fileUtil.write(")");
            }

            fileUtil.write("{");

            if (ctx.implementation() == null) {
                fileUtil.write("}");
            } else {
                fileUtil.write("\n");
                visit(ctx.implementation());
                fileUtil.write("\n}\n");
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
            fileUtil.write("void");
            return this.visitReturnType(ctx);
        }
    }


    @Override
    public Value visitJson(edujavaParser.JsonContext ctx) {
        //fileUtil.write("new Json();");
        return visitChildren(ctx);
    }



    @Override
    public Value visitFunctionDeclarations(edujavaParser.FunctionDeclarationsContext ctx) {
        String value = ctx.getText();
        System.out.println("visitFunctionDeclarations detected: " + value );
       // return visitChildren(ctx);
        return super.visitFunctionDeclarations(ctx);
    }

    @Override
    public Value visitPair(edujavaParser.PairContext ctx) {
        String key = ctx.STRING().getText();
        String value = ctx.value().data().getText();
        fileUtil.write(key + " , " + value + ");");
        return new Value("OK");
    }

    @Override
    public Value visitValue(edujavaParser.ValueContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitJsonObj(edujavaParser.JsonObjContext ctx) {

        return visitChildren(ctx);
    }

    @Override
    public Value visitGetData(edujavaParser.GetDataContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Value visitAssignmentExpression(edujavaParser.AssignmentExpressionContext ctx) {
        String value = ctx.getText();
        System.out.println("visitAssignmentExpression detected: " + value );
        fileUtil.write("=");
        return visitChildren(ctx);
    }

    @Override
    public Value visitDeclaration(edujavaParser.DeclarationContext ctx) {
        String value = ctx.getText();
        System.out.println("visitDeclaration detected: " + value );

        if (ctx.dataType().getText().contains("jsonixus")) {
            System.out.println("🐤"  + ctx.dataType().getText().toString());
            fileUtil.write("Json ");
            visit(ctx.name());
            fileUtil.write("= new Json();");
            return new Value("done");
        } else {
            System.out.println(ctx.dataType().getText().toString());

        }
        return super.visitDeclaration(ctx);
    }

    @Override
    public Value visitAddJsonPair(edujavaParser.AddJsonPairContext ctx) {

        String name = ctx.name().getText();
        fileUtil.write(name + "." + "addValue(");
        String key = ctx.STRING().getText().toString();
        String value = ctx.data().getText().toString();
        fileUtil.write(key + " , " + value + ");");
        return new Value("OK");
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
        fileUtil.write(ctx.BOOLEAN().getSymbol().getText());
        return new Value(Boolean.valueOf(ctx.getText()));
    }

    @Override
    public Value visitDataInt(edujavaParser.DataIntContext ctx) {
        String value = ctx.getText();
        System.out.println("visitDataInt detected: " + value );
        fileUtil.write(ctx.INT().getSymbol().getText());
        return new Value(Integer.valueOf(ctx.getText()));
    }

    @Override
    public Value visitDataDbl(edujavaParser.DataDblContext ctx) {
        String value = ctx.getText();
        System.out.println("visitDataDouble detected: " + value );
        fileUtil.write(ctx.DOUBLE().getSymbol().getText());
        return new Value(Double.valueOf(ctx.getText()));
    }

    @Override
    public Value visitDataString(edujavaParser.DataStringContext ctx) {
        String value = ctx.getText();
        System.out.println("visitDataString detected: " + value );
        fileUtil.write(ctx.STRING().getSymbol().getText());
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
        fileUtil.write("\n" + ctx.RETURNSYM().getSymbol().getText());
        return super.visitReturnStatement(ctx);
    }

    @Override
    public Value visitName(edujavaParser.NameContext ctx) {
        String value = ctx.getText();
        System.out.println("visitName detected: " + value );
        fileUtil.write(value);
        return super.visitName(ctx);
    }

    @Override
    public Value visitLoopSemicolon(edujavaParser.LoopSemicolonContext ctx) {
        String value = ctx.getText();
        fileUtil.write(";");
        return super.visitLoopSemicolon(ctx);
    }

    @Override
    public Value visitSemicolon(edujavaParser.SemicolonContext ctx) {
        String value = ctx.getText();
        fileUtil.write(";\n");
        System.out.println("visitSemicolon detected: " + value );
        return super.visitSemicolon(ctx);
    }
}
