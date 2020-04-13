package utils.numbers;

import utils.primes.PrimeFactorization;

public class Rational {

  private final PrimeFactorization numerator;
  private final PrimeFactorization denominator;

  public Rational(PrimeFactorization numerator, PrimeFactorization denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }

  public PrimeFactorization numerator() {
    return this.numerator;
  }

  public PrimeFactorization denominator() {
    return this.denominator;
  }

  /* Returns a new rational equivalent to this reduced to lowest terms. */
  public Rational reduce() {
    // TODO: Possible performance improvement: do this directly without separately computing GCD.
    PrimeFactorization gcd = numerator.gcd(denominator);
    return new Rational(
      numerator.divideBy(gcd).numerator(),
      denominator.divideBy(gcd).numerator());
  }
}