package src.utils.numbers;

/** Functions to calculate sums of common series. */
public final class Series {

  /* Returns the sum 1 + 2 + ... + n. */
  public static long ofIntegersUpTo(int n) {
    return (n * (n + 1)) / 2;
  }

  /* Returns the sum 1^2 + 2^2 + ... + n^2. */
  public static long ofIntegerSquaresUpTo(int n) {
    return (n * (n + 1) * (2 * n + 1)) / 6;
  }
}
