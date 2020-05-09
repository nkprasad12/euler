package problems.problems101to200.problems111to120;

import java.lang.invoke.MethodHandles;

public class Problem112 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    long n = 1;
    long ct = 0;
    while (ct * 100 != n * 99) {
      n++;
      ct += isBouncy(n) ? 1 : 0;
    }
    return Long.toString(n);
  }

  static boolean isBouncy(long n) {
    boolean increasing = true;
    boolean decreasing = true;
    long last = n % 10;
    n /= 10;
    while (n > 0 && (decreasing || increasing)) {
      long d = n % 10;
      if (d > last) {
        decreasing = false;
      }
      if (d < last) {
        increasing = false;
      }
      last = d;
      n /= 10;
    }
    return !decreasing && !increasing;
  }
}
