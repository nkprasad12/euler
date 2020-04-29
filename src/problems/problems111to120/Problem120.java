package problems.problems111to120;

import static utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;

public class Problem120 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    // Similar to the analysis in Problem 123, we see that the remainder in division of
    // (a - 1)^n + (a + 1)^n is equal to 2 (n even) and 2 a n mod a^2 (n odd).
    //  
    // Note that if n = a + k, then 
    // 2 a (a + k) mod a^2 = 2 a^2 + 2 a k (mod a^2) = 2 a k mod a^2, 
    // However, note that if a is odd, then a + k has a different parity
    // than k. Therefore, we need to check n until 2a.

    // For 3 <= a <= 1000
    return range(3l, 1000l)
        .map(
            a -> 
                // Try out n in range 0 to 2a - 1. Note n = 0 produces 0 and is skipped.
                range(1l, 2 * a - 1)
                    // r = 2 if n is even and 2 a n mod a^2 if odd
                    .map(n -> n % 2 == 0 ? 2 : (2l * n * a) % (a * a))
                    // Get r_max, the maximum r
                    .max(r -> r)
                    .get())
        // Sum of all r_max
        .reducing(0l, (sum, r) -> sum + r)
        .lastValue()
        .toString();
  }
}
