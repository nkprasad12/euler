package utils.generators.base;

import java.util.Optional;
import java.util.function.Function;
import utils.generators.Generator;

/** Generator that flattens a Generator of Generators into a single Generator. */
public final class FlatMappingGenerator<I, O> implements Generator<O> {

  private final Generator<I> generator;
  private final Function<I, Generator<O>> mapper;

  private Optional<Generator<O>> currentGenerator = Optional.empty();
  private Optional<O> next;

  public FlatMappingGenerator(Generator<I> generator, Function<I, Generator<O>> mapper) {
    this.generator = generator;
    this.mapper = mapper;
    findNextO();
  }

  @Override
  public O getNext() {
    O result = next.get();
    findNextO();
    return result;
  }

  @Override
  public boolean hasNext() {
    return next.isPresent();
  }

  private void findNextO() {
    next = Optional.empty();
    while (!next.isPresent()) {
      if (!currentGenerator.isPresent() || !currentGenerator.get().hasNext()) {
        if (!generator.hasNext()) {
          return;
        }
        currentGenerator = Optional.of(mapper.apply(generator.getNext()));
      }
      if (!currentGenerator.get().hasNext()) {
        continue;
      }
      next = Optional.of(currentGenerator.get().getNext());
    }
  }
}
