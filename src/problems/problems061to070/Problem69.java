package problems.problems061to070;

import java.lang.invoke.MethodHandles;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

public class Problem69 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    /*
     * Note that φ(n) = n Product [1 - 1 / p] for all primes p that divide n.
     * Therefore, f(n) = n / φ(n) = Product [p / (p - 1)] for all primes p that divide n.
     *
     * For any n = p1^e1 * p2^e2 * ... * pk^ek, n <= K, p1 < p2 < ... < pk
     * Note that f(n) does not depend on the exponent ei, so wlog assume that we select n
     * for which all the exponents are 1.
     *
     * Suppose n <= N is a number such that f(n) >= f(m) for all m in [1, N].
     * Suppose for the sake of contradiction that the set P = {p1, p2, ..., pk}
     * are not the k smallest primes. Let p_i be any such prime divisor that is
     * not one of the k smallest primes, and let p_z be one of the k smallest
     * primes that is not in the set P.
     *
     * Let m = n * p_i / p_z.
     * Then, p_i < p_z so m < n <= N. Additionally,
     * p_i < p_z => [p_i / (p_i - 1)] > [ p_z / (p_z - 1)], so
     * f(n) < f(m).
     *
     * Therefore, the solution to this problem is to multiply the smallest primes
     * while the product is less than 1000000.
     * 2 * 3 * 5 * 7 * 11 = 2310, and 13 * 17 = 221.
     * Note that 2000 < 2310 < 3000 and 200 < 221 < 300, so
     * 400000 < 2 * 3 * 5 * 7 * 11 * 13 * 17 < 900000.
     * Therefore, the value P of the product is <= 1 million, but P * 19 > 1 million.
     */
    return String.valueOf(2 * 3 * 5 * 7 * 11 * 13 * 17);
  }

  public static String alternateSolution() {
    PrimeFactorizations helper = new PrimeFactorizations(new Primes());
    double phiRatio = 0;
    long maxN = 0;
    for (long n = 1; n <= 1000000; n++) {
      long phi = helper.factorizationOf(n).totient();
      if (n * 1.0 / phi > phiRatio) {
        maxN = n;
        phiRatio = n * 1.0 / phi;
      }
    }
    System.out.println(phiRatio);
    return String.valueOf(maxN);
  }
}
