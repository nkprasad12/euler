package problems.problems001to100.problems031to040;

import static utils.generators.Generators.from;

import java.lang.invoke.MethodHandles;
import utils.primes.Primes;

public class Problem35 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  static String solution() {
    Primes primes = new Primes();
    return from(primes.primesUpTo(1000000l))
        .filter(p -> isCircularPrime(p, primes))
        .reducing(0, (ct, next) -> ct + 1)
        .lastValue()
        .toString();
  }

  public static boolean isCircularPrime(Long p, Primes primes) {
    int number = (int) p.longValue();
    int numDigits = 0;
    while (number > 0) {
      if (number % 10 == 0) {
        return false;
      }
      number /= 10;
      numDigits++;
    }
    int largestPowerOfTenLessThan = 1;
    for (int i = 0; i < numDigits - 1; i++) {
      largestPowerOfTenLessThan *= 10;
    }
    number = (int) p.longValue();
    for (int i = 0; i < numDigits - 1; i++) {
      int lastDigit = number % 10;
      number /= 10;
      number += lastDigit * largestPowerOfTenLessThan;
      if (!primes.isPrime(number)) {
        return false;
      }
    }
    return true;
  }
}
