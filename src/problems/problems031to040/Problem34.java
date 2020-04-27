package problems.problems031to040;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import utils.numbers.BigNumber;

public class Problem34 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Factorial factorials = new Factorial(9);

    // There can't be curious numbers above 10 million since 9! * 8 is less than this.
    System.out.println(
        curiousNumbersUpTo(10000000l, factorials).stream()
            .reduce(0l, (subtotal, next) -> subtotal + next));
  }

  static ArrayList<Long> curiousNumbersUpTo(long max, Factorial factorials) {
    ArrayList<Long> curiousNumbers = new ArrayList<>();
    for (long i = 10; i < max; i++) {
      if (isCurious(i, factorials)) {
        curiousNumbers.add(i);
      }
    }
    return curiousNumbers;
  }

  static boolean isCurious(long n, Factorial factorials) {
    return n == digitFactorialSum(n, factorials);
  }

  static long digitFactorialSum(long n, Factorial factorials) {
    return BigNumber.fromLong(n).digits().stream()
        .map(d -> factorials.of(d))
        .reduce(0, (subtotal, next) -> subtotal + next);
  }

  private static final class Factorial {

    int[] facs;

    Factorial(int max) {
      facs = new int[max + 1];
      facs[0] = 1;
      for (int i = 1; i <= max; i++) {
        facs[i] = i * facs[i - 1];
      }
    }

    int of(int num) {
      return facs[num];
    }
  }
}
