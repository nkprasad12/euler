package src.problems.problems01to10;

import java.lang.invoke.MethodHandles;

public class Problem1 {
  
  static void exec() {
    System.out.println(MethodHandles.lookup().lookupClass());

    int sum = 0;
    for (int i = 1; i < 1000; i++) {
      if (i % 3 == 0 || i % 5 == 0) {
        sum += i;
      }
    }
    System.out.println(sum);
  }
}