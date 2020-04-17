package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import src.utils.generators.Generator;
import src.utils.generators.consumers.GeneratorConsumer;

public class Assertions {

  @SafeVarargs
  public static <T> void assertGenerates(Generator<T> generator, T ... expected) {
	  assertGenerates(generator, Arrays.asList(expected));
	}

	public static <T> void assertGenerates(Generator<T> generator, List<T> expected) {
	  for (T t : expected) {
			assertTrue(
          "Generator needs next to match " + t,
          generator.hasNext());
			assertEqual(generator.getNext(), t);
		}
		assertFalse(generator.hasNext());
	}
	
	@SafeVarargs
    public static <T> void assertGenerates(GeneratorConsumer<T> generatorConsumer, T ... expected) {
	    assertGenerates(generatorConsumer, Arrays.asList(expected));
	}

	public static <T> void assertGenerates(GeneratorConsumer<T> generatorConsumer, List<T> expected) {
	    assertGenerates(generatorConsumer.generator(), expected);
	}

	public static <T> void assertGeneratesNone(Generator<T> generator) {
		assertFalse(
			"Generator expected to generate no elements, but hasNext() returns true",
			generator.hasNext());
	}

	public static <T> void assertGeneratesNone(GeneratorConsumer<T> generatorConsumer) {
		assertGeneratesNone(generatorConsumer.generator());
	}

    @SafeVarargs
    public static <T> void assertListMatches(List<T> list, T ... expected) {
	    assertListsMatch(list, Arrays.asList(expected));
	}

	public static <T> void assertListsMatch(List<T> actual, List<T> expected) {
	    int n = expected.size();
	    assertEqual(actual.size(), n);
	
	    for (int i = 0; i < n; i++) {
	        assertEqual(actual.get(i), expected.get(i), "Index " + i);
	    }
    }
	
	public static <K, V> void assertMapsMatch(Map<K, V> expected, Map<K, V> actual) {
		assertEqual(actual.size(), expected.size(), "Map sizes");

		for (K key : expected.keySet()) {
			assertTrue(actual.containsKey(key));
			assertEqual(expected.get(key), actual.get(key), "Value for key " + key);
		}
	} 

    public static <T> void assertEqual(T actual, T expected) {
        assertEqual(actual, expected, "");
    }

	public static <T> void assertEqual(T actual, T expected, String tag) {
	    assertTrue(
	        String.format(tag + " actual: %s does not match expected: %s", actual, expected),
	        actual.equals(expected));
	}
}