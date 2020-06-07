package problems.problems601to700.problems691to700;

import static java.math.BigInteger.valueOf;
import static utils.numbers.NumericUtils.powerModNCheating;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Problem700 {
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    long mod = 4503599627370517l;
    long birthday = 1504170715041707l;

    // Computed using brute force via the commented loop.
    long sumOfEulerCoins = 1517926517477964l;
    long lastEulerCoin = 258162l;
    // long sumOfEulerCoins = 0;
    // long cutoff = 1000000l;
    // long current = 0;
    // while (lastEulerCoin > cutoff) {
    // current = (current + birthday) % mod;
    // if (current < lastEulerCoin) {
    // System.out.println(current + " -> " + sumOfEulerCoins);
    // sumOfEulerCoins += current;
    // lastEulerCoin = current;
    // }
    // if (current == 0) {
    // break;
    // }
    // }

    SortedMap<Long, Long> idxForValues = new TreeMap<>();
    long start = lastEulerCoin - 1;
    BigInteger bInv = valueOf(powerModNCheating(birthday, mod - 2, mod));
    BigInteger p = valueOf(mod);
    for (long maybeEulerCoin = start; maybeEulerCoin > 0; maybeEulerCoin--) {
      long idx = bInv.multiply(valueOf(maybeEulerCoin)).mod(p).longValue();
      idxForValues.put(idx, maybeEulerCoin); 
    }

    for (Map.Entry<Long, Long> entry : idxForValues.entrySet()) {
      long eulerCoin = entry.getValue();
      if (eulerCoin < lastEulerCoin) {
        sumOfEulerCoins += eulerCoin;
        lastEulerCoin = eulerCoin;
      }
    }
    return Long.toString(sumOfEulerCoins);
  }
}
