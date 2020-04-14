package src.problems.problems11to20;

import java.lang.invoke.MethodHandles;

import src.utils.generators.Generators;
import src.utils.numbers.BigNumber;

public class Problem16 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(
        Generators.from(BigNumber.fromLong(2).toPower(1000).digits())
            .reduce(0, (sum, next) -> sum + next));
  }
}