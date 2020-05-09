package problems.problems101to200.problems121to130;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;

import utils.generators.Generators;
import utils.numbers.NumericUtils;

public class Problem125 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    int n = 10000;
    long lim = ((long) n) * ((long) n);
    long[] sqSums = new long[n + 1];
    for (int i = 1; i <= n; i++) {
      sqSums[i] = sqSums[i - 1] + ((long) i) * ((long) i);
    }

    HashSet<Long> sqPals = new HashSet<>();
    for (int i = 0; i <= n; i++) {
      for (int j = 2; i + j <= n; j++)  {
        long diff = sqSums[i + j] - sqSums[i];
        if (diff >= lim) {
          break;
        }
        if (NumericUtils.isPalindrome(diff)) {
          sqPals.add(diff);
        }
      }
    }
    return Generators.from(sqPals).reduce(0l, (sum, next) -> sum + next).toString();
  }
}
