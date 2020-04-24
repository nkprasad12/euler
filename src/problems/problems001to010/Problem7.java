package src.problems.problems001to010;

import java.lang.invoke.MethodHandles;
import src.utils.primes.Primes;

public class Problem7 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();

    System.out.println(primes.nthPrime(10001));
  }
}
