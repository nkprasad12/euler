package problems.problems411to420;

import java.lang.invoke.MethodHandles;

public class Problem420 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    /*
     * Let B =
     * (a, b)
     * (c, d)
     *
     * Then B^2 =
     * (a^2 + bc, b(a + d))
     * (c(a + d), d^2 + bc)
     *
     * For any such matrix B with det(B) > 0, there is a pair matrix C such that C^2 = B^2,
     * and det(C) = - det(C) < 0; C =
     * 1 / m (a^2 - det(B) + bc, b(a + d))
     *       (c(a + d), d^2 - det(B) + bc)
     *
     * where m = sqrt((a - d)^2 + 4bc)
     *
     * This solution essentially checks the matrices B such that tr(B^2) = a^2 + d^2 + 2bc < N,
     * and determines whether the corresponding C matrix has positive integer entries.
     */

    long numValid = 0;
    long N = 10000000;
    // We require tr(B^2) < N, so it's safe to only check a < root(N) <=> a^2 < N to avoid floating
    // point operations for performance reasons.
    for (long a = 1; a * a < N; a++) {
      // We only need to check d <= a. If we find a solution where a > d, by symmetry we know that
      // the matric with a and d swapped is also a solution (and vice versa).
      for (long d = 1; d <= a; d++) {
        long aSq = a * a;
        long dSq = d * d;
        long ad = a * d;
        // This is one bound on bc that comes from the trace restriction given in the problem:
        // tr(B^2) < N <=> 2bc < N - a^2 - d^2
        long traceBound = N - aSq - dSq;
        traceBound = traceBound % 2 == 0 ? traceBound / 2 - 1 : traceBound / 2;
        // By design, we only need to consider matrics B with det(B) > 0, which gives bc < ad
        long detBound = ad - 1;
        long bcMax = Math.min(traceBound, detBound);
        if (bcMax < 1) {
          continue;
        }
        // If a > d, for any solution we find we also know there's a solution where d and a are
        // flipped.
        long diagFactor = a == d ? 1 : 2;
        // This is related to the factor m mentioned above. Note that m = sqrt((a - d)^2 + 4bc)
        // must be an integer for the C matrix to have positive integer entries. This means that
        // Let k = a - d; then this requires k^2 + 4bc = m^2.
        long k = a - d;
        // We know the bottom right entry of the C matrix is d^2 - ad + 2bc, and this must be
        // positive, so bc >= d * k / 2 + 1, so compute the first integer greater than
        // sqrt(k^2 + 4 (dk / 2 + 1)).
        long m = (long) Math.ceil(Math.sqrt(k * k + 4 * ((d * k) / 2 + 1)));
        // If m and k are not both even or not both odd, note that m^2 - k^2 is not divisible by
        // 4, then bc would not be an integer.
        if (m % 2 != k % 2) {
          m++;
        }
        long bcStart = (m * m - k * k) / 4;
        long bc = bcStart;
        while (bc <= bcMax) {
          long det = ad - bc;
          // Compute the top left entry of the C matrix; this needs to be a positive integer.
          long e = aSq - det + bc;
          if (e % m != 0) {
            // m += 2 because we need m to have the same parity as k. Then, note that if
            // m^2 = k^2 + 4bc, then (m + 2)^2 = m^2 + 4m + 4 = k^2 + 4m + 4 + 4bc =
            // k^2 + 4(bc + m + 1), so the next valid value of bc so that m is an integer is
            // bc + m + 1.
            bc += m + 1;
            m += 2;
            continue;
          }
          // Compute the bottom right entry of the C matrix; this needs to be a positive integer.
          long h = dSq - det + bc;
          if (h % m != 0) {
            bc += m + 1;
            m += 2;
            continue;
          }
          // Get b and c from bc. Note that we only need to have c up to sqrt(bc), because
          // by symmetry if we have a solution where b > c, then the matrix where b and c
          // are swapped is also automatically a solution.
          for (long c = 1; c * c <= bc; c++) {
            if (bc % c != 0) {
              continue;
            }
            long b = bc / c;
            // Verify the remaining elements of the C matrix are positive integers.
            long f = b * (a + d);
            if (f % m != 0) {
              continue;
            }
            long g = c * (a + d);
            if (g % m != 0) {
              continue;
            }
            // Add solutions to the count.
            long multiplicity = diagFactor * (b == c ? 1 : 2);
            numValid += multiplicity;
          }
          bc += m + 1;
          m += 2;
        }
      }
    }
    return String.valueOf(numValid);
  }
}
