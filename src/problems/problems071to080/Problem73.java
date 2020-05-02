package problems.problems071to080;

import java.lang.invoke.MethodHandles;

import utils.numbers.NumericUtils;
import utils.primes.PrimeFactorization;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

public class Problem73 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    int numberOfFractions = 0;
    for (int d = 4; d <= 12000; d++) {
      for (int j = (int) Math.ceil(d / 3.0); j <= d / 2; j++) {
        if (NumericUtils.gcd(d, j) == 1) {
          numberOfFractions++;
        }
      }
    }
    return String.valueOf(numberOfFractions);
  }

  // Runs in 1/3 time of solution(), 400 ms vs 1200 ms on one machine.
  public static String alternateSolution() {
    PrimeFactorizations helper = new PrimeFactorizations(new Primes());
    PrimeFactorization[] factorizations = new PrimeFactorization[12001];
    for (int i = 4; i <= 12000; i++) {
      factorizations[i] = helper.factorizationOf(i);
    }
    int numberOfFractions = 0;
    for (int d = 4; d <= 12000; d++) {
      for (int j = (int) Math.ceil(d / 3.0); j <= d / 2; j++) {
        if (factorizations[d].isCoprimeWith(j)) {
          numberOfFractions++;
        }
      }
    }
    return String.valueOf(numberOfFractions);
  }
}
