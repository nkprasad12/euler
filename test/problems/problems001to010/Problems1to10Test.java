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

  @Test
  public void problem5_producesExpected() {
    assertEqual(Problem5.solution(), "232792560"); 
  }

  @Test
  public void problem6_producesExpected() {
    assertEqual(Problem6.solution(), "25164150"); 
  }

  @Test
  public void problem7_producesExpected() {
    assertEqual(Problem7.solution(), "104743"); 
  }

  @Test
  public void problem10_producesExpected() {
    assertEqual(Problem10.solution(), "142913828922");
  }
}
