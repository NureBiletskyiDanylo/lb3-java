package ua.nure.jfm.task3.converter;

public class PositionalConverter {

	public static String convert(String s) {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		String s;
		s = "10:15:16";
		System.out.printf("%s ==> %s%n", s, PositionalConverter.convert(s));
		s = "10:17:2";
		System.out.printf("%s ==> %s%n", s, PositionalConverter.convert(s));
		s = "10:171:36";
		System.out.printf("%s ==> %s%n", s, PositionalConverter.convert(s));
	}

}
