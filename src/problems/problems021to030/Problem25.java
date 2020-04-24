package src.problems.problems021to030;

import java.lang.invoke.MethodHandles;
import src.utils.numbers.BigNumber;

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
}
