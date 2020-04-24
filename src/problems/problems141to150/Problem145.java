package src.problems.problems141to150;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

public class Problem145 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    int count = 0;
    for (int i = 1; i < 1000000000; i++) {
      if (isReversible(i)) {
        count++;
      }
    }
    System.out.println(count);

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static boolean isReversible(int k) {
    if (k % 10 == 0) {
      return false;
    }
    ArrayList<Integer> digits = new ArrayList<>();
    while (k > 0) {
      digits.add((int) k % 10);
      k = k / 10;
    }
    int carry = 0;
    for (int i = 0; i < digits.size(); i++) {
      int sum = digits.get(i) + digits.get(digits.size() - 1 - i) + carry;
      if (sum % 2 == 0) {
        return false;
      }
      carry = 0;
      while (sum >= 10) {
        carry++;
        sum -= 10;
      }
    }
    return true;
  }
}
