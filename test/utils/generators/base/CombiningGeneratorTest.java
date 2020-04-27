package utils.generators.base;

import static assertions.Assertions.assertGenerates;
import static assertions.Assertions.assertGeneratesNone;
import static utils.generators.base.tuples.Tuples.pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import utils.generators.Generator;
import utils.generators.base.tuples.Tuples.Pair;

public class CombiningGeneratorTest {

  private static final String FIRST = "first";
  private static final String SECOND = "second";
  private static final String THIRD = "third";
  private static final String FOURTH = "fourth";
  private static final String FIFTH = "fifth";
  private static final String SIXTH = "sixth";
  private static final String SEVENTH = "seventh";

  private static final List<String> EMPTY = Collections.unmodifiableList(Collections.emptyList());
  private static final List<String> FIRST_THREE =
      Collections.unmodifiableList(Arrays.asList(FIRST, SECOND, THIRD));
  private static final List<String> JUST_FOURTH =
      Collections.unmodifiableList(Arrays.asList(FOURTH));
  private static final List<String> LAST_THREE =
      Collections.unmodifiableList(Arrays.asList(FIFTH, SIXTH, SEVENTH));

  @Test
  public void combiningGenerator_emptyGenerators_generatesNone() {
    Generator<String> first = new IteratorWrappingGenerator<>(EMPTY.iterator());
    Generator<String> second = new IteratorWrappingGenerator<>(EMPTY.iterator());

    Generator<Pair<String, String>> generator = new CombiningGenerator<>(first, second);

    assertGeneratesNone(generator);
  }

  @Test
  public void combiningGenerator_oneEmptyGenerator_generatesNone() {
    Generator<String> first = new IteratorWrappingGenerator<>(EMPTY.iterator());
    Generator<String> second = new IteratorWrappingGenerator<>(LAST_THREE.iterator());

    Generator<Pair<String, String>> generator = new CombiningGenerator<>(first, second);

    assertGeneratesNone(generator);
  }

  @Test
  public void combiningGenerator_singletonGenerator_generatesOne() {
    Generator<String> first = new IteratorWrappingGenerator<>(LAST_THREE.iterator());
    Generator<String> second = new IteratorWrappingGenerator<>(JUST_FOURTH.iterator());

    Generator<Pair<String, String>> generator = new CombiningGenerator<>(first, second);

    assertGenerates(generator, pair(FIFTH, FOURTH));
  }

  @Test
  public void combiningGenerator_generators_generatesExpected() {
    Generator<String> first = new IteratorWrappingGenerator<>(FIRST_THREE.iterator());
    Generator<String> second = new IteratorWrappingGenerator<>(LAST_THREE.iterator());

    Generator<Pair<String, String>> generator = new CombiningGenerator<>(first, second);

    assertGenerates(generator, pair(FIRST, FIFTH), pair(SECOND, SIXTH), pair(THIRD, SEVENTH));
  }
}
