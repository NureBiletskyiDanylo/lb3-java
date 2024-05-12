package ua.nure.jfm.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * @author Dmytro Kolesnykov
 */
class Part1Test extends Base {

	@ParameterizedTest
	@CsvFileSource(delimiter = '|', encoding = "Cp1251", resources = "part1-convert1.csv")
	void testConvert1(String inputString, String expectedString) {
		String expected = expectedString.replace("~", "\n");
		String input = inputString.replace("~", "\n");
		String actual = Part1.convert1(input).replaceAll("\r", "");
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@CsvFileSource(delimiter = '|', encoding = "Cp1251", resources = "part1-convert2.csv")
	void testConvert2(String inputString, String expectedString) {
		String expected = expectedString.replace("~", "\n");
		String input = inputString.replace("~", "\n");
		String actual = Part1.convert2(input).replaceAll("\r", "");
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@CsvFileSource(delimiter = '|', encoding = "Cp1251", resources = "part1-convert3.csv")
	void testConvert3(String inputString, String expectedString) {
		String expected = expectedString.replace("~", "\n");
		String input = inputString.replace("~", "\n");
		String actual = Part1.convert3(input).replaceAll("\r", "");
		assertEquals(expected, actual);
	}

}
