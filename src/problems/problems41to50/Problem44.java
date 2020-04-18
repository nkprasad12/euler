package src.problems.problems41to50;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;

import src.utils.generators.Generators;

public class Problem44 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    int iMax = 5000;

    HashMap<String, Long> pentagonalDifferences = new HashMap<String, Long>();

    List<Long> pentagonals = Generators.range(1, iMax).map(i -> pentagonalNumber(i)).list();
    

    for (int i = 0; i<pentagonals.size(); i++) {
      for (int j = i; j<pentagonals.size(); j++) {
        long sum = pentagonals.get(i) + pentagonals.get(j);
        long difference = pentagonals.get(j) - pentagonals.get(i);
        if (isPentagonal(difference) && isPentagonal(sum)) {
          pentagonalDifferences.put(pentagonals.get(i) + ":" + pentagonals.get(j), difference);
        }
      }
    }

    long minValue = Generators.from(pentagonalDifferences.values()).reduce(Long.MAX_VALUE, (a, b) -> Math.min(a, b));
    System.out.println(minValue);
  }

  static long pentagonalNumber(long i) {
    long result = (i * (3 * i - 1)) / 2;
    if (i % 1000 == 0) {
      System.out.println("Computed pentagonal: " + i + "-" + result);
    }
    return result;
  }

  static boolean isPentagonal(long k) {
    long sum = 1 + 24 * k;
    long root = (long) Math.sqrt(sum);
    if (sum != root * root) {
      return false;
    }
    return (root  % 6) == 5;
  } 
}