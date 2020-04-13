package utils.generators;

/**
 * Generator representing sequences that may be too large or unnecesary to
 * store in memory, but can be computed sequentially.
 * 
 * For example, the list of divisors of a large number may be extremely
 * large, but given it's prime factorization it's easy to compute each
 * in sequence.
 */
public interface Generator<T> {

  /* Returns the next element. */
  public T getNext();

  /* Whether there are any elements left. */
  public boolean hasNext();
}