package problems.problems001to010;

import static assertions.Assertions.assertEqual;

import org.junit.Test;

public class Problems1to10Test {

  @Test
  public void problem1_producesExpected() {
    assertEqual(Problem1.solution(), "233168");
  }

  @Test
  public void problem2_producesExpected() {
    assertEqual(Problem2.solution(), "4613732");
  }

  @Test
  public void problem3_producesExpected() {
    assertEqual(Problem3.solution(), "6857");
  }

  @Test
  public void problem4_producesExpected() {
    assertEqual(Problem4.solution(), "906609");
  }
}