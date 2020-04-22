package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.utils.generators.Generator;
import src.utils.generators.Generators;
import src.utils.generators.consumers.GeneratorConsumer;

public class Assertions {

  @SafeVarargs
  public static <T> void assertGenerates(Generator<T> generator, T ... expected) {
	  assertGenerates(generator, Arrays.asList(expected));
	}

	public static <T> void assertGenerates(Generator<T> generator, List<T> expected) {
	  for (T t : expected) {
			assertTrue(
          "Generator does not have next, but expected element " + t,
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

  @SafeVarargs
	public static <T> void assertGeneratesUnordered(
      Generator<T> generator, T ... expected) {
    assertGeneratesUnordered(generator, Arrays.asList(expected));
  }

	public static <T> void assertGeneratesUnordered(
      Generator<T> generator, List<T> expected) {
    assertGeneratesUnordered(Generators.from(generator), expected);
  }

  @SafeVarargs
	public static <T> void assertGeneratesUnordered(
      GeneratorConsumer<T> generatorConsumer, T ... expected) {
    assertGeneratesUnordered(generatorConsumer, Arrays.asList(expected));
  }

	public static <T> void assertGeneratesUnordered(
      GeneratorConsumer<T> generatorConsumer, List<T> expected) {
    Set<T> actual = generatorConsumer.set();
    assertEqual(
        actual.size(), 
        expected.size(), 
        "Generates number of elements is not equal to expected number.");
    for (T t : expected) {
      assertTrue("Expected element " + t + " is not generated.", actual.contains(t));
    }
	}

  @SafeVarargs
  public static <T> void assertGeneratesLists(Generator<List<T>> generator, List<T> ... expected) {
	  assertGeneratesLists(generator, Arrays.asList(expected));
	}

	public static <T> void assertGeneratesLists(Generator<List<T>> generator, List<List<T>> expected) {
	  for (List<T> t : expected) {
			assertTrue(
          "Generator does not have next, but expected element " + t,
          generator.hasNext());
			assertEqual(generator.getNext(), t);
		}
		assertFalse(generator.hasNext());
	}
	
	@SafeVarargs
  public static <T> void assertGeneratesLists(
      GeneratorConsumer<List<T>> generatorConsumer, List<T> ... expected) {
    assertGeneratesLists(generatorConsumer, Arrays.asList(expected));
	}

	public static <T> void assertGeneratesLists(
      GeneratorConsumer<List<T>> generatorConsumer, List<List<T>> expected) {
	  assertGeneratesLists(generatorConsumer.generator(), expected);
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
    assertEqual(actual.size(), n, "List size");

    for (int i = 0; i < n; i++) {
      assertEqual(actual.get(i), expected.get(i), "Index " + i);
    }
  }

  public static <T> void assertContainEqualElements(
      Collection<T> actual, Collection<T> expected) {
    assertEqual(actual.size(), expected.size());
    ArrayList<T> actualList = new ArrayList<T>(actual);
    ArrayList<T> expectedList = new ArrayList<T>(expected);
    for (T t : actualList) {
      assertTrue(
          "Actual contains unexpected element " + t,
          expectedList.remove(t));
    }
    assertTrue(
        "Actual does not contain expected elements " + expectedList, 
        expectedList.size() == 0);
  }
	
	public static <K, V> void assertMapsMatch(Map<K, V> expected, Map<K, V> actual) {
		assertEqual(actual.size(), expected.size(), "Map sizes");

		for (K key : expected.keySet()) {
			assertTrue(actual.containsKey(key));
			assertEqual(expected.get(key), actual.get(key), "Value for key " + key);
		}
	} 

  public static <T> void assertNotEqual(T first, T second) {
    assertFalse(
        String.format("first: %s unexpectedly matches second: %s", first, second),
        first.equals(second));
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