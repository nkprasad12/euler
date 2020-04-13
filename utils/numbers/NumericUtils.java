package utils.numbers;

import java.lang.Math;

public final class NumericUtils {

  public static boolean isPerfectSquare(int k) {
    int root = (int) Math.sqrt(k);
    return k == root * root;
  }

  /* Given an input k, returns the number n such that k is the nth triangular number, or null. */
  public static final Integer inverseTriangle(int k) {
    if (!isPerfectSquare(1 + 8 * k)) {
      return null;
    }
    return ((int) Math.sqrt(1 + 8 * k) - 1) / 2;
  }
}