package utils.generators.base;

import static utils.generators.base.tuples.Tuples.pair;

import utils.generators.Generator;
import utils.generators.base.tuples.Tuples.Pair;

/**
 * Generator that a pair of generators into a generator of pairs.
 *
 * <p>Generates elements only as long as both generators have next elements.
 */
public class CombiningGenerator<T, U> implements Generator<Pair<T, U>> {

  private final Generator<T> first;
  private final Generator<U> second;

  public CombiningGenerator(Generator<T> first, Generator<U> second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public Pair<T, U> getNext() {
    return pair(first.getNext(), second.getNext());
  }

  @Override
  public boolean hasNext() {
    return first.hasNext() && second.hasNext();
  }
}
