package utils.generators.base;

import static org.junit.Assert.assertFalse;
import static assertions.Assertions.assertGenerates;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import utils.generators.Generator;
import utils.generators.base.ConcatenatingGenerator;
import utils.generators.base.IteratorWrappingGenerator;

public class ConcatenatingGeneratorTest {

  private static final String FIRST = "first";
  private static final String SECOND = "second";
  private static final String THIRD = "third";
  private static final String FOURTH = "fourth";
  private static final String FIFTH = "fifth";
  private static final String ALPHA = "alpha";

  private static final List<String> EMPTY = Collections.unmodifiableList(Collections.emptyList());
  private static final List<String> FIRST_THREE =
      Collections.unmodifiableList(Arrays.asList(FIRST, SECOND, THIRD));
  private static final List<String> LAST_TWO =
      Collections.unmodifiableList(Arrays.asList(FOURTH, FIFTH));
  private static final List<String> JUST_ALPHA =
      Collections.unmodifiableList(Collections.singletonList(ALPHA));

  @Test
  public void concateningGenerator_emptyGenerators_hasEmptyGenerator() {
    Generator<String> first = new IteratorWrappingGenerator<>(EMPTY.iterator());
    Generator<String> second = new IteratorWrappingGenerator<>(EMPTY.iterator());

    Generator<String> generator = new ConcatenatingGenerator<>(first, second);

    assertFalse(generator.hasNext());
  }

  @Test
  public void concateningGenerator_emptyThenNonEmpty_hasContents() {
    Generator<String> first = new IteratorWrappingGenerator<>(FIRST_THREE.iterator());
    Generator<String> second = new IteratorWrappingGenerator<>(EMPTY.iterator());

    Generator<String> generator = new ConcatenatingGenerator<>(first, second);

    assertGenerates(generator, FIRST, SECOND, THIRD);
  }

  @Test
  public void concateningGenerator_nonEmptyThenEmpty_hasContents() {
    Generator<String> first = new IteratorWrappingGenerator<>(EMPTY.iterator());
    Generator<String> second = new IteratorWrappingGenerator<>(LAST_TWO.iterator());

    Generator<String> generator = new ConcatenatingGenerator<>(first, second);

    assertGenerates(generator, FOURTH, FIFTH);
  }

  @Test
  public void concateningGenerator_nonEmptyThenNonEmpty_hasContents() {
    Generator<String> first = new IteratorWrappingGenerator<>(FIRST_THREE.iterator());
    Generator<String> second = new IteratorWrappingGenerator<>(LAST_TWO.iterator());

    Generator<String> generator = new ConcatenatingGenerator<>(first, second);

    assertGenerates(generator, FIRST, SECOND, THIRD, FOURTH, FIFTH);
  }

  @Test
  public void concateningGenerator_singletonThenLarge_generatesExpected() {
    Generator<String> first = new IteratorWrappingGenerator<>(JUST_ALPHA.iterator());
    Generator<String> second = new IteratorWrappingGenerator<>(LAST_TWO.iterator());

    Generator<String> generator = new ConcatenatingGenerator<>(first, second);

    assertGenerates(generator, ALPHA, FOURTH, FIFTH);
  }

  @Test
  public void concateningGenerator_largeThenSingleton_generatesExpected() {
    Generator<String> first = new IteratorWrappingGenerator<>(FIRST_THREE.iterator());
    Generator<String> second = new IteratorWrappingGenerator<>(JUST_ALPHA.iterator());

    Generator<String> generator = new ConcatenatingGenerator<>(first, second);

    assertGenerates(generator, FIRST, SECOND, THIRD, ALPHA);
  }
}
