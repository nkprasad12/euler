package src.problems.problems01to10;

import static src.utils.generators.base.tuples.Tuples.pair;

import java.lang.invoke.MethodHandles;

import src.utils.generators.Generators;

public class Problem2 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Generators.fromRecursion(
          pair(1, 2), (i, j) -> pair(j, i + j), (i, j) -> (j < 4000000))
        .filter((i, j) -> ((j % 2) == 0))
        .reducing(0, (sum, nextPair) -> (sum + nextPair.second()))
        .printLast();
  }

}