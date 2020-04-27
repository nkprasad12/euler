package utils.generators.base;

import static assertions.Assertions.assertGenerates;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import org.junit.Test;
import utils.generators.Generator;

public class IteratorWrappingGeneratorTest {

  private static final String FIRST = "first";
  private static final String SECOND = "second";
  private static final String THIRD = "third";

  @Test
  public void iteratorWrapping_emptyIterator_hasEmptyGenerator() {
    ArrayList<Integer> empty = new ArrayList<>();
    Generator<Integer> generator = new IteratorWrappingGenerator<>(empty.iterator());

    assertFalse(generator.hasNext());
  }

  @Test
  public void iteratorWrapping_nonEmptyIterator_hasContents() {
    ArrayList<String> list = new ArrayList<>();
    list.add(FIRST);
    list.add(SECOND);
    list.add(THIRD);

    Generator<String> generator = new IteratorWrappingGenerator<>(list.iterator());

    assertGenerates(generator, FIRST, SECOND, THIRD);
  }
}
