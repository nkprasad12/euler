package utils.primes;

import static assertions.Assertions.assertEqual;
import static assertions.Assertions.assertMapsMatch;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import utils.numbers.Rational;

public class PrimeFactorizationTest {

  private Primes primes;

  @Before
  public void setup() {
    primes = new Primes();
  }

  private PrimeFactorization fromLong(long n) {
    return PrimeFactorization.of(n, primes);
  }

  @Test
  public void fromLong_one_hasNoFactors() {
    PrimeFactorization number = fromLong(1l);

    assertTrue(number.factorMap().isEmpty());
  }

  @Test
  public void fromLong_prime_hasOneFactor() {
    PrimeFactorization number = fromLong(37l);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(37l, 1);
    assertMapsMatch(number.factorMap(), expected);
  }

  @Test
  public void fromLong_primeSquare_hasOneFactorWithPower() {
    PrimeFactorization number = fromLong(49l);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(7l, 2);
    assertMapsMatch(number.factorMap(), expected);
  }

  @Test
  public void fromLong_primeProduct_hasTwoFactors() {
    PrimeFactorization number = fromLong(143l);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(11l, 1);
    expected.put(13l, 1);
    assertMapsMatch(number.factorMap(), expected);
  }

  @Test
  public void fromLong_largeComposite_hasExpectedFactors() {
    PrimeFactorization number = fromLong(3118500l);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(2l, 2);
    expected.put(3l, 4);
    expected.put(5l, 3);
    expected.put(7l, 1);
    expected.put(11l, 1);
    assertMapsMatch(number.factorMap(), expected);
  }

  @Test
  public void factorMap_isUnmodifiable() {
    PrimeFactorization number = fromLong(3118500l);

    assertThrows(UnsupportedOperationException.class, () -> number.factorMap().put(5l, 2));
  }

  @Test
  public void exponentOf_forFactors_returnsCorrectPower() {
    PrimeFactorization number = fromLong(3118500l);

    assertEqual(number.exponentOf(2l), 2);
    assertEqual(number.exponentOf(3l), 4);
    assertEqual(number.exponentOf(5l), 3);
    assertEqual(number.exponentOf(7l), 1);
    assertEqual(number.exponentOf(11l), 1);
  }

  @Test
  public void exponentOf_forNonFactors_returnsZero() {
    PrimeFactorization number = fromLong(7l);

    assertEqual(number.exponentOf(2l), 0);
    assertEqual(number.exponentOf(3l), 0);
    assertEqual(number.exponentOf(5l), 0);
    assertEqual(number.exponentOf(11l), 0);
  }

  @Test
  public void largestFactor_prime_returnsPrime() {
    PrimeFactorization number = fromLong(97l);

    assertEqual(number.largestFactor(), 97l);
  }

  @Test
  public void largestFactor_composite_returnsLargestFactor() {
    PrimeFactorization number = fromLong(3118500l);

    assertEqual(number.largestFactor(), 11l);
  }

  @Test
  public void numberOfDivisors_prime_returnsTwo() {
    PrimeFactorization number = fromLong(97l);

    assertEqual(number.numberOfDivisors(), 2l);
  }

  @Test
  public void numberOfDivisors_primeSquare_returnsThree() {
    PrimeFactorization number = fromLong(25l);

    assertEqual(number.numberOfDivisors(), 3l);
  }

  @Test
  public void numberOfDivisors_composite_returnsExpected() {
    PrimeFactorization number = fromLong(3118500l);

    assertEqual(number.numberOfDivisors(), 240l);
  }

  @Test
  public void isCoprimeWith_one_isTrue() {
    assertTrue(fromLong(2).isCoprimeWith(1));
  }

  @Test
  public void isCoprimeWith_primes_isTrue() {
    assertTrue(fromLong(5).isCoprimeWith(7));
    assertTrue(fromLong(7).isCoprimeWith(5));
  }

  @Test
  public void isCoprimeWith_primesAndCoprimeComposite_isTrue() {
    assertTrue(fromLong(23).isCoprimeWith(95));
    assertTrue(fromLong(95).isCoprimeWith(23));
  }

  @Test
  public void isCoprimeWith_coprimeComposites_isTrue() {
    assertTrue(fromLong(120).isCoprimeWith(77));
    assertTrue(fromLong(77).isCoprimeWith(120));
  }

  @Test
  public void isCoprimeWith_nonCoprimeComposites_isFalse() {
    assertFalse(fromLong(120).isCoprimeWith(55));
    assertFalse(fromLong(55).isCoprimeWith(120));
  }

  @Test
  public void gcd_primes_isOne() {
    PrimeFactorization a = fromLong(7l);
    PrimeFactorization b = fromLong(13l);

    PrimeFactorization gcd = a.gcd(b);
    assertTrue(gcd.factorMap().isEmpty());
  }

  @Test
  public void gcd_primes_largerFirst_isOne() {
    PrimeFactorization a = fromLong(13l);
    PrimeFactorization b = fromLong(7l);

    PrimeFactorization gcd = a.gcd(b);
    assertTrue(gcd.factorMap().isEmpty());
  }

