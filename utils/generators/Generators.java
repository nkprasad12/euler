package utils.generators;

import java.lang.Iterable;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Scanner;
import java.util.HashSet;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Generators {

  public static <T> GeneratorConsumer<T> from(Generator<T> generator) {
    return new GeneratorConsumer<>(generator);
  }

  public static <T> GeneratorConsumer<T> from(Iterable<T> iterable) {
    return new GeneratorConsumer<>(
         new IteratorWrappingGenerator<>(iterable.iterator()));
  }

  public static GeneratorConsumer<String> fromTextFile(String fileName) {
    return fromTextFile(fileName, null);
  }

  public static GeneratorConsumer<String> fromTextFile(String fileName, String delimiter) {
    return from(new FileReadingGenerator("textfiles/" + fileName, delimiter));
  }

  // Number ranges
  public static GeneratorConsumer<Long> naturals() {
    return from(NaturalNumberGenerator.withMin(1l, i -> i + 1l));
  }

  public static GeneratorConsumer<Long> naturalsUpTo(long max) {
    return naturals().until(i -> i == max);
  }

  public static GeneratorConsumer<Integer> naturalsUpTo(int max) {
    return range(1, max);
  }
  
  public static GeneratorConsumer<Integer> range(int min, int max) {
    return from(NaturalNumberGenerator.withMin(min, i -> i + 1)).until(i -> i == max);
  }

  public static GeneratorConsumer<Long> range(long min, long max) {
    return from(NaturalNumberGenerator.withMin(min, i -> i + 1l)).until(i -> i == max);
  }

  // Permutations
  public static <T extends Comparable<T>> GeneratorConsumer<List<T>> permutationsOf(List<T> list) {
    return from(new PermutationGenerator<>(list));
  }

  /* Convenience class for consuming elements in a generator. */
  public static final class GeneratorConsumer<T> {

    private final Generator<T> generator;

    private GeneratorConsumer(Generator<T> generator) {
      this.generator = generator;
    }

    public Generator<T> generator() {
      return this.generator;
    }

    // Other GeneratorConsumers below
    public <O> GeneratorConsumer<O> map(Function<T, O> mapper) {
      return new GeneratorConsumer<>(
          new MappingGenerator<>(generator, mapper));
    }

    public GeneratorConsumer<T> filter(Predicate<T> predicate) {
      return new GeneratorConsumer<>(
          new FilteringGenerator<>(generator, predicate));
    }

    public GeneratorConsumer<T> until(Predicate<T> terminateOn) {
      return new GeneratorConsumer<>(
          new TerminatingGenerator<>(generator, terminateOn));
    }

    public <R> GeneratorConsumer<R> reducing(R initial, BiFunction<R, T, R> reduction) {
      return new GeneratorConsumer<>(
          new ReducingGenerator<>(generator, initial, reduction));
    }

    public GeneratorConsumer<IndexAndValue<T>> addIndices() {
      return from(new IndexingGenerator<T>(generator));
    }

    private static <T> Function<T, T> sideEffect(Consumer<T> consumer) {
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
      return filter(predicate).until(t -> true).lastIndex() != -1;
    }

    // Terminal operation collecting generated items below.
    public static <I, C extends Collection<I>> C chainingAdd(C collection, I item) {
      collection.add(item);
      return collection;
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

  public static final class IndexAndValue<T> {

    private final int index;
    private final T value;

    private IndexAndValue(int index, T value) {
      this.index = index;
      this.value = value;
    }

    public int index() {
      return index;
    }

    public T value() {
      return value;
    }

    @Override
    public String toString() {
      return String.format("index: %d, value: %s", index, value);
    }

  }

  static final class MappingGenerator<I, O> implements Generator<O> {

    private final Generator<I> generator;
    private final Function<I, O> mapper;

    private MappingGenerator(Generator<I> generator, Function<I, O> mapper) {
      this.generator = generator;
      this.mapper = mapper;
    }

    @Override
    public O getNext() {
      return mapper.apply(generator.getNext());
    }

    @Override
    public boolean hasNext() {
      return generator.hasNext();
    }
  }

  static final class FilteringGenerator<T> implements Generator<T> {

    private final Generator<T> generator;
    private final Predicate<T> predicate;

    private T storedNext;
    private boolean hasNext;

    private FilteringGenerator(Generator<T> generator, Predicate<T> predicate) {
      this.generator = generator;
      this.predicate = predicate;
      findNext();
    }

    @Override
    public T getNext() {
      T result = storedNext;
      findNext();
      return result;
    }

    @Override
    public boolean hasNext() {
      return hasNext;
    }

    private void findNext() {
      T next = null;
      while(generator.hasNext()) {
        T candidate = generator.getNext();
        if (predicate.test(candidate)) {
          next = candidate;
          break;
        }
      }
      storedNext = next;
      hasNext = storedNext != null;
    }
  }

  static final class IteratorWrappingGenerator<T> implements Generator<T> {

    private final Iterator<T> iterator;

    public IteratorWrappingGenerator(Iterator<T> iterator) {
      this.iterator = iterator;
    }

    @Override
    public T getNext() {
      return iterator.next();
    }

    public boolean hasNext() {
      return iterator.hasNext();
    }
  }

  static final class TerminatingGenerator<T> implements Generator<T> {

    private final Generator<T> generator;
    private final Predicate<T> terminateOn;

    private boolean shouldTerminate = false;

    public TerminatingGenerator(Generator<T> generator, Predicate<T> terminateOn) {
      this.generator = generator;
      this.terminateOn = terminateOn;
    }

    @Override
    public T getNext() {
      T result = generator.getNext();
      shouldTerminate = terminateOn.test(result);
      return result;
    }

    @Override
    public boolean hasNext() {
      return !shouldTerminate && generator.hasNext();
    }
  }

  static final class ReducingGenerator<R, T> implements Generator<R> {

    private final Generator<T> generator;
    private final BiFunction<R, T, R> reduction;

    private R current;

    public ReducingGenerator(Generator<T> generator, R initial, BiFunction<R, T, R> reduction) {
      this.generator = generator;
      this.reduction = reduction;
      current = initial;
    }

    @Override
    public R getNext() {
      current = reduction.apply(current, generator.getNext());
      return current;
    }

    @Override
    public boolean hasNext() {
      return generator.hasNext();
    }
  }

  static final class FileReadingGenerator implements Generator<String> {

    private final Scanner scanner;

    public FileReadingGenerator(String filePath, String delimiter) {
      try {
        this.scanner = new Scanner(new File(filePath));
        this.scanner.useDelimiter(delimiter);
      } catch (Exception e) {
        throw new RuntimeException("Error while opening file", e);
      }
    }

    @Override
    public String getNext() {
      return scanner.next();
    }

    public boolean hasNext() {
      boolean result = scanner.hasNext();
      if (!result) {
        scanner.close();
      }
      return result;
    }
  }

  static final class IndexingGenerator<T> implements Generator<IndexAndValue<T>> {

    private final Generator<T> generator;

    private int index = 0;

    public IndexingGenerator(Generator<T> generator) {
      this.generator = generator;
    }

    @Override
    public IndexAndValue<T> getNext() {
      IndexAndValue<T> result = new IndexAndValue<>(index, generator.getNext());
      index += 1;
      return result;
    }

    @Override
    public boolean hasNext() {
      return generator.hasNext(); 
    }
  }

}