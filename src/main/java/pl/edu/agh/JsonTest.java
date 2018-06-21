package pl.edu.agh;

public class JsonTest {
    public static void main(String[] args) {
        Json json = new Json();
        json.addValue("klucz1", "wartosc1");
        json.addValue("klucz2", "wartosc2");
        System.out.println(
                "getValue(\"wartosc2\"): " + json.getValue("wartosc1")
        );
        System.out.println(
                "\n\ngetJson():\n" + json.getJson()
        );
        System.out.println(
                "\n\nprint():\n" + json
        );
    }
}
