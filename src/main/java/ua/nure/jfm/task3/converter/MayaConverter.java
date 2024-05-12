package ua.nure.jfm.task3.converter;

// Maya numerals. 
// You can use Google Font: "Noto Sans Mayan Numerals" to see the Mayan digits.
public class MayaConverter {

	private static final String[] ar = new String[20];

	static {
		int mayaZeroHighSurrogate = 0xDEE0;
		for (int j = 0; j < 20; j++) {
			ar[j] = "\uD834" + (char) (mayaZeroHighSurrogate + j);
		}
	}

	public static String convert(String from) {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		System.out.println("All the digits:");
		for (int j = 0; j < ar.length; j++) {
			System.out.printf("%2s: %s%n", j, ar[j]);
		}
		System.out.println("~~~");
		System.out.println(429);
		System.out.println(MayaConverter.convert("429"));
		System.out.println("~~~");
		System.out.println("100_000");
		System.out.println(MayaConverter.convert(String.valueOf("100_000")));
	}

}
