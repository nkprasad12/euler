package src.utils.generators.base;

import java.util.Optional;
import java.util.function.Predicate;

import src.utils.generators.Generator;

/** Generator that gives elements of an input generator until a termination condition is met. */
public final class TerminatingGenerator<T> implements Generator<T> {

    private final Generator<T> generator;
    private final Predicate<T> terminateOn;

    private Optional<T> next = Optional.empty();

    public TerminatingGenerator(Generator<T> generator, Predicate<T> terminateOn) {
      this.generator = generator;
      this.terminateOn = terminateOn;
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
      if (generator.hasNext()) {
        T candidate = generator.getNext();
        next = terminateOn.test(candidate) ? Optional.empty() : Optional.of(candidate);
      } else {
        next = Optional.empty();
      }
    }
  }