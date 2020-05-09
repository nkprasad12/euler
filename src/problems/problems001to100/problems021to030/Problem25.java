package problems.problems001to100.problems021to030;

import static utils.generators.Generators.fromRecursion;
import static utils.generators.base.tuples.Tuples.pair;

import java.lang.invoke.MethodHandles;
import utils.numbers.BigNumber;

public class Problem25 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    BigNumber low = BigNumber.fromLong(1l);
    BigNumber high = BigNumber.fromLong(2l);
    int i = 3;
    while (high.digits().size() < 1000) {
      BigNumber tmp = low.addTo(high);
      low = high;
      high = tmp;
      i++;
    }
    System.out.println(i);
  }

  static String solution() {
    BigNumber low = BigNumber.fromLong(1l);
    BigNumber high = BigNumber.fromLong(2l);
    return fromRecursion(
            pair(low, high), (l, h) -> pair(h, l.addTo(h)), (l, h) -> h.digits().size() < 1000)
        .reduce(3, (ct, next) -> ct + 1)
        .toString();
  }
}
