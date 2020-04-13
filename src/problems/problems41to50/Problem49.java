package src.problems.problems41to50;

import java.lang.invoke.MethodHandles;
import java.util.List;

import src.utils.generators.Generator;
import src.utils.generators.Generators;

public class Problem49 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    Generator<List<Integer>> gen = Generators.permutationsOf(Generators.range(1, 3).list()).generator();
    Generators.range(0, 10)
       .filter(i -> gen.hasNext())
       .forEach(i -> System.out.println(gen.getNext()));
  }
}