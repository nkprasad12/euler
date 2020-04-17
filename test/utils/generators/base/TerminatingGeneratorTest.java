package test.utils.generators.base;

import static org.junit.Assert.assertFalse;
import static test.Assertions.assertGenerates;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import src.utils.generators.Generator;
import src.utils.generators.base.IteratorWrappingGenerator;
import src.utils.generators.base.TerminatingGenerator;

public class TerminatingGeneratorTest {

    private static final List<Integer> EMPTY =
        Collections.unmodifiableList(Collections.emptyList());
    private static final List<Integer> LIST =
        Collections.unmodifiableList(Arrays.asList(1, 2, 3));

    @Test
    public void terminating_emptyGenerator_givesEmptyGenerator() {
        Generator<Integer> original = new IteratorWrappingGenerator<>(EMPTY.iterator());

        Generator<Integer> generator =
            new TerminatingGenerator<>(original, i -> false);

        assertFalse(generator.hasNext());
    }

    @Test
    public void terminating_falsePredicate_givesIdenticalGenerator() {
        Generator<Integer> original = new IteratorWrappingGenerator<>(LIST.iterator());

        Generator<Integer> generator =
            new TerminatingGenerator<>(original, i -> false);

        assertGenerates(generator, 1, 2, 3);
    }

    @Test
    public void terminating_truePredicate_givesEmptyGenerator() {
        Generator<Integer> original = new IteratorWrappingGenerator<>(LIST.iterator());

        Generator<Integer> generator =
            new TerminatingGenerator<>(original, i -> true);

        assertFalse(generator.hasNext());
    }

    @Test
    public void terminating_stopsBeforeTerminalPredicate() {
        Generator<Integer> original = new IteratorWrappingGenerator<>(LIST.iterator());

        Generator<Integer> generator =
            new TerminatingGenerator<>(original, i -> i == 2);

        assertGenerates(generator, 1);
    }
}
