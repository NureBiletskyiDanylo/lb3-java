package ua.nure.jfm.task3.converter;

// Cyrillic numerals.
public class CyrillicConverter {

	private static char[][] digits = {
			{ '\u0430', '\u0432', '\u0433', '\u0434', '\u0454', '\u0455', '\u0437', '\u0438', '\u0473'},
			{'\u0456', '\u043A', '\u043B', '\u043C', '\u043D', '\u046F', '\u043E', '\u043F', '\u0447'},
			{'\u0440', '\u0441', '\u0442', '\u0443', '\u0444', '\u0445', '\u0471', '\u0461', '\u0446'}
		};
	
	/**
	 * The thousands sign to multiply the number's value.
	 */
	private static char kMul = '\u0482';
	
	public static String convert(String str) {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		System.out.println("All the digits:");
		int k = 1;
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 9; i++) {
				System.out.printf("%3s: %s [%s, %s]%n", (i + 1) * k, digits[j][i], j, i);
			}
			k *= 10;
		}
		System.out.println(kMul);
		String s = "999_999";
		System.out.printf("Maximum value (%s):%n", s);
		System.out.println(CyrillicConverter.convert(s));
	}

}
