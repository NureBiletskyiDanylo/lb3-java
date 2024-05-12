package ua.nure.jfm.task3.converter;

// Roman numerals.
public class RomanConverter {

	private static final char[] digits = { 
			'\u2160', // I
			'\u2164', // V
			'\u2169', // X
			'\u216C', // L
			'\u216D', // C
			'\u216E', // D
			'\u216F'  // M
	};

	private static final int[] values = { 1, 5, 10, 50, 100, 500, 1000 };

	public static String convert(String str) {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		System.out.println("All the digits");
		for (int j = 0; j < digits.length; j++) {
			System.out.printf("%s: %s%n", digits[j], values[j]);
		}
		String s;
		System.out.println("~~~");
		s = "444";
		System.out.println(s);
		System.out.println(RomanConverter.convert(s));
		System.out.println("~~~");
		s = "3_999";
		System.out.println(s);
		System.out.println(RomanConverter.convert(s));
	}

}
