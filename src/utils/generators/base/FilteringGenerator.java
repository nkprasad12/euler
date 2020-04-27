package utils.generators.base;

import java.util.function.Predicate;
import utils.generators.Generator;

/** Generator that removes values that do not match the filter. */
public final class FilteringGenerator<T> implements Generator<T> {

  private final Generator<T> generator;
  private final Predicate<T> filter;

  private T storedNext;
  private boolean hasNext;

  public FilteringGenerator(Generator<T> generator, Predicate<T> filter) {
    this.generator = generator;
    this.filter = filter;
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
    while (generator.hasNext()) {
      T candidate = generator.getNext();
      if (filter.test(candidate)) {
        next = candidate;
        break;
      }
    }
    storedNext = next;
    hasNext = storedNext != null;
  }
}
