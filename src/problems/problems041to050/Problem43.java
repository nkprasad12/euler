package src.problems.problems041to050;

import static java.lang.Integer.parseInt;
import static src.utils.generators.Generators.range;

import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;
import src.utils.generators.Generators;
import src.utils.numbers.BigNumber;

public class Problem43 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();

    Generators.permutationsOf(range(0, 9).list())
        .filter(list -> isSubstringDivisible(list))
        .map(list -> new BigNumber(reverse(list)))
        .reducing(BigNumber.fromLong(1), (sum, next) -> sum.addTo(next))
        .lastValue()
        .print();

    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static <T> List<T> reverse(List<T> list) {
    Collections.reverse(list);
    return list;
  }

  public static boolean isSubstringDivisible(List<Integer> list) {
    // No leading zeros
    if (list.get(0) == 0) {
      return false;
    }
    if (list.get(3) % 2 != 0) {
      return false;
    }
    if (getSubNumber(list, 2, 4) % 3 != 0) {
      return false;
    }
    if (list.get(5) % 5 != 0) {
      return false;
    }
    if (getSubNumber(list, 4, 6) % 7 != 0) {
      return false;
    }
    if (getSubNumber(list, 5, 7) % 11 != 0) {
      return false;
    }
    if (getSubNumber(list, 6, 8) % 13 != 0) {
      return false;
    }
    if (getSubNumber(list, 7, 9) % 17 != 0) {
      return false;
    }
    return true;
  }

  public static Integer getSubNumber(List<Integer> list, int startIdx, int endIdx) {
    List<Integer> numbers = list.subList(startIdx, endIdx + 1);

    String strNum = "";
    for (Integer num : numbers) {
      strNum += num.toString();
    }

    return parseInt(strNum);
  }
}
