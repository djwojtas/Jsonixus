package pl.edu.agh;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public FileUtil(String filePath) {
        try {
            file = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FileWriter file;

    public void write(String s) {
        try {
            file.write(s);
        } catch (IOException e) {
            System.out.println("Problem with printing to file.");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            file.close();
        } catch (IOException e) {
            System.out.println("Problem with closing file.");
            e.printStackTrace();
        }
    }
}
