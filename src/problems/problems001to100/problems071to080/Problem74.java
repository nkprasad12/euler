package problems.problems001to100.problems071to080;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;

public class Problem74 {

   public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    int[] memo = new int[fac(9) * 6 + 1];
    int[] lengths = new int[fac(9) * 6 + 1];
    int result = 0;
    for (int i = 0; i < 1000000; i++) {
        HashSet<Integer> chain = new HashSet<>();
        int next = i;
        int nonRepeatingTerms = 0;
        while (!chain.contains(next)) {
            if (lengths[next] == (60 - nonRepeatingTerms)) {
              System.out.println(i + " " + nonRepeatingTerms + " " + next);
            }
            nonRepeatingTerms++;
            chain.add(next);
            if (memo[next] == 0) {
              memo[next] = next(next);
            }
            next = memo[next];
        }
        lengths[i] = nonRepeatingTerms;
        if (nonRepeatingTerms == 60) {
            result++;
        }
    }
    return Integer.toString(result);
  }

  // private static final void sol() {
  //   int result = 0;
  //   Node[] nodes = new Node[1000000];
  //   for (int i = 0; i < 1000000; i++) {
  //     if (nodes[i] != null) {
  //       if (nodes[i].length == 60) {
  //         result++;
  //       }
  //       continue;
  //     }
  //     nodes[i] = new Node(i);
      
  //   }
  //   System.out.println(result);
  // }

  // private static final class Node {

  //   final int value;
  //   Node next = null;
  //   int length;

  //   private Node(int value) {
  //     this.value = value;
  //     this.length = -1;
  //   }
  // }

  private static final int next(int number) {
    int result = 0;
    while (number > 0) {
      result += fac(number % 10);
      number /= 10;      
    }
    return result;
  }

  private static final int fac(int digit) {
    switch (digit) {
        case 0: return 1;
        case 1: return 1;
        case 2: return 2;
        case 3: return 6;
        case 4: return 24;
        case 5: return 120;
        case 6: return 720;
        case 7: return 5040;
        case 8: return 40320;
        case 9: return 362880;
    }
    throw new RuntimeException("Not a digit, nerd");
  }
}