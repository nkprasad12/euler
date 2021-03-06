package utils.generators.base;

import static assertions.Assertions.assertGenerates;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import utils.generators.Generator;

public class ReducingGeneratorTest {

  private static final List<Integer> EMPTY = Collections.unmodifiableList(Collections.emptyList());
  private static final List<Integer> LIST = Collections.unmodifiableList(Arrays.asList(1, 2, 3));

  @Test
  public void reducing_emptyGenerator_givesEmptyGenerator() {
    Generator<Integer> original = new IteratorWrappingGenerator<>(EMPTY.iterator());

    Generator<String> generator = new ReducingGenerator<>(original, "", (str, i) -> str + i);

    assertFalse(generator.hasNext());
  }

  @Test
  public void reducing_inputGenerator_performsReduction() {
    Generator<Integer> original = new IteratorWrappingGenerator<>(LIST.iterator());

    Generator<String> generator = new ReducingGenerator<>(original, "", (str, i) -> str + i);

    assertGenerates(generator, "1", "12", "123");
  }
}
