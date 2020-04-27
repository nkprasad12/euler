package problems.problems011to020;

import java.lang.invoke.MethodHandles;
import utils.generators.Generators;
import utils.numbers.BigNumber;

public class Problem16 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(
        Generators.from(BigNumber.fromLong(2).toPower(1000).digits())
            .reduce(0, (sum, next) -> sum + next));
  }
}
