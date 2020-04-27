package problems.problems031to040;

import static utils.generators.Generators.from;
import static utils.generators.Generators.range;
import static utils.generators.base.tuples.Tuples.pair;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import utils.numbers.NumericUtils;

public class Problem39 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    range(2, 499)
        .mapAndPair(i -> range(1, i - 1))
        .filter((a, b) -> NumericUtils.isPerfectSquare(a * a + b * b))
        .mapPair((a, b) -> a + b + (int) Math.sqrt(a * a + b * b))
        .filter(p -> p <= 1000)
        .reducing(new HashMap<Integer, Integer>(), Problem39::putOrIncrement)
        .lastValue()
        .asGenerator()
        .flatMap(map -> from(map.entrySet()))
        .mapToPair(entry -> pair(entry.getKey(), entry.getValue()))
        .max(pair -> (long) pair.second())
        .print();

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static HashMap<Integer, Integer> putOrIncrement(HashMap<Integer, Integer> map, int num) {
    if (map.containsKey(num)) {
      map.put(num, map.get(num) + 1);
    } else {
      map.put(num, 1);
    }
    return map;
  }
}
