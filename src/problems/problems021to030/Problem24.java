package problems.problems021to030;

import java.lang.invoke.MethodHandles;
import utils.generators.Generators;

public class Problem24 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(solution());
  }

  static String solution() {
    return Generators.permutationsOf(Generators.range(0, 9).list())
        .addIndices()
        .whileTrue((i, permutation) -> i < 1000000)
        .mapPair((i, permutation) -> Generators.from(permutation))
        .lastValue()
        .get()
        .reduce("", (a, b) -> a + b)
        .toString();
  }
}
