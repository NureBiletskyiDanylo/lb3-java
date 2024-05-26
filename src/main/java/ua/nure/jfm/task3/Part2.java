package ua.nure.jfm.task3;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.nure.jfm.task3.Utils.getContent;

public class Part2 {

    private static final String PATH = "part2.txt";

    public static void main(String[] args) {
        String lines = getContent(PATH);
        System.out.println(convert(lines, 2));
    }

    public static String convert(String input, int k) {
        StringBuilder sb = new StringBuilder();
        String begining = k + ": ";
        sb.append(begining);
        String reges = "(\\p{L}+(?:['.,~-]\\p{L}+)*[.,!?-]?)";
        Pattern pattern = Pattern.compile(reges);
        Matcher matcher = pattern.matcher(input);
        int arrLen = countMatches(matcher);
        String[] arr = new String[arrLen];
        int index = 0;
        while (matcher.find()) {
            arr[index++] = matcher.group(1);
        }
        arr = sortStringArr(arr);
        int currentRating = 0;
        int maxLen = 0;
        int i = 0;
        while (currentRating <= k && i < arr.length) {
            String word = arr[i];
            if (arr[i].length() > maxLen) {
                maxLen = arr[i].length();
                currentRating++;
            }

            if (currentRating == k) {
                String toAppend = arr[i] + " ";
                sb.append(toAppend);
            }
            i++;
        }
        if (currentRating < k) {
            return "";
        }
        sb.deleteCharAt(sb.length() - 1);
        String result = sb.toString();
        return result;
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

    private static int countMatches(Matcher matcher) {
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        matcher.reset();
        return count;
    }

    private static String[] sortStringArr(String[] arr) {
        int newLen = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && !contains(i, arr[i], arr)) {
                newLen++;
            }
        }
        String[] newArr = new String[newLen];
        int j = 0;
        // get rid of duplicates
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && !contains(j, arr[i], newArr)) {
                newArr[j++] = arr[i];
            }
        }

        for (int i = 1; i < newArr.length; i++) {
            int k = i - 1;
            String keyValue = newArr[i];
            while (k >= 0 && keyValue.length() < newArr[k].length()) {
                newArr[k + 1] = newArr[k];
                k--;
            }
            newArr[k + 1] = keyValue;
        }

        return newArr;
    }

    private static boolean contains(int startIndex, String word, String[] arr) {
        if (startIndex == 0) {
            return false;
        }
        for (int i = startIndex - 1; i >= 0; i--) {
            if (arr[i].equals(word)) {
                return true;
            }
        }
        return false;
    }
}