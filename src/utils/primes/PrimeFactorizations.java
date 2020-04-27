package utils.primes;

import utils.generators.Generator;
import utils.generators.Generators;
import utils.generators.specialized.DivisorGenerator;

/** Factory class for prime factorizations. */
public final class PrimeFactorizations {

  private final Primes primes;

  public PrimeFactorizations(Primes primes) {
    this.primes = primes;
  }

  /** Decomposes the input into its prime factors. */
  public PrimeFactorization factorizationOf(long n) {
    return PrimeFactorization.of(n, primes);
  }

  /** Generates the proper divisors of the input. */
  public Generator<Long> properDivisorsOf(long n) {
    return properDivisorsOf(factorizationOf(n));
  }

  /** Generates the proper divisors of the input. */
  public Generator<Long> properDivisorsOf(PrimeFactorization number) {
    return new DivisorGenerator(number, true, false);
  }

  /** Returns whether an integer is equal to the sum of its proper divisors. */
  public boolean isPerfect(long n) {
    return n == Generators.from(properDivisorsOf(n)).reduce(0l, (a, b) -> a + b);
  }

  /** Returns whether an integer is less than the sum of its proper divisors. */
  public boolean isAbundant(long n) {
    return n < Generators.from(properDivisorsOf(n)).reduce(0l, (a, b) -> a + b);
  }
}
