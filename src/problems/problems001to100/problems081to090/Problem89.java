package problems.problems001to100.problems081to090;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import utils.generators.Generators;

public class Problem89 {

  static Map<String, Integer> numeralMap =
      new HashMap<String, Integer>() {
        private static final long serialVersionUID = 1L;

        {
          put("I", 1);
          put("V", 5);
          put("X", 10);
          put("L", 50);
          put("C", 100);
          put("D", 500);
          put("M", 1000);
          put("IV", 4);
          put("IX", 9);
          put("XL", 40);
          put("XC", 90);
          put("CD", 400);
          put("CM", 900);
        }
      };

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    String result =
        Generators.fromTextFile("problem89.txt")
            .map(
                roman ->
                    roman.length()
                        - getRomanNumeralFromNumber(getNumberFromRomanNumeral(roman)).length())
            .reduce(0, (sum, saved) -> sum + saved)
            .toString();

    return result;
  }

  public static String getRomanNumeralFromNumber(int n) {
    LinkedHashMap<String, Integer> numeralCount = new LinkedHashMap<String, Integer>();

    int numeralAmount = 0;

    numeralAmount = n / 1000;
    numeralCount.put("M", numeralAmount);
    n -= numeralAmount * 1000;

    numeralAmount = n / 500;
    numeralCount.put("D", numeralAmount);
    n -= numeralAmount * 500;

    numeralAmount = n / 100;
    numeralCount.put("C", numeralAmount);
    n -= numeralAmount * 100;

    CollapseVals(numeralCount, "C", 4, "D", 1, "CM");
    CollapseVals(numeralCount, "C", 4, null, null, "CD");

    numeralAmount = n / 50;
    numeralCount.put("L", numeralAmount);
    n -= numeralAmount * 50;

    numeralAmount = n / 10;
    numeralCount.put("X", numeralAmount);
    n -= numeralAmount * 10;

    CollapseVals(numeralCount, "X", 4, "L", 1, "XC");
    CollapseVals(numeralCount, "X", 4, null, null, "XL");

    numeralAmount = n / 5;
    numeralCount.put("V", numeralAmount);
    n -= numeralAmount * 5;

    numeralAmount = n;
    numeralCount.put("I", numeralAmount);
    n -= numeralAmount;

    CollapseVals(numeralCount, "I", 4, "V", 1, "IX");
    CollapseVals(numeralCount, "I", 4, null, null, "IV");

    String strResult = "";
    for (Map.Entry<String, Integer> val : numeralCount.entrySet()) {
      for (int i = 0; i < val.getValue(); i++) {
        strResult += val.getKey();
      }
    }

    return strResult;
  }

  public static void CollapseVals(
      LinkedHashMap<String, Integer> values,
      String val1,
      Integer count1,
      String val2,
      Integer count2,
      String newValue) {
    if (val2 == null) {
      if (values.get(val1) == count1) {
        values.put(newValue, 1);
        values.put(val1, 0);
      }
    } else {
      if (values.get(val1) == count1 && values.get(val2) == count2) {
        values.put(newValue, 1);
        values.put(val1, 0);
        values.put(val2, 0);
      }
    }
  }

  public static int getNumberFromRomanNumeral(String numeral) {
    int number = 0;

    char[] numerals = numeral.toCharArray();

    int i = 0;
    while (i < numerals.length) {
      char c1 = numerals[i];
      char c2 = '?';
      if (i != numerals.length - 1) {
        c2 = numerals[i + 1];
      }

      if (valueOfPair(c1, c2) != null) {
        number += valueOfPair(c1, c2);
        i++;
      } else {
        number += value(c1);
      }

      i++;
    }

    return number;
  }

  static Integer valueOfPair(char c1, char c2) {
    Integer value = numeralMap.getOrDefault("" + c1 + c2, null);

    return value;
  }

  static int value(char c) {

    Integer value = numeralMap.getOrDefault("" + c, null);

    if (value == null) {
      throw new RuntimeException("Invalid Roman numeral");
    }

    return value;
  }

  static String reduce(String num) {
    String originalNum = num;
    int previousLength = num.length();
    int currentLength = 0;

    while (previousLength != currentLength) {
      previousLength = currentLength;
      // Repeats
      num = num.replaceAll("IIII", "IV");
      num = num.replaceAll("XXXX", "XL");
      num = num.replaceAll("CCCC", "CD");
      num = num.replaceAll("VV", "X");
      num = num.replaceAll("LL", "C");
      num = num.replaceAll("DD", "M");

      // I in the middle
      num = num.replaceAll("XXXIX", "IL");
      num = num.replaceAll("CCCIC", "ID");
      num = num.replaceAll("VIV", "IX");
      num = num.replaceAll("LIL", "IC");
      num = num.replaceAll("DID", "IM");

      // V in the middle
      num = num.replaceAll("XXXVX", "VL");
      num = num.replaceAll("CCCVC", "VD");
      num = num.replaceAll("LVL", "VC");
      num = num.replaceAll("DVD", "VM");

      // X in the middle
      num = num.replaceAll("LXL", "XC");
      num = num.replaceAll("DXD", "XM");

      // L in the middle
      num = num.replaceAll("DLD", "LM");

      // C in the middle
      num = num.replaceAll("DCD", "CM");

      // D in the middle
      // None
      num = num.replaceAll("VX", "V");
      num = num.replaceAll("LC", "L");
      num = num.replaceAll("DM", "M");

      currentLength = num.length();
    }
    System.out.println(originalNum + " -> " + num);
    return num;
  }
}
