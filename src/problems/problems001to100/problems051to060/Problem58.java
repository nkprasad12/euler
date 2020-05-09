package problems.problems001to100.problems051to060;

import java.lang.invoke.MethodHandles;
import utils.primes.Primes;

public class Problem58 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    long numPrimes = 3;
    long totalNum = 5;
    long sideLength = 3;
    while (numPrimes * 10 >= totalNum) {
      totalNum += 4;
      sideLength += 2;
      if (Primes.isPrimeStatic(sideLength * sideLength - (sideLength - 1))) {
        numPrimes++;
      }
      if (Primes.isPrimeStatic(sideLength * sideLength - 2 * (sideLength - 1))) {
        numPrimes++;
      }
      if (Primes.isPrimeStatic(sideLength * sideLength - 3 * (sideLength - 1))) {
        numPrimes++;
      }
    }
    return String.valueOf(sideLength);
  }
}
