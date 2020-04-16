package src.problems.problems21to30;

import java.lang.invoke.MethodHandles;

import src.utils.generators.Generators;

public class Problem24 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    
    Generators.permutationsOf(Generators.range(0, 9).list())
        .addIndices()
        .whileTrue(indexAndValue -> indexAndValue.index() < 1000000)
        .map(indexAndValue -> Generators.from(indexAndValue.value()))
        .lastValue()
        .reducing("", (a, b) -> a + b)
        .printLast();
  }
}