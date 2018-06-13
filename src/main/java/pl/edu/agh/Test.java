package pl.edu.agh;

import pl.edu.agh.generated.edujavaBaseListener;
import pl.edu.agh.generated.edujavaParser;

public class Test extends edujavaBaseListener {
    @Override
    public void exitMainFunction(edujavaParser.MainFunctionContext ctx) {
        System.out.println("main");
    }
}
