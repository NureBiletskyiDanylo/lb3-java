package ua.nure.jfm.task3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

	private static final String PATH = "part2.txt";

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
		try{lines = new String(Files.readAllBytes(Paths.get(PATH)));}
		catch (IOException exc)
		{
			System.out.println("Can't open a file");
			return;
		}
		for (int j = 0; j < 10; j++) {
			System.out.println(convert(lines, j));
		}
	}

	public static String convert(String input, int k) {
		StringBuilder sb = new StringBuilder();
		String reges = "([,. ])([\\w',]{" + k + "})([,. ])";
		Pattern pattern = Pattern.compile(reges);
		Matcher matcher = pattern.matcher(input);
		String[] strings = new String[(int)matcher.results().count()];
		matcher.reset();
		int count = 0;
		while(matcher.find())
		{
			if(sb.length() == 0 && count == 0)
			{
				sb.append(matcher.group(2));
				String clearedString = getStringWithoutSpecificSymbols(matcher.group(2));
				strings[count++] = clearedString;
				sb.append(" ");
				continue;
			}
			String matchedString = getStringWithoutSpecificSymbols(matcher.group(2));
			if(contains(strings, matchedString))
			{
				continue;
			}
			sb.append(matcher.group(2));
			sb.append(" ");
		}
		String result = sb.toString();
		result.trim();
		return result;
	}

	private static String getStringWithoutSpecificSymbols(String string)
	{
		char[] array = string.toCharArray();
		deleteSpecificChars(array);
		String normalizedString = new String(array);
		return normalizedString;
	}
	private static boolean contains(String[] array, String string)
	{
		for(int i = 0; i < array.length; i++)
		{
			if(string.equals(array[i]))
			{
				return true;
			}
		}
		return false;
	}

	private static void deleteSpecificChars(char[] array) {
		int newLen = 0;
		for(int i = 0; i < array.length; i++)
		{
			if((array[i] >= 'a' && array[i] <= 'z') || (array[i] >= 'A' && array[i] <= 'Z'))
			{
				newLen++;
			}
		}

		char[] newChar = new char[newLen];

		int j = 0;
		for(int i = 0; i < array.length; i++)
		{
			if((array[i] >= 'a' && array[i] <= 'z') || (array[i] >= 'A' && array[i] <= 'Z'))
			{
				newChar[j++] = array[i];
			}
		}

		array = newChar;
	}
}