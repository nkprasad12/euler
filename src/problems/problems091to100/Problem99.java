package src.problems.problems091to100;

import static java.lang.Double.parseDouble;
import static java.lang.Math.log;

import java.lang.invoke.MethodHandles;

import src.utils.generators.Generators;

public class Problem99 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    Generators.fromTextFile("problem99.txt")
        .addIndices()
        .mapPair(i -> i + 1, str -> str.split(","))
        .mapPair(i -> i, arr -> parseDouble(arr[1]) * log(parseDouble(arr[0])))
        .max((i, exp) -> exp)
        .print();;

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }
}
