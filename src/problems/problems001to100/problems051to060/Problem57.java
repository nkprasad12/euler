package problems.problems001to100.problems051to060;

import static utils.numbers.BigNumber.fromLong;

import java.lang.invoke.MethodHandles;
import utils.numbers.BigNumber;

public class Problem57 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    BigNumber p = fromLong(3);
    BigNumber q = fromLong(2);
    int numLongerNumerator = 0;
    for (int n = 1; n < 1000; n++) {
      // F_0 = 1 + 1 / (1 + 1)
      // F_n+1 = 1 + 1 / (1 + F_n)
      //       = 1 + 1 / (1 + p / q)
      //       = 1 + q / (p + q)
      //       = (p + 2q) / (p + q)

      // p / q -> (p + 2q) / (p + q)
      BigNumber tmp = q;
      q = p.addTo(q);
      p = tmp.addTo(q);
      // There are always relatively prime.
      // gcd(p + 2q, p + q):
      // p + 2q - (p + q) = q
      // p + q - q = p
      // which is now gcd(p, q)
      // By induction, this reduces to gcd(1, 1) = 1
      if (p.digits().size() > q.digits().size()) {
        numLongerNumerator++;
      }
    }
    return String.valueOf(numLongerNumerator);
  }
}
