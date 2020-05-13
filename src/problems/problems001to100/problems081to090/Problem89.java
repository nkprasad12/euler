package problems.problems001to100.problems081to090;

import java.lang.invoke.MethodHandles;

import utils.generators.Generators;

public class Problem89 {

    public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    return Generators.fromTextFile("problem89.txt")
            .map(roman -> roman.length() - reduce(roman).length())
            .reduce(0, (sum, saved) -> sum + saved)
            .toString();
  }

  static int value(char c) {
    if (c == 'I') {
      return 1;
    }
    if (c == 'V') {
      return 5;
    }
    if (c == 'X') {
      return 10;
    }
    if (c == 'L') {
     return 50;
    }
    if (c == 'C') {
      return 100;
    }
    if (c == 'D') {
      return 500;
    }
    if (c == 'M') {
      return 1000;
    }
    throw new RuntimeException("Invalid Roman numeral");
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