package problems.problems051to060;

import static utils.generators.Generators.from;
import static utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;

import utils.generators.Generators;
import utils.numbers.BigNumber;

public class Problem56 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  private static String solution() {
    return Generators.fromCartesianProductOf(range(2, 99), () -> range(2, 99))
        .mapPair((a, b) -> BigNumber.fromLong(a).toPower(b).digits())
        .map(digits -> from(digits).reduce(0, (sum, next) -> sum + next))
        .max(sum -> sum)
        .toString();
  }     
}