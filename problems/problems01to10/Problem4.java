package problems.problems01to10;

import java.lang.invoke.MethodHandles;

public class Problem4 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    findMaxPalindrome();
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