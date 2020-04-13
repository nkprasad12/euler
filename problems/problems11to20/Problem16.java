package problems.problems11to20;

import java.lang.invoke.MethodHandles;

import utils.numbers.BigNumber;

public class Problem16 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(BigNumber.fromLong(2).toPower(1000).sumOfDigits());
  }
}