package test.utils.primes;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static src.utils.primes.Primes.isPrimeStatic;

import org.junit.Test;

import src.utils.generators.Generators;

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
}