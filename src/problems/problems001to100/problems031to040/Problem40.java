package problems.problems001to100.problems031to040;

import static utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;

public class Problem40 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    String digits =
        range(1, 1000000)
            .reducing("", (str, k) -> str + k)
            .whileTrue(str -> str.length() < 1000100)
            .lastValue()
            .get();
    System.out.println(digits.length());
    System.out.println(
        Character.getNumericValue(digits.charAt(0))
            * Character.getNumericValue(digits.charAt(9))
            * Character.getNumericValue(digits.charAt(99))
            * Character.getNumericValue(digits.charAt(999))
            * Character.getNumericValue(digits.charAt(9999))
            * Character.getNumericValue(digits.charAt(99999))
            * Character.getNumericValue(digits.charAt(999999)));

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }
}
