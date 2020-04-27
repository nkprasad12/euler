package utils.generators;

import static assertions.Assertions.assertGenerates;
import static assertions.Assertions.assertGeneratesNone;
import static utils.generators.base.tuples.Tuples.pair;

import org.junit.Test;

public class GeneratorsTest {

  @Test
  public void fromRecursion_hasExpectedValues() {
    assertGeneratesNone(Generators.fromRecursion(0, i -> i + 1, i -> i < 0));

    assertGenerates(Generators.fromRecursion(0, i -> i + 1, i -> i <= 0), 0);

    assertGenerates(Generators.fromRecursion(0, i -> i + 1, i -> i <= 3), 0, 1, 2, 3);
  }

  @Test
  public void fromRecursion_ofPairs_hasExpectedValues() {
    assertGeneratesNone(
        Generators.fromRecursion(pair(1, 1), (i, j) -> pair(j, i + j), (i, j) -> j < 1));

    assertGenerates(
        Generators.fromRecursion(pair(1, 1), (i, j) -> pair(j, i + j), (i, j) -> j <= 1),
        pair(1, 1));

    assertGenerates(
        Generators.fromRecursion(pair(1, 1), (i, j) -> pair(j, i + j), (i, j) -> j <= 5),
        pair(1, 1),
        pair(1, 2),
        pair(2, 3),
        pair(3, 5));
  }

  @Test
  public void empty_generatesNone() {
    assertGeneratesNone(Generators.empty());
  }

  @Test
  public void fromCartesianProductOf_emptySets_returnsExpected() {
    assertGeneratesNone(
        Generators.fromCartesianProductOf(Generators.empty(), () -> Generators.empty()));

    assertGeneratesNone(
        Generators.fromCartesianProductOf(Generators.empty(), () -> Generators.from(1)));

    assertGeneratesNone(
        Generators.fromCartesianProductOf(Generators.from(1), () -> Generators.empty()));
  }

  @Test
  public void fromCartesianProductOf_nonEmptySets_returnsExpected() {
    assertGenerates(
        Generators.fromCartesianProductOf(Generators.from(1), () -> Generators.from("1")),
        pair(1, "1"));

    assertGenerates(
        Generators.fromCartesianProductOf(Generators.from(1, 2), () -> Generators.from("1")),
        pair(1, "1"),
        pair(2, "1"));

    assertGenerates(
        Generators.fromCartesianProductOf(Generators.from(1), () -> Generators.from("1", "2")),
        pair(1, "1"),
        pair(1, "2"));

    assertGenerates(
        Generators.fromCartesianProductOf(
            Generators.from(1, 2, 3), () -> Generators.from("1", "2")),
        pair(1, "1"),
        pair(1, "2"),
        pair(2, "1"),
        pair(2, "2"),
        pair(3, "1"),
        pair(3, "2"));
  }

  @Test
  public void range_generatesExpected() {
    assertGeneratesNone(Generators.range(1, 0));
    assertGenerates(Generators.range(1, 1), 1);
    assertGenerates(Generators.range(1, 3), 1, 2, 3);
    assertGenerates(Generators.range(-1, 2), -1, 0, 1, 2);
  }
}
