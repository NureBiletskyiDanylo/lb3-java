package ua.nure.jfm.task3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.jfm.task3.Utils.getContent;

public class Part2 {

    private static final String PATH = "part2.txt";

    public static void main(String[] args) {
        String lines = getContent(PATH);
        System.out.println(convert(lines, 5));
    }

    public static String convert(String input, int k) {
        StringBuilder sb = new StringBuilder();
        String reges = "(?<=[\\s\\n~])([\\p{L},.?!]{"+ k + "})(?=[\\s]|$)";
        Pattern pattern = Pattern.compile(reges);
        Matcher matcher = pattern.matcher(input);
        StringBuilder forExisting = new StringBuilder();
        while (matcher.find()) {
            if (sb.length() == 0 && forExisting.length() == 0) {
                sb.append(k + ": ");
                sb.append(matcher.group(1));
                String clearedString = getStringWithoutSpecificSymbols(matcher.group(1));
                forExisting.append(clearedString);
                sb.append(" ");
                continue;
            }
            String matchedString = getStringWithoutSpecificSymbols(matcher.group(1));
            if (forExisting.indexOf(matchedString) != -1) {
                continue;
            }
            sb.append(matcher.group(1));
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        String result = sb.toString();
        return result;
    }

    private static String getStringWithoutSpecificSymbols(String string) {
        char[] array = string.toCharArray();
        deleteSpecificChars(array);
        String normalizedString = new String(array);
        return normalizedString;
    }

    private static void deleteSpecificChars(char[] array) {
        int newLen = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] >= 'a' && array[i] <= 'z') || (array[i] >= 'A' && array[i] <= 'Z')
                    || (array[i] >= 'а' && array[i] <= 'я') || (array[i] >= 'А' && array[i] <= 'Я')) {
                newLen++;
            }
        }

        char[] newChar = new char[newLen];

        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] >= 'a' && array[i] <= 'z') || (array[i] >= 'A' && array[i] <= 'Z')) {
                newChar[j++] = array[i];
            }
        }

        array = newChar;
    }
}