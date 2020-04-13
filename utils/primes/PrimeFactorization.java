package utils.primes;

import java.lang.Math;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.Collections;

import utils.numbers.Rational;
  
  /* Class representing the prime factorization of a number. */
  public final class PrimeFactorization {

    /* Computes and returns the factorization of the input. */
    public static PrimeFactorization of(long n, Primes primes) {
      return new PrimeFactorization(primes.factor(n), primes);
    }

    private final SortedMap<Long, Integer> factorMap;
    private final Primes primes;

    private PrimeFactorization(SortedMap<Long, Integer> factorMap, Primes primes) {
      this.factorMap = factorMap;
      this.primes = primes;
    }

    /* The exponent of `prime` that appears in this prime factorization. */
    public int exponentOf(long prime) {
      Integer candidate = factorMap.get(prime);
      return candidate == null ? 0 : candidate;
    }

    /* Returns a read-only version of the underlying map of factors. */
    public SortedMap<Long, Integer> factorMap() {
      return Collections.unmodifiableSortedMap(factorMap);
    }

    /* Returns the largest prime factor in this factorization. */
    public long largestFactor() {
      return factorMap.lastKey();
    }

    public long numberOfDivisors() {
      long divisors = 1;
      for (Entry<Long, Integer> entry : factorMap().entrySet()) {
        Integer exponent = entry.getValue();
        divisors *= exponent + 1;
      }
      return divisors;
    }

    /* Returns the Greatest Common Denimonator between this PrimeFactorization and another. */
    public PrimeFactorization gcd(PrimeFactorization other) {
      // TODO - we can do better than this in terms of performance.
      //        The prime factors are stored in a TreeSet, so we can
      //        set a sorted iteration to walk through both primes
      //        lists in a single pass - O(n + m)
      SortedMap<Long, Integer> commonFactors = new TreeMap<>();
      for (Entry<Long, Integer> entry : factorMap.entrySet()) {
        Long prime = entry.getKey();
        Integer exponent = Math.min(entry.getValue(), other.exponentOf(prime));
        if (exponent > 0) {
          commonFactors.put(prime, exponent);
        }
      }
      return new PrimeFactorization(commonFactors, this.primes);
    }

    /* Returns the rational result of a divison of this PrimeFactorization by another. */
    public Rational divideBy(PrimeFactorization divisor) {
      // TODO - we can do better than this in terms of performance.
      //        The prime factors are stored in a TreeSet, so we can
      //        set a sorted iteration to walk through both primes
      //        lists in a single pass - O(n + m)
      SortedMap<Long, Integer> numeratorFactors = new TreeMap<>();
      SortedMap<Long, Integer> denominatorFactors = new TreeMap<>();
      for (Entry<Long, Integer> entry : factorMap.entrySet()) {
        Long prime = entry.getKey();
        Integer exponent = entry.getValue() - divisor.exponentOf(prime);
        if (exponent > 0) {
          numeratorFactors.put(prime, exponent);
        }
      }
      for (Entry<Long, Integer> entry : divisor.factorMap().entrySet()) {
        Long prime = entry.getKey();
        Integer exponent = entry.getValue() - this.exponentOf(prime);
        if (exponent > 0) {
          denominatorFactors.put(prime, exponent);
        }
      }
      return new Rational(
        new PrimeFactorization(numeratorFactors, primes),
        new PrimeFactorization(denominatorFactors, primes));
    }

    /* Multiplies this PrimeFactorization by another. */
    public PrimeFactorization multiplyBy(PrimeFactorization other) {
      SortedMap<Long, Integer> factors = new TreeMap<>();
      for (Entry<Long, Integer> entry : factorMap.entrySet()) {
        factors.put(entry.getKey(), entry.getValue());
      }
      for (Entry<Long, Integer> entry : other.factorMap().entrySet()) {
        Long prime = entry.getKey();
        Integer currentExponent = factors.get(prime);
        Integer newExponent = entry.getValue();
        if (currentExponent != null) {
          newExponent += currentExponent;
        }
        factors.put(prime, newExponent);
      }
      return new PrimeFactorization(factors, this.primes);      
    }

    public PrimeFactorization toPower(int exponent) {
      if (exponent == 0) {
        return PrimeFactorization.of(1, this.primes);
      } else if (exponent % 2 == 0) {
        PrimeFactorization halfPower = toPower(exponent / 2);
        return halfPower.multiplyBy(halfPower);
      } else {
        return this.multiplyBy(this.toPower(exponent - 1));
      }
    }

    public long toLong() {
      long product = 1;
      for (Entry<Long, Integer> entry : factorMap().entrySet()) {
        Long prime = entry.getKey();
        Integer exponent = entry.getValue();
        for (int i = 0; i < exponent; i++) {
          product *= prime;
        }
      }
      return product;
    }

    @Override
    public String toString() {
      String str = "";
      for (Long prime : factorMap.keySet()) {
        str += prime + "^" + factorMap.get(prime) + " * ";
      }
      return str;
    }
  }