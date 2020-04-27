package utils.primes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/* Utility functions relating to prime numbers and primality. */
public final class Primes {

  private final PrimeStorage primes;

  public Primes() {
    primes = PrimeStorage.create();
  }

  /** Returns when a number is prime. */
  public static boolean isPrimeStatic(long n) {
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

  /* Returns a set of all the prime numbers <= max. */
  public List<Long> primesUpTo(long max) {
    computePrimesUpTo(max);
    return primes.valuesUpTo(max);
  }

  /* Computes and saves all the prime numbers <= max. */
  private void computePrimesUpTo(long max) {
    if (max > Integer.MAX_VALUE - 1) {
      throw new RuntimeException("Computing max too high - time to implement segmenting sieving.");
    }
    int n = (int) max;
    boolean[] isCompositeOrOldPrime = new boolean[n + 1];
    for (Long prime : primes) {
      if (prime <= n) {
        isCompositeOrOldPrime[prime.intValue()] = true;
      }
      long j = prime * prime;
      while (j <= n) {
        isCompositeOrOldPrime[(int) j] = true;
        j += prime;
      }
    }
    for (int i = 2; i <= n; i++) {
      if (isCompositeOrOldPrime[i]) {
        continue;
      }
      long prime = (long) i;
      primes.add(prime);
      long j = prime * prime;
      while (j <= n) {
        isCompositeOrOldPrime[(int) j] = true;
        j += prime;
      }
    }
  }

  /* Returns the 1-indexed nth prime number. */
  public long nthPrime(int n) {
    if (n <= 0) {
      throw new RuntimeException("Index n must be 1 indexed");
    }
    // n (log n + log (log n)) is a known upper bound for the nth prime, n > 6.
    long max;
    if (n <= 6) {
      // 13 is the 6th prime.
      max = 13;
    } else {
      double logN = Math.log((double) n);
      long logLogN = (long) Math.ceil(Math.log(logN));
      max = ((long) n) * ((long) Math.ceil(logN) + logLogN);
    }
    computePrimesUpTo(max);
    // return getNthElement(primes, n);
    return primes.primesList.get(n - 1);
  }

  /* Determines whether the input number is prime. */
  public boolean isPrime(long n) {
    if (n <= primes.last()) {
      return primes.contains(n);
    }
    long root = (long) Math.sqrt(n);
    for (Long prime : primesUpTo(root)) {
      if (n % prime == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns a map representing the prime factorization of the input.
   *
   * <p>The keys of the map are the prime factors, and the values are the powers to which the prime
   * key is raised in the factorization.
   */
  public SortedMap<Long, Integer> factor(long n) {
    if (n <= 0) {
      throw new RuntimeException("Only positive integers can be factored");
    }
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
    computePrimesUpTo(remaining);
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
    if (remaining > 1) {
      throw new RuntimeException("Error in factor method - could not complete factorization.");
    }
    return factorMap;
  }

  private static final class PrimeStorage implements Iterable<Long> {

    static PrimeStorage create() {
      PrimeStorage storage = new PrimeStorage();
      storage.add(2l);
      return storage;
    }

    private final HashSet<Long> primesSet;
    private final ArrayList<Long> primesList;

    private PrimeStorage() {
      this.primesSet = new HashSet<>();
      this.primesList = new ArrayList<>();
    }

    @Override
    public Iterator<Long> iterator() {
      return primesList.listIterator();
    }

    void add(Long element) {
      primesSet.add(element);
      primesList.add(element);
    }

    long last() {
      return primesList.get(primesList.size() - 1);
    }

    boolean contains(long prime) {
      return primesSet.contains(prime);
    }

    List<Long> valuesUpTo(Long max) {
      int index =
          max >= primesList.get(primesList.size() - 1)
              ? primesList.size()
              : firstIndexGreaterThan(max);
      return Collections.unmodifiableList(primesList.subList(0, index));
    }

    private int firstIndexGreaterThan(Long max) {
      int start = 0;
      int end = primesList.size() - 1;
      int index = -1;
      while (start <= end) {
        int mid = (start + end) / 2;
        if (primesList.get(mid) <= max) {
          start = mid + 1;
        } else {
          index = mid;
          end = mid - 1;
        }
      }
      return index;
    }
  }
}
