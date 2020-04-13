package problems.problems21to30;

import java.lang.invoke.MethodHandles;

import utils.generators.Generators;

public class Problem24 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    
    Generators.permutationsOf(Generators.range(0, 9).list())
        .addIndices()
        .until(indexAndValue -> indexAndValue.index() == 999999)
        .map(indexAndValue -> Generators.from(indexAndValue.value()))
        .lastValue()
        .reducing("", (a, b) -> a + b)
        .printLast();
  }
}