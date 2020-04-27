// import static utils.generators.Generators.from;
// import static utils.generators.Generators.range;
// import static utils.generators.Generators.fromCartesianProductOf;

import java.lang.invoke.MethodHandles;

public class ProblemN {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    return "";
  }
}
