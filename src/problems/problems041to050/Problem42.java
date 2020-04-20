package src.problems.problems041to050;

// package src.problems.problemsXtoX+9

import src.utils.generators.Generators;
// import static src.utils.generators.Generators.fromCartesianProductOf;
import src.utils.numbers.NumericUtils;

import java.lang.invoke.MethodHandles;

public class Problem42 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    Generators.fromTextFile("problem42.txt", ",")
        .map(str -> str.substring(1, str.length() - 1))
        .map(str -> GetAlphabeticalValue(str))
        .filter(v -> NumericUtils.inverseTriangle(v) != null)
        .reducing(0, (ct, next) -> ct + 1)
        .lastValue()
        .print();

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }


  static int GetAlphabeticalValue(String name) {
    char[] nameChars = name.toLowerCase().toCharArray();

   int initialAscii = ((int) 'a') - 1;
   int sumValue = 0;
   for (char letter : nameChars) {
     sumValue += ((int) letter) - initialAscii;
   }

   return sumValue;
  }
}
