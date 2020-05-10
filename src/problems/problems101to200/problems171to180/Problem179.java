package problems.problems101to200.problems171to180;

import java.lang.invoke.MethodHandles;

public class Problem179 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    int[] nums = new int[10000001];
    int[] divs = new int[10000001];
    for (int i = 1; i <= 10000000; i++) {
      nums[i] = i;
      divs[i] = 1;
    }
    int current = 2;
    while (current <= 10000000) {
      if (nums[current] == 1)  {
        current++;
        continue;
      }
      for (int j = current; j <= 10000000; j += current) {
        int exp = 0;
        while (nums[j] % current == 0) {
          nums[j] /= current;
          exp++;
        }
        divs[j] *= (exp + 1);
      }
      current++;
    }
    int ct = 0;
    for (int i = 1; i < 10000000; i++) {
      if (divs[i] == divs[i + 1]) {
        ct++;
      }
    }
    return Integer.toString(ct);
  }
}