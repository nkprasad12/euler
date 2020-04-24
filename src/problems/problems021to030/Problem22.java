package src.problems.problems021to030;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import src.utils.generators.Generators;

public class Problem22 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    ArrayList<String> names =
        Generators.fromTextFile("problem22.txt", ",")
            .map(nameStr -> nameStr.substring(1, nameStr.length() - 1))
            .collectInto(new ArrayList<>());
    Collections.sort(names);
    Generators.range(0, names.size() - 1)
        .map(i -> ((long) i + 1l) * GetAlphabeticalValue(names.get(i)))
        .reducing(0l, (a, b) -> a + b)
        .lastValue()
        .print();
  }

  static long GetAlphabeticalValue(String name) {
    char[] nameChars = name.toLowerCase().toCharArray();

    long initialAscii = ((long) 'a') - 1;
    long sumValue = 0;
    for (char letter : nameChars) {
      sumValue += ((long) letter) - initialAscii;
    }

    return sumValue;
  }
}
