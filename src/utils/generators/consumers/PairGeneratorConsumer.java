package src.utils.generators.consumers;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import src.utils.generators.Generator;
import src.utils.generators.base.tuples.Tuples;
import src.utils.generators.base.tuples.Tuples.Pair;

public class PairGeneratorConsumer<T, R> extends GeneratorConsumer<Pair<T, R>> {

  public PairGeneratorConsumer(Generator<Pair<T, R>> generator) {
    super(generator);
  }

  public PairGeneratorConsumer(GeneratorConsumer<Pair<T, R>> pairGeneratorConsumer) {
    this(pairGeneratorConsumer.generator());
  }

  /** Maps a generator of pairs back to a generator of single values using the mapper. */
  public <S> GeneratorConsumer<S> mapPair(BiFunction<T, R, S> mapper) {
    return map(pair -> mapper.apply(pair.first(), pair.second()));
  }

  /**
   * Maps a generator of pairs to a generator of pairs.
   *
   * <p>The first in the each generator pair is the first in the original pair mapped using the
   * first map function, and similarly for the second.
   */
  public <S, W> PairGeneratorConsumer<S, W> mapPair(
      Function<T, S> firstMap, Function<R, W> secondMap) {
    return mapPair((t, r) -> firstMap.apply(t), (t, r) -> secondMap.apply(r));
  }

  /**
   * Maps a generator of pairs to a generator of pairs.
   *
   * <p>The first in the each generator pair is the first in the original pair mapped using the
   * first map function, and the second is the result of the pair mapped with the second map
   * function.
   */
  public <S, W> PairGeneratorConsumer<S, W> mapPair(
      Function<T, S> firstMap, BiFunction<T, R, W> secondMap) {
    return mapPair((t, r) -> firstMap.apply(t), secondMap);
  }

  /**
   * Maps a generator of pairs to a generator of pairs.
   *
   * <p>See mapPair(Function, BiFunction).
   */
  public <S, W> PairGeneratorConsumer<S, W> mapPair(
      BiFunction<T, R, S> firstMap, Function<R, W> secondMap) {
    return mapPair(firstMap, (t, r) -> secondMap.apply(r));
  }

  /**
   * Maps a generator of pairs to a generator of pairs.
   *
   * <p>The first of the generator pair is the result of the first BiFunction, and similarly for the
   * second.
   */
  public <S, W> PairGeneratorConsumer<S, W> mapPair(
      BiFunction<T, R, S> firstMap, BiFunction<T, R, W> secondMap) {
    return new PairGeneratorConsumer<>(
        map(
            pair ->
                Tuples.pair(
                    firstMap.apply(pair.first(), pair.second()),
                    secondMap.apply(pair.first(), pair.second()))));
  }

  /**
   * Filters the generator using a BiPredicate, where the first in each pair is the first input to
   * the predicate and the second in the pair is the second.
   *
   * <p>See GeneratorConsumer::filter
   */
  public PairGeneratorConsumer<T, R> filter(BiPredicate<T, R> predicate) {
    return new PairGeneratorConsumer<>(filter(pair -> predicate.test(pair.first(), pair.second())));
  }

  /**
   * Stops the generator at a certain point using a BiPredicate, where the first in each pair is the
   * first input to the predicate and the second in the pair is the second.
   *
   * <p>See GeneratorConsumer::whileTrue
   */
  public PairGeneratorConsumer<T, R> whileTrue(BiPredicate<T, R> predicate) {
    return new PairGeneratorConsumer<>(
        whileTrue(pair -> predicate.test(pair.first(), pair.second())));
  }

  /**
   * Reduces the generator of pairs starting from an inital pair. The firstReduction is appled to
   * the first of the pair, and the secondReduction is applied to the second.
   *
   * <p>See GeneratorConsumer::reducing
   */
  public <S, W> PairGeneratorConsumer<S, W> reducing(
      Pair<S, W> initial, BiFunction<S, T, S> firstReduction, BiFunction<W, R, W> secondReduction) {
    return new PairGeneratorConsumer<>(
        reducing(
            initial,
            (soFarPair, nextPair) ->
                Tuples.pair(
                    firstReduction.apply(soFarPair.first(), nextPair.first()),
                    secondReduction.apply(soFarPair.second(), nextPair.second()))));
  }

  /**
   * Returns the maximun value of the generated elements, as determined by the given metric.
   *
   * <p>This is a convenience function unwrapping the underlying generator of pairs.
   */
  public <S extends Comparable<S>> LastValue<Pair<T, R>> max(BiFunction<T, R, S> metric) {
    return max(pair -> metric.apply(pair.first(), pair.second()));
  }

  @Override
  public PairGeneratorConsumer<T, R> print(String tag) {
    return new PairGeneratorConsumer<>(print(tag));
  }
}
