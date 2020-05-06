package problems.problems051to060;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import utils.numbers.BigNumber;

public class Problem55 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    int numLycharels = 0;
    for (long n = 1; n < 10000; n++) {
      BigNumber num = BigNumber.fromLong(n);
      boolean isLycherel = true;
      for (int i = 0; i < 50; i++) {
        num = nextLychrel(num);
        if (isPalindrome(num)) {
          isLycherel = false;
          break;
        }
      }
      if (isLycherel) {
        numLycharels++;
      }
    }
    return String.valueOf(numLycharels);
  }

  public static BigNumber reverse(BigNumber number) {
    List<Integer> digits = new ArrayList<>(number.digits().size());
    for (int i = number.digits().size() - 1; i >= 0; i--) {
      digits.add(number.digits().get(i));
    }
    return new BigNumber(digits);
  }

  public static BigNumber nextLychrel(BigNumber current) {
      return current.addTo(reverse(current));
  }
  
  public static boolean isPalindrome(BigNumber n) {
    if (n.digits().get(0) == 0) {
      return false;
    }
    for (int i = 0; i < n.digits().size() / 2 + 1; i++) {
      if (n.digits().get(i) != n.digits().get(n.digits().size() - i - 1)) {
        return false;
      }
    }
    return true;
  }
}