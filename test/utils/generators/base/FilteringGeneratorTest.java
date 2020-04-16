package test.utils.generators.base;

import static org.junit.Assert.assertFalse;
import static test.Assertions.assertGenerates;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import src.utils.generators.Generator;
import src.utils.generators.base.FilteringGenerator;
import src.utils.generators.base.IteratorWrappingGenerator;

public class FilteringGeneratorTest {

    private static final List<Integer> EMPTY =
        Collections.unmodifiableList(Collections.emptyList());
    private static final List<Integer> LIST =
        Collections.unmodifiableList(Arrays.asList(1, 2, 3));

    @Test
    public void filter_emptyGenerator_givesEmptyGenerator() {
        Generator<Integer> original = new IteratorWrappingGenerator<>(EMPTY.iterator());

        Generator<Integer> generator =
            new FilteringGenerator<>(original, i -> true);

        assertFalse(generator.hasNext());
    }

    @Test
    public void filter_nonEmptyGenerator_filterAll_givesEmptyGenerator() {
        Generator<Integer> original = new IteratorWrappingGenerator<>(LIST.iterator());

        Generator<Integer> generator =
            new FilteringGenerator<>(original, i -> false);

        assertFalse(generator.hasNext());
    }

    @Test
    public void filter_nonEmptyGenerator_givesExpected() {
        Generator<Integer> original = new IteratorWrappingGenerator<>(LIST.iterator());

        Generator<Integer> generator =
            new FilteringGenerator<>(original, i -> i % 2 != 0);

        assertGenerates(generator, 1, 3);
    }
}