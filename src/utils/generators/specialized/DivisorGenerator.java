package src.utils.generators.specialized;

import java.util.SortedMap;
import java.util.Map.Entry;

import src.utils.generators.Generator;
import src.utils.primes.PrimeFactorization;

/** Generates all integer divisors of a given integer. */
public class DivisorGenerator implements Generator<Long> {

  private final int primeFactors;
  private final long[] primes;
  private final int[] exponents;
  private final int[] state;

  private final boolean includeNumber;

  public DivisorGenerator(
      PrimeFactorization number,
      boolean includeOne,
      boolean includeNumber) {
    SortedMap<Long, Integer> factorMap = number.factorMap();
    this.includeNumber = includeNumber;
    primeFactors = factorMap.keySet().size();
    primes = new long[primeFactors];
    exponents = new int[primeFactors];
    state = new int[primeFactors];

    int i = 0;
    for (Entry<Long, Integer> entry : factorMap.entrySet()) {
      primes[i] = entry.getKey();
      exponents[i] = entry.getValue();
      i++;
    }
    if (!includeOne) {
      state[0] = 1;
    }
  }

  @Override
  public Long getNext() {
    long result = valueForState();
    nextState();
    return result;
  }

  @Override
  public boolean hasNext() {
    return state.length > 0 && state[0] != -1;
  }

  private void nextState() {
    for (int i = 0; i < primeFactors; i++) {
      if (state[i] < exponents[i]) {
        state[i] += 1;
        break;
      }
      state[i] = 0;
    }
    boolean overflow = true;
    if (!includeNumber) {
      boolean allMatch = true;
      for (int i = 0; i < primeFactors; i++) {
        if (state[i] != exponents[i]) {
          allMatch = false;
          break;
        }
      }
      if (allMatch) {
        state[0] = -1;
      }
      return;
    }
    for (int i = 0; i < primeFactors; i++) {
      if (state[i] != 0) {
        overflow = false;
        break;
      }
    }
    if (overflow) {
      state[0] = -1;
    }
  }

  private long valueForState() {
    long product = 1;
    for (int i = 0; i < primeFactors; i++) {
      product *= power(primes[i], state[i]);
    }
    return product;
  }

  private static long power(long base, int toPower) {
    if (toPower == 0) {
      return 1l;
    } else if (toPower % 2 == 0) {
      long halfPower = power(base, toPower / 2);
      return halfPower * halfPower;
    } else {
      return power(base, toPower - 1) * base;
    }
  }
}