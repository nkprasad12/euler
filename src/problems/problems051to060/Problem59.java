package problems.problems051to060;

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

  public static String solution() {
    List<Integer> bytes = fromTextFile("problem59.txt", ",").map(Integer::parseInt).list();
    List<Integer> key = new ArrayList<Integer>(Arrays.asList(97, 97, 97));

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
    int [] theChars = new int[5];
    theChars[0] = (int) ' ';
    theChars[1] = (int) 't';
    theChars[2] = (int) 'h';
    theChars[3] = (int) 'e';
    theChars[4] = (int) ' ';
    int patternPosition = 0;
    int sum = 0;

    for (int i = 0; i < bytes.size(); i++) {
      int currByte = key.get(i % 3) ^ bytes.get(i);
      sum += currByte;
      if (currByte == theChars[patternPosition]) {
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
    if (key.get(2) > 122) {
      key.set(2, 97);
      key.set(1, key.get(1) + 1);
    }
    if (key.get(1) > 122) {
      key.set(1, 97);
      key.set(0, key.get(0) + 1);
    }
    if (key.get(0) > 122) {
      return null;
    }
    return key;
  }
}
