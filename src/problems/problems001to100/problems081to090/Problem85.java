package problems.problems001to100.problems081to090;

import static utils.generators.base.tuples.Tuples.pair;

import java.lang.invoke.MethodHandles;
import utils.generators.base.tuples.Tuples.Pair;

public class Problem85 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(slowution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String slowution() {
    long closestToTwoMillion = 2000000;
    long area = 0;
    for (int m = 1; m < 1415; m++) {
      for (int n = m; n < 1415; n++) {
        long numSolutions = numRectangles(m, n);
        long diff = 2000000 - numSolutions;
        if (Math.abs(diff) < closestToTwoMillion) {
          closestToTwoMillion = Math.abs(diff);
          area = m * n;
        }
      }
    }
    return Long.toString(area);
  }

  // M x N big rectangle
  // for 1 <= m <= M and 1 <= n <= N
  // (M - m + 1) * (N - n + 1) rectangles of size m x n
  //
  // S(N, M) = Sum Sum (M - m + 1) * (N - n + 1)
  // = M (M + 1) N (N + 1) / 4
  //
  // M * (M + 1) / 4 + K
  // S(N, M) = K * N (N + 1) = L
  //
  // S(N, M) = L, then for fixed M,
  // N = (-1 + root(1 + 16L / (M (M + 1))) / 2
  public static String solution() {
    int target = 2000000;
    int closest = target;
    int areaOfClosest = 0;
    for (int n = 1; n < target; n++) {
      Pair<Integer, Integer> diffAndArea = closestDiffAndGridArea(target, n);
      if (diffAndArea == null) {
        break;
      }
      if (diffAndArea.first() < closest) {
        closest = diffAndArea.first();
        areaOfClosest = diffAndArea.second();
        System.out.println(diffAndArea + ", n = " + n);
      }
    }
    return Integer.toString(areaOfClosest);
  }

  static Pair<Integer, Integer> closestDiffAndGridArea(int L, int M) {
    double frac = 16.0 * L / (M * (M + 1));
    double root = Math.sqrt(1 + frac);
    int N = (int) ((root - 1) / 2);
    if (N <= 1) {
      return null;
    }
    int nDiff = (int) Math.abs(L - numRectangles(M, N));
    int nPlusOneDiff = (int) Math.abs(L - numRectangles(M, N + 1));
    return nDiff < nPlusOneDiff ? pair(nDiff, M * N) : pair(nPlusOneDiff, M * (N + 1));
  }

  static long numRectangles(int M, int N) {
    return M * (M + 1) * N * (N + 1) / 4;
  }
}
