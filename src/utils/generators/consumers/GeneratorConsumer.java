package utils.generators.consumers;

import static utils.generators.base.tuples.Tuples.pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import utils.generators.Generator;
import utils.generators.base.CombiningGenerator;
import utils.generators.base.FilteringGenerator;
import utils.generators.base.FlatMappingGenerator;
import utils.generators.base.IteratorWrappingGenerator;
import utils.generators.base.MappingGenerator;
import utils.generators.base.RecursiveGenerator;
import utils.generators.base.ReducingGenerator;
import utils.generators.base.TerminatingGenerator;
import utils.generators.base.tuples.Tuples;
import utils.generators.base.tuples.Tuples.Pair;

/** Convenience wrapper class for fluent computations with generators. */
public class GeneratorConsumer<T> {

  /** Creates a GeneratorConsumer wrapping the input Generator. */
  public static <T> GeneratorConsumer<T> from(Generator<T> generator) {
    return new GeneratorConsumer<>(generator);
  }

  /** Convenience method for GeneratorConsumer from a recursion, with an end condition. */
  public static <T> GeneratorConsumer<T> fromRecursion(
      T initial, Function<T, T> recursion, Predicate<T> generateWhile) {
    return from(new RecursiveGenerator<T>(initial, recursion)).whileTrue(generateWhile);
  }

  /** Convenience method for creating a flap mapping generator with a GeneratorConsumer function. */
  public static <I, O> FlatMappingGenerator<I, O> flatMappingGenerator(
      Generator<I> generator, Function<I, GeneratorConsumer<O>> mapper) {
    return new FlatMappingGenerator<>(generator, i -> mapper.apply(i).generator());
  }

  private final Generator<T> generator;

  public GeneratorConsumer(Generator<T> generator) {
    this.generator = generator;
  }

  public Generator<T> generator() {
    return this.generator;
  }

  /** Returns a GeneratorConsumer mapping the underlying Generator. */
  public <R> GeneratorConsumer<R> map(Function<T, R> mapper) {
    return new GeneratorConsumer<>(new MappingGenerator<>(generator, mapper));
  }

  /**
   * Returns a GeneratorConsumer mapping the underlying Generator to a Generator of Generators, and
   * then flattening the result.
   */
  public <R> GeneratorConsumer<R> flatMap(Function<T, GeneratorConsumer<R>> mapper) {
    return new GeneratorConsumer<>(flatMappingGenerator(generator, mapper));
  }

  /** Convenience method returning a PairGeneratorConsumer from a generator of pairs. */
  public <R, S> PairGeneratorConsumer<R, S> mapToPair(Function<T, Pair<R, S>> mapper) {
    return new PairGeneratorConsumer<>(map(mapper));
  }

  /**
   * Returns a PairGeneratorConsumer consisting of, for each of the underlying Generator's elements,
   * pairs of the elements and all of the elements of the resulting GeneratorConsumer of the mapper.
   */
  public <R> PairGeneratorConsumer<T, R> mapAndPair(Function<T, GeneratorConsumer<R>> mapper) {
    return new PairGeneratorConsumer<>(flatMap(t -> mapper.apply(t).map(r -> Tuples.pair(t, r))));
  }

  /** Generator of all pairs of this generator and the underlying generator of the supplier. */
  public <R> PairGeneratorConsumer<T, R> pairEachWith(Supplier<GeneratorConsumer<R>> supplier) {
    return mapAndPair(t -> supplier.get());
  }

  /** GeneratorConsumer that removes elements that do not match the predicate. */
  public GeneratorConsumer<T> filter(Predicate<T> predicate) {
    return new GeneratorConsumer<>(new FilteringGenerator<>(generator, predicate));
  }

  /**
   * GeneratorConsumer that generates elements of the underlying generator only as long as the
   * predicate is true and stops after.
   *
   * <p>The first element not to satisfy the predicate, if any, is not a generated element.
   */
  public GeneratorConsumer<T> whileTrue(Predicate<T> predicate) {
    return new GeneratorConsumer<>(new TerminatingGenerator<>(generator, predicate.negate()));
  }

  /**
   * GeneratorConsumer that reduces in sequence each element of the underlying generator according
   * to the given reduction function.
   */
  public <R> GeneratorConsumer<R> reducing(R initial, BiFunction<R, T, R> reduction) {
    return new GeneratorConsumer<>(new ReducingGenerator<>(generator, initial, reduction));
  }

