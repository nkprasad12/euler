package problems.problems001to100.problems031to040;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import utils.generators.Generators;

public class Problem34 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  static String solution() {
    Factorial factorials = new Factorial(9);
    // There can't be curious numbers above 10 million since 9! * 8 is less than this.
    return Generators.range(10l, 9999999l)
        .filter(i -> isCurious(i, factorials))
        .reducing(0l, (sum, next) -> sum + next)
        .lastValue()
        .toString();
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
    int m = (int) n;
    int sum = 0;
    while (m > 0) {
      sum += factorials.of((int) m % 10);
      m /= 10;
    }
    return sum;
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
