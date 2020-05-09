package problems.problems001to100.problems051to060;

import static java.util.Arrays.asList;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class Problem53 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  static String solution() {
    int totalGreater = 0;
    List<Integer> lastRow = new ArrayList<>(asList(1, 1));
    for (int n = 2; n <= 100; n++) {
      List<Integer> currentRow = new ArrayList<>();
      currentRow.add(1);
      for (int i = 0; i < lastRow.size() - 1; i++) {
        int sum = lastRow.get(i) + lastRow.get(i + 1);
        currentRow.add(sum);
        if (sum > 1000000) {
          break;
        }
      }
      if (currentRow.size() != n) {
        totalGreater += (n + 1) - 2 * (currentRow.size() - 1);
      } else {
        currentRow.add(1);
      }
      lastRow = currentRow;
    }
    return String.valueOf(totalGreater);
  }
}
