package problems.problems601to700.problems691to700;

import java.lang.invoke.MethodHandles;

public class Problem700 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    long lastEulerCoin = 4503599627370517l;
    long sumOfEulerCoins = 0;
    long current = 0;
    while (true) {
      System.out.println(current);     
      current = (current + 1504170715041707l) % 4503599627370517l;
      if (current < lastEulerCoin) {
        System.out.println(current + " -> " + sumOfEulerCoins);
        sumOfEulerCoins += current;
        lastEulerCoin = current;
      }
      if (current == 0) {
        break;
      }
    }
    return Long.toString(sumOfEulerCoins);
  }

//   public static int inverse(int factor, int mod){

//   }
}

// n * m = k mod p
// given n, k, p, find m
// k=19471, m = __
//
// A_n = a * n (mod p)
//  O(log(m)^2)