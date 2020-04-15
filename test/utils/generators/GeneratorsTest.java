package test.utils.generators;

import static test.Assertions.assertListMatches;

import java.util.List;

import org.junit.Test;

import src.utils.generators.Generators;

public class GeneratorsTest {

    // TODO: Add many more test cases, especially for Pair functions.

    @Test
    public void flatMap_flattensGeneratorOfGenerators() {
        List<String> list = 
            Generators.range(0, 1)
                .flatMap(i -> Generators.range(0, 1).map(j -> String.format("%d %d", i, j)))
                .list();
        
        assertListMatches(list, "0 0", "0 1", "1 0", "1 1");
    }
}