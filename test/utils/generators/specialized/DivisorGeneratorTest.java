package utils.generators.specialized;

import static assertions.Assertions.assertContainEqualElements;
import static assertions.Assertions.assertGenerates;
import static assertions.Assertions.assertGeneratesNone;

import java.util.Arrays;
import org.junit.Test;
import utils.generators.Generators;
import utils.generators.consumers.GeneratorConsumer;
import utils.generators.specialized.DivisorGenerator;
import utils.primes.PrimeFactorizations;
import utils.primes.Primes;

public class DivisorGeneratorTest {

  @Test
  public void divisors_ofOne_areOne() {
    assertGenerates(divisors(1, true, true), 1l);
  }

  @Test
  public void properDivisors_ofOne_areEmpty() {
    assertGeneratesNone(divisors(1, true, false));
  }

  @Test
  public void nonUnitDivisors_ofOne_areEmpty() {
    assertGeneratesNone(divisors(1, false, true));
  }

  @Test
  public void properNonUnitDivisors_ofOne_areEmpty() {
    assertGeneratesNone(divisors(1, false, false));
  }

  @Test
  public void divisors_ofPrime_areOneAndPrime() {
    assertGenerates(divisors(3, true, true), 1l, 3l);
  }

  @Test
  public void properDivisors_ofPrime_areOne() {
    assertGenerates(divisors(3, true, false), 1l);
  }

  @Test
  public void nonUnitDivisors_ofPrime_arePrime() {
    assertGenerates(divisors(3, false, true), 3l);
  }

  @Test
  public void properNonUnitDivisors_ofPrime_areEmpty() {
    assertGeneratesNone(divisors(3, false, false));
  }

  @Test
  public void divisors_ofPrimeSquare_generatesExpected() {
    assertContainEqualElements(divisors(25, true, true).list(), Arrays.asList(1l, 5l, 25l));
  }

  @Test
  public void properDivisors_ofPrimeSquare_generatesExpected() {
    assertContainEqualElements(divisors(25, true, false).list(), Arrays.asList(1l, 5l));
  }

  @Test
  public void nonUnitDivisors_ofPrimeSquare_generatesExpected() {
    assertContainEqualElements(divisors(25, false, true).list(), Arrays.asList(5l, 25l));
  }

  @Test
  public void properNonUnitDivisors_ofPrimeSquare_generatesExpected() {
    assertContainEqualElements(divisors(25, false, false).list(), Arrays.asList(5l));
  }

  @Test
  public void divisors_ofComposite_generatesExpected() {
    assertContainEqualElements(
        divisors(12, true, true).list(), Arrays.asList(1l, 2l, 3l, 4l, 6l, 12l));
  }

  @Test
  public void properDivisors_ofComposite_generatesExpected() {
    assertContainEqualElements(divisors(12, true, false).list(), Arrays.asList(1l, 2l, 3l, 4l, 6l));
  }

  @Test
  public void nonUnitDivisors_ofComposite_generatesExpected() {
    assertContainEqualElements(
        divisors(12, false, true).list(), Arrays.asList(2l, 3l, 4l, 6l, 12l));
  }

  @Test
  public void properNonUnitDivisors_ofComposite_generatesExpected() {
    assertContainEqualElements(divisors(12, false, false).list(), Arrays.asList(2l, 3l, 4l, 6l));
  }

  @Test
  public void divisors_ofLargeComposite_generatesExpected() {
    assertContainEqualElements(
        divisors(24726, true, true).list(),
        Arrays.asList(
            1l, 2l, 3l, 6l, 13l, 26l, 39l, 78l, 317l, 634l, 951l, 1902l, 4121l, 8242l, 12363l,
            24726l));
  }

  private static GeneratorConsumer<Long> divisors(long n, boolean includeOne, boolean includeN) {
    PrimeFactorizations p = new PrimeFactorizations(new Primes());
    return Generators.from(new DivisorGenerator(p.factorizationOf(n), includeOne, includeN));
  }
}
