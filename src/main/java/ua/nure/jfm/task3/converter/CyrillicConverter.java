package ua.nure.jfm.task3.converter;

// Cyrillic numerals.
public class CyrillicConverter {

    private static char[][] digits = {
            // a - 1	в - 2		г - 3	  д - 4		є - 5	  s - 6		з - 7    и - 8    Ѳ - 9
            {'\u0430', '\u0432', '\u0433', '\u0434', '\u0454', '\u0455', '\u0437', '\u0438', '\u0473'},
            // і - 10	к - 20		л - 30	  м - 40    н - 50    Ѯ - 60    о - 70   п - 80    ч - 90
            {'\u0456', '\u043A', '\u043B', '\u043C', '\u043D', '\u046F', '\u043E', '\u043F', '\u0447'},
            // р - 100	с - 200   т - 300   У - 400     ф - 500  х - 600  Ѱ - 700   Ѡ - 800    ц - 900
            {'\u0440', '\u0441', '\u0442', '\u0443', '\u0444', '\u0445', '\u0471', '\u0461', '\u0446'}
    };

    /**
     * The thousands sign to multiply the number's value.
     */
    private static char kMul = '\u0482';

    public static String convert(String str) {
        StringBuilder clearNumber = getClearNumber(str);
        int howManyThousands = clearNumber.length() - 3;
        boolean hasThousands = false;
        StringBuilder result;
        if (howManyThousands > 0) {
            result = convertWithThousands(clearNumber);
        } else {
            result = convertWithoutThousands(clearNumber);
        }
        return result.toString();
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

    private static StringBuilder getClearNumber(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                sb.append(str.charAt(i));
            }
        }
        return sb;
    }

    private static StringBuilder convertWithThousands(StringBuilder number) {
        StringBuilder result = new StringBuilder();
        int numberPowerIndex = 0;
        int index = number.length() - 1;
        while (index >= (number.length() - 3)) {

            if (number.charAt(index) == '0') {
                numberPowerIndex++;
                index--;
                continue;
            }
            int charPositionInDigits = (number.charAt(index) - '0') - 1;
            result.insert(0, digits[numberPowerIndex++][charPositionInDigits]);
            index--;
        }

        while (index >= 0) {
            if (numberPowerIndex > 2) {
                numberPowerIndex = 0;
            }
            if (number.charAt(index) == '0') {
                numberPowerIndex++;
                index--;
                continue;
            }
            int charPositionInDigits = (number.charAt(index) - '0') - 1;
            result.insert(0, digits[numberPowerIndex++][charPositionInDigits]);
            index--;
            result.insert(0, kMul);
        }
        return result;
    }

    private static StringBuilder convertWithoutThousands(StringBuilder number) {
        StringBuilder result = new StringBuilder();
        int numberPowerIndex = 0;
        int index = number.length() - 1;

        while (index >= 0) {
            if (number.charAt(index) == '0') {
                numberPowerIndex++;
                index--;
                continue;
            }
            int charPositionInDigits = (number.charAt(index) - '0') - 1;
            result.insert(0, digits[numberPowerIndex++][charPositionInDigits]);
            index--;
        }
        return result;
    }
}
