package test.utils.generators.specialized;

import static test.Assertions.assertEqual;
import static test.Assertions.assertGeneratesLists;
import static test.Assertions.assertGeneratesNone;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import src.utils.generators.Generators;

public class PermutationGeneratorTest {

  @Test
  public void emptyList_hasNoPermutations() {
    assertGeneratesNone(Generators.from(Collections.emptyList()));
  }

  @Test
  public void singletonList_hasOnePermutation() {
    assertGeneratesLists(Generators.permutationsOf(Collections.singletonList(1)), Arrays.asList(1));
  }

  @Test
  public void threeList_hasOrderedPermutations() {
    assertGeneratesLists(
        Generators.permutationsOf(Arrays.asList(2, 1, 3)),
        Arrays.asList(1, 2, 3),
        Arrays.asList(1, 3, 2),
        Arrays.asList(2, 1, 3),
        Arrays.asList(2, 3, 1),
        Arrays.asList(3, 1, 2),
        Arrays.asList(3, 2, 1));
  }

  @Test
  public void permutationsOf_doesNotModifyOriginalList() {
    List<Integer> unmodifiable = Collections.unmodifiableList(Arrays.asList(2, 1));

    assertGeneratesLists(
        Generators.permutationsOf(unmodifiable), Arrays.asList(1, 2), Arrays.asList(2, 1));
  }

  @Test
  public void permutationsOf_nineElements_hasExpectedNumber() {
    List<Integer> tenElements = Generators.range(1, 10).list();
    int zeroIndexedExpectedNumber = 3628799;

    int actualNumber =
        Generators.permutationsOf(tenElements)
            .addIndices()
            .mapPair((i, permutation) -> i)
            .lastValue()
            .get();

    assertEqual(actualNumber, zeroIndexedExpectedNumber);
  }
}
