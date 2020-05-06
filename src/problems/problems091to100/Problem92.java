package problems.problems091to100;

import java.lang.invoke.MethodHandles;

public class Problem92 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    int num89 = 0;
    int[] memo = new int[10000001];
    for (int i = 1; i < 10000000; i++) {
      int total = i;
      while (total != 89 && total != 1) {
        total = next(total);
        if (memo[total] != 0) {
          total = memo[total];
          break;
        }
      }
      memo[i] = total;
      if (total == 89) {
        num89++;
      }
    }

    return String.valueOf(num89);
  }

  public static int next(int current) {
    int sum = 0;
    while (current > 0) {
      int last = current % 10;
      sum += last * last;
      current /= 10;
    }
    return sum;
  }
}
