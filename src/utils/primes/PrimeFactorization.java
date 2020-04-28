package utils.primes;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
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
    TreeMap<Long, Integer> copyMap = new TreeMap<>();
    for (Map.Entry<Long, Integer> entry : factorMap.entrySet()) {
      if (entry.getValue() < 0) {
        throw new RuntimeException("Factor map contains negative exponent.");
      }
      if (entry.getValue() == 0) {
        continue;
      }
      copyMap.put(entry.getKey(), entry.getValue());
    }
    this.factorMap = copyMap;
    this.primes = primes;
  }

  /* Returns a read-only version of the underlying map of factors. */
  public SortedMap<Long, Integer> factorMap() {
    return Collections.unmodifiableSortedMap(factorMap);
  }

  /* The exponent of `prime` that appears in this prime factorization. */
  public int exponentOf(long prime) {
    Integer candidate = factorMap.get(prime);
    return candidate == null ? 0 : candidate;
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
    SortedMap<Long, Integer> commonFactors = new TreeMap<>();
    Iterator<Entry<Long, Integer>> otherFactors = other.factorMap().entrySet().iterator();
    if (!otherFactors.hasNext()) {
      return new PrimeFactorization(commonFactors, this.primes);
    }
    Entry<Long, Integer> otherFactor = otherFactors.next();
    for (Entry<Long, Integer> entry : factorMap.entrySet()) {
      Long prime = entry.getKey();
      while (otherFactor.getKey() < prime) {
        if (!otherFactors.hasNext()) {
          break;
        }
        otherFactor = otherFactors.next();
      }
      if (otherFactor.getKey() > prime) {
        continue;
      }
      Integer exponent = Math.min(entry.getValue(), otherFactor.getValue());
      commonFactors.put(prime, exponent);
    }
    return new PrimeFactorization(commonFactors, this.primes);
  }

  public Rational divideBy(PrimeFactorization divisor) {
    SortedMap<Long, Integer> numeratorFactors = new TreeMap<>();
    SortedMap<Long, Integer> denominatorFactors = new TreeMap<>();
    Iterator<Entry<Long, Integer>> divisorFactors = divisor.factorMap().entrySet().iterator();
    if (!divisorFactors.hasNext()) {
      return new Rational(this, of(1, primes));
    }
    if (factorMap.isEmpty()) {
      return new Rational(this, divisor);
    }
    Entry<Long, Integer> divisorFactor = divisorFactors.next();
    for (Entry<Long, Integer> entry : factorMap.entrySet()) {
      Long prime = entry.getKey();
      while (divisorFactor != null && divisorFactor.getKey() < prime) {
        denominatorFactors.put(divisorFactor.getKey(), divisorFactor.getValue());
        divisorFactor = divisorFactors.hasNext() ? divisorFactors.next() : null;
      }
      if (divisorFactor == null || divisorFactor.getKey() > prime) {
        numeratorFactors.put(prime, entry.getValue());
        continue;
      }
      if (prime.equals(divisorFactor.getKey())) {
        Integer thisExp = entry.getValue();
        Integer otherExp = divisorFactor.getValue();
        if (thisExp > otherExp) {
          numeratorFactors.put(prime, thisExp - otherExp);
        } else if (otherExp > thisExp) {
          denominatorFactors.put(prime, otherExp - thisExp);
        }
        divisorFactor = divisorFactors.hasNext() ? divisorFactors.next() : null;
      }
    }
    while (divisorFactor != null) {
      denominatorFactors.put(divisorFactor.getKey(), divisorFactor.getValue());
      divisorFactor = divisorFactors.hasNext() ? divisorFactors.next() : null;
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

  public Long toLong() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (this.getClass() != o.getClass()) {
      return false;
    }
    PrimeFactorization p = (PrimeFactorization) o;
    if (this.factorMap().size() != p.factorMap().size()) {
      return false;
    }
    for (Map.Entry<Long, Integer> entry : this.factorMap().entrySet()) {
      if (!p.factorMap().containsKey(entry.getKey())) {
        return false;
      }
      if (!entry.getValue().equals(p.factorMap().get(entry.getKey()))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
