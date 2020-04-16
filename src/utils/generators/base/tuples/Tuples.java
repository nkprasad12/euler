package src.utils.generators.base.tuples;

public final class Tuples {

  public static <T, U, A, B, C> Tuple<T, U, A, B, C> pair(T t, U u) {
    return new Tuple<T, U, A, B, C>(t, u, null, null, null, 2);
  }

  public static <T, U, V, A, B> Tuple<T, U, V, A, B> triplet(T t, U u, V v) {
    return new Tuple<T, U, V, A, B>(t, u, v, null, null, 3);
  }

  public static final class Tuple<T, U, V, W, X> {

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
  }
}