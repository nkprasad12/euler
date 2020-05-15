package problems.problems001to100.problems061to070;

import static utils.generators.Generators.from;
import static utils.numbers.BigNumber.fromLong;

import java.lang.invoke.MethodHandles;
import utils.numbers.BigNumber;

public class Problem65 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    BigNumber num = fromLong(1); // r_100 = 1
    BigNumber den = fromLong(1);
    for (int i = 99; i >= 1; i--) {
      // [2; 1, 2, 1, 1, 4, 1, 1, 6, ....]
      long r = 1;
      if (i == 1) {
        r = 2;
      } else if (i % 3 == 0) {
        r = 2 * (i / 3);
      }
      // p / q -> (p * r + q) / p
      BigNumber tmp = num.multiplyBy(fromLong(r)).addTo(den);
      den = num;
      num = tmp;
    }
    return from(num.digits()).reduce(0, (sum, digit) -> sum + digit).toString();
  }
}

// [r1; r2, r3, r4]
//        1
// r1 + -------
//      r2 + 1
//           ---------
//           r3 + 1
//                ----------
//                r4 + 1
// =
//        1
// r1 + -------
//      r2 + 1
//           ---------
//           r3 + 1
//                ----------
//                p0 / q0
// =
//        1
// r1 + -------
//      r2 + 1
//           ---------
//           (p0 * r3 + q) / p0
// =
//        1
// r1 + -------
//      r2 + 1
//           ---------
//           p1 / q1
// r + 1 / (p / q)
//
