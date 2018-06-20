package pl.edu.agh;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import pl.edu.agh.generated.edujavaBaseListener;
import pl.edu.agh.generated.edujavaParser;

//import java.awt.desktop.SystemSleepEvent;
import java.io.FileWriter;
import static java.lang.System.exit;

public class Test extends edujavaBaseListener {

//
//    private FileWriter outputFile;
//    private void startPrinting(){
//        try {
//            outputFile = new FileWriter("");
//            outputFile.write("");
//
//        } catch (Exception e) {
//            System.out.println("Problem with file to write to.");
//            exit(1);
//            e.printStackTrace();
//        }
//    }


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
