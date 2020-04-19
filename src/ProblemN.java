package src;
// package src.problems.problemsXtoX+9

import java.lang.invoke.MethodHandles;

public class ProblemN {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }
}