  @Test
  public void gcd_sameNumber_isNumber() {
    PrimeFactorization a = fromLong(3118500l);
    PrimeFactorization b = fromLong(3118500l);

    PrimeFactorization gcd = a.gcd(b);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(2l, 2);
    expected.put(3l, 4);
    expected.put(5l, 3);
    expected.put(7l, 1);
    expected.put(11l, 1);
    assertMapsMatch(gcd.factorMap(), expected);
  }

  @Test
  public void gcd_composites_isExpected() {
    PrimeFactorization a = fromLong(72l);
    PrimeFactorization b = fromLong(81l);

    PrimeFactorization gcd = a.gcd(b);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(3l, 2);
    assertMapsMatch(gcd.factorMap(), expected);
  }

  @Test
  public void gcd_composites_largerFirst_isExpected() {
    PrimeFactorization a = fromLong(81l);
    PrimeFactorization b = fromLong(72l);

    PrimeFactorization gcd = a.gcd(b);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(3l, 2);
    assertMapsMatch(gcd.factorMap(), expected);
  }

  @Test
  public void gcd_largeComposites_isExpected() {
    PrimeFactorization a = fromLong(5549544l);
    PrimeFactorization b = fromLong(322959l);

    PrimeFactorization gcd = a.gcd(b);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(3l, 1);
    expected.put(7l, 2);
    expected.put(13l, 1);
    assertMapsMatch(gcd.factorMap(), expected);
  }

  @Test
  public void gcd_largeComposites_lergerFirst_isExpected() {
    PrimeFactorization a = fromLong(322959l);
    PrimeFactorization b = fromLong(5549544l);

    PrimeFactorization gcd = a.gcd(b);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(3l, 1);
    expected.put(7l, 2);
    expected.put(13l, 1);
    assertMapsMatch(gcd.factorMap(), expected);
  }

  @Test
  public void gcd_doesNotMofifyOriginals() {
    PrimeFactorization a = fromLong(72l);
    PrimeFactorization b = fromLong(81l);

    a.gcd(b);

    Map<Long, Integer> expectedA = new HashMap<>();
    expectedA.put(2l, 3);
    expectedA.put(3l, 2);
    Map<Long, Integer> expectedB = new HashMap<>();
    expectedB.put(3l, 4);
    assertMapsMatch(a.factorMap(), expectedA);
    assertMapsMatch(b.factorMap(), expectedB);
  }

  @Test
  public void multiplyBy_one_returnsEqual() {
    PrimeFactorization product = fromLong(72l).multiplyBy(fromLong(1l));

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(2l, 3);
    expected.put(3l, 2);

    assertMapsMatch(product.factorMap(), expected);
  }

  @Test
  public void multiplyBy_coprime_returnsJoinedFactorMap() {
    PrimeFactorization product = fromLong(25l).multiplyBy(fromLong(1331l));

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(5l, 2);
    expected.put(11l, 3);

    assertMapsMatch(product.factorMap(), expected);
  }

  @Test
  public void multiplyBy_oneDividesOther_changesOnlyExponents() {
    PrimeFactorization product = fromLong(216l).multiplyBy(fromLong(6l));

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(2l, 4);
    expected.put(3l, 4);

    assertMapsMatch(product.factorMap(), expected);
  }

  @Test
  public void multiplyBy_hasExpectedResult() {
    PrimeFactorization product = fromLong(12l).multiplyBy(fromLong(370));

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(2l, 3);
    expected.put(3l, 1);
    expected.put(5l, 1);
    expected.put(37l, 1);

    assertMapsMatch(product.factorMap(), expected);
  }

  @Test
  public void multiplyBy_doesNotMofifyOriginals() {
    PrimeFactorization a = fromLong(72l);
    PrimeFactorization b = fromLong(81l);

    a.multiplyBy(b);

    Map<Long, Integer> expectedA = new HashMap<>();
    expectedA.put(2l, 3);
    expectedA.put(3l, 2);
    Map<Long, Integer> expectedB = new HashMap<>();
    expectedB.put(3l, 4);
    assertMapsMatch(a.factorMap(), expectedA);
    assertMapsMatch(b.factorMap(), expectedB);
  }

  @Test
  public void divideBy_coprimes_hasExpected() {
    PrimeFactorization a = fromLong(72l);
    PrimeFactorization b = fromLong(143l);

    Rational r = a.divideBy(b);

    assertMapsMatch(r.numerator().factorMap(), a.factorMap());
    assertMapsMatch(r.denominator().factorMap(), b.factorMap());
  }

  @Test
  public void divideBy_notCoprimes_hasExpected() {
    PrimeFactorization a = fromLong(1008l);
    PrimeFactorization b = fromLong(720l);
    Map<Long, Integer> expectedA = new HashMap<>();
    expectedA.put(7l, 1);
    Map<Long, Integer> expectedB = new HashMap<>();
    expectedB.put(5l, 1);

    Rational r = a.divideBy(b);

    assertMapsMatch(r.numerator().factorMap(), expectedA);
    assertMapsMatch(r.denominator().factorMap(), expectedB);
  }

  @Test
  public void toLong_hasExpectedResult() {
    PrimeFactorization a = fromLong(72l);
    PrimeFactorization b = fromLong(81l);

    assertEqual(a.toLong(), 72l);
    assertEqual(b.toLong(), 81l);
    assertEqual(a.multiplyBy(b).toLong(), 72l * 81l);
  }
}
