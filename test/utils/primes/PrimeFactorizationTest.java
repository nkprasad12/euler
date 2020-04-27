package utils.primes;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static assertions.Assertions.assertEqual;
import static assertions.Assertions.assertMapsMatch;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import utils.numbers.Rational;
import utils.primes.PrimeFactorization;
import utils.primes.Primes;

public class PrimeFactorizationTest {

  @Test
  public void fromLong_one_hasNoFactors() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(1l, primes);

    assertTrue(number.factorMap().isEmpty());
  }

  @Test
  public void fromLong_prime_hasOneFactor() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(37l, primes);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(37l, 1);
    assertMapsMatch(number.factorMap(), expected);
  }

  @Test
  public void fromLong_primeSquare_hasOneFactorWithPower() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(49l, primes);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(7l, 2);
    assertMapsMatch(number.factorMap(), expected);
  }

  @Test
  public void fromLong_primeProduct_hasTwoFactors() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(143l, primes);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(11l, 1);
    expected.put(13l, 1);
    assertMapsMatch(number.factorMap(), expected);
  }

  @Test
  public void fromLong_largeComposite_hasExpectedFactors() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(3118500l, primes);

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
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(3118500l, primes);

    assertThrows(UnsupportedOperationException.class, () -> number.factorMap().put(5l, 2));
  }

  @Test
  public void exponentOf_forFactors_returnsCorrectPower() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(3118500l, primes);

    assertEqual(number.exponentOf(2l), 2);
    assertEqual(number.exponentOf(3l), 4);
    assertEqual(number.exponentOf(5l), 3);
    assertEqual(number.exponentOf(7l), 1);
    assertEqual(number.exponentOf(11l), 1);
  }

  @Test
  public void exponentOf_forNonFactors_returnsZero() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(7l, primes);

    assertEqual(number.exponentOf(2l), 0);
    assertEqual(number.exponentOf(3l), 0);
    assertEqual(number.exponentOf(5l), 0);
    assertEqual(number.exponentOf(11l), 0);
  }

  @Test
  public void largestFactor_prime_returnsPrime() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(97l, primes);

    assertEqual(number.largestFactor(), 97l);
  }

  @Test
  public void largestFactor_composite_returnsLargestFactor() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(3118500l, primes);

    assertEqual(number.largestFactor(), 11l);
  }

  @Test
  public void numberOfDivisors_prime_returnsTwo() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(97l, primes);

    assertEqual(number.numberOfDivisors(), 2l);
  }

  @Test
  public void numberOfDivisors_primeSquare_returnsThree() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(25l, primes);

    assertEqual(number.numberOfDivisors(), 3l);
  }

  @Test
  public void numberOfDivisors_composite_returnsExpected() {
    Primes primes = new Primes();
    PrimeFactorization number = PrimeFactorization.of(3118500l, primes);

    assertEqual(number.numberOfDivisors(), 240l);
  }

  @Test
  public void gcd_primes_isOne() {
    Primes primes = new Primes();
    PrimeFactorization a = PrimeFactorization.of(7l, primes);
    PrimeFactorization b = PrimeFactorization.of(13l, primes);

    PrimeFactorization gcd = a.gcd(b);
    assertTrue(gcd.factorMap().isEmpty());
  }

  @Test
  public void gcd_sameNumber_isNumber() {
    Primes primes = new Primes();
    PrimeFactorization a = PrimeFactorization.of(3118500l, primes);
    PrimeFactorization b = PrimeFactorization.of(3118500l, primes);

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
    Primes primes = new Primes();
    PrimeFactorization a = PrimeFactorization.of(72l, primes);
    PrimeFactorization b = PrimeFactorization.of(81l, primes);

    PrimeFactorization gcd = a.gcd(b);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(3l, 2);
    assertMapsMatch(gcd.factorMap(), expected);
  }

  @Test
  public void gcd_largeComposites_isExpected() {
    Primes primes = new Primes();
    PrimeFactorization a = PrimeFactorization.of(5549544l, primes);
    PrimeFactorization b = PrimeFactorization.of(322959l, primes);

    PrimeFactorization gcd = a.gcd(b);

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(3l, 1);
    expected.put(7l, 2);
    expected.put(13l, 1);
    assertMapsMatch(gcd.factorMap(), expected);
  }

  @Test
  public void gcd_doesNotMofifyOriginals() {
    Primes primes = new Primes();
    PrimeFactorization a = PrimeFactorization.of(72l, primes);
    PrimeFactorization b = PrimeFactorization.of(81l, primes);

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
    Primes primes = new Primes();
    PrimeFactorization product =
        PrimeFactorization.of(72l, primes).multiplyBy(PrimeFactorization.of(1l, primes));

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(2l, 3);
    expected.put(3l, 2);

    assertMapsMatch(product.factorMap(), expected);
  }

  @Test
  public void multiplyBy_coprime_returnsJoinedFactorMap() {
    Primes primes = new Primes();
    PrimeFactorization product =
        PrimeFactorization.of(25l, primes).multiplyBy(PrimeFactorization.of(1331l, primes));

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(5l, 2);
    expected.put(11l, 3);

    assertMapsMatch(product.factorMap(), expected);
  }

  @Test
  public void multiplyBy_oneDividesOther_changesOnlyExponents() {
    Primes primes = new Primes();
    PrimeFactorization product =
        PrimeFactorization.of(216l, primes).multiplyBy(PrimeFactorization.of(6l, primes));

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(2l, 4);
    expected.put(3l, 4);

    assertMapsMatch(product.factorMap(), expected);
  }

  @Test
  public void multiplyBy_hasExpectedResult() {
    Primes primes = new Primes();
    PrimeFactorization product =
        PrimeFactorization.of(12l, primes).multiplyBy(PrimeFactorization.of(370, primes));

    Map<Long, Integer> expected = new HashMap<>();
    expected.put(2l, 3);
    expected.put(3l, 1);
    expected.put(5l, 1);
    expected.put(37l, 1);

    assertMapsMatch(product.factorMap(), expected);
  }

  @Test
  public void multiplyBy_doesNotMofifyOriginals() {
    Primes primes = new Primes();
    PrimeFactorization a = PrimeFactorization.of(72l, primes);
    PrimeFactorization b = PrimeFactorization.of(81l, primes);

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
    Primes primes = new Primes();
    PrimeFactorization a = PrimeFactorization.of(72l, primes);
    PrimeFactorization b = PrimeFactorization.of(143l, primes);

    Rational r = a.divideBy(b);

    assertMapsMatch(r.numerator().factorMap(), a.factorMap());
    assertMapsMatch(r.denominator().factorMap(), b.factorMap());
  }

  @Test
  public void divideBy_notCoprimes_hasExpected() {
    Primes primes = new Primes();
    PrimeFactorization a = PrimeFactorization.of(1008l, primes);
    PrimeFactorization b = PrimeFactorization.of(720l, primes);
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
    Primes primes = new Primes();
    PrimeFactorization a = PrimeFactorization.of(72l, primes);
    PrimeFactorization b = PrimeFactorization.of(81l, primes);

    assertEqual(a.toLong(), 72l);
    assertEqual(b.toLong(), 81l);
    assertEqual(a.multiplyBy(b).toLong(), 72l * 81l);
  }
}
