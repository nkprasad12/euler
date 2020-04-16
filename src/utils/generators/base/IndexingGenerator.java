package src.utils.generators.base;

import src.utils.generators.Generator;
import src.utils.generators.base.tuples.IndexAndValue;

public final class IndexingGenerator<T> implements Generator<IndexAndValue<T>> {

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