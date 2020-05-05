import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.linalg.Matrix;

class Sandbox {

  public static void main(String[] args) throws FileNotFoundException {
    // PrintStream o = new PrintStream(new File("nEq100Solution.txt"));
    System.out.println(MethodHandles.lookup().lookupClass());
    // PrintStream console = System.out;
    // System.setOut(o);

    HashMap<Matrix, List<Matrix>> squares = new HashMap<>();
    long max = 100;
    for (long a = 1; a < max; a++) {
      for (long d = 1; d < max - a; d++) {
        if (a * a + d * d + 2 >= max) {
          continue;
        }
        for (long k = 0; k <= max * max - 1; k++) {
          long[][] tmp = new long[2][2];
          tmp[0][0] = a;
          tmp[1][1] = d;
          tmp[0][1] = k / max + 1;
          tmp[1][0] = k % max + 1;
          Matrix m = Matrix.from(tmp);
          Matrix m2 = m.multiplyBy(m);
          if (m2.trace() < max) {
            if (squares.containsKey(m2)) {
              squares.get(m2).add(m);
              System.out.println("Square:");
              System.out.println(m2);
              System.out.println("Roots:");
              System.out.println(squares.get(m2));
            } else {
              ArrayList<Matrix> list = new ArrayList<>();
              list.add(m);
              squares.put(m2, list);
            }
          }
        }
      }
    }
    // System.setOut(console);
  }
}
