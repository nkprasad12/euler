package problems.problems001to010;

import java.lang.invoke.MethodHandles;
import utils.primes.PrimeFactorization;
import utils.primes.Primes;

public class Problem3 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    return String.valueOf(PrimeFactorization.of(600851475143l, new Primes()).largestFactor());
  }
}
