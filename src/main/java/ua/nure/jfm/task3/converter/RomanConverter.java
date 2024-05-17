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
		int num = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				continue;
			}
			num = num * 10 + (str.charAt(i) - '0');
		}

		if (num < 1 || num > 3999) {
			throw new IllegalArgumentException("Roman conversion works up until 3999");
		}

		StringBuilder sb = new StringBuilder();

		// Convert number to Roman numeral
		int i = values.length - 1;
		while (num > 0) {
			while (num >= values[i]) {
				sb.append(digits[i]);
				num -= values[i];
			}
			if (i > 0) {
				int subtractiveValue = values[i] - values[i % 2 == 0 ? i - 2 : i - 1];
				if (num >= subtractiveValue) {
					sb.append(digits[i % 2 == 0 ? i - 2 : i - 1]).append(digits[i]);
					num -= subtractiveValue;
				}
			}
			i--;
		}
		return sb.toString();
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

    private static int stringNumberCount(String str){
        int count = 0;
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) < '0' || str.charAt(i) > '9')
            {
                continue;
            }
            count++;
        }
        return count;
    }

}
