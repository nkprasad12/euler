package problems.problems201to300.problems201to210;

import java.lang.invoke.MethodHandles;

public class Problem206 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    // First possible solution > sqrt(1020304050607080900)
    long n = 1010101030;
    while (true) {
      if (isValidSolution(n)) {
        break;
      }
      // Since the square ends with 9_0, the root end in either 30 or 70
      if (n % 100 == 30) {
        n += 40;
      } else {
        n += 60;
      }
    }
    return Long.toString(n);
  }

  static boolean isValidSolution(long n) {
    // Divide by 10 first so the square fits in a long.
    n /= 10;
    long s = n * n;
    long required = 9;
    boolean isOddPosition = false;
    while (s > 0) {
      long d = s % 10;
      s /= 10;
      isOddPosition = !isOddPosition;
      if (isOddPosition) {
        if (required == d) {
          required--;
        } else {
          return false;
        }
      }
    }
    return true;
  }
}
