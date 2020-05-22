package problems.problems201to300.problems201to210;

import java.lang.invoke.MethodHandles;

import utils.numbers.BigNumber;

public class Problem206 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    long n = 1010101030;
    while (true) {
      BigNumber s = BigNumber.fromLong(n).toPower(2);
      if (checkThing(s)) {
        break;
      }
      if (n % 100 == 30) {
        n += 40;
      } else {
        n += 60;
      }
    }
    return Long.toString(n);
  }

  static boolean checkThing(BigNumber num) {
    for (int i = 0; i < 10; i++) {
      int expected = 0;
      if (i > 0) {
        expected = 10 - i;
      }
      if (num.digits().get(2 * i) != expected) {
        return false;
      } 
    }
    return true;
  }

  //  last digit of n is 0 
  //  what number squares to 1_2_3_4_5_6_7_8_9
  // 0 -> 0
  // 1 -> 1
  // 2 -> 4
  // 3 -> 9
  // 4 -> 6
  // 5 -> 5
  // 6 -> 6
  // 7 -> 9
  // 8 -> 4
  // 9 -> 1

  // 


  // s = 1_2_3_4_5_6_7_8_900
  // n = _....30 or _.....70
  // n % 100 = 30 or 70

  // sqrt(1929394959697989900) - sqrt(1020304050607080900) = 378925613
} // 9^9 = 387420489