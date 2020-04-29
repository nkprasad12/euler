package problems.problems121to130;

import static utils.generators.Generators.from;
import static utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;

public class Problem121 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    int turns = 15;
    // Total number of possible sequences of disc selections: 2^turns
    int sequences = range(1, turns).reduce(1, (product, next) -> product * 2);
    // Total number of ways to choose disks - (turns + 1)!
    long totalWays = range(2, turns + 1).reduce(1l, (fac, n) -> fac * n);
    // One for every possible sequence
    return range(0, sequences - 1)
        // Map the int to binary, with `turns` bits.
        // The ith value in the array is true iff the ith disk taken was Red.
        .map(n -> range(0, turns - 1).map(i -> (n & (1 << i)) != 0).list())
        // Consider only sequences where the player wins the game (gets a majority Red discs).
        .filter(bits -> from(bits).reduce(0, (ct, next) -> next ? ct + 1 : ct) >= turns / 2 + 1)
        // Map each winning sequence to the number of ways that sequence was possible.
        .map(
            bits -> 
                from(bits)
                    .addIndices()
                    // In every round, there's only one Red disc, so if the bit is true, there's
                    // one way for that round to occur. Otherwise, for the (1-indexed) ith round,
                    // there are k Blue discs. Indices are 0-indexed, so i + 1 ways.
                    .mapPair((i, bit) -> bit ? 1l : i + 1l)
                    // Each selection is independent. Multiply the possible ways to get the
                    // required selection at each turn to get the total number of ways the entire
                    // sequence was possible.
                    .reduce(1l, (ways, next) -> ways * next))
        // Each sequence is independent. Add the number of ways each winning sequence is possible 
        // to get the total number of ways to win the game.
        .reducing(0l, (ways, next) -> ways + next)
        .lastValue()
        // If there's a probability p of winning with an entry fee of 1, the expected value of the
        // game for the bank is 1 / p. Therefore, the bank can afford to set the payout at 1 / p,
        // rounded down. In this case, p = waysToWin / totalWays.
        .map(waysToWin -> totalWays / waysToWin)
        .toString();
  }
}
