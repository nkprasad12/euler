package problems.problems011to020;

import static assertions.Assertions.assertEqual;

import org.junit.Test;

public class Problems11to20Test {

  @Test
  public void problem12_hasExpectedResult() {
    assertEqual(Problem12.solution(), "76576500");
  }

  @Test
  public void problem13_hasExpectedResult() {
    assertEqual(Problem13.solution(), "5537376230");
  }

  @Test
  public void problem16_hasExpectedResult() {
    assertEqual(Problem16.solution(), "1366");
  }

  @Test
  public void problem19_hasExpectedResult() {
    assertEqual(Problem19.solution(), "171");
  }

  @Test
  public void problem20_hasExpectedResult() {
    assertEqual(Problem20.solution(), "648");
  }
}
