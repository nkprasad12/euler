package src.utils.generators.base;

import java.util.ArrayList;
import java.util.Arrays;
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

import src.utils.generators.Generator;
import src.utils.generators.base.tuples.Tuples;
import src.utils.generators.base.tuples.Tuples.Tuple;

/* Convenience class for consuming elements in a generator. */
  public class GeneratorConsumer<T> {

  /* Creates a GeneratorConsumer wrapping the input Generator. */
    public static <T> GeneratorConsumer<T> from(Generator<T> generator) {
      return new GeneratorConsumer<>(generator);
    }

    @SafeVarargs
    public static <T> GeneratorConsumer<T> from(T ... inputs) {
      return from(new IteratorWrappingGenerator<>(Arrays.asList(inputs).iterator()));
    }

    public static <T> GeneratorConsumer<T> fromRecursion(
        T initial, Function<T, T> recursion, Predicate<T> generateWhile) {
      return from(new RecursiveGenerator<T>(initial, recursion))
          .whileTrue(generateWhile);
    }

    private final Generator<T> generator;

    public GeneratorConsumer(Generator<T> generator) {
      this.generator = generator;
    }

    public Generator<T> generator() {
      return this.generator;
    }

    // Other GeneratorConsumers below
    public <R> GeneratorConsumer<R> map(Function<T, R> mapper) {
      return new GeneratorConsumer<>(
          new MappingGenerator<>(generator, mapper));
    }

    public <R> GeneratorConsumer<R> flatMap(Function<T, GeneratorConsumer<R>> mapper) {
      return new GeneratorConsumer<>(new FlatMappingGenerator<>(generator, mapper));
    }

    public <R, S> PairGeneratorConsumer<R, S> mapPair(Function<T, Tuple<R, S, ?, ?, ?>> mapper) {
      return new PairGeneratorConsumer<>(map(mapper));
    }

    public <R> PairGeneratorConsumer<T, R> mapAsPair(Function<T, GeneratorConsumer<R>> mapper) {
      return new PairGeneratorConsumer<>(
          flatMap(t -> mapper.apply(t).map(r -> Tuples.pair(t, r))));
    }

    public <R> PairGeneratorConsumer<T, R> pairEachWith(Supplier<GeneratorConsumer<R>> supplier) {
      return mapAsPair(t -> supplier.get());
    }

    public GeneratorConsumer<T> filter(Predicate<T> predicate) {
      return new GeneratorConsumer<>(
          new FilteringGenerator<>(generator, predicate));
    }

    public GeneratorConsumer<T> whileTrue(Predicate<T> predicate) {
      return new GeneratorConsumer<>(
          new TerminatingGenerator<>(generator, predicate.negate()));
    }

    public <R> GeneratorConsumer<R> reducing(R initial, BiFunction<R, T, R> reduction) {
      return new GeneratorConsumer<>(
          new ReducingGenerator<>(generator, initial, reduction));
    }

    public PairGeneratorConsumer<Integer, T> addIndices() {
      // return from(new IndexingGenerator<T>(generator));
      return new PairGeneratorConsumer<>(
          fromRecursion(
              Tuples.pair(0, generator.getNext()),
              pair -> Tuples.pair(pair.first() + 1, generator.getNext()),
              unused -> generator.hasNext()));
    }

    static <T> Function<T, T> sideEffect(Consumer<T> consumer) {
        return t -> {
          consumer.accept(t);
          return t;
        };
    }

    public GeneratorConsumer<T> print(String tag) {
      return map(sideEffect(t -> System.out.println(String.format("%s: %s", tag, t))));
    }

    // Terminal operations below.

    // Fundamental terminal operations.
    private Optional<T> processAll(Consumer<T> consumer) {
      Optional<T> result = Optional.empty();
      while (generator.hasNext()) {
        result = Optional.of(generator.getNext());
        consumer.accept(result.get());
      }
      return result;
    }

    public void forEach(Consumer<T> consumer) {
      processAll(consumer);
    }

    public Optional<T> lastValue() {
      return processAll(t -> {});
    }

    public void print() {
      forEach(t -> System.out.println(t));
    }

    public void printLast() {
      System.out.println(lastValue());
    }

    // Terminal operations returning single items below.
    public static <A, B> void setToResult(B b, A a, BiFunction<B, A, B> function) {
      b = function.apply(b, a);
    }

    public <R> R reduce(R initial, BiFunction<R, T, R> function) {
      return reducing(initial, function).lastValue().orElse(initial);
    }

    public <R> void reduceAndPrint(R initial, BiFunction<R, T, R> function) {
      System.out.println(reduce(initial, function));
    }

    public boolean anyMatch(Predicate<T> predicate) {
      return filter(predicate).generator().hasNext();
    }

    // Terminal operation collecting generated items below.
    public static <I, C extends Collection<I>> C chainingAdd(C collection, I item) {
      collection.add(item);
      return collection;
    }  

    public <C extends Collection<T>> GeneratorConsumer<C> collectingInto(C collection) {
      return reducing(collection, GeneratorConsumer::chainingAdd);
    }

    public <C extends Collection<T>> C collectInto(C collection) {
      return reduce(collection, GeneratorConsumer::chainingAdd);
    }

    public List<T> list() {
      return collectInto(new ArrayList<T>());
    }

    public Set<T> set() {
      return collectInto(new HashSet<T>());      
    }

  }