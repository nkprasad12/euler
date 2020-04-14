package src.problems.problems11to20;

import java.lang.invoke.MethodHandles;

import src.utils.generators.Generators;
import src.utils.numbers.BigNumber;

public class Problem20 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Generators.from(BigNumber.factorialOf(100).digits())
        .reducing(0, (sum, next) -> sum + next)
        .printLast();
  }
}