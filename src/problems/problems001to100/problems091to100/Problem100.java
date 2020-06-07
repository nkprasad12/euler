package problems.problems001to100.problems091to100;

import static utils.numbers.BigNumber.fromLong;

import java.lang.invoke.MethodHandles;
import utils.numbers.BigNumber;
import utils.numbers.NumericUtils;

public class Problem100 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {

    for (long r = 1; r <= 10000000l; r++) {
      long k2 = 1 + 8 * r * r;
      if (NumericUtils.isPerfectSquare(k2)) {
        long k = (long) Math.sqrt(k2);
        long b = (1 + 2 * r + k) / 2;
        System.out.println("r, b, k: " + r + ", " + b + ", " + k);
        System.out.println("(b + r) / b: " + ((b + r * 1.0) / b));
      }
    }

    BigNumber p = fromLong(1);
    BigNumber q = fromLong(1);
    for (int i = 0; i < 32; i++) {
      if (i % 2 != 0) {
        System.out.println(p + " " + q);
      }
      BigNumber tmp = p.addTo(q);
      p = tmp.addTo(q);
      q = tmp;
    }

    // k = 886731088897
    // r = sqrt((886731088897^2 - 1) / 8) = 313506783024
    // b = (1 + 2r + k) / 2 = 756872327473

    /*double r2 = Math.sqrt(2);
    long ans = 0;
    for (long n = (long) 1; n < Long.MAX_VALUE; n++) {
        long minB = (long) (n / r2) - 10l;
        long maxB = (long) (n / r2) + 10l;
        for (long b = minB; b < maxB; b++) {
            long num = b*(b-1);
            long denom = n*(n-1);

            if (denom % num == 0 && denom / num == 2) {
                ans = b;
                break;
            }
        }
        if (ans > 0) {
            break;
        }
    }
    System.out.println(ans);*/

    return "";
  }
}

/*
  b = [(1 + 2r) + sqrt(1 + 8r^2)] / 2

  So - when is sqrt(1 + 8r^2) an integer?
  Let 1 + 8r^2 = k^2

  b = (1 + 2r + k) / 2

  b = (1 + 2*sqrt((k^2 - 1)/8) + k) / 2

  0 = -b^2 + r^2 +b+2br-r


         1 + sqrt( 1 + 2(n^2 - n) )
  b =   ------------------------------
                    2

 When is sqrt(1 + 2(n^2 - n)) an integer?
  1 + 2(n^2 - n) = k^2 = (1 + 2z)^2 = 1 + 4z + 4z^2
        n(n - 1) = 2z(z + 1)
  2(n^2 -n) = k^2 - 1

  k=29 when n=21
  k=169 when n=120

           1 + sqrt( 1 + 2(k^2 - 1) )
  n =   ------------------------------
                    2

  k = sqrt((m^2 + 1) / 2)

  m = sqrt(2a^2 - 1)

  a = sqrt((y^2 - 1) / 2)

  y = sqrt(2c^2 + 1)

  c = sqrt((d^2 - 1) / 2)




  ‽ = sqrt(2c^2 + 1)
*/
