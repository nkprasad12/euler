package src.utils.generators.base;

import java.util.function.Function;

import src.utils.generators.Generator;

/** Generator that transforms each element of an input generator. */
public final class MappingGenerator<I, O> implements Generator<O> {

    private final Generator<I> generator;
    private final Function<I, O> mapper;

    public MappingGenerator(Generator<I> generator, Function<I, O> mapper) {
      this.generator = generator;
      this.mapper = mapper;
    }

    @Override
    public O getNext() {
      return mapper.apply(generator.getNext());
    }

    @Override
    public boolean hasNext() {
      return generator.hasNext();
    }
  }