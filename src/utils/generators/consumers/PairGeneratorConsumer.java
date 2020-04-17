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

    public <S> GeneratorConsumer<S> mapPair(BiFunction<T, R, S> mapper) {
      return map(pair -> mapper.apply(pair.first(), pair.second()));
    }

    public <S, W> PairGeneratorConsumer<S, W> mapPair(Function<T, S> firstMap, Function<R, W> secondMap) {
      return mapPair((t, r) -> firstMap.apply(t), (t, r) -> secondMap.apply(r));
    }
                    
    public <S, W> PairGeneratorConsumer<S, W> mapPair(Function<T, S> firstMap, BiFunction<T, R, W> secondMap) {
      return mapPair((t, r) -> firstMap.apply(t), secondMap);
    }

    public <S, W> PairGeneratorConsumer<S, W> mapPair(BiFunction<T, R, S> firstMap, Function<R, W> secondMap) {
      return mapPair(firstMap, (t, r) -> secondMap.apply(r));
    }

    public <S, W> PairGeneratorConsumer<S, W> mapPair(BiFunction<T, R, S> firstMap, BiFunction<T, R, W> secondMap) {
      return new PairGeneratorConsumer<>(
          map(
              pair -> 
                  Tuples.pair(
                      firstMap.apply(pair.first(), pair.second()),
                      secondMap.apply(pair.first(), pair.second()))));
    }

    public PairGeneratorConsumer<T, R> filter(BiPredicate<T, R> predicate) {
      return new PairGeneratorConsumer<>(filter(pair -> predicate.test(pair.first(), pair.second())));
    }

    public PairGeneratorConsumer<T, R> whileTrue(BiPredicate<T, R> predicate) {
      return new PairGeneratorConsumer<>(whileTrue(pair -> predicate.test(pair.first(), pair.second())));
    }

    public <S, W> PairGeneratorConsumer<S, W> reducing(
        Pair<S, W> initial, 
        BiFunction<S, T, S> firstReduction,
        BiFunction<W, R, W> secondReduction) {
      return new PairGeneratorConsumer<>(
          reducing(
              initial, 
              (soFarPair, nextPair) ->
                  Tuples.pair(
                      firstReduction.apply(soFarPair.first(), nextPair.first()),
                      secondReduction.apply(soFarPair.second(), nextPair.second()))));
    }
  
    @Override
    public PairGeneratorConsumer<T, R> print(String tag) {
      return new PairGeneratorConsumer<>(print(tag));
    }
  }