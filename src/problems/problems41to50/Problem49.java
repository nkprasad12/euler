package src.problems.problems41to50;

import static src.utils.generators.Generators.from;
import static src.utils.generators.Generators.fromCartesianProductOf;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import src.utils.generators.Generators;
import src.utils.generators.consumers.GeneratorConsumer;
import src.utils.primes.Primes;
import src.utils.generators.base.tuples.Tuples.Pair;

public class Problem49 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    System.out.println(answer());
  }

  public static String answer() {
    Primes primesHelper = new Primes();
    Set<Long> primes = primesHelper.primesUpTo(10000l);
    return from(primes)
        .filter(p -> p > 999 && p != 1487 && p != 4817 && p != 8147) // Get all eligible primes.
        .map(p -> Long.toString(p)) // Convert prime to string
        .map(s -> permutationsOf(s) // Get all permutations of prime
                      .map(l -> from(l).reduce("", (soFar, next) -> soFar + next))
                      .filter(str -> str.length() > 3) // Some may have leading zeros!
                      .map(Long::parseLong) 
                      .list())
        .map(l -> from(l).filter(primesHelper::isPrime).list()) // Leave only the prime permutations
        .filter(l -> l.size() >= 3) // We need three terms
        .map(l -> fromCartesianProductOf(from(l), () -> from(l)) // For all of the prime permutations of a prime
                      .filter((i, j) -> i > j) // Only take each distinct pair
                      .reduce(new HashMap<Long, TreeSet<Long>>(), Problem49::primePairReduction)
                      .values()) // Get a map of all that are the same distance away
        .map(v -> from(v)
                      // Keep only the sets of max size
                      .reduce(
                          new TreeSet<Long>(),
                          (max, next) -> next.size() > max.size() ? next : max))
        .filter(ts -> ts.size() % 2 == 1) // If two pairs share a term, the set will be odd.
        .map(ts -> from(ts).reduce("", (s, n) -> s + n))
        .lastValue()
        .get();
  }

  public static GeneratorConsumer<List<String>> permutationsOf(String str) {
    List<String> chars = new ArrayList<>();
    for (char c : str.toCharArray()) {
      chars.add(Character.toString(c));
    }
    return Generators.permutationsOf(chars);
  }

  public static HashMap<Long, TreeSet<Long>> primePairReduction(
      HashMap<Long, TreeSet<Long>> hashMap, Pair<Long, Long> pair) {
    Long d = pair.first() - pair.second();
    if (hashMap.containsKey(d)) {
      hashMap.get(d).add(pair.first());
      hashMap.get(d).add(pair.second());
    } else {
      TreeSet<Long> set = new TreeSet<Long>();
      set.add(pair.first());
      set.add(pair.second());
      hashMap.put(d, set);
    }
    return hashMap;
  }
}