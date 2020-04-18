package src.problems.problems31to40;

import java.lang.invoke.MethodHandles;

import src.utils.primes.Primes;
public class Problem37 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
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
          System.out.println("Truncatable: " + k);
        }
      }
      currentMax += batchSize;
      System.out.println("Current Max: " + currentMax);
    }
    System.out.println(sum);
  }

  public static boolean isTruncatablePrime(Long num, Primes p) {
    String numStr = num.toString();
    for (int i = 0; i < numStr.length(); i++) {
      String subPrime = numStr.substring(i);
      String otherPrime = numStr.substring(0, i + 1);
      if (!Primes.isPrimeStatic(Long.parseLong(subPrime)) || !Primes.isPrimeStatic(Long.parseLong(otherPrime))) {
        return false;
      }
    }
    return true;
  }
}