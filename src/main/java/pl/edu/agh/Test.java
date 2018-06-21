package pl.edu.agh;

import pl.edu.agh.generated.edujavaBaseListener;
import pl.edu.agh.generated.edujavaParser;

//import java.awt.desktop.SystemSleepEvent;

public class Test extends edujavaBaseListener {

    @Override
    public void exitCalculation(edujavaParser.CalculationContext ctx) {
        System.out.println("dsdsd");
        super.exitCalculation(ctx);
    }

    @Override
    public void enterPlusMinusCalculations(edujavaParser.PlusMinusCalculationsContext ctx) {
        System.out.println("dsdsd");
        super.enterPlusMinusCalculations(ctx);
    }

    @Override
    public void exitPlusMinusCalculations(edujavaParser.PlusMinusCalculationsContext ctx) {
        String value = ctx.getData().toString();
        System.out.println("fddfdfdf");
        super.exitPlusMinusCalculations(ctx);
    }

    @Override
    public void exitMainFunction(edujavaParser.MainFunctionContext ctx) {
        System.out.println("main");
    }
}
