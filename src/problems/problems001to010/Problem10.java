package problems.problems001to010;

import static utils.generators.Generators.from;

import java.lang.invoke.MethodHandles;
import utils.primes.Primes;

public class Problem10 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(solution());
  }

  public static String solution() {
    Primes primes = new Primes();
    return String.valueOf(from(primes.primesUpTo(2000000l)).reduce(0l, (sum, p) -> sum + p));
  }
}
