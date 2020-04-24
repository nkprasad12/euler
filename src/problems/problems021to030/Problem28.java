package src.problems.problems021to030;

public class Problem28 {

  // Top right corners of squares, after 1 are:
  //    (1 + 2n)^2
  // To get from the top to the other numbers counter clockwise:
  //    subtract 2n, 4n, 6n respectively
  // Sum is 4(1 + 2n)^2 - 12n
  // This is equivalent to
  //    f(n) = 4(4n^2 + n + 1)
  // Sum of spiral M x M is
  //    S(k) 1 + Sum from 1 to k of 4(4n^2 + n + 1), where k = (M - 1) / 2
  // Well known:
  //    Sum from 1 to k of 1 is k
  //    Sum from 1 to k of k is k * (k + 1) / 2
  //    Sum from 1 to k^2 of k is k * (k + 1) * (2k + 1) / 6
  // This gives:
  //    S(k) = 1 + 4 * (4 * k * (k + 1) * (2k + 1) / 6 + k * (k + 1) / 2 + k)
  // Answer for this problem is S(500)
  public static void main(String[] args) {
    System.out.println(669171001l);
  }
}
