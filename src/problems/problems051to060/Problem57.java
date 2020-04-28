package problems.problems051to060;

import static utils.numbers.BigNumber.fromLong;

import java.lang.invoke.MethodHandles;

import utils.numbers.BigNumber;

public class Problem57 {

  // F_0 = 1 + 1 / (1 + 1)
  // F_n+1 = 1 + 1 / (1 + F_n)
  //       = 1 + 1 / (1 + p / q)
  //       = 1 + q / (p + q)
  //       = (p + 2q) / (p + q)

  // p / q -> (p + 2q) / (p + q)

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    fromLong(5).addTo(fromLong(7));
    BigNumber pn = fromLong(3);
    BigNumber qn = fromLong(2);
    int numLongerNumerator = 0;
    for (int n = 1;  n<1000; n++){
        // Check if pn is longer
        BigNumber qnm1 = qn;
        qn = pn.addTo(qn);
        pn = qnm1.addTo(qn);

        int len_qn = qn.digits().size();
        int len_pn = pn.digits().size();

        if (len_pn > len_qn){
            numLongerNumerator++;
        }
    }

    return String.valueOf(numLongerNumerator);
  }
}