package problems.problems31to40;

import java.lang.invoke.MethodHandles;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Problem31 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(numberOfCombinations(200, COIN_VALUES));
  }

  public static final List<Integer> COIN_VALUES = 
      Collections.unmodifiableList(
          new ArrayList<>(Arrays.asList(1, 2, 5, 10, 20, 50, 100, 200)));

  public static long numberOfCombinations(int target, List<Integer> coins) {
    long[] memo = new long[target + 1];
    memo[0] = 1;
    for (Integer coin : coins) {
      for (int i = 1; i <= target; i++) {
        if (coin <= i) {
          memo[i] += memo[i - coin];
        }
      }
    }
    return memo[target];
  }

}