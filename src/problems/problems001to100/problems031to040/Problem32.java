package problems.problems001to100.problems031to040;

import static utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashSet;
import utils.generators.Generators;

public class Problem32 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    HashSet<Integer> set = new HashSet<Integer>();
    for (int a = 100; a < 9999; a++) {
      for (int b = 0; b < a; b++) {
        Integer c = a * b;
        if (isPandigital(a + "" + b + "" + c.toString())) {
          set.add(c);
        }
      }
    }

    Integer sum = 0;
    for (Integer i : set) {
      sum += i;
    }
    System.out.println(sum);
  }

  static String solution() {
    HashSet<Integer> products =
        range(100, 9999)
            .mapAndPair(a -> a < 1000 ? range(10, 99) : range(2, 9))
            .filter((a, b) -> isPandigital(a, b))
            .mapPair((a, b) -> a * b)
            .collectInto(new HashSet<>());
    return Generators.from(products).reduce(0, (sum, next) -> sum + next).toString();
  }

  public static boolean isPandigital(String input) {
    if (input.length() != 9) {
      return false;
    }
    char[] ordered = input.toCharArray();
    Arrays.sort(ordered);
    return new String(ordered).equals("123456789");
  }

  public static boolean isPandigital(int a, int b) {
    boolean[] hasChar = new boolean[9];
    int product = a * b;
    while (a > 0) {
      int digit = a % 10;
      a /= 10;
      if (digit == 0) {
        return false;
      }
      if (hasChar[digit - 1]) {
        return false;
      }
      hasChar[digit - 1] = true;
    }
    while (b > 0) {
      int digit = b % 10;
      b /= 10;
      if (digit == 0) {
        return false;
      }
      if (hasChar[digit - 1]) {
        return false;
      }
      hasChar[digit - 1] = true;
    }
    while (product > 0) {
      int digit = product % 10;
      product /= 10;
      if (digit == 0) {
        return false;
      }
      if (hasChar[digit - 1]) {
        return false;
      }
      hasChar[digit - 1] = true;
    }
    for (int i = 0; i < 9; i++) {
      if (!hasChar[i]) {
        return false;
      }
    }
    return true;
  }
}
