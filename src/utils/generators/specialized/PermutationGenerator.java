package src.utils.generators.specialized;

import java.util.List;

import src.utils.generators.Generator;
import src.utils.generators.Generators;

import java.util.Collections;

/** Generates all permutations of a list of comparable elements. */
public final class PermutationGenerator<T extends Comparable<T>> implements Generator<List<T>>{

  private final int size;

  private List<T> objects;
  boolean isFinished;

  public PermutationGenerator(List<T> objects) {
    this.objects = Generators.from(objects).list();
    Collections.sort(this.objects);
    this.size = this.objects.size();
    isFinished = size == 0;
  }
  // TODO: Possibly generate multiset permutations using the Takaoka algorithm.
  //       currently, this will retern repeats if the list is not distinct.
  @Override
  public List<T> getNext() {
    List<T> result = Generators.from(objects).list();
    int j = -1;
    for (int z = result.size()-2; z >= 0; z--) {
      int compareResult = result.get(z).compareTo(result.get(z+1));
      if (compareResult < 0) {
        j = z;
        break;
      }
    }
    final int rightmostSmaller = j;
    /*
    TODO: Do this with generators
    :'(
    int rightmostSmaller =
        Generators.naturalsUpTo(size - 1)
            .map(i -> i - 1)
            .map(i -> size - 2 - i).print("j")
            .until(i -> objects.get(i).compareTo(objects.get(i + 1)) < 0).print("compareResult")
            .lastValue(-1);
            :'(
    */
    if (rightmostSmaller == -1) {
      isFinished = true;
    } else {
      int ceilIndex =
          Generators.range(rightmostSmaller + 1, size - 1)
              .filter(i -> objects.get(i).compareTo(objects.get(rightmostSmaller)) > 0)
              .reduce(
                   rightmostSmaller + 1,
                   (a, b) -> objects.get(a).compareTo(objects.get(b)) < 0 ? a : b);
      swap(ceilIndex, rightmostSmaller);
      Generators.range(rightmostSmaller + 1, (size + rightmostSmaller) / 2)
          .forEach(i -> swap(i, size + rightmostSmaller - i));
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