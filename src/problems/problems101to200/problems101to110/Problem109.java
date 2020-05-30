package problems.problems101to200.problems101to110;

import java.lang.invoke.MethodHandles;

public class Problem109 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    int totalCount = 0;
    for (int i = 1; i < 100; i++) {
      totalCount += numberOfWays(i);
    }
    return Integer.toString(totalCount);
  }

  static int[] singles = new int[21];
  static int[] doubles = new int[21];
  static int[] triples = new int[20];
  static int[] allValues = new int[62];
  static {
    for (int i = 1; i <= 20; i++) {
      singles[i - 1] = i;
      doubles[i - 1] = 2 * i;
      triples[i - 1] = 3 * i;
    }
    singles[20] = 25;
    doubles[20] = 50;
    for (int i = 0; i < 62; i++) {
      if (i < 21) {
        allValues[i] = singles[i];
      } else if (i < 42) {
        allValues[i] = doubles[i - 21];
      } else {
        allValues[i] = triples[i - 42];
      }
    }
  }

  public static int numberOfWays(int n) {
    int count = 0;
    for (int i = 0; i <= 20; i++) {
      if (doubles[i] > n) {
        return count;
      }
      int target = n - doubles[i];
      count += pairOfThrowsForTarget(target);
    }
    return count;
  }

  public static int pairOfThrowsForTarget(int n) {
    int numThrows = 0;
    if (n == 0) {
      return 1;
    }
    for (int i = 0; i < allValues.length; i++) {
      int dartValue = allValues[i];
      int target = n - dartValue;
      if (target < 0) {
        continue;
      }
      if (target == 0) {
        numThrows += 2;
        continue;
      }
      for (int j = 0; j < allValues.length; j++) {
        if (target - allValues[j] == 0) {
          if (i == j) {
            numThrows += 2;
          } else {
            numThrows++;
          }
        }
      }
    }

    return numThrows / 2;
  }
}
