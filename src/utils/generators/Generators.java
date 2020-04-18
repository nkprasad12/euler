package src.utils.generators;

import java.lang.Iterable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import src.utils.generators.base.FileReadingGenerator;
import src.utils.generators.base.IteratorWrappingGenerator;
import src.utils.generators.base.tuples.Tuples.Pair;
import src.utils.generators.consumers.GeneratorConsumer;
import src.utils.generators.consumers.PairGeneratorConsumer;
import src.utils.generators.specialized.DivisorGenerator;
import src.utils.generators.specialized.PermutationGenerator;
import src.utils.primes.PrimeFactorizations;

/** Factory class for creating GeneratorConsumers for fluent chaining. */
public final class Generators {

  /** Creates a GeneratorConsumer wrapping the input Generator. */
  public static <T> GeneratorConsumer<T> from(Generator<T> generator) {
    return GeneratorConsumer.from(generator);
  }

  /** Creates a generator of pairs from the two input generators. */
  public static <T, U> PairGeneratorConsumer<T, U> from(
      Generator<T> first, Generator<U> second) {
    return from(first).combineWith(second);
  }

  /** Creates a GeneratorConsumer wrapping the input iterable as a generator. */
  public static <T> GeneratorConsumer<T> from(Iterable<T> iterable) {
    return new GeneratorConsumer<>(
         new IteratorWrappingGenerator<>(iterable.iterator()));
  }

  /** Creates a GeneratorConsumer of the input elements. */
  @SafeVarargs
  public static <T> GeneratorConsumer<T> from(T ... inputs) {
    return from(new IteratorWrappingGenerator<>(Arrays.asList(inputs).iterator()));
  }

  /** 
   * Creates a generator with semantics similar to a for loop.
   * 
   * The initial value is the first possible generated value, the recursion
   * function describes how to get new values from previous values, and 
   * the predicate describes the condition that all generated elements should
   * satisfy. 
   * 
   * Element generation stops as soon as a single element not satisfying the
   * predicate would have been generated.
   */
  public static <T> GeneratorConsumer<T> fromRecursion(
      T initial, Function<T, T> recursion, Predicate<T> generateWhile) {
    return GeneratorConsumer.fromRecursion(initial, recursion, generateWhile);
  }

  /** Convenience method identical to fromRecursion, but for generators of pairs. */
  public static <T, R> PairGeneratorConsumer<T, R> fromRecursion(
      Pair<T, R> initial,
      BiFunction<T, R, Pair<T, R>> map,
      BiPredicate<T, R> generateWhile) {
    return new PairGeneratorConsumer<T, R>(
        fromRecursion(
            initial, 
            pair -> map.apply(pair.first(), pair.second()),
            pair -> generateWhile.test(pair.first(), pair.second())));
  }

  /** 
   * Returns a generator generating all pairs in the Cartesian product
   * of the first generator and the generator supplied by the second.
   */
  public static <T, R> PairGeneratorConsumer<T, R> fromCartesianProductOf(
      GeneratorConsumer<T> first, Supplier<GeneratorConsumer<R>> second) {
    return first.pairEachWith(second);
  }


  /** Identical to fromTextFile(String, String), delimiting by whitespace. */
  public static GeneratorConsumer<String> fromTextFile(String fileName) {
    return fromTextFile(fileName, null);
  }

  /**
   * Returns a generator that generates tokens read from the input text file.
   * 
   * Tokens are delimited by the input pattern for the delimiter. 
   * File must be in the src/textfiles/ directory.
   */
  public static GeneratorConsumer<String> fromTextFile(String fileName, String delimiter) {
    return from(new FileReadingGenerator("src/textfiles/" + fileName, delimiter));
  }
  
  /** Generates the int range [min, max] */
  public static GeneratorConsumer<Integer> range(int min, int max) {
    return fromRecursion(min, i -> i + 1, i -> i <= max);
  }

  /** Generates the long range [min, max] */
  public static GeneratorConsumer<Long> range(long min, long max) {
    return fromRecursion(min, i -> i + 1l, i -> i <= max);  
  }

  /** Generates all permutations of the input list. */
  public static <T extends Comparable<T>> GeneratorConsumer<List<T>> permutationsOf(List<T> list) {
    return from(new PermutationGenerator<>(list));
  }

  /** Generates all proper divisors of a given integer. */
  public static GeneratorConsumer<Long> properDivisorsOf(long n, PrimeFactorizations p) {
    return from(new DivisorGenerator(p.factorizationOf(n), true, false));
  }

  /** Generates all divisors of a given integer, including the integer itself. */
  public static GeneratorConsumer<Long> divisorsOf(long n, PrimeFactorizations p) {
    return from(new DivisorGenerator(p.factorizationOf(n), true, true));
  }

  /** Generates no elements. */
  public static <T> GeneratorConsumer<T> empty() {
    return from(Collections.emptySet());
  }
}