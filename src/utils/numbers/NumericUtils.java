package utils.numbers;

public final class NumericUtils {

  /* Determines whether an input number is a perfect square. */
  public static boolean isPerfectSquare(int k) {
    int root = (int) Math.sqrt(k);
    return k == root * root;
  }

  /* Determines whether an input number is a perfect square. */
  public static boolean isPerfectSquare(long k) {
    long root = (long) Math.sqrt(k);
    return k == root * root;
  }

  /* Given an input k, returns the number n such that k is the nth triangular number, or null. */
  public static final Integer inverseTriangle(int k) {
    if (!isPerfectSquare(1 + 8 * k)) {
      return null;
    }
    return ((int) Math.sqrt(1 + 8 * k) - 1) / 2;
  }

  public static final Long inverseTriangle(long k) {
    if (!isPerfectSquare(1 + 8 * k)) {
      return null;
    }
    return ((long) Math.sqrt(1 + 8 * k) - 1) / 2;
  }

  public static final Long inversePentagon(long k) {
    if (!isPerfectSquare(1 + 24 * k)) {
      return null;
    }
    long numerator = (long) Math.sqrt(1 + 24 * k) + 1;
    if (numerator % 6 != 0) {
      return null;
    }
    return numerator / 6;
  }

  public static final Integer inversePentagon(int k) {
    Long result = inversePentagon((long) k);
    return result != null ? result.intValue() : null;
  }

  public static final Integer inverseHexagon(int k) {
    Long result = inverseHexagon((long) k);
    return result != null ? result.intValue() : null;
  }

  public static final Long inverseHexagon(long k) {
    if (!isPerfectSquare(1 + 8 * k)) {
      return null;
    }

    long numerator = ((long) Math.sqrt(1 + 8 * k) + 1);

    if (numerator % 4 != 0) {
      return null;
    }

    return numerator / 4;
  }

  /* Computes the result `base`^`exponent` (mod `mod`) */
  public static long powerModN(long base, long exponent, long mod) {
    if (exponent == 0) {
      return 1l;
    } else if (exponent % 2 == 0) {
      long halfPow = powerModN(base, exponent / 2, mod);
      if (halfPow >= 3037000499l) {
        throw new RuntimeException("Mod too large - use BigNumber instead.");
      }
      return (halfPow * halfPow) % mod;
    } else {
      return (powerModN(base, exponent - 1, mod) * base) % mod;
    }
  }

  public static long gcd(long a, long b) {
    if (b > a) {
      gcd(b, a);
    }

    long temp = 0;
    while (b != 0) {
      temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }
}
