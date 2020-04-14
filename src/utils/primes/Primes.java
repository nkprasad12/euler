package src.utils.primes;

import java.lang.Math;
import java.util.Collections;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

import src.utils.generators.Generators;

/* Utility functions relating to prime numbers and primality. */
public final class Primes {

  private final SortedSet<Long> primes;

  private long maxChecked;

  public Primes() {
    primes = new TreeSet<>();
    primes.add(2l);
    maxChecked = 2l;
  }

  /**  
   * Returns whether a number is prime using the Miller test.
   * 
   * This is technically conditional on the Riemann Hypothesis, but
   * we we get an incorrect result using this algorithm this means we've
   * disproven the Riemann Hypothesis, so this is a worthy trade off.
   */
  public static boolean isPrimeStatic(long n) {
    if (n == 2) {
      return true;
    }
    if (n % 2 == 0) {
      return false;
    }
    // Write n = 2^r * d + 1
    long d = n - 1;
    long r = 0;
    while (d % 2 == 0) {
      d /= 2;
      r += 1;
    }
    double logN = Math.log((double) n);
    long max = Math.min(n - 2, 2 + (long) Math.floor(2 * logN * logN));
    for (long a = 2; a <= max; a++) {
      long x = powerModN(a, d, n);
      if (x == 1 || x == n - 1) {
        continue;
      }
      boolean shouldContinue = false;
      for (int i = 0; i < r - 1; i++) {
        x = powerModN(x, 2, n);
        if (x == n - 1) {
          shouldContinue = true;
          break;
        }
      }
      if (shouldContinue) {
        continue;
      }
      return false;
    }
    return true;
  }

  public static long powerModN(long base, long exponent, long mod) {
    if (exponent == 0) {
      return 1l;
    } else if (exponent % 2 == 0) {
      long halfPow = powerModN(base, exponent / 2, mod);
      return (halfPow * halfPow) % mod;
    } else {
      return (powerModN(base, exponent - 1, mod) * base) % mod;
    }
  }

  /* Returns a set of all the prime numbers <= max. */
  public SortedSet<Long> primesUpTo(long max) {
    computePrimesUpTo(max);
    return Collections.unmodifiableSortedSet(primes);
  }

  /* Computes and saves all the prime numbers <= max. */
  private void computePrimesUpTo(long max) {
    // TODO: Replace this with a prime sieve
    if (max > maxChecked) {
      for (long n = maxChecked + 1; n <= max; n++) {
        if (!isDivisibleByKnownPrimesUpTo(n)) {
          primes.add(n);
        }
      }
      maxChecked = max;      
    }
  }

  public long nthPrime(int n) {
    for (int i = primes.size(); i < n; i++) {
      computeNextPrime();
    }
    return primes.last();
  }

  private long computeNextPrime() {
    long candidate = maxChecked + 1;
    while (true) {
      if (isDivisibleByKnownPrimes(candidate)) {
        candidate++;
      } else {
        primes.add(candidate);
        maxChecked = candidate;
        break;
      }
    }
    return candidate;
  }

  /* Determines whether the input number is prime. */
  public boolean isPrime(long n) {
    long root = root(n);
    computePrimesUpTo(root);
    return !isDivisibleByKnownPrimesUpTo(root);
  }

  public static boolean isPrimeNonStoring(long n) {
    if (n <= 1) {
      return false;
    }
    if (n == 2) {
      return true;
    }
    if (n % 2 == 0) {
      return false;
    }

    long boundary = (long) Math.floor(Math.sqrt(n));

    for (int i = 3; i <= boundary; i += 2) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }

  private boolean isDivisibleByKnownPrimesUpTo(long n) {
    return Generators.from(primes)
        .filter(prime -> prime <= n)
        .reducing(true, (soFar, prime) -> soFar && n % prime == 0)
        .lastValue();
  }

  private boolean isDivisibleByKnownPrimes(long n) {
    for (Long prime : primes) {
      if (n % prime == 0) {
        return true;
      }
    }
    return false;
  }

  /** 
   * Returns a map representing the prime factorization of the input.
   *
   * The keys of the map are the prime factors, and the values are the
   * powers to which the prime key is raised in the factorization. 
   */
  public SortedMap<Long, Integer> factor(long n) {
    long remaining = n;
    SortedMap<Long, Integer> factorMap = new TreeMap<>();
    for (Long prime : primes) {
      int exponent = 0;
      while (remaining % prime == 0) {
        exponent++;
        remaining /= prime;
      }
      if (exponent > 0) {
        factorMap.put(prime, exponent);
      }
    }
    while (remaining > 1) {
      long prime = computeNextPrime();
      int exponent = 0;
      while (remaining % prime == 0) {
        exponent++;
        remaining /= prime;
      }
      if (exponent > 0) {
        factorMap.put(prime, exponent);
      }      
    }
    return factorMap;
  }

  private long root(long n) {
    return (long) Math.ceil(Math.sqrt(n));
  }
}