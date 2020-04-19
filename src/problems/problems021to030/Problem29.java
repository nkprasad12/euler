package src.problems.problems021to030;

import static src.utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;

import src.utils.generators.Generators;
import src.utils.numbers.BigNumber;

import java.util.HashSet;

public class Problem29 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    int size =
        Generators.fromCartesianProductOf(range(2l, 100l), () -> range(2, 100))
            .mapPair((a, b) -> BigNumber.fromLong(a).toPower(b))
            .collectInto(new HashSet<>())
            .size();
    System.out.println(size);
  }
}