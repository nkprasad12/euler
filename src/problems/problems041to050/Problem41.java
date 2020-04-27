package problems.problems041to050;

import static utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import utils.generators.Generators;
import utils.generators.consumers.GeneratorConsumer;
import utils.primes.Primes;

public class Problem41 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    Primes primes = new Primes();
    primes.primesUpTo(100000);
    range(1, 9)
        .reducing("", (str, k) -> str + k)
        .flatMap((str) -> permutationsOf(str))
        .map(str -> Long.parseLong(str))
        .filter(p -> primes.isPrime(p))
        .max(k -> k)
        .print();

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String combine(List<String> list) {
    String result = "";
    for (String s : list) {
      result += s;
    }
    return result;
  }

  public static GeneratorConsumer<String> permutationsOf(String str) {
    List<String> chars = new ArrayList<>();
    for (char c : str.toCharArray()) {
      chars.add(Character.toString(c));
    }
    return Generators.permutationsOf(chars).map(list -> combine(list));
  }
}
