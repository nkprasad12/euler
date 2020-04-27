package problems.problems091to100;

import static utils.numbers.BigNumber.fromLong;

import java.lang.invoke.MethodHandles;
import utils.numbers.BigNumber;

public class Problem97 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    BigNumber prime =
        lastDigitsOfPower(fromLong(2), 7830457).multiplyBy(fromLong(28433l)).addTo(fromLong(1l));
    System.out.println(lastDigitsOf(prime));

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  private static BigNumber lastDigitsOfPower(BigNumber base, long exponent) {
    if (exponent == 0) {
      return fromLong(1l);
    } else if (exponent % 2 == 0) {
      BigNumber halfPow = lastDigitsOfPower(base, exponent / 2);
      BigNumber power = halfPow.multiplyBy(halfPow);
      return lastDigitsOf(power);
    } else {
      BigNumber power = lastDigitsOfPower(base, exponent - 1).multiplyBy(base);
      return lastDigitsOf(power);
    }
  }

  private static BigNumber lastDigitsOf(BigNumber number) {
    return new BigNumber(number.digits().subList(0, Math.min(10, number.digits().size())));
  }
}
