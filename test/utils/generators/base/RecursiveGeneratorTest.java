package utils.generators.base;

import static org.junit.Assert.assertTrue;
import static assertions.Assertions.assertEqual;

import org.junit.Test;
import utils.generators.Generator;
import utils.generators.base.RecursiveGenerator;

public class RecursiveGeneratorTest {

  @Test
  public void recursiveGenerator_hasUnlimitedElements() {
    Generator<Integer> generator = new RecursiveGenerator<>(0, n -> n);

    // We can't verify that it never stops, but we can try lots of elements;
    for (int i = 0; i < 100000; i++) {
      assertTrue(generator.hasNext());
      generator.getNext();
    }
  }

  @Test
  public void recursiveGenerator_startWithInitial() {
    String initial = "Hello There";
    Generator<String> generator = new RecursiveGenerator<>(initial, n -> "Not Hello There");

    assertEqual(initial, generator.getNext());
  }

  @Test
  public void recursiveGenerator_mapsRecursion() {
    Generator<Integer> generator = new RecursiveGenerator<>(1, n -> 2 * n + 3);

    assertEqual(generator.getNext(), 1);
    assertEqual(generator.getNext(), 5);
    assertEqual(generator.getNext(), 13);
    assertEqual(generator.getNext(), 29);
  }
}
