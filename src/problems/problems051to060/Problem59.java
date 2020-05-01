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
    List<Integer> key = new ArrayList<Integer>(Arrays.asList(0, 0, 0));

    int maxThes = Integer.MIN_VALUE;
    int sum = -1;
    while (key != null) {
      Pair<Integer, String> result = decrypt(bytes, key);
      int numThes = countThes(result.second());
      if (numThes > maxThes) {
        System.out.println("key: " + key + ", num thes: " + numThes);
        maxThes = numThes;
        sum = result.first();
      }
      key = incrementKey(key);
    }

    return String.valueOf(sum);
  }

  private static int countThes(String message) {
    // TODO: Optimize this
    String target = " the ";
    int lastIndex = 0;
    int count = 0;
    while (lastIndex != -1) {
      lastIndex = message.indexOf(target, lastIndex);
      if (lastIndex != -1) {
        count++;
        lastIndex += 5;
      }
    }
    return count;
  }

  private static Pair<Integer, String> decrypt(List<Integer> bytes, List<Integer> key) {
    int sum = 0;
    char[] message = new char[bytes.size()];
    for (int i = 0; i < bytes.size(); i++) {
      int currByte = key.get(i % 3) ^ bytes.get(i);
      sum += currByte;
      message[i] = (char) currByte;
    }
    return pair(sum, new String(message));
  }

  private static List<Integer> incrementKey(List<Integer> key) {
    Integer value1 = key.get(0);
    Integer value2 = key.get(1);
    Integer value3 = key.get(2);

    value3++;

    if (value3 > 127) {
      value3 = 0;
      value2++;
    }

    if (value2 > 127) {
      value2 = 0;
      value1++;
      System.out.println(key);
    }

    if (value1 > 127) {
      return null;
    }

    List<Integer> result = new ArrayList<Integer>(3);
    result.add(value1);
    result.add(value2);
    result.add(value3);

    return result;
  }
}
