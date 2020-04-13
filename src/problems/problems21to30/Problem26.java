package src.problems.problems21to30;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

import src.utils.generators.Generators;
import src.utils.generators.Tuples;

public class Problem26 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Generators.range(2, 999)
        .map(i -> Tuples.pair(i, repeatingPart(1, i).length()))
        .reducing(
            Tuples.pair(-1, -1),
            (maxSoFar, next) -> next.second() > maxSoFar.second() ? next : maxSoFar)
        .map(pair -> pair.first())
        .printLast();
  }

  static String repeatingPart(int numerator, int denominator) {
    int d = numerator;
    HashMap<Integer, Integer> ds = new HashMap<Integer, Integer>();
    String str = "";
    boolean record = false;
    while (ds.getOrDefault(d, 0) < 2) {
      if (!ds.containsKey(d)) {
        ds.put(d, 1);
      } else {
        ds.put(d, ds.get(d)+1);
        record = true;
      }
      int zeros = -1;
      while (d < denominator) {
        zeros++;
        d *= 10;
      }
      for (int i = 0; i < zeros; i++) {
        if (record) {
          str += "0";
        }
      }
      int ct = 0;
      while (d > denominator) {
        d -= denominator;
        ct++;
      }
      if (record) {
        str += ct;        
      }
    }
    return str;  
  }
}