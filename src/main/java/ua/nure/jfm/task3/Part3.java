package ua.nure.jfm.task3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String PATH = "part3.txt";

    public static void main(String[] args) {
        FileReader reader;
        try {
            reader = new FileReader(PATH);
        } catch (FileNotFoundException exc) {
            System.out.println("File wasn't found");
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(reader);
        String lines = null;
        try {
            lines = new String(Files.readAllBytes(Paths.get(PATH)));
        } catch (IOException exc) {
            System.out.println("Can't open a file");
            return;
        }
        System.out.println(convert(lines));
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder();
        String regex = "\\b([\\p{L}]+\\n?)\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String currentWord = matcher.group(1);
            String backRegex = "\\b(" + currentWord + "\\n?)\\b";
            Pattern backPattern = Pattern.compile(backRegex, Pattern.CASE_INSENSITIVE);
			int ind = input.lastIndexOf(currentWord);
			if(ind != 0) {
				String inputUpUntilCurrentWord = input.substring(0, input.indexOf(currentWord) - 1);
				Matcher backMatcher = backPattern.matcher(inputUpUntilCurrentWord);
				boolean shouldChangeRegister = countMatches(sb.toString(), currentWord) ;
				if (shouldChangeRegister) {
					currentWord = changeRegister(currentWord);
				}
			}
			sb.append(currentWord);
            if(sb.charAt(sb.length() - 1) != '\n')
            {
                sb.append(" ");
            }
        }
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
    }

    private static String changeRegister(String word) {
        StringBuilder changedCase = new StringBuilder();
        char[] wordArray = word.toCharArray();
        for (char letter : wordArray) {
            if ((letter >= 'A' && letter <= 'Z') || (letter >= 'А' && letter <= 'Я')) {
                changedCase.append((char)(letter + 32));
            } else if ((letter >= 'a' && letter <= 'z') || (letter >= 'а' && letter <= 'я')) {
                changedCase.append((char)(letter - 32));
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

	private static boolean countMatches(String input, String word)
	{
		StringBuilder loweredStringBuilder = toLower(new StringBuilder(input));
		String loweredWord = toLower(new StringBuilder(word)).toString();
		int count = 0;
		int index = 0;

		while(true)
		{
			index = loweredStringBuilder.indexOf(loweredWord, index);
			count++;
			if(index == -1) break;
			index++;
		}
		count--;

		return count != 0 && count % 2 != 0;
	}

	private static StringBuilder toLower(StringBuilder input)
	{
		StringBuilder result = new StringBuilder();
		char[] sbArray = input.toString().toCharArray();
		for(char letter : sbArray)
		{
			if ((letter >= 'A' && letter <= 'Z') || (letter >= 'А' && letter <= 'Я')) {
				result.append((char)(letter + 32));
			}
			else if (letter == 'І') {
				result.append('і');
			} else if (letter != '\n'){
				result.append(letter);
			}
		}
		return result;
	}
}

