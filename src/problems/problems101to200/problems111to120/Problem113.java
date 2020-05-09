package problems.problems101to200.problems111to120;

import java.lang.invoke.MethodHandles;

public class Problem113 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    return Long.toString(nonBouncies(100));
  }

  static long nonBouncies(int len) {
    long total = 0;
    Long[][] incMemo = new Long[len + 1][10];
    Long[][] decMemo = new Long[len + 1][10];
    for (int k = 1; k <= len; k++) {
      total += nonBounciesOfLen(k, incMemo, decMemo);
    }
    return total;
  }

  static long nonBounciesOfLen(int len, Long[][] incMemo, Long[][] decMemo) {
    long total = 0;
    for (int k = 0; k < 10; k++) {
      total += incNums(len, k, incMemo) + decNums(len, k, decMemo);
    }
    return total - 9;
  }

  static long incNums(int len, int ld, Long[][] memo) {
    if (len == 1) {
      return ld == 0 ? 0 : 1;
    }
    if (memo[len][ld] != null) {
      return memo[len][ld];
    }
    long result = 0;
    for (int k = 0; k <= ld; k++) {
      result += incNums(len - 1, k, memo);
    }
    memo[len][ld] = result;
    return result;
  }

  static long decNums(int len, int ld, Long[][] memo) {
    if (len == 1) {
      return ld == 0 ? 0 : 1;
    }
    if (memo[len][ld] != null) {
      return memo[len][ld];
    }
    long result = 0;
    for (int k = ld; k <= 9; k++) {
      result += decNums(len - 1, k, memo);
    }
    memo[len][ld] = result;
    return result;
  }
}
