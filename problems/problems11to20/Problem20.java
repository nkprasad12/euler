package problems.problems11to20;

import java.lang.invoke.MethodHandles;

import utils.numbers.BigNumber;

public class Problem20 {
  
  public static void exec() {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(BigNumber.factorialOf(100).sumOfDigits());
  }
}