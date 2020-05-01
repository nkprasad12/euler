package problems.problems051to060;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

public class Problem52 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    for (long i = 1; i < Long.MAX_VALUE / 6; i++) {
      if (doesItWork(i)) {
        return String.valueOf(i);
      }
    }
    return "You're bad and you should feel bad.";
  }

  public static boolean doesItWork(long n) {
    String nStr = sortString(String.valueOf(n));
    for (int i = 2; i <= 6; i++) {
      long multiple = i * n;
      String multipleStr = sortString(String.valueOf(multiple));
      if (!multipleStr.equals(nStr)) {
        return false;
      }
    }
    return true;
  }

  public static String sortString(String str) {
    char[] chars = str.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }
}
