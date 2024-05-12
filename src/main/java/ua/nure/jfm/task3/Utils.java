package ua.nure.jfm.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// You can add your own methods if necessary
public final class Utils {

    private static final String ENCODING = "Cp1251";

    private Utils() {}

    public static String getContent(String path) {
        String res = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            res = new String(bytes, ENCODING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }
    
    public static void main(String[] args) {
		System.out.println(getContent("part1.txt"));
	}

}