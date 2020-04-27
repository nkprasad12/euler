package problems.problems021to030;

import java.lang.invoke.MethodHandles;
import utils.generators.Generators;
import utils.numbers.BigNumber;

public class Problem30 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Generators.range(2l, 999999l)
        .filter(
            n ->
                n
                    == (long)
                        Generators.from(BigNumber.fromLong(n).digits())
                            .map(d -> d * d * d * d * d)
                            .reduce(0, (a, b) -> a + b))
        .reducing(0l, (sum, next) -> sum + next)
        .lastValue()
        .print();
  }
}
