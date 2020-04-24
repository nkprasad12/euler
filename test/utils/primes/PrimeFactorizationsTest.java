package test.utils.primes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import src.utils.primes.PrimeFactorizations;
import src.utils.primes.Primes;

public class PrimeFactorizationsTest {

  @Test
  public void isPerfect_returnsExpectedResult() {
    PrimeFactorizations p = new PrimeFactorizations(new Primes());

    assertTrue(p.isPerfect(6));
    assertFalse(p.isPerfect(5));
    assertFalse(p.isPerfect(7));

    assertTrue(p.isPerfect(28));
    assertFalse(p.isPerfect(27));
    assertFalse(p.isPerfect(29));

    assertTrue(p.isPerfect(496));
    assertFalse(p.isPerfect(495));
    assertFalse(p.isPerfect(497));

    assertTrue(p.isPerfect(8128));
    assertFalse(p.isPerfect(8127));
    assertFalse(p.isPerfect(8129));
  }

  @Test
  public void isAbundant_returnsExpectedResult() {
    PrimeFactorizations p = new PrimeFactorizations(new Primes());

    assertTrue(p.isAbundant(12));
    assertFalse(p.isAbundant(11));
    assertFalse(p.isAbundant(13));

    assertTrue(p.isAbundant(56));
    assertFalse(p.isAbundant(55));
    assertFalse(p.isAbundant(57));

    assertTrue(p.isAbundant(992));
    assertFalse(p.isAbundant(991));
    assertFalse(p.isAbundant(993));

    assertTrue(p.isAbundant(945));
    assertFalse(p.isAbundant(944));
    assertFalse(p.isAbundant(946));
  }
}
