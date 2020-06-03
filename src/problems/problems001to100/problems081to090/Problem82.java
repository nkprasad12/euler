package problems.problems001to100.problems081to090;

import static utils.generators.Generators.from;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import utils.generators.Generators;

public class Problem82 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    List<List<Long>> grid = getGrid();
    // Problem 82
    int n = grid.size();
    int minDist = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
        int dist = getMinimumDistanceForEntry(grid, i);
        if (dist < minDist) {
            minDist = dist;
        }
    }
    // Problem 83
    // int minDist = getMinimumDistanceForEntry(grid, 0);
    return Integer.toString(minDist);
  }

  static int getMinimumDistanceForEntry(List<List<Long>> grid, int src) {
    int n = grid.size();
    int[] dists = new int[n * n];
    for (int i = 0; i < n * n; i++) {
      dists[i] = Integer.MAX_VALUE;
    }
    dists[src] = (int) (0 + grid.get(src % n).get(src / n));
    PriorityQueue<Integer> Q = new PriorityQueue<>(n, (a, b) -> Integer.compare(dists[a], dists[b]));
    for (int i = 0; i < n * n; i++) {
      Q.add(i);
    }

    while (Q.size() != 0) {
      int min = Q.poll();
      int minDist = dists[min];

      int min_i = min % n;
      int min_j = min / n;
      
      int neighbor_i = min_i + 1;
      int neighbor_j = min_j;
      int neighbor_dist = 0;
      if (neighbor_i < n && neighbor_j < n && neighbor_i >= 0 && neighbor_j >= 0) {
        neighbor_dist = (int) (minDist + grid.get(neighbor_i).get(neighbor_j));
        if (neighbor_dist < dists[n*neighbor_j+neighbor_i]) {
          dists[n*neighbor_j+neighbor_i] = neighbor_dist;
          Q.remove(n*neighbor_j+neighbor_i);
          Q.add(n*neighbor_j+neighbor_i);
        }
      }

      neighbor_i = min_i - 1;
      neighbor_j = min_j;
      if (neighbor_i < n && neighbor_j < n && neighbor_i >= 0 && neighbor_j >= 0) {
        neighbor_dist = (int) (minDist + grid.get(neighbor_i).get(neighbor_j));
        if (neighbor_dist < dists[n*neighbor_j+neighbor_i]) {
          dists[n*neighbor_j+neighbor_i] = neighbor_dist;
          Q.remove(n*neighbor_j+neighbor_i);
          Q.add(n*neighbor_j+neighbor_i);
        }
      }

      neighbor_i = min_i;
      neighbor_j = min_j + 1;
      if (neighbor_i < n && neighbor_j < n && neighbor_i >= 0 && neighbor_j >= 0) {
        neighbor_dist = (int) (minDist + grid.get(neighbor_i).get(neighbor_j));
        if (neighbor_dist < dists[n*neighbor_j+neighbor_i]) {
          dists[n*neighbor_j+neighbor_i] = neighbor_dist;
          Q.remove(n*neighbor_j+neighbor_i);
          Q.add(n*neighbor_j+neighbor_i);
        }
      }

      // Problem 83: add this
      // neighbor_i = min_i;
      // neighbor_j = min_j - 1;
      // if (neighbor_i < n && neighbor_j < n && neighbor_i >= 0 && neighbor_j >= 0) {
      //   neighbor_dist = (int) (minDist + grid.get(neighbor_i).get(neighbor_j));
      //   if (neighbor_dist < dists[n*neighbor_j+neighbor_i]) {
      //     dists[n*neighbor_j+neighbor_i] = neighbor_dist;
      //     Q.remove(n*neighbor_j+neighbor_i);
      //     Q.add(n*neighbor_j+neighbor_i);
      //   }
      // }
    }

    // Problem 82
    int minDist = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
        int u = n * (n - 1) + i;
        if (dists[u] < minDist) {
            minDist = dists[u];
        }
    }
    // Problem 83
    // int minDist = dists[n * n - 1];
    return minDist;
  }

  static List<List<Long>> getGrid() {
    return Generators.fromTextFile("problem82.txt")
        .map(line -> line.split(","))
        .map(tokens -> from(Arrays.asList(tokens)).map(Long::parseLong).list())
        .list();
  } 
}