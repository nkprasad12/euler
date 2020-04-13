package problems.problems21to30;

import java.lang.invoke.MethodHandles;

import utils.generators.Generators;
import utils.numbers.BigNumber;

import java.util.HashSet;

public class Problem29 {
  
  public static void exec() {
    System.out.println(MethodHandles.lookup().lookupClass());

    int size =
        Generators.range(2l, 100l)
        .map(a -> BigNumber.fromLong(a))
        .map(
            a -> 
                Generators.range(2, 100)
                    .map(b -> a.toPower(b))
                    .collectInto(new HashSet<BigNumber>()))
        .reducing(
              new HashSet<BigNumber>(),
              (soFar, newElements) -> {
                soFar.addAll(newElements);
                return soFar;
              })
        .lastValue()
        .size();
    System.out.println(size);
  }
}