package problems.problems001to100.problems061to070;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;

public class Problem62 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(wayFasterSolution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    long answer = 0;
    long n = 1;
    HashMap<Long, Integer> cubePermutations = new HashMap<>();
    long lim = 10;
    while (answer == 0) {
      long n3 = n * n * n;
      if (n3 < 0) {
        return "You suck";
      }
      if (n3 >= lim) {
        cubePermutations = new HashMap<>();
        lim *= 10;
      }
      boolean isNew = true;
      for (Long cube : cubePermutations.keySet()) {
        if (arePermutations(cube, n3)) {
          isNew = false;
          int val = cubePermutations.get(cube) + 1;
          if (val >= 5) {
            answer = cube;
          }
          cubePermutations.put(cube, val);
        }
      }
      if (isNew) {
        cubePermutations.put(n3, 1);
      }
      n++;
    }

    return Long.toString(answer);
  }

  static boolean arePermutations(long x, long y) {
    int[] digits = new int[10];
    while (x > 0) {
      digits[(int) (x % 10)]++;
      x /= 10;
    }
    while (y > 0) {
      digits[(int) (y % 10)]--;
      y /= 10;
    }
    for (int i = 0; i < 10; i++) {
      if (digits[i] != 0) {
        return false;
      }
    }
    return true;
  }

  private static String wayFasterSolution() {
    Node root = new Node(-1);
    Node latestLeaf = root;
    long n = 1;
    while (latestLeaf.count != 5) {
      long n3 = n * n * n;
      latestLeaf = addToTree(root, n3);
      n++;
    }
    return Long.toString(latestLeaf.originalCube);
  }

  private static Node addToTree(Node root, long cube) {
    long x = cube;
    int[] digits = new int[10];
    while (x > 0) {
      digits[(int) (x % 10)]++;
      x /= 10;
    }
    Node current = root;
    for (int d = 0; d < 10; d++) {
      for (int j = 0; j < digits[d]; j++) {
        if (current.children[d] == null) {
          current.children[d] = new Node(cube);
        }
        current = current.children[d];
      }
    }
    current.count++;
    return current;
  } 

  private static final class Node {

    private final long originalCube;
    private final Node[] children;
    private int count;

    private Node(long originalCube) {
      this.originalCube = originalCube;
      this.children = new Node[10];
      this.count = 0;
    }
  }
}
