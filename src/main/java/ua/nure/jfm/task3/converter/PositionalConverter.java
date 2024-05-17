package ua.nure.jfm.task3.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionalConverter {

    private static final char[] values2To36 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static String convert(String s) {
        String regex = "(\\d{1,2}):([A-Z\\d_]+?):(\\d{1,2})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find() && ((matcher.group(1) != null) &&
                (matcher.group(2) != null) && (matcher.group(3) != null))) {
            int baseFrom = parseIntC(matcher.group(1));
            String numberToConvert = getRidOfWrongCharacters(matcher.group(2));
            int baseTo = parseIntC(matcher.group(3));
            int baseTenNumber = fromBaseNToBaseTen(baseFrom, numberToConvert);
            return fromBaseTenToBaseN(baseTenNumber, baseTo);
        }
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        String s;
        //s = "10:15:16";
        s = "15:10_000:4";  //30113001 expected
        System.out.printf("%s ==> %s%n", s, PositionalConverter.convert(s));
        s = "10:17:2";
        System.out.printf("%s ==> %s%n", s, PositionalConverter.convert(s));
        s = "10:171:36";
        System.out.printf("%s ==> %s%n", s, PositionalConverter.convert(s));
    }


    private static int parseIntC(String numberInString) {
        int num = 0;
        for (int i = 0; i < numberInString.length(); i++) {
            if (numberInString.charAt(i) < '0' || numberInString.charAt(i) > '9') {
                continue;
            }
            num = num * 10 + (numberInString.charAt(i) - '0');
        }
        return num;
    }

    private static int fromBaseNToBaseTen(int baseN, String value) {
        if(baseN == 10)
        {
            return parseIntC(value);
        }
        if (value.length() == 1) {
            return indexOf(value.charAt(0));
        }

        int base10Result = 0;
        int numbersRemain = value.length() - 1;
        int index = 0;
        while (numbersRemain >= 0) {
            int valueInDecimal = indexOf(value.charAt(index++));
            base10Result += valueInDecimal * (int) Math.pow(baseN, numbersRemain--);
        }

        return base10Result;
    }

    private static String fromBaseTenToBaseN(int baseTenNumber, int baseN)
    {
        if(baseN == 10)
        {
            return "" + baseTenNumber;
        }
        StringBuilder result = new StringBuilder();
        if(baseN < 10)
        {
            while(baseTenNumber > 0)
            {
                int remainder = baseTenNumber % baseN;
                result.insert(0, remainder);
                baseTenNumber /= baseN;
            }
        }
        else
        {
            while(baseTenNumber > 0)
            {
                char remainder = values2To36[(baseTenNumber % baseN)];
                result.insert(0, remainder);
                baseTenNumber /= baseN;
            }
        }
        return result.toString();
    }
    private static int indexOf(char value) {
        for (int i = 0; i < values2To36.length; i++) {
            if (values2To36[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static  String getRidOfWrongCharacters(String number)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < number.length(); i++)
        {
            if(indexOf(number.charAt(i)) != -1)
            {
                sb.append(number.charAt(i));
            }
        }
        return sb.toString();
    }
}
