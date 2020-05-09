package problems.problems001to100.problems001to010;

import java.lang.invoke.MethodHandles;
import utils.generators.Generators;

public class Problem1 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(solution());
  }

  public static String solution() {
    return Generators.range(1, 999)
        .filter(i -> i % 3 == 0 || i % 5 == 0)
        .reducing(0, (sumSoFar, next) -> sumSoFar + next)
        .lastValue()
        .toString();
  }
}
