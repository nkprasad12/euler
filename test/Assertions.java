package test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

public class Assertions {

    @SafeVarargs
    public static <T> void assertListMatches(List<T> list, T ... expected) {
	    Assertions.assertListsMatch(list, Arrays.asList(expected));
	}

	public static <T> void assertListsMatch(List<T> actual, List<T> expected) {
	    int n = expected.size();
	    Assertions.assertEqual(actual.size(), n);
	
	    for (int i = 0; i < n; i++) {
	        Assertions.assertEqual(actual.get(i), expected.get(i), "Index " + i);
	    }
    }
    
    public static <T> void assertEqual(T actual, T expected) {
        Assertions.assertEqual(actual, expected, "");
    }

	public static <T> void assertEqual(T actual, T expected, String tag) {
	    assertTrue(
	        String.format(tag + " actual: %d does not match expected: %d", actual, expected),
	        actual.equals(expected));
	}

	// public static void assertEqual(int actual, int expected) {
	//     Assertions.assertEqual(actual, expected, "");
	// }

	// public static void assertEqual(int actual, int expected, String tag) {
	//     assertTrue(
	//         String.format(tag + " actual: %d does not match expected: %d", actual, expected),
	//         actual == expected);
	// }
}