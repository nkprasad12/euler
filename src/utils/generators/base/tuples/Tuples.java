package src.utils.generators.base.tuples;

import java.util.Objects;

public final class Tuples {

  public static <T, U> Pair<T, U> pair(T t, U u) {
    return new Pair<T, U>(t, u);
  }

  public static <T, U, V, A, B> Tuple<T, U, V, A, B> triplet(T t, U u, V v) {
    return new Tuple<T, U, V, A, B>(t, u, v, null, null, 3);
  }

  public static final class Pair<T, U> extends Tuple<T, U, T, T, T> {
    private Pair(T first, U second) {
      super(first, second, null, null, null, 2);
    }
  }

  private static class Tuple<T, U, V, W, X> {

    private final T first;
    private final U second;
    private final V third;
    private final W fourth;
    private final X fifth;
    private final int num;

    private Tuple(T first, U second, V third, W fourth, X fifth, int num) {
      this.first = first;
      this.second = second;
      this.third = third;
      this.fourth = fourth;
      this.fifth = fifth;
      this.num = num;
    }

    public T first() {
      return first;
    }

    public U second() {
      return second;
    }

    public V third() {
      return third;
    }

    public W fourth() {
      return fourth;
    }

    public X fifth() {
      return fifth;
    }

    @Override
    public String toString() {
      String str = "(";
      String glue = ", ";
      if (num > 0) {
        str += first;
      }
      if (num > 1) {
        str += glue + second;
      }
      if (num > 2) {
        str += glue + third;
      }
      if (num > 3) {
        str += glue + fourth;
      }
      if (num > 4) {
        str += glue + fifth;
      }
      return str + ")";
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || this.getClass() != o.getClass()) {
        return false;
      }
      if (!(o instanceof Tuple<?, ?, ?, ?, ?>)) {
        return false;
      }
      Tuple<?, ?, ?, ?, ?> other = (Tuple<?, ?, ?, ?, ?>) o;
      if (num > 0 && !first.equals(other.first())) {
        return false;
      }
      if (num > 1 && !second.equals(other.second())) {
        return false;
      }
      if (num > 2 && !third.equals(other.third())) {
        return false;
      }
      if (num > 3 && !fourth.equals(other.fourth())) {
        return false;
      }
      if (num > 4 && !fifth.equals(other.fifth())) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      if (num == 1) {
        return Objects.hash(first);
      } else if (num == 2) {
        return Objects.hash(first, second);
      } else if (num == 3) {
        return Objects.hash(first, second, third);
      } else if (num == 4) {
        return Objects.hash(first, second, third, fourth);
      } else {
        return Objects.hash(first, second, third, fourth, fifth);
      }
    }
  }
}
