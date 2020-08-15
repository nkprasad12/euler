package problems.problems001to100.problems051to060;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import utils.primes.Primes;

public class Problem51 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    Primes primes = new Primes();
    for (long prime : primes.primesUpTo(1000000l)) {
      if (numPrimesInFamily(prime, primes) >= 8l) {
        return Long.toString(prime);
      }
    }
    return "Ya dun goofed";
  }

  public static long numPrimesInFamily(long prime, Primes primes) {
    long maxInFamily = 0;
    int pDigits = numDigits(prime);
    List<boolean[]> masks = masksForNumber(prime);
    for (boolean[] mask : masks) {
      long maskTotal = 0;
      long maskPlaceValue = 1;
      long n = prime;
      long maskDigitInPrime = -1;
      for (int i = mask.length - 1; i >= 0; i--) {
        long curDigit = n % 10;
        n /= 10;
        if (mask[i]) {
          maskDigitInPrime = curDigit;
          maskTotal += maskPlaceValue;
        }
        maskPlaceValue *= 10;
      }
      long familyBase = prime - maskDigitInPrime * maskTotal;
      long numInFamily = 0;
      for (long d = 0; d < 10; d++) {
        long candidate = familyBase + d * maskTotal;
        if (numDigits(candidate) == pDigits && primes.isPrime(candidate)) {
          numInFamily++;
        }
      }
      maxInFamily = Math.max(maxInFamily, numInFamily);
    }
    return maxInFamily;
  }

  private static int numDigits(long n) {
    int digits = 0;
    while (n > 0) {
      n /= 10;
      digits++;
    }
    return digits;
  }

  private static List<boolean[]> masksForNumber(long prime) {

    List<boolean[]> result = new ArrayList<boolean[]>();
    List<List<Integer>> digitPositions = new ArrayList<List<Integer>>();
    String primeStr = String.valueOf(prime);

    for (int i = 0; i <= 9; i++) {
      List<Integer> positions = new ArrayList<Integer>();
      for (int j = 0; j < primeStr.length(); j++) {
        char c = primeStr.charAt(j);
        String number = String.valueOf(i);
        if (c == number.charAt(0)) {
          positions.add(j);
        }
      }
      digitPositions.add(positions);
    }

    for (List<Integer> positions : digitPositions) {
      int n = positions.size();

      if (n == 0) {
        continue;
      }

      for (int k = 1; k < Math.pow(2, n); k++) {
        String binary = Integer.toBinaryString(k);
        while (binary.length() < n) {
          binary = "0" + binary;
        }
        boolean[] newMask = new boolean[primeStr.length()];

        for (int i = 0; i < n; i++) {
          newMask[positions.get(i)] = binary.charAt(i) == '1';
        }
        result.add(newMask);
      }
    }

    return result;
  }
}
