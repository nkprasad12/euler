package problems.problems041to050;

import java.lang.invoke.MethodHandles;
import utils.numbers.NumericUtils;

public class Problem45 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    long n = 286;
    while (true) {
      long t_n = (n * (n + 1)) / 2;
      Long t = NumericUtils.inverseTriangle(t_n);
      Long p = NumericUtils.inversePentagon(t_n);
      Long h = NumericUtils.inverseHexagon(t_n);
      if (p != null && h != null) {
        Long p_n = (p * (3 * p - 1)) / 2;
        Long h_n = h * (2 * h - 1);
        if (h_n.equals(p_n) && h_n.equals(t_n)) {
          System.out.println("p: " + p + ", h: " + h + ", t: " + t);
          System.out.println("P_n: " + h_n + ", H_n: " + p_n + ", T_n: " + t_n);
        }
        break;
      }
      n++;
    }
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }
}
