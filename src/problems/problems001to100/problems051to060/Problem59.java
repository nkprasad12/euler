package problems.problems001to100.problems051to060;

import static utils.generators.Generators.fromTextFile;
import static utils.generators.base.tuples.Tuples.pair;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.generators.base.tuples.Tuples.Pair;

public class Problem59 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  private static final int LETTER_MIN = (int) 'a';
  private static final int LETTER_MAX = (int) 'z';
  private static final int[] THE_CHARS = {(int) ' ', (int) 't', (int) 'h', (int) 'e', (int) ' '};

  public static String solution() {
    List<Integer> bytes = fromTextFile("problem59.txt", ",").map(Integer::parseInt).list();
    List<Integer> key = new ArrayList<Integer>(Arrays.asList(LETTER_MIN, LETTER_MIN, LETTER_MIN));

    int maxThes = 0;
    int sum = -1;
    while (key != null) {
      Pair<Integer, Integer> result = decrypt(bytes, key);
      int numThes = result.second();
      if (numThes > maxThes) {
        maxThes = numThes;
        sum = result.first();
      }
      key = incrementKey(key);
    }
    return String.valueOf(sum);
  }

  private static Pair<Integer, Integer> decrypt(List<Integer> bytes, List<Integer> key) {
    int numThes = 0;
    int patternPosition = 0;
    int sum = 0;

    for (int i = 0; i < bytes.size(); i++) {
      int currByte = key.get(i % 3) ^ bytes.get(i);
      sum += currByte;
      if (currByte == THE_CHARS[patternPosition]) {
        if (patternPosition == 4) {
          patternPosition = 0;
          numThes++;
        } else {
          patternPosition++;
        }
      } else {
        patternPosition = 0;
      }
    }
    return pair(sum, numThes);
  }

  private static List<Integer> incrementKey(List<Integer> key) {
    // We are given that the key is 3 lower case characters.
    key.set(2, key.get(2) + 1);
    if (key.get(2) > LETTER_MAX) {
      key.set(2, LETTER_MIN);
      key.set(1, key.get(1) + 1);
    }
    if (key.get(1) > LETTER_MAX) {
      key.set(1, LETTER_MIN);
      key.set(0, key.get(0) + 1);
    }
    if (key.get(0) > LETTER_MAX) {
      return null;
    }
    return key;
  }
}
