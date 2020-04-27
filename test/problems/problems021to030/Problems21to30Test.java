package problems.problems021to030;

import static assertions.Assertions.assertEqual;

import org.junit.Test;

public class Problems21to30Test {

  @Test
  public void problem21_hasExpectedResult() {
    assertEqual(Problem21.solution(), "31626");
  }

  @Test
  public void problem23_hasExpectedResult() {
    assertEqual(Problem23.solution(), "4179871");
  }

  @Test
  public void problem24_hasExpectedResult() {
    assertEqual(Problem24.solution(), "2783915460");
  }

  @Test
  public void problem25_hasExpectedResult() {
    assertEqual(Problem25.solution(), "4782");
  }

  @Test
  public void problem26_hasExpectedResult() {
    assertEqual(Problem26.solution(), "983");
  }

  @Test
  public void problem29_hasExpectedResult() {
    assertEqual(Problem29.solution(), "9183");
  }

  @Test
  public void problem30_hasExpectedResult() {
    assertEqual(Problem30.solution(), "443839");
  }
}
