package problems.problems031to040;

import java.lang.invoke.MethodHandles;
import utils.generators.Generators;
import utils.generators.base.tuples.Tuples;

public class Problem33 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    int numProduct = 1;
    int denomProduct = 1;
    for (int num = 10; num <= 99; num++) {
      for (int denom = num + 1; denom <= 99; denom++) {
        Integer result = isCancelling(num, denom);
        if (result != null) {
          System.out.println("element found" + num + "/" + denom);
          numProduct *= num;
          denomProduct *= denom;
        }
      }
    }

    System.out.println(numProduct);
    System.out.println("---------");
    System.out.println(denomProduct);

    Generators.range(10, 98)
        .mapAndPair(i -> Generators.range(i + 1, 99))
        .filter((a, b) -> isCancelling(a, b) != null)
        .reducing(
            Tuples.pair(1, 1), (aProd, aNext) -> aProd * aNext, (bProd, bNext) -> bProd * bNext)
        .lastValue()
        .print();
  }

  public static Integer isCancelling(int a, int b) {

    String aStr = Integer.toString(a);
    String bStr = Integer.toString(b);
    if (aStr.contains("0") || bStr.contains("0")) {
      return null;
    }
    if (aStr.charAt(0) == bStr.charAt(1)) {
      int num = secondDigit(a);
      int denom = firstDigit(b);

      return (b * num) == (denom * a) ? denom : null;
    }
    if (aStr.charAt(1) == bStr.charAt(0)) {
      int num = firstDigit(a);
      int denom = secondDigit(b);

      return (b * num) == (denom * a) ? denom : null;
    }
    return null;
  }

  public static int getDigit(int num, int loc) {
    return Character.getNumericValue(Integer.toString(num).charAt(loc));
  }

  public static int firstDigit(int num) {
    return Character.getNumericValue(Integer.toString(num).charAt(0));
  }

  public static int secondDigit(int num) {
    return Character.getNumericValue(Integer.toString(num).charAt(1));
  }
}
