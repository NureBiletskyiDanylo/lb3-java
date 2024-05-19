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
        long number = parseIntC(from);
        StringBuilder sb = new StringBuilder();
        if (number == 0) {
            return ar[0];
        }

        while (number > 0) {
            int remainder = (int) number % 20;
            sb.insert(0, ar[remainder]);
            number /= 20;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(parseIntC("429"));
        System.out.println("All the digits:");
        for (int j = 0; j < ar.length; j++) {
            System.out.printf("%2s: %s%n", j, ar[j]);
        }
        System.out.println("~~~");
        System.out.println(2147483647);
        System.out.println(MayaConverter.convert("2147483647"));
        System.out.println("~~~");
        System.out.println("100_000");
        System.out.println(MayaConverter.convert(String.valueOf("100_000")));
    }

    private static int countDigits(String string) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= '0' && string.charAt(i) <= '9') {
                count++;
            }
        }
        return count;
    }

    private static int parseIntC(String string) {
        int stringDigitsLength = countDigits(string);
        int number = 0;
        int currentNumberIndex = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= '0' && string.charAt(i) <= '9') {
                int currentNumber = (int) string.charAt(i) - '0';
                int power = (int) Math.pow(10, (stringDigitsLength - currentNumberIndex - 1));
                number += currentNumber * power;
                currentNumberIndex++;
            }
        }
        return number;
    }
}
