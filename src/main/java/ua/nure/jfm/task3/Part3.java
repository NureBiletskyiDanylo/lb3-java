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

public class Part3 {

    private static final String PATH = "part3.txt";

    private static int arraysSizes = 50;
    private static String[] wordArray = new String[arraysSizes];
    private static int[] countOfWordsArray = new int[arraysSizes];
    private static int currentAmountOfDifferentWords = 0;

    public static void main(String[] args) {
        String lines = getContent(PATH);
        System.out.println(convert(lines));
    }

    public static String convert(String input) {
        //WordsList listOfWords = new WordsList();
        StringBuilder sb = new StringBuilder();
        String regex = "(\\b\\p{L}+\\b)([,\\s.\\n\\r]*)";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE + Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String currentWord = matcher.group(1);
            String currentWordInLower = toLower(new StringBuilder(matcher.group(1))).toString();
            addNewWordForCounting(currentWordInLower);
            int wordCount = getCountOfWordEntries(currentWordInLower);
            if (wordCount > 0 && wordCount % 2 == 0) {
                currentWord = changeRegister(currentWord);
            }
            sb.append(currentWord);
            sb.append(matcher.group(2));
        }

        arraysSizes = 50;
        currentAmountOfDifferentWords = 0;
        wordArray = new String[arraysSizes];
        countOfWordsArray = new int[arraysSizes];
        return sb.toString();
    }

    private static String changeRegister(String word) {
        StringBuilder changedCase = new StringBuilder();
        char[] wordArray = word.toCharArray();
        for (char letter : wordArray) {
            if ((letter >= 'A' && letter <= 'Z') || (letter >= 'А' && letter <= 'Я')) {
                changedCase.append((char) (letter + 32));
            } else if ((letter >= 'a' && letter <= 'z') || (letter >= 'а' && letter <= 'я')) {
                changedCase.append((char) (letter - 32));
            } else if (letter == 'і') {
                changedCase.append('І');
            } else if (letter == 'І') {
                changedCase.append('і');
            } else {
                changedCase.append(letter);
            }
        }
        return changedCase.toString();
    }

    private static StringBuilder toLower(StringBuilder input) {
        StringBuilder result = new StringBuilder();
        char[] sbArray = input.toString().toCharArray();
        for (char letter : sbArray) {
            if ((letter >= 'A' && letter <= 'Z') || (letter >= 'А' && letter <= 'Я')) {
                result.append((char) (letter + 32));
            } else if (letter == 'І') {
                result.append('і');
            } else if (letter != '\n') {
                result.append(letter);
            }
        }
        return result;
    }

    private static int getCountOfWordEntries(String word) {
        int index = getIndexOfAWord(word);
        if (index == -1) {
            return -1;
        }
        return countOfWordsArray[index];
    }

    private static int getIndexOfAWord(String word) {
        int index = 0;
        while (index < currentAmountOfDifferentWords) {
            if (wordArray[index].equals(word)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private static void addNewWordForCounting(String word) {
        if (getCountOfWordEntries(word) != -1) {
            int wordIndex = getIndexOfAWord(word);
            countOfWordsArray[wordIndex] += 1;
            return;
        }

        if (currentAmountOfDifferentWords == countOfWordsArray.length) {
            increaseArraysSizes();
        }

        countOfWordsArray[currentAmountOfDifferentWords] = 1;
        wordArray[currentAmountOfDifferentWords] = word;
        currentAmountOfDifferentWords++;
    }

    private static void increaseArraysSizes() {
        int newArraySizes = arraysSizes * 2;
        String[] newWordsArray = new String[newArraySizes];
        int[] newWordsCountArray = new int[newArraySizes];
        int j = 0;
        for (int i = 0; i < countOfWordsArray.length; i++) {
            newWordsCountArray[j] = countOfWordsArray[i];
            newWordsArray[j] = wordArray[i];
            j++;
        }

        countOfWordsArray = newWordsCountArray;
        wordArray = newWordsArray;
        arraysSizes = newArraySizes;
    }
}