  /**
   * Reduces each generator element according to the reduction (or returns the specified initial if
   * no elements are generated) and returns the result.
   */
  public <R> R reduce(R initial, BiFunction<R, T, R> function) {
    return reducing(initial, function).lastValue().orElse(initial);
  }

  /** Convenience method for {@link reduce} that prints rather than returns the result. */
  public <R> void reduceAndPrint(R initial, BiFunction<R, T, R> function) {
    System.out.println(reduce(initial, function));
  }

  /**
   * Creates a generator of pairs from pairwise matching generated elements of this generator with
   * the second generator.
   */
  public <U> PairGeneratorConsumer<T, U> combineWith(Generator<U> second) {
    return new PairGeneratorConsumer<>(new CombiningGenerator<>(generator, second));
  }

  /** Convenience overload of combineWith(Generator) */
  public <U> PairGeneratorConsumer<T, U> combineWith(GeneratorConsumer<U> second) {
    return combineWith(second.generator());
  }

  /**
   * Returns a PairGeneratorConsumer of each element of the underlying generator paired with its
   * 0-indexed index.
   *
   * <p>If this underlying generater has elements t_0, t_1, and so on, the returned produces (0,
   * t_0), (1, t_1), and so on.
   */
  public PairGeneratorConsumer<Integer, T> addIndices() {
    return from(new RecursiveGenerator<>(0, i -> i + 1)).combineWith(generator);
  }

  /**
   * Returns a Generator with elements identical to this one, but that prints each element as it is
   * generated.
   */
  public GeneratorConsumer<T> print(String tag) {
    return map(sideEffect(t -> System.out.println(String.format("%s: %s", tag, t))));
  }

  /** Executes some action on each generated element. */
  public void forEach(Consumer<T> consumer) {
    processAll(consumer);
  }

  /** Returns a representation of the last generated value, if any. */
  public LastValue<T> lastValue() {
    return new LastValue<>(processAll(t -> {}));
  }

  /** Prints all generated elements. */
  public void print() {
    forEach(t -> System.out.println(t));
  }

  /**
   * Returns whether any elements match the predicate.
   *
   * <p>Returns false if there are no generated elements.
   */
  public boolean anyMatch(Predicate<T> predicate) {
    return filter(predicate).generator().hasNext();
  }

  /** Collects all the generated elements into the specified collection. */
  public <C extends Collection<T>> C collectInto(C collection) {
    return reduce(collection, GeneratorConsumer::chainingAdd);
  }

  /** Convenience method that collects all elements into a list. */
  public List<T> list() {
    return collectInto(new ArrayList<T>());
  }

  /** Convenience method that collects all elements into a set. */
  public Set<T> set() {
    return collectInto(new HashSet<T>());
  }

  /** Returns the maximun value of the generated elements, as determined by the given metric. */
  public <R extends Comparable<R>> LastValue<T> max(Function<T, R> metric) {
    Pair<T, R> initial = pair(null, null);
    Pair<T, R> maxPair =
        reduce(
            initial,
            (max, next) -> {
              R score = metric.apply(next);
              boolean nextGreater = max.second() == null || score.compareTo(max.second()) > 0;
              return nextGreater ? pair(next, score) : max;
            });
    return new LastValue<>(maxPair == initial ? Optional.empty() : Optional.of(maxPair.first()));
  }

  // Fundamental terminal operation.
  private Optional<T> processAll(Consumer<T> consumer) {
    Optional<T> result = Optional.empty();
    while (generator.hasNext()) {
      result = Optional.of(generator.getNext());
      consumer.accept(result.get());
    }
    return result;
  }

  private static <I, C extends Collection<I>> C chainingAdd(C collection, I item) {
    collection.add(item);
    return collection;
  }

  private static <T> Function<T, T> sideEffect(Consumer<T> consumer) {
    return t -> {
      consumer.accept(t);
      return t;
    };
  }

  public static final class LastValue<T> {

    private final Optional<T> lastValue;

    private LastValue(Optional<T> lastValue) {
      this.lastValue = lastValue;
    }

    public T get() {
      return lastValue.get();
    }

    public boolean isPresent() {
      return lastValue.isPresent();
    }

    public boolean isEmpty() {
      return !isPresent();
    }

    public T orElse(T other) {
      return lastValue.orElse(other);
    }

    public GeneratorConsumer<T> asGenerator() {
      List<T> list = new ArrayList<T>();
      if (lastValue.isPresent()) {
        list.add(lastValue.get());
      }
      return from(new IteratorWrappingGenerator<>(list.iterator()));
    }

    public void print() {
      System.out.println(lastValue.isPresent() ? lastValue.get() : "No values generated.");
    }
  }
}
