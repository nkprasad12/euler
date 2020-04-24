package test.utils.generators.base;

import static org.junit.Assert.assertFalse;
import static test.Assertions.assertGenerates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import src.utils.generators.Generator;
import src.utils.generators.base.FlatMappingGenerator;
import src.utils.generators.base.IteratorWrappingGenerator;

public class FlatMappingGeneratorTest {

  private static final List<Integer> EMPTY = Collections.unmodifiableList(Collections.emptyList());
  private static final List<Integer> LIST = Collections.unmodifiableList(Arrays.asList(3, 2, 4));
  private static final List<Integer> INITIAL_ZERO =
      Collections.unmodifiableList(Arrays.asList(0, 2));
  private static final List<Integer> FINAL_ZERO = Collections.unmodifiableList(Arrays.asList(3, 0));
  private static final List<Integer> MIDDLE_ZERO =
      Collections.unmodifiableList(Arrays.asList(3, 0, 2));
  private static final List<Integer> JUST_ONE = Collections.unmodifiableList(Arrays.asList(1));

  @Test
  public void emptyGenerator_flatMapsToEmptyGenerator() {
    Generator<Integer> initial = new IteratorWrappingGenerator<>(EMPTY.iterator());

    Generator<String> generator =
        new FlatMappingGenerator<>(initial, FlatMappingGeneratorTest::getStringGenerator);

    assertFalse(generator.hasNext());
  }

  @Test
  public void normalGenerator_flatMapsToExpected() {
    Generator<Integer> initial = new IteratorWrappingGenerator<>(LIST.iterator());

    Generator<String> generator =
        new FlatMappingGenerator<>(initial, FlatMappingGeneratorTest::getStringGenerator);

    assertGenerates(generator, "3", "3", "3", "2", "2", "4", "4", "4", "4");
  }

  @Test
  public void normalGenerator_flatMapsToEmpty_generatesNone() {
    Generator<Integer> initial = new IteratorWrappingGenerator<>(LIST.iterator());

    Generator<Integer> generator =
        new FlatMappingGenerator<>(initial, i -> new IteratorWrappingGenerator<>(EMPTY.iterator()));

    assertFalse(generator.hasNext());
  }

  @Test
  public void initialEmptyMappedGenerator_flatMapsToExpected() {
    Generator<Integer> initial = new IteratorWrappingGenerator<>(INITIAL_ZERO.iterator());

    Generator<String> generator =
        new FlatMappingGenerator<>(initial, FlatMappingGeneratorTest::getStringGenerator);

    assertGenerates(generator, "2", "2");
  }

  @Test
  public void finalEmptyMappedGenerator_flatMapsToExpected() {
    Generator<Integer> initial = new IteratorWrappingGenerator<>(FINAL_ZERO.iterator());

    Generator<String> generator =
        new FlatMappingGenerator<>(initial, FlatMappingGeneratorTest::getStringGenerator);

    assertGenerates(generator, "3", "3", "3");
  }

  @Test
  public void middleEmptyMappedGenerator_flatMapsToExpected() {
    Generator<Integer> initial = new IteratorWrappingGenerator<>(MIDDLE_ZERO.iterator());

    Generator<String> generator =
        new FlatMappingGenerator<>(initial, FlatMappingGeneratorTest::getStringGenerator);

    assertGenerates(generator, "3", "3", "3", "2", "2");
  }

  @Test
  public void oneElementGenerator_flatMapsToExpected() {
    Generator<Integer> initial = new IteratorWrappingGenerator<>(JUST_ONE.iterator());

    Generator<String> generator =
        new FlatMappingGenerator<>(initial, FlatMappingGeneratorTest::getStringGenerator);

    assertGenerates(generator, "1");
  }

  // Maps i to the generator "i" "i" ... "i" (repeated i times).
  private static Generator<String> getStringGenerator(int i) {
    ArrayList<String> list = new ArrayList<>();
    for (int k = 0; k < i; k++) {
      list.add(Integer.toString(i));
    }
    return new IteratorWrappingGenerator<>(list.iterator());
  }
}
