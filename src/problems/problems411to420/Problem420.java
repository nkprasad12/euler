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
    long numValid = 0;
    long N = 200000;
    for (long a = 1; a * a < N; a++) {
      for (long d = 1; d <= a; d++) {
        long aSq = a * a;
        long dSq = d * d;
        long ad = a * d;

        long traceBound = N - aSq - dSq;
        traceBound = traceBound % 2 == 0 ? traceBound / 2 - 1 : traceBound / 2;
        long detBound = ad - 1;
        long bcMax = Math.min(traceBound, detBound);
        if (bcMax < 1) {
          continue;
        }
        long diagFactor = a == d ? 1 : 2;
        for (long bc = 1; bc <= bcMax; bc++) {
          // Check if (a - d)^2 + 4bc is a perfect square
          long denomSquare = (a - d) * (a - d) + 4 * bc;
          long denom = (long) Math.sqrt(denomSquare);
          if (denom * denom != denomSquare) {
            continue;
          }
          long det = ad - bc;
          long e = aSq - det + bc;
          if (e <= 0 || e % denom != 0) {
            continue;
          }
          long h = dSq - det + bc;
          if (h <= 0 || h % denom != 0) {
            continue;
          }
          for (long c = 1; c * c <= bc; c++) {
            if (bc % c != 0) {
              continue;
            }
            long b = bc / c;
            if (aSq + dSq >= N - 2 * bc) {
              continue;
            }
            long f = b * (a + d);
            if (f <= 0 || f % denom != 0) {
              continue;
            }
            long g = c * (a + d);
            if (g <= 0 || g % denom != 0) {
              continue;
            }
            long multiplicity = diagFactor * (b == c ? 1 : 2);
            numValid += multiplicity;
            // e /= denom;
            // f /= denom;
            // g /= denom;
            // h /= denom;
            // long trB = a * a + d * d + 2 * b * c;
            // long trC = e * e + h * h + 2 * f * g;
            // String bMatStr = matrixFormat(a, b, c, d);
            // String cMatStr = matrixFormat(e, f, g, h);
            // System.out.println("Found B matrix, multiplicity " + multiplicity + ":\n" + a + " " +
            // b + "\n" + c + " " + d);
            // System.out.println("Paired C matrix: \n" + e / denom + " " + f / denom + "\n" + g /
            // denom + " " + h / denom + "\n");
          }
        }
      }
    }
    return String.valueOf(numValid);
  }

  static String matrixFormat(long a, long b, long c, long d) {
    return a + " " + b + "\n" + c + " " + d;
  }
}
