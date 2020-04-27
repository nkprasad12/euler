package utils.generators.base;

import java.util.function.Function;
import utils.generators.Generator;

/** An infinite generator that maps future values in terms of previous values. */
public final class RecursiveGenerator<T> implements Generator<T> {

  private final Function<T, T> recursion;

  private T current;

  public RecursiveGenerator(T initial, Function<T, T> recursion) {
    this.current = initial;
    this.recursion = recursion;
  }

  @Override
  public T getNext() {
    T result = current;
    current = recursion.apply(current);
    return result;
  }

  @Override
  public boolean hasNext() {
    return true;
  }
}
