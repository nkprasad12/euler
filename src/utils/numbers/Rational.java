package utils.numbers;

import utils.primes.PrimeFactorization;
import utils.primes.Primes;

/** Class that represents a rational number as the division of two integers. */
public class Rational {

  public static Rational reduced(PrimeFactorization numerator, PrimeFactorization denominator) {
    return new Rational(numerator, denominator).reduce();
  }

  public static Rational fromLongs(long numerator, long denominator) {
    return fromLongs(numerator, denominator, new Primes());
  }

  public static Rational fromLongs(long numerator, long denominator, Primes primes) {
    return new Rational(
        PrimeFactorization.of(numerator, primes), PrimeFactorization.of(denominator, primes));
  }

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

  /** Returns a new rational equivalent to this reduced to lowest terms. */
  public Rational reduce() {
    return numerator.divideBy(denominator);
  }

  @Override
  public String toString() {
    return numerator.toString() + " / " + denominator.toString();
  }
}
