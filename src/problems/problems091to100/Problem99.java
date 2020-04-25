package src.problems.problems091to100;

import static java.lang.Double.parseDouble;
import static java.lang.Math.log;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
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
        .print();

    reid();

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static void reid() {
    ArrayList<String> nums =
        Generators.fromTextFile("problem99.txt").collectInto(new ArrayList<>());

    double maxNum = 0;
    int maxIdx = 0;
    for (int i = 0; i < nums.size(); i++) {
      // String[] baseExponent = new String[] { nums.get(i).trim(), nums.get(i+1).trim() };
      String[] baseExponent = nums.get(i).split(",");
      if (parseDouble(baseExponent[1]) * log(parseDouble(baseExponent[0])) > maxNum) {
        maxNum = parseDouble(baseExponent[1]) * log(parseDouble(baseExponent[0]));
        maxIdx = i;
      }
    }
    System.out.println(maxIdx + 1);
    System.out.println(nums.get(maxIdx));
    System.out.println(maxNum);
  }
}
