package src.problems.problems031to040;

import java.lang.invoke.MethodHandles;
import src.utils.primes.Primes;

public class Problem37 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long start = System.nanoTime();
    int numTruncatable = 0;
    int sum = 0;
    long currentMax = 10;
    long batchSize = 1000000;
    Primes primes = new Primes();
    while (numTruncatable < 11) {
      primes.primesUpTo(currentMax + batchSize);
      for (long k = currentMax; k < currentMax + batchSize; k++) {
        if (isTruncatablePrime(k, primes)) {
          numTruncatable++;
          sum += k;
        }
      }
      currentMax += batchSize;
    }
    String elapsed = ((System.nanoTime() - start) / 1000000) + " ms: ";
    System.out.println(elapsed + sum);
  }

  public static boolean isTruncatablePrime(Long num, Primes p) {
    String numStr = num.toString();
    for (int i = 0; i < numStr.length(); i++) {
      String subPrime = numStr.substring(i);
      String otherPrime = numStr.substring(0, i + 1);
      if (!p.isPrime(Long.parseLong(subPrime)) || !p.isPrime(Long.parseLong(otherPrime))) {
        return false;
      }
    }
    return true;
  }
}
