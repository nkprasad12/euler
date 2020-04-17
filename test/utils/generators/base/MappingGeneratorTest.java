package test.utils.generators.base;

import static org.junit.Assert.assertFalse;
import static test.Assertions.assertGenerates;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import src.utils.generators.Generator;
import src.utils.generators.base.IteratorWrappingGenerator;
import src.utils.generators.base.MappingGenerator;

public class MappingGeneratorTest {

    private static final List<Integer> EMPTY =
        Collections.unmodifiableList(Collections.emptyList());
    private static final List<Integer> LIST =
        Collections.unmodifiableList(Arrays.asList(1, 2, 3));

    @Test
    public void map_emptyGenerator_givesEmptyGenerator() {
        Generator<Integer> original = new IteratorWrappingGenerator<>(EMPTY.iterator());

        Generator<String> generator =
            new MappingGenerator<>(original, i -> Integer.toString(i));

        assertFalse(generator.hasNext());
    }

    @Test
    public void map_nonEmptyGenerator_givesMappedResult() {
        Generator<Integer> original = new IteratorWrappingGenerator<>(LIST.iterator());

        Generator<String> generator =
            new MappingGenerator<>(original, i -> Integer.toString(i));

        assertGenerates(generator, "1", "2", "3");
    }
}
