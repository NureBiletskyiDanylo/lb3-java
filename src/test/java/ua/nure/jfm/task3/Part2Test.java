package ua.nure.jfm.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * @author Dmytro Kolesnykov
 */
class Part2Test extends Base {

	@ParameterizedTest
	@CsvFileSource(delimiter = '|', encoding = "Cp1251", resources = "part2.csv")
	void testConvert(String inputString, String kString, String expectedString) {
		String expected = expectedString.replace("~", "\n");
		int k = Integer.parseInt(kString);
		String input = inputString.replace("~", "\n");
		String actual = Part2.convert(input, k).replaceAll("\r", "");
		assertEquals(expected, actual);
	}

}
