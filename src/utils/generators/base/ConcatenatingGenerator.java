package src.utils.generators.base;

import java.util.Optional;
import src.utils.generators.Generator;

/** Generator that conbines two generators in sequence into a single generator. */
public final class ConcatenatingGenerator<T> implements Generator<T> {

  private final Generator<T> first;
  private final Generator<T> second;

  boolean useFirst = true;
  Optional<T> next = Optional.empty();

  public ConcatenatingGenerator(Generator<T> first, Generator<T> second) {
    this.first = first;
    this.second = second;
    findNext();
  }

  @Override
  public T getNext() {
    T result = next.get();
    findNext();
    return result;
  }

  @Override
  public boolean hasNext() {
    return next.isPresent();
  }

  private void findNext() {
    next = Optional.empty();
    while (!next.isPresent()) {
      if (useFirst) {
        if (first.hasNext()) {
          next = Optional.of(first.getNext());
        } else {
          useFirst = false;
        }
      } else {
        if (second.hasNext()) {
          next = Optional.of(second.getNext());
        } else {
          break;
        }
      }
    }
  }
}
