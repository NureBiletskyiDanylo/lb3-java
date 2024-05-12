package ua.nure.jfm.task3;

import ua.nure.jfm.task3.converter.CyrillicConverter;
import ua.nure.jfm.task3.converter.MayaConverter;
import ua.nure.jfm.task3.converter.PositionalConverter;
import ua.nure.jfm.task3.converter.RomanConverter;

public class Demo {
	
	private static final String[] EMPTY = new String[] {};
	
    public static void main(String[] args) {
        System.out.println("----Part 1----");
        Part1.main(EMPTY);

        System.out.println("----Part 2----");
        Part2.main(EMPTY);

        System.out.println("----Part 3----");
        Part3.main(EMPTY);

        String s;
        
        System.out.println("----Cyrillic----");
        s = "999_999";
        System.out.println(s);
        System.out.println(CyrillicConverter.convert(s));

        System.out.println();
        System.out.println("----Maya----");
        s = "100_000";
        System.out.println(s);
        System.out.println(MayaConverter.convert(s));

        System.out.println();
        System.out.println("----Roman----");
        s = "3_999";
        System.out.println(s);
        System.out.println(RomanConverter.convert(s));

        System.out.println();
        System.out.println("----Positional----");
        PositionalConverter.main(EMPTY);
    }

}