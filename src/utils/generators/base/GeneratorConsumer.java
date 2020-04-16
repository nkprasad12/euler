package src.utils.generators.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import src.utils.generators.Generator;
import src.utils.generators.Generators;
import src.utils.generators.base.tuples.IndexAndValue;
import src.utils.generators.base.tuples.Tuples;
import src.utils.generators.base.tuples.Tuples.Tuple;

/* Convenience class for consuming elements in a generator. */
  public class GeneratorConsumer<T> {

    final Generator<T> generator;

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

    public <R, S> PairGeneratorConsumer<R, S> mapPair(Function<T, Tuple<R, S, ?, ?, ?>> mapper) {
      return new PairGeneratorConsumer<>(map(mapper));
    }

    public <R> GeneratorConsumer<R> flatMap(Function<T, GeneratorConsumer<R>> mapper) {
      return new GeneratorConsumer<>(new FlatMappingGenerator<>(generator, mapper));
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

    public GeneratorConsumer<IndexAndValue<T>> addIndices() {
      return Generators.from(new IndexingGenerator<T>(generator));
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
    private IndexAndValue<T> processAll(T defaultValue, Consumer<T> consumer) {
      int i = -1;
      T last = defaultValue;
      while (generator.hasNext()) {
        i++;
        last = generator.getNext();
        consumer.accept(last);
      }
      return new IndexAndValue<>(i, last);
    }

    private IndexAndValue<T> processAll(Consumer<T> consumer) {
      return processAll(null, consumer);
    }

    private IndexAndValue<T> lastIndexAndValue(T defaultValue) {
      return processAll(defaultValue, t -> {});
    }

    private int lastIndex() {
      return lastIndexAndValue(null).index();
    }

    public void forEach(Consumer<T> consumer) {
      processAll(consumer);
    }

    public T lastValue(T defaultValue) {
      return lastIndexAndValue(defaultValue).value();
    }

    public T lastValue() {
      return lastValue(null);
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
      return reducing(initial, function).lastValue(initial);
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