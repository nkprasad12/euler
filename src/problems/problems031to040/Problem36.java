package src.problems.problems031to040;

import java.lang.invoke.MethodHandles;
import src.utils.numbers.BigNumber;

public class Problem36 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    int sum = 0;
    for (int i = 1; i < 1000000; i++) {
      BigNumber base10 = BigNumber.fromLong(i, 10);
      BigNumber base2 = BigNumber.fromLong(i, 2);

      if (isPalindrome(base10) && isPalindrome(base2)) {
        sum += i;
      }
    }
    System.out.println(sum);
  }

  static boolean isPalindrome(BigNumber n) {
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
