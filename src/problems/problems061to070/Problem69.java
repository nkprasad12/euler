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
