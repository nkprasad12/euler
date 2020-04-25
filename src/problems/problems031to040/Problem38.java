package src.problems.problems031to040;

import static java.lang.Integer.parseInt;
import static src.utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class Problem38 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    range(1, 9999)
        .map(
            k ->
                range(1, 9)
                    .reducing("", (str, i) -> str + (k * i))
                    .whileTrue(str -> str.length() <= 9)
                    .lastValue()
                    .get())
        .filter(str -> isPandigital(str))
        .max(str -> Long.parseLong(str))
        .print();

    int maxPanDigital = 0;
    for (int i = 1; i < 9999; i++) {
      String nineDigit = getNineDigit(i);
      if (isPandigital(nineDigit) && parseInt(nineDigit) > maxPanDigital) {
        maxPanDigital = parseInt(nineDigit);
      }
    }
    System.out.println("Max Pan digital: " + maxPanDigital);

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String getNineDigit(Integer input) {
    String value = input.toString();
    Integer curMultiple = 2;
    while ((value +  input * curMultiple).length() < 10) {
      value += input * curMultiple;
      curMultiple++;
    }
    return value;
  }

  public static boolean isPandigital(String input) {
    if (input.length() != 9) {
      return false;
    }
    char[] ordered = input.toCharArray();
    Arrays.sort(ordered);
    return new String(ordered).equals("123456789");
  }
}
