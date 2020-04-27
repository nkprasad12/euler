package src.problems.problems001to010;

import java.lang.invoke.MethodHandles;
import src.utils.generators.Generators;
import src.utils.primes.Primes;

public class Problem10 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Primes primes = new Primes();
    Generators.from(primes.primesUpTo(2000000l)).reduceAndPrint(0l, (sum, next) -> sum + next);
  }
}
