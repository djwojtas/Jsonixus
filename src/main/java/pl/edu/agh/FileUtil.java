package pl.edu.agh;

import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.exit;

public class FileUtil {

    public FileUtil(String filePath) {
        try {
            file = new FileWriter(filePath);
            file.write("package pl.edu.agh;");
            file.write("\n//import testImport.xd; \n\n");
            file.write("public class Output { \n\n public static void print(Object o) { \n System.out.println(o); \n } \n");
        } catch (IOException e) {
            System.out.println("Problem with file to write to.");
            exit(1);
            e.printStackTrace();
        }
    }

    private FileWriter file;

    public void write(String s) {
        try {
            file.write(" " + s );
        } catch (IOException e) {
            System.out.println("Writing to file failure.");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            file.write("\n}");
            file.close();
        } catch (IOException e) {
            System.out.println("Closing file failure");
            e.printStackTrace();
        }
    }
}
