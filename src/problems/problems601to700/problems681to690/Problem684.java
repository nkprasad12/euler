package problems.problems601to700.problems681to690;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import utils.numbers.NumericUtils;

public class Problem684 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    // 1 2 3 4 5 6 7 8 9 -> (2 - 1) (3 - 1) ... (10 - 1) = Sum (2 through 10) * 1 - 9
    // 19 29 39 49 59 69 79 89 99 -> (20 - 1) (30 - 1) ... (100 - 1) = Sum (2 through 10) * 10 - 9
    // 199 299 399 499 599 699 799 899 999 -> (200 - 1) (300 - 1) ... (1000 - 1) = Sum (2 through
    // 10) * 100 - 9 = 44 * 10^(numberOfDigits-1) - 9
    // S(27)

    // S(N) in base 9???
    // N / 9 = m
    // from k = 0 to m - 1 of
    // 44 * 10^k - 9
    // = (44 * 10^0 - 9) + (44 * 10^1 - 9) + (44 * 10^2 - 9) + ... + (44 * 10^(m - 1) - 9)
    // = -------------------------------------m total terms -----------------------------------
    // = 44 * (10^0 + 10^1 + ... + 10^(m - 1)) - 9 * m
    // = 44 * (10^m - 1) / 9 - 9 * m

    // N % 9 = r
    // sum 10^(m-1) - 1 + 10^(m), 10^(m-1) - 1 + 2* 10^(m), ... , 10^(m-1) - 1 + r* 10^(m)
    // = r * (10^(m-1) - 1) + sum_1^r * 10^m

    // List<Long> fibs = fibsUpTo(90);
    // long mod = 1000000007l;
    // long result = 0l;
    // fibs.remove(0);
    // fibs.remove(0);
    // System.out.println(fibs.size());
    // for (Long fib : fibs) {
    //     System.out.println(fib);
    //     result = (result + S(fib)) % mod;
    // }

    System.out.println(s(27));
    return Long.toString(0);
  }

  public static long S(long N) {
    long result = 0;
    result += Sdiv9(N);
    long m = (N / 9) * 9 + 1;
    long mod = 1000000007l;
    for (; m <= N; m++) {
      result = (result + s(m)) % mod;
    }
    return result;
  }

  public static long s(long M) {
    long r = M % 9;
    long m = M / 9;
    long mod = 1000000007l;
    return (((r + 1) * (NumericUtils.powerModN(10, m, mod))) % mod - 1) % mod;
  }

  public static long Sdiv9(long N) {
    long m = N / 9;
    long mod = 1000000007l; // 1000000007
    long tenPow = NumericUtils.powerModN(10, m, mod);
    // ................................ I don't think this division works mod 1000000007
    return ((54 * (((tenPow - 1) % mod) / 9) % mod) - ((9 * m) % mod)) % mod;
  }

  public static List<Long> fibsUpTo(int n) {
    ArrayList<Long> fibs = new ArrayList<Long>();
    fibs.add(0l);

    if (n == 0) {
      return fibs;
    } else if (n == 1) {
      fibs.add(1l);
    } else {
      fibs.add(1l);
      for (int i = 2; i < n + 1; i++) {
        fibs.add(fibs.get(i - 1) + fibs.get(i - 2));
      }
    }
    return fibs;
  }
}
