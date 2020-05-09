package problems.problems001to100.problems011to020;

import java.lang.invoke.MethodHandles;
import utils.generators.Generators;
import utils.numbers.BigNumber;

public class Problem20 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(solution());
  }

  public static String solution() {
    return Generators.from(BigNumber.factorialOf(100).digits())
        .reducing(0, (sum, next) -> sum + next)
        .lastValue()
        .toString();
  }
}
