package utils.generators.base;

import static assertions.Assertions.assertGenerates;
import static assertions.Assertions.assertGeneratesNone;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import utils.generators.Generator;
import utils.generators.base.IteratorWrappingGenerator;
import utils.generators.base.TerminatingGenerator;

public class TerminatingGeneratorTest {

  private static final List<Integer> EMPTY = Collections.unmodifiableList(Collections.emptyList());
  private static final List<Integer> LIST = Collections.unmodifiableList(Arrays.asList(1, 2, 3));
  private static final List<Integer> SINGLETON = Collections.unmodifiableList(Arrays.asList(5));

  @Test
  public void terminating_emptyGenerator_givesEmptyGenerator() {
    Generator<Integer> original = new IteratorWrappingGenerator<>(EMPTY.iterator());

    Generator<Integer> generator = new TerminatingGenerator<>(original, i -> false);

    assertGeneratesNone(generator);
  }

  @Test
  public void terminating_falsePredicate_givesIdenticalGenerator() {
    Generator<Integer> original = new IteratorWrappingGenerator<>(LIST.iterator());

    Generator<Integer> generator = new TerminatingGenerator<>(original, i -> false);

    assertGenerates(generator, 1, 2, 3);
  }

  @Test
  public void terminating_truePredicate_givesEmptyGenerator() {
    Generator<Integer> original = new IteratorWrappingGenerator<>(LIST.iterator());

    Generator<Integer> generator = new TerminatingGenerator<>(original, i -> true);

    assertGeneratesNone(generator);
  }

  @Test
  public void terminating_stopsBeforeTerminalPredicate() {
    Generator<Integer> original = new IteratorWrappingGenerator<>(LIST.iterator());

    Generator<Integer> generator = new TerminatingGenerator<>(original, i -> i == 2);

    assertGenerates(generator, 1);
  }

  @Test
  public void terminating_singletonWithFalsePredicate_generatesExpected() {
    Generator<Integer> original = new IteratorWrappingGenerator<>(SINGLETON.iterator());

    Generator<Integer> generator = new TerminatingGenerator<>(original, i -> false);

    assertGenerates(generator, 5);
  }

  @Test
  public void terminating_singletonWithTruePredicate_generatesNone() {
    Generator<Integer> original = new IteratorWrappingGenerator<>(SINGLETON.iterator());

    Generator<Integer> generator = new TerminatingGenerator<>(original, i -> true);

    assertGeneratesNone(generator);
  }
}
