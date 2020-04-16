package src.utils.generators;

import java.lang.Iterable;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import src.utils.generators.base.FileReadingGenerator;
import src.utils.generators.base.GeneratorConsumer;
import src.utils.generators.base.IteratorWrappingGenerator;
import src.utils.generators.base.PairGeneratorConsumer;
import src.utils.generators.base.RecursiveGenerator;
import src.utils.generators.base.tuples.Tuples.Tuple;

/* Factory class for creating GeneratorConsumers for fluent chaining. */
public final class Generators {

  /* Creates a GeneratorConsumer wrapping the input Generator. */
  public static <T> GeneratorConsumer<T> from(Generator<T> generator) {
    return new GeneratorConsumer<>(generator);
  }

  /* Creates a GeneratorConsumer wrapping the input iterable, as a generator. */
  public static <T> GeneratorConsumer<T> from(Iterable<T> iterable) {
    return new GeneratorConsumer<>(
         new IteratorWrappingGenerator<>(iterable.iterator()));
  }

  public static <T> GeneratorConsumer<T> fromRecursion(
      T initial, Function<T, T> recursion, Predicate<T> generateWhile) {
    return from(new RecursiveGenerator<T>(initial, recursion))
        .whileTrue(generateWhile);
  }

  public static <T, R> PairGeneratorConsumer<T, R> fromRecursion(
      Tuple<T, R, ?, ?, ?> initial,
      BiFunction<T, R, Tuple<T, R, ?, ?, ?>> map,
      BiPredicate<T, R> generateWhile) {
    return new PairGeneratorConsumer<T, R>(
        fromRecursion(
            initial, 
            pair -> map.apply(pair.first(), pair.second()),
            pair -> generateWhile.test(pair.first(), pair.second())));
  }

  public static <T, R> PairGeneratorConsumer<T, R> fromCartesianProductOf(
      Generator<T> first, Supplier<GeneratorConsumer<R>> second) {
    return from(first).pairEachWith(second);
  }

  public static GeneratorConsumer<String> fromTextFile(String fileName) {
    return fromTextFile(fileName, null);
  }

  public static GeneratorConsumer<String> fromTextFile(String fileName, String delimiter) {
    return from(new FileReadingGenerator("src/textfiles/" + fileName, delimiter));
  }

  // Number ranges
  public static GeneratorConsumer<Long> naturals() {
    return from(NaturalNumberGenerator.withMin(1l, i -> i + 1l));
  }

  public static GeneratorConsumer<Long> naturalsUpTo(long max) {
    return naturals().whileTrue(i -> i <= max);
  }

  public static GeneratorConsumer<Integer> naturalsUpTo(int max) {
    return range(1, max);
  }
  
  public static GeneratorConsumer<Integer> range(int min, int max) {
    return from(NaturalNumberGenerator.withMin(min, i -> i + 1)).whileTrue(i -> i <= max);
  }

  public static GeneratorConsumer<Long> range(long min, long max) {
    return from(NaturalNumberGenerator.withMin(min, i -> i + 1l)).whileTrue(i -> i <= max);
  }

  // Permutations
  public static <T extends Comparable<T>> GeneratorConsumer<List<T>> permutationsOf(List<T> list) {
    return from(new PermutationGenerator<>(list));
  }

}