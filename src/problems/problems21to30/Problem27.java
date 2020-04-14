package src.problems.problems21to30;

import java.lang.invoke.MethodHandles;
import java.util.function.Function;

import src.utils.primes.Primes;

import java.lang.Math;

public class Problem27 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println("final answer: " + maxConsecutivePrimes(poly(-79, 1601)));
    System.out.println(brute());
  }

  static int brute() {
    int maxConsecutivePrimes = 0;
    int maxProduct = 0;
    for (int a = -1000; a < 1000; a++) {
      for (int b = -1000; b <= 1000; b++) {
        Function<Integer, Integer> polyFn = poly(a, b);
        int n = maxConsecutivePrimes(polyFn);
        if (n > maxConsecutivePrimes) {
          maxConsecutivePrimes = n;
          maxProduct = a * b;
        }
      }
    }
    return maxProduct;
  }

  static int maxConsecutivePrimes(Function<Integer, Integer> polyFn) {
    int n = 0;
    while(true) {
      long result = polyFn.apply(n);
      if (Primes.isPrimeStatic(result)) {
        n++;
      } else {
        return n;
      }
    }  
  }

  static Function<Integer, Integer> poly(int a, int b) {
    return n -> Math.abs(n * n + a * n + b);
  }
}