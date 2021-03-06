package utils.primes;

import static assertions.Assertions.assertEqual;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static utils.primes.Primes.isPrimeStatic;

import java.util.List;
import java.util.SortedMap;
import org.junit.Test;
import utils.generators.Generators;

public class PrimesTest {

  @Test
  public void isPrimeStatic_correctlyDeterminesLargePrime() {
    assertTrue(isPrimeStatic(26002429l));
  }

  @Test
  public void isPrimeStatic_correctlyDeterminesLargeComposite() {
    assertFalse(isPrimeStatic(26002431l));
  }

  @Test
  public void isPrimeStatic_correctlyCountsPrimesUpTo1Million() {
    int numPrimesLessThan1Million =
        Generators.range(2l, 999999l)
            .filter(n -> isPrimeStatic(n))
            .reduce(0, (primesSoFar, next) -> primesSoFar + 1);
    assertTrue(numPrimesLessThan1Million == 78498);
  }

  @Test
  public void primesUpTo_correctlyCountsPrimesUpTo1Million() {
    Primes primes = new Primes();

    assertTrue(primes.primesUpTo(999999l).size() == 78498);
  }

  @Test
  public void primesUpTo_doesNotContainValuesAboveLimit() {
    Primes primes = new Primes();
    primes.primesUpTo(1000l);
    List<Long> primesUpTo = primes.primesUpTo(100l);

    assertTrue(primesUpTo.get(primesUpTo.size() - 1) <= 100);
  }

  @Test
  public void primesUpTo_primeMax_containsPrine() {
    Primes primes = new Primes();
    List<Long> primesUpTo = primes.primesUpTo(97l);

    assertEqual(primesUpTo.get(primesUpTo.size() - 1), 97l);
  }

  @Test
  public void primesUpTo_returnsUnModifiableSet() {
    Primes primes = new Primes();
    List<Long> list = primes.primesUpTo(100l);

    assertThrows(UnsupportedOperationException.class, () -> list.add(4l));
  }

  @Test
  public void nthPrime_returnsCorrectResults() {
    Primes primes = new Primes();

    assertEqual(primes.nthPrime(5), 11l);
    assertEqual(primes.nthPrime(299), 1979l);
    assertEqual(primes.nthPrime(100), 541l);
  }

  @Test
  public void isPrime_correctlyDeterminesLargePrime() {
    Primes primes = new Primes();

    assertTrue(primes.isPrime(26002429l));
    assertTrue(primes.primesUpTo(10).contains(2l));
  }

  @Test
  public void isPrime_correctlyDeterminesLargeComposite() {
    Primes primes = new Primes();

    assertFalse(primes.isPrime(26002431l));
  }

  @Test
  public void isPrime_correctlyCountsPrimesUpTo1Million() {
    Primes primes = new Primes();

    int numPrimesLessThan1Million =
        Generators.range(2l, 999999l)
            .filter(n -> primes.isPrime(n))
            .reduce(0, (primesSoFar, next) -> primesSoFar + 1);
    assertEqual(numPrimesLessThan1Million, 78498);
  }

  @Test
  public void testFactor_zero_throwsException() {
    Primes primes = new Primes();

    assertThrows(RuntimeException.class, () -> primes.factor(0));
  }

  @Test
  public void testFactor_one_isEmptyList() {
    Primes primes = new Primes();

    SortedMap<Long, Integer> factors = primes.factor(1l);
    assertTrue(factors.isEmpty());
  }

  @Test
  public void testFactor_6875_hasExpectedFactors() {
    Primes primes = new Primes();

    SortedMap<Long, Integer> factors = primes.factor(6875l);

    assertEqual(factors.size(), 2);
    assertTrue(factors.containsKey(5l));
    assertEqual(factors.get(5l), 4);
    assertTrue(factors.containsKey(11l));
    assertEqual(factors.get(11l), 1);
  }
}
