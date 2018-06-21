package pl.edu.agh;

public class JsonTest {
    public static void main(String[] args) {
        Json json = new Json();
        json.addValue("jebando", "wdupando");
        json.addValue("niejebndo", "niewdupando");
        System.out.println(
                "getValue(\"jebando\"): " + json.getValue("jebando")
        );
        System.out.println(
                "\n\ngetJson():\n" + json.getJson()
        );
        System.out.println(
                "\n\nprint():\n" + json
        );
    }
}
