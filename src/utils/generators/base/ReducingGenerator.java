package src.utils.generators.base;

import java.util.function.BiFunction;
import src.utils.generators.Generator;

/** Generator that maps and accumulates each element of an input generator. */
public final class ReducingGenerator<R, T> implements Generator<R> {

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
