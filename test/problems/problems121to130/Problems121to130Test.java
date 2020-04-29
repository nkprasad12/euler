package problems.problems121to130;

import static assertions.Assertions.assertEqual;

import org.junit.Test;

public class Problems121to130Test {

  @Test
  public void problem121_hasExpectedResult() {
    assertEqual(Problem121.solution(), "2269");
  }

  @Test
  public void problem123_hasExpectedResult() {
    assertEqual(Problem123.solution(), "21035");
  }
}