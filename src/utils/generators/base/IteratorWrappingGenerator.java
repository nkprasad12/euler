package src.utils.generators.base;

import java.util.Iterator;

import src.utils.generators.Generator;

public final class IteratorWrappingGenerator<T> implements Generator<T> {

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