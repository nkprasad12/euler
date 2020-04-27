package problems.problems001to010;

import static utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;

public class Problem4 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    findMaxPalindrome();
  }

  public static String solution() {
    return range(100, 999)
        .flatMap(i -> range(100, i).map(j -> i * j))
        .reduce(-1, (max, next) -> next > max && isPalindrome(next) ? next : max)
        .toString();
  }

  public static void findMaxPalindrome() {
    int maxValue = 0;
    for (int i = 100; i <= 999; i++) {
      for (int j = 100; j <= 999; j++) {
        int val = i * j;
        if (isPalindrome(val) && val > maxValue) {
          maxValue = val;
        }
      }
    }
    System.out.println(maxValue);
  }

  static boolean isPalindrome(Integer n) {
    String str = Integer.toString(n);
    int len = str.length();
    for (int i = 0; i < len / 2 + 1; i++) {
      if (str.charAt(i) != str.charAt(len - i - 1)) {
        return false;
      }
    }
    return true;
  }
}
