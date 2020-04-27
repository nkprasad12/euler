package problems.problems031to040;

import static utils.generators.Generators.from;
import static utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;
import java.util.List;
import utils.generators.Generators;
import utils.numbers.BigNumber;
import utils.primes.Primes;

public class Problem35 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    Primes primes = new Primes();

    int num =
        from(primes.primesUpTo(1000000l)).filter(p -> isCircularPrime(p, primes)).list().size();

    System.out.println(num);
  }

  public static boolean isCircularPrime(Long p, Primes primes) {
    List<Integer> digits = BigNumber.fromLong(p).digits();
    if (digits.contains(0)) {
      return false;
    }
    return !Generators.permutationsOf(range(0, digits.size() - 1).list())
        .filter(Problem35::isPermutationRotation2) // Rotation
        .map(perm -> from(perm).map(i -> digits.get(i)).list()) // Rotated digit list
        .map(d -> Long.parseLong(new BigNumber(d).toString()))
        .anyMatch(pr -> !primes.isPrime(pr)); // BigNumber
  }

  public static boolean isPermutationRotation(List<Integer> list) {
    int last = list.get(0);
    for (int i = 1; i < list.size(); i++) {
      if (list.get(i) != 0 && list.get(i) - last != 1) {
        return false;
      }
      last = list.get(i);
    }
    return true;
  }

  public static boolean isPermutationRotation2(List<Integer> list) {
    Integer current = list.get(0);
    int nonIncrementingDigits = 0;
    for (int i = 0; i < list.size() - 1; i++) {

      if (list.get(i + 1) - current != 1) {
        nonIncrementingDigits++;
      }
      current = list.get(i + 1);
    }

    return nonIncrementingDigits <= 1;
  }
}
