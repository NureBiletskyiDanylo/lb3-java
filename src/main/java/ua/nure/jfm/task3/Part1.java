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

public class Part1 {

    private static final String PATH = "part1.txt";

    public static void main(String[] args) {
        String lines = getContent(PATH);
        System.out.println(convert1(lines));
        System.out.println();
        System.out.println(convert2(lines));
        System.out.println();
        System.out.println(convert3(lines));

    }

    public static String convert1(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append("LastName;Email\n");
        String regex1 = "(\\p{L}+);((\\p{L}+)(\\d?@\\w+.+))";
        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            sb.append(matcher.group(1)).append(";").append(matcher.group(2)).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String convert2(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append("LastName;MiddleName;FirstName\n");

        String regex = ";((\\p{L}+)(?:-(\\p{L}+))?)(?: (\\p{L}+)?(?: (\\p{L}+))?);";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            if (matcher.group(5) != null) {
                sb.append(matcher.group(5)).append(";").append(matcher.group(4)).append(";").append(matcher.group(2)).append("\n");
                continue;
            }
            sb.append(matcher.group(4)).append(";;").append(matcher.group(1)).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    public static String convert3(String input) {
        StringBuilder sb = new StringBuilder();
        int lastNameMaxLength = 0;
        int firstSecondNameMaxLength = 0;
        int loginMaxLength = 0;

        String regex = "(\\w+);((\\p{L}+[-]?(\\p{L}+)?) (\\p{L}+)) ?(\\p{L}+)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            if (matcher.group(6) != null) {
                lastNameMaxLength = lastNameMaxLength < matcher.group(6).length() ? matcher.group(6).length() : lastNameMaxLength;
                firstSecondNameMaxLength = firstSecondNameMaxLength < matcher.group(2).length() ? matcher.group(2).length() : firstSecondNameMaxLength;
            } else {
                lastNameMaxLength = lastNameMaxLength < matcher.group(5).length() ? matcher.group(5).length() : lastNameMaxLength;
                firstSecondNameMaxLength = firstSecondNameMaxLength < matcher.group(3).length() ? matcher.group(3).length() : firstSecondNameMaxLength;
            }
            loginMaxLength = loginMaxLength < matcher.group(1).length() ? matcher.group(1).length() : loginMaxLength;
        }

        matcher.reset();

        String whiteSpace = " ";
        while (matcher.find()) {
            if (matcher.group(6) == null) {
                String str = matcher.group(5);
                sb.append(matcher.group(5));
                int l = str.length();
                int whiteSpaceL = lastNameMaxLength - l;
                whiteSpace = " ".repeat(whiteSpaceL);
                l = whiteSpace.length();
                sb.append(whiteSpace).append(" ");

                sb.append(matcher.group(3));
                whiteSpace = " ".repeat(firstSecondNameMaxLength - matcher.group(3).length());
                sb.append(whiteSpace).append(" ");
            } else {
                sb.append(matcher.group(6));
                whiteSpace = " ".repeat(lastNameMaxLength - matcher.group(6).length());
                sb.append(whiteSpace).append(" ");

                sb.append(matcher.group(2));
                whiteSpace = " ".repeat(firstSecondNameMaxLength - matcher.group(2).length());
                sb.append(whiteSpace).append(" ");
            }
            sb.append(matcher.group(1));
            whiteSpace = " ".repeat(loginMaxLength - matcher.group(1).length());
            sb.append(whiteSpace);
            sb.append("\n");
        }

        while (sb.charAt(sb.length() - 1) == ' ' || sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
