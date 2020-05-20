package problems.problems101to200.problems101to110;

import java.lang.invoke.MethodHandles;

public class Problem110 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    return Problem108.diophantineReiprocals(4000000l);
  }
}