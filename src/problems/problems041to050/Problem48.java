package src.problems.problems041to050;

import static src.utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;
import src.utils.numbers.NumericUtils;

public class Problem48 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    long mod = 10000000000l;
    range(1l, 1000l)
        .map(k -> NumericUtils.powerModN(k, k, mod))
        .reducing(0l, (sum, next) -> (sum + next) % mod)
        .lastValue()
        .print();

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }
}
