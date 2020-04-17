package src;

import java.lang.invoke.MethodHandles;

import src.utils.generators.Generators;

class Sandbox {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Generators.range(1, 3)
        .pairEachWith(() -> Generators.range(4, 5))
        .mapPair(i -> i * i, j -> j * j)
        .lastValue()
        .print();
  }
}