package src.utils.generators.base.tuples;

public final class IndexAndValue<T> {

    private final int index;
    private final T value;

    public IndexAndValue(int index, T value) {
      this.index = index;
      this.value = value;
    }

    public int index() {
      return index;
    }

    public T value() {
      return value;
    }

    @Override
    public String toString() {
      return String.format("index: %d, value: %s", index, value);
    }

  }