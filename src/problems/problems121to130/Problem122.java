package problems.problems121to130;

import static utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Problem122 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  private static String solution() {
    // All the powers we haven't found yet. Note we don't care about n^1, because this
    // requires 0 multiplications and therefore doesn't contribute to the sum.
    Set<Long> required = range(2l, 200l).set();
    long sum = 0;
    // Start off with n^1, which requires 0 multiplications
    Node root = new Node(null, 1, 0);
    // Queue of leaf nodes. Start with just root
    Queue<Node> leaves = new LinkedList<Node>();
    leaves.add(root);
    // While there are powers in the range we still need to compute
    while (!required.isEmpty()) {
      // Get the first leaf in the Queue.
      Node current = leaves.poll();
      Node multiplicand = current;
      // Multiple the leaf node with all of its ancestor nodes
      while (multiplicand != null) {
        long newPower = current.value + multiplicand.value;
        long newDepth = current.depth + 1;
        // If the new node represents a power we haven't found yet, add to the sum.
        if (required.contains(newPower)) {
          required.remove(newPower);
          sum += newDepth;
        }
        // Add this new node to the queue.
        leaves.add(new Node(current, newPower, newDepth));
        multiplicand = multiplicand.parent;
      }
    }
    return String.valueOf(sum);
  }

  // Represents a node in the multiplication tree.
  // This represents the product n^`value`, and it was produced using `depth` multiplications.
  private static final class Node {

    Node parent;
    long value;
    long depth;

    Node(Node parent, long value, long depth) {
      this.parent = parent;
      this.value = value;
      this.depth = depth;
    }
  }
}
