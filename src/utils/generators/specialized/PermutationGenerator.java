package src.utils.generators.specialized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import src.utils.generators.Generator;

/** Generates all permutations of a list of comparable elements. */
public final class PermutationGenerator<T extends Comparable<T>> implements Generator<List<T>> {

  private final int size;

  private List<T> objects;
  boolean isFinished;

  public PermutationGenerator(List<T> objects) {
    this.objects = new ArrayList<>(objects);
    Collections.sort(this.objects);
    this.size = this.objects.size();
    isFinished = size == 0;
  }
  // TODO: Possibly generate multiset permutations using the Takaoka algorithm.
  //       currently, this will return repeats if the list is not distinct.
  @Override
  public List<T> getNext() {
    List<T> result = new ArrayList<>(objects);
    int j = -1;
    for (int z = size - 2; z >= 0; z--) {
      int compareResult = result.get(z).compareTo(result.get(z + 1));
      if (compareResult < 0) {
        j = z;
        break;
      }
    }
    final int rightmostSmaller = j;
    if (rightmostSmaller == -1) {
      isFinished = true;
    } else {
      int ceilIndex = rightmostSmaller + 1;
      for (int i = rightmostSmaller + 2; i <= size - 1; i++) {
        if (objects.get(i).compareTo(objects.get(rightmostSmaller)) > 0) {
          ceilIndex = objects.get(ceilIndex).compareTo(objects.get(i)) < 0 ? ceilIndex : i;
        }
      }
      swap(ceilIndex, rightmostSmaller);
      int limit = (size + rightmostSmaller) / 2;
      for (int i = rightmostSmaller + 1; i <= limit; i++) {
        swap(i, size + rightmostSmaller - i);
      }
    }
    return result;
  }

  @Override
  public boolean hasNext() {
    return !isFinished;
  }

  private void swap(int i, int j) {
    T tmp = objects.get(i);
    objects.set(i, objects.get(j));
    objects.set(j, tmp);
  }
}
