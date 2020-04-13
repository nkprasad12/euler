package utils.generators;

import java.util.function.Function;

class NaturalNumberGenerator<T> implements Generator<T> {

  public static <T> NaturalNumberGenerator<T> withMin(T min, Function<T, T> increment) {
    return new NaturalNumberGenerator<>(min, increment);
  }

  private final Function<T, T> increment;

  private T current;

  private NaturalNumberGenerator(T min, Function<T, T> increment) {
    this.increment = increment;
    current = min;
  }

  @Override
  public T getNext() {
    T result = current;
    current = increment.apply(current);
    return result;
  }

  @Override
  public boolean hasNext() {
    return true;
  }
}