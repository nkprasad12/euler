package src.utils.primes;

import src.utils.generators.DivisorGenerator;
import src.utils.generators.Generator;
import src.utils.generators.Generators;

public final class PrimeFactorizations {

  private final Primes primes;

  public PrimeFactorizations(Primes primes) {
    this.primes = primes;
  }

  public PrimeFactorization of(long n) {
    return PrimeFactorization.of(n, primes);
  }

  PrimeFactorization factorizationOf(long n) {
    return of(n);
  }

  public Generator<Long> properDivisorsOf(long n) {
    return properDivisorsOf(factorizationOf(n));
  }

  public Generator<Long> properDivisorsOf(PrimeFactorization number) {
    return new DivisorGenerator(number, true, false);
  }

  public boolean isPerfect(long n) {
    return n == Generators.from(properDivisorsOf(n)).reduce(0l, (a, b) -> a + b);
  }

  public boolean isAbundant(long n) {
    return n < Generators.from(properDivisorsOf(n)).reduce(0l, (a, b) -> a + b);
  }
}