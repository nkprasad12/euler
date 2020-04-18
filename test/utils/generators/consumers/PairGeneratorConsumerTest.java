package test.utils.generators.consumers;

import static src.utils.generators.base.tuples.Tuples.pair;
import static test.Assertions.assertGenerates;
import static test.Assertions.assertGeneratesNone;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import org.junit.Test;

import src.utils.generators.base.IteratorWrappingGenerator;
import src.utils.generators.consumers.GeneratorConsumer;
import src.utils.generators.consumers.PairGeneratorConsumer;

public class PairGeneratorConsumerTest {

  private static final List<String> EMPTY =
      Collections.unmodifiableList(Collections.emptyList());
  private static final List<String> SINGLETON =
      Collections.unmodifiableList(Collections.singletonList("Ten"));
  private static final List<String> LIST =
      Collections.unmodifiableList(Arrays.asList("Two", "One", "Six"));

  @Test
  public void mapPair_pairToSingle_generatesExpected() {
    assertGeneratesNone(emptyPairGenerator().mapPair((i, str) -> i + str));

    assertGenerates(
        singletonPairGenerator().mapPair((i, str) -> i + str),
        "0Ten");

    assertGenerates(
        manyPairGenerator().mapPair((i, str) -> i + str),
        "0Two", "1One", "2Six");
  }

  @Test
  public void mapPair_functionAndFunction_generatesExpected() {
    assertGeneratesNone(
        emptyPairGenerator().mapPair(i -> i + 7, str -> str + "7"));

    assertGenerates(
        singletonPairGenerator().mapPair(i -> i + 7, str -> str + "7"),
        pair(7, "Ten7"));

    assertGenerates(
        manyPairGenerator().mapPair(i -> i + 7, str -> str + "7"),
        pair(7, "Two7"), pair(8, "One7"), pair(9, "Six7"));
  }

  @Test
  public void mapPair_functionAndBiFunction_generatesExpected() {
    assertGeneratesNone(
        emptyPairGenerator().mapPair(i -> i + 7, (i, str) -> str + i));

    assertGenerates(
        singletonPairGenerator().mapPair(i -> i + 7, (i, str) -> str + i),
        pair(7, "Ten0"));

    assertGenerates(
        manyPairGenerator().mapPair(i -> i + 7, (i, str) -> str + i),
        pair(7, "Two0"), pair(8, "One1"), pair(9, "Six2"));
  }

  @Test
  public void mapPair_biFunctionAndFunction_generatesExpected() {
    assertGeneratesNone(
        emptyPairGenerator().mapPair((i, str) -> str + i, str -> str + str));

    assertGenerates(
        singletonPairGenerator().mapPair((i, str) -> str + i, str -> str + str),
        pair("Ten0", "TenTen"));

    assertGenerates(
        manyPairGenerator().mapPair((i, str) -> str + i, str -> str + str),
        pair("Two0", "TwoTwo"), pair("One1", "OneOne"), pair("Six2", "SixSix"));
  }

  @Test
  public void mapPair_biFunctionAndBiFunction_generatesExpected() {
    assertGeneratesNone(
        emptyPairGenerator().mapPair((i, str) -> str + i, (i, str) -> i * str.length()));

    assertGenerates(
        singletonPairGenerator().mapPair((i, str) -> str + i, (i, str) -> i * str.length()),
        pair("Ten0", 0));

    assertGenerates(
        manyPairGenerator().mapPair((i, str) -> str + i, (i, str) -> i * str.length()),
        pair("Two0", 0), pair("One1", 3), pair("Six2", 6));
  }

  @Test
  public void filter_empty_generatesNone() {
    assertGeneratesNone(emptyPairGenerator().filter((i, str) -> true));
    assertGeneratesNone(emptyPairGenerator().filter((i, str) -> false));
  }

  @Test
  public void filter_singleton_generatesExpected() {
    assertGenerates(
        singletonPairGenerator().filter((i, str) -> true),
        pair(0, "Ten"));
    assertGeneratesNone(singletonPairGenerator().filter((i, str) -> false));
  }

  @Test
  public void filter_many_generatesExpected() {
    assertGenerates(
        manyPairGenerator().filter((i, str) -> i == 1 || str.charAt(0) == 'S'),
        pair(1, "One"), pair(2, "Six"));
  }

  @Test
  public void whileTrue_generatesExpected() {
    assertGeneratesNone(
        emptyPairGenerator().whileTrue((i, str) -> true));

    assertGeneratesNone(
        emptyPairGenerator().whileTrue((i, str) -> false));

    assertGenerates(
        singletonPairGenerator().whileTrue((i, str) -> true),
        pair(0, "Ten"));

    assertGenerates(
        manyPairGenerator().whileTrue((i, str) -> i == 0 || str.charAt(0) == 'O'),
        pair(0, "Two"), pair(1, "One"));
  }

  @Test
  public void reducing_generatesExpected() {
    BiFunction<String, Integer, String> firstReduction = (soFar, i) -> soFar + i;
    BiFunction<Integer, String, Integer> secondReduction = (sum, str) -> sum + str.length();

    assertGeneratesNone(
        emptyPairGenerator().reducing(pair("", 0), firstReduction, secondReduction));

    assertGenerates(
        singletonPairGenerator().reducing(pair("", 0), firstReduction, secondReduction), 
        pair("0", 3));

    assertGenerates(
        manyPairGenerator().reducing(pair("", 0), firstReduction, secondReduction),
        pair("0", 3), pair("01", 6), pair("012", 9));
  }

  private static PairGeneratorConsumer<Integer, String> emptyPairGenerator() {
    return getPairGenerator(EMPTY);
  }

  private static PairGeneratorConsumer<Integer, String> singletonPairGenerator() {
    return getPairGenerator(SINGLETON);
  }

  private static PairGeneratorConsumer<Integer, String> manyPairGenerator() {
    return getPairGenerator(LIST);
  }

  private static <T> PairGeneratorConsumer<Integer, T> getPairGenerator(List<T> list) {
    return GeneratorConsumer.from(
         new IteratorWrappingGenerator<>(list.iterator())).addIndices();
  }

}