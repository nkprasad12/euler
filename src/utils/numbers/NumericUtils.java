package src.utils.numbers;

import java.lang.Math;

public final class NumericUtils {

  /* Determines whether an input number is a perfect square. */
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

  /* Computes the result `base`^`exponent` (mod `mod`) */
  public static long powerModN(long base, long exponent, long mod) {
    if (exponent == 0) {
      return 1l;
    } else if (exponent % 2 == 0) {
      long halfPow = powerModN(base, exponent / 2, mod);
      return (halfPow * halfPow) % mod;
    } else {
      return (powerModN(base, exponent - 1, mod) * base) % mod;
    }
  }
}