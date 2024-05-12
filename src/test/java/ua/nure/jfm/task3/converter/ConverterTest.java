package ua.nure.jfm.task3.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ua.nure.jfm.task3.Base;

/**
 * @author Dmytro Kolesnykov
 */
public class ConverterTest extends Base {

	@ParameterizedTest
	@CsvFileSource(delimiter = '|', encoding = "UTF-8", resources = "cyrillic.csv")
	void testCyrillicConvert(String input, String expected) {
		String actual = CyrillicConverter.convert(input);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@CsvFileSource(delimiter = '|', encoding = "UTF-8", resources = "positional.csv")
	void testPositionalConvert(String input, String expected) {
		String actual = PositionalConverter.convert(input);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@CsvFileSource(delimiter = '|', encoding = "UTF-8", resources = "maya.csv")
	void testMayanConvert(String input, String expected) {
		String actual = MayaConverter.convert(input);
		assertEquals(expected, actual);
	}

	@ParameterizedTest
	@CsvFileSource(delimiter = '|', encoding = "UTF-8", resources = "roman.csv")
	void testRomanConvert(String input, String expected) {
		String actual = RomanConverter.convert(input);
		assertEquals(expected, actual);
	}

}
