package problems.problems001to010;

import java.lang.invoke.MethodHandles;
import utils.primes.Primes;

public class Problem7 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(solution());
  }

  public static String solution() {
    Primes primes = new Primes();
    return String.valueOf(primes.nthPrime(10001));
  }
}
