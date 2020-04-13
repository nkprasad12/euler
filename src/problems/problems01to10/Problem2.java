package src.problems.problems01to10;

import java.lang.invoke.MethodHandles;

public class Problem2 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
 
    long low = 1;
    long high = 2;
    long sum = 0;

    while (high < 4000000) {
      sum += high % 2 == 0 ? high : 0;
      long tmp = low + high;
      low = high;
      high = tmp;
    }
    System.out.println(sum);
  }

}