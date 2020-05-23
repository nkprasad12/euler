package problems.problems001to100.problems071to080;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

public class Problem74 {

  public static void main(final String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    final long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  private static String solution() {
    HashMap<Integer, Integer> currentLevel = new HashMap<>();
    for (int i = 0; i < 1000000; i++) {
      int next = nextInChain(i);
      if (loopLength(i) == -1) {
        Integer k = currentLevel.get(next);
        currentLevel.put(next, k == null ? 1 : k + 1);
      }
    }

    int result = 0;
    HashMap<Integer, Integer> nextLevel = new HashMap<>();
    for (int chainLen = 3; chainLen <= 60; chainLen++) {
      for (final Map.Entry<Integer, Integer> entry : currentLevel.entrySet()) {
        final int next = nextInChain(entry.getKey());
        final int loopSize = loopLength(next);
        if (loopSize != -1) {
          if (chainLen + loopSize == 60) {
            result += entry.getValue();
          }
          continue;
        }
        final Integer k = nextLevel.get(next);
        nextLevel.put(next, entry.getValue() + (k == null ? 0 : k));
      }
      currentLevel = nextLevel;
      nextLevel = new HashMap<>();
    }
    return Integer.toString(result);
  }

  private static int loopLength(final int n) {
    if (n == 145) {
      return 0;
    }
    if (n == 169) {
      return 2;
    }
    if (n == 363601) {
      return 2;
    }
    if (n == 1454) {
      return 2;
    }
    if (n == 871) {
      return 1;
    }
    if (n == 45361) {
      return 1;
    }
    if (n == 872) {
      return 1;
    }
    if (n == 45362) {
      return 1;
    }
    return -1;
  }

  private static final int nextInChain(int number) {
    int result = 0;
    while (number > 0) {
      result += fac(number % 10);
      number /= 10;
    }
    return result;
  }

  private static final int fac(final int digit) {
    switch (digit) {
      case 0:
        return 1;
      case 1:
        return 1;
      case 2:
        return 2;
      case 3:
        return 6;
      case 4:
        return 24;
      case 5:
        return 120;
      case 6:
        return 720;
      case 7:
        return 5040;
      case 8:
        return 40320;
      case 9:
        return 362880;
    }
    throw new RuntimeException("Not a digit, nerd");
  }
}
