package problems.problems011to020;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

public class Problem14 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    HashMap<Long, Long> memo = new HashMap<>();
    memo.put(1l, 1l);
    long curLargest = 0l;
    long maxNum = 0l;
    long maxVal = 1000000l;
    for (long i = 1l; i <= maxVal; i++) {
      long curResult = collatzIter(memo, i);
      if (curResult > curLargest) {
        curLargest = curResult;
        maxNum = i;
      }
    }
    System.out.println(maxNum);
  }

  public static long nextCollatz(long n) {
    return n % 2 == 0 ? n / 2 : 3 * n + 1;
  }

  public static long collatzIter(HashMap<Long, Long> dict, long n) {
    long ops = 0;
    long currentVal = n;
    while (true) {
      if (dict.containsKey(currentVal)) {
        dict.put(n, ops + dict.get(currentVal));
        break;
      }
      currentVal = nextCollatz(currentVal);
      ops = ops + 1;
    }
    return dict.get(n);
  }
}
