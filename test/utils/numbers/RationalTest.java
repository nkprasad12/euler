package test.utils.numbers;

import static test.Assertions.assertEqual;

import org.junit.Test;
import src.utils.numbers.Rational;
import src.utils.primes.PrimeFactorization;
import src.utils.primes.Primes;

public class RationalTest {

  @Test
  public void rationalFromLongs_hasExpectedNumeratorDenominator() {
    Rational q = Rational.fromLongs(65l, 28l);
    assertEqual(q.numerator().toLong(), 65l);
    assertEqual(q.denominator().toLong(), 28l);
  }

  @Test
  public void rationalPrimeFactorizations_hasExpectedNumeratorDenominator() {
    Primes primes = new Primes();
    Rational q =
        new Rational(PrimeFactorization.of(46l, primes), PrimeFactorization.of(180l, primes));
    assertEqual(q.numerator(), PrimeFactorization.of(46l, primes));
    assertEqual(q.denominator(), PrimeFactorization.of(180l, primes));
  }

  @Test
  public void rationalReduce_equalNumeratorDenominator_hasExpected() {
    Rational q = Rational.fromLongs(34, 34).reduce();
    assertEqual(q.numerator().toLong(), 1l);
    assertEqual(q.denominator().toLong(), 1l);
  }

  @Test
  public void rationalReduce_numeratorOne_hasExpected() {
    Rational q = Rational.fromLongs(1, 34).reduce();
    assertEqual(q.numerator().toLong(), 1l);
    assertEqual(q.denominator().toLong(), 34l);
  }

  @Test
  public void rationalReduce_denominatorOne_hasExpected() {
    Rational q = Rational.fromLongs(34, 1).reduce();
    assertEqual(q.numerator().toLong(), 34l);
    assertEqual(q.denominator().toLong(), 1l);
  }

  @Test
  public void rationalReduce_coprime_hasExpected() {
    Rational q = Rational.fromLongs(34, 57).reduce();
    assertEqual(q.numerator().toLong(), 34l);
    assertEqual(q.denominator().toLong(), 57l);
  }

  @Test
  public void rationalReduce_notCoprime_hasExpected() {
    Rational q = Rational.fromLongs(84, 7).reduce();
    assertEqual(q.numerator().toLong(), 12l);
    assertEqual(q.denominator().toLong(), 1l);
  }

  @Test
  public void rationalReduce_notCoprime_bothComposite_hasExpected() {
    Rational q = Rational.fromLongs(84, 36).reduce();
    assertEqual(q.numerator().toLong(), 7l);
    assertEqual(q.denominator().toLong(), 3l);
  }
}
