package problems.problems101to200.problems131to140;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import utils.primes.Primes;

public class Problem131 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(initialSolution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  // Initial solution. Runs in 91 seconds. Uses the fact that if
  // n^3 + n^3 = (n + k)^3, then
  // n = (3k^2 + k sqrt(k * (4p - 3k))) / (p - 3k)
  static String initialSolution() {
    int max = 1000000;
    List<Long> primes = new Primes().primesUpTo(max);
    List<Long> results = new ArrayList<>();
    for (Long p : primes) {
      for (long k = 1; k < p / 3; k++) {
        long d = 4 * p - 3 * k;
        if (d < 0) {
          break;
        }
        long kd = k * d;
        long root = (long) Math.sqrt(kd);
        if (kd != root * root) {
          continue;
        }
        long numerator = 3 * k * k + k * root;
        long denom = 2 * (p - 3 * k);
        if (numerator % denom != 0) {
          continue;
        }
        results.add(p);
      }
    }
    return Integer.toString(results.size());
  }

  // Second solution. Runs in 500 ms. Uses the observed fact
  // (but I was unable to prove when writing this) that k is always a square
  // for the valid solutions.
  static String sketchySolution() {
    int max = 1000000;
    List<Long> primes = new Primes().primesUpTo(max);
    List<Long> results = new ArrayList<>();
    for (Long p : primes) {
      for (long r = 0; r * r <= 4 * p; r++) {
        long k3 = 4 * p - r * r;
        if (k3 % 3 != 0 || k3 >= p) {
          continue;
        }
        long k = k3 / 3;
        long kRoot = (long) Math.sqrt(k);
        if (kRoot * kRoot != k) {
          continue;
        }
        long numerator = 3 * k * k + k * r * kRoot;
        long denom = 2 * (p - 3 * k);
        if (numerator % denom != 0) {
          continue;
        }
        results.add(p);
      }
    }
    return Integer.toString(results.size());
  }
}
