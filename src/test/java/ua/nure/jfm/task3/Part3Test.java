package ua.nure.jfm.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * @author Dmytro Kolesnykov
 */
class Part3Test extends Base {

	@ParameterizedTest
	@CsvFileSource(delimiter = '|', encoding = "Cp1251", resources = "part3.csv")
	void testConvert(String inputString, String expectedString) {
		String expected = expectedString.replace("~", "\n");
		String input = inputString.replace("~", "\n");
		String actual = Part3.convert(input).replaceAll("\r", "");
		assertEquals(expected, actual);
	}

}
