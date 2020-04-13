package problems.problems01to10;

import java.lang.invoke.MethodHandles;

import utils.primes.Primes;

public class Problem7 {
  
  public static void exec() {
    System.out.println(MethodHandles.lookup().lookupClass());
    
    Primes primes = new Primes();

    System.out.println(primes.nthPrime(10001));
  }
}