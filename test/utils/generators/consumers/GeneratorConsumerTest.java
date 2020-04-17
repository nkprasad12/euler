package test.utils.generators.consumers;

import static src.utils.generators.base.tuples.Tuples.pair;
import static test.Assertions.assertEqual;
import static test.Assertions.assertGenerates;
import static test.Assertions.assertGeneratesNone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import src.utils.generators.base.IteratorWrappingGenerator;
import src.utils.generators.consumers.GeneratorConsumer;

public class GeneratorConsumerTest {

  private static final List<Integer> EMPTY =
      Collections.unmodifiableList(Collections.emptyList());
  private static final List<Integer> LIST =
      Collections.unmodifiableList(Arrays.asList(2, 1, 3));

  @Test
  public void map_mapsEachOfUnderlying() {
    assertGenerates(from(LIST).map(i -> i * i), 4, 1, 9);
  }

  @Test
  public void flatMap_generatesExpected() {
    assertGenerates(
        from(LIST).flatMap(this::constantStringGenerator), 
        "2", "2", "1", "3", "3", "3");
  }

  @Test 
  public void mapAndPair_generatesExpected() {
    assertGenerates(
        from(LIST).mapAndPair(this::sequentialStringGenerator),
        pair(2, "0"), pair(2, "1"), pair(1, "0"), pair(3, "0"), pair(3, "1"), pair(3, "2"));
  }

  @Test 
  public void pairEachWith_generatesExpected() {
    assertGenerates(
        from(LIST).pairEachWith(() -> from(LIST)),
        pair(2, 2), pair(2, 1), pair(2, 3),
        pair(1, 2), pair(1, 1), pair(1, 3),
        pair(3, 2), pair(3, 1), pair(3, 3));
  }

  @Test
  public void filter_filtersNonMatching() {
    assertGenerates(from(LIST).filter(i -> i > 1), 2, 3);
  }

  @Test 
  public void whileTrue_terminatesAsExpected() {
    assertGenerates(from(LIST).whileTrue(i -> i <= 2), 2, 1);
  }

  @Test
  public void reducing_generatesExpected() {
    assertGenerates(
        from(LIST).reducing("Hi", (str, next) -> str + next),
        "Hi2", "Hi21", "Hi213");
  }

  @Test
  public void reduce_nonEmpty_returnsExpected() {
    assertEqual(
        from(LIST).reduce("Hi", (str, next) -> str + next),
        "Hi213");
  }

  @Test
  public void reduce_empty_returnsInitial() {
    assertEqual(
        from(EMPTY).reduce("Hi", (str, next) -> str + next),
        "Hi");
  }

  @Test 
  public void combineWith_hasExpectedResult() {
    assertGenerates(
      from(LIST).combineWith(sequentialStringGenerator(8)),
      pair(2, "0"), pair(1, "1"), pair(3, "2"));
  }

  @Test
  public void addIndices_emptyList_producesEmptyResult() {
    assertGeneratesNone(from(EMPTY).addIndices());
  }

  @Test
  public void addIndices_oneElement_addsExpectedIndices() {
    assertGenerates(
        from(Collections.singletonList(7)).addIndices(),
        pair(0, 7));
  }

  @Test
  public void addIndices_nonEmpty_addsExpectedIndices() {
    assertGenerates(
        from(LIST).addIndices(),
        pair(0, 2), pair(1, 1), pair(2, 3));
  }

  private static <T> GeneratorConsumer<T> from(List<T> list) {
    return GeneratorConsumer.from(new IteratorWrappingGenerator<>(list.iterator()));
  }

  // Maps i to the generator "i" "i" ... "i" (repeated i times).
  private GeneratorConsumer<String> constantStringGenerator(int i) {
    ArrayList<String> list = new ArrayList<>();
    for (int k = 0; k < i; k++) {
      list.add(Integer.toString(i));
    }
    return GeneratorConsumer.from(new IteratorWrappingGenerator<>(list.iterator()));
  }

  // Maps i to the generator "0" "1" ... "i - 1".
  private GeneratorConsumer<String> sequentialStringGenerator(int i) {
      ArrayList<String> list = new ArrayList<>();
      for (int k = 0; k < i; k++) {
      list.add(Integer.toString(k));
      }
      return GeneratorConsumer.from(new IteratorWrappingGenerator<>(list.iterator()));
  }
}