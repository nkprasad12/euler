package test.utils.numbers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static src.utils.numbers.NumericUtils.inverseHexagon;
import static src.utils.numbers.NumericUtils.inversePentagon;
import static src.utils.numbers.NumericUtils.inverseTriangle;
import static src.utils.numbers.NumericUtils.isPerfectSquare;
import static test.Assertions.assertEqual;

import org.junit.Test;
import src.utils.numbers.NumericUtils;

public class NumericUtilsTest {

  @Test
  public void isPerfectSquare_perfectSquares_returnsTrue() {
    assertTrue(isPerfectSquare(49));
    assertTrue(isPerfectSquare(121));
    assertTrue(isPerfectSquare(10000));
    assertTrue(isPerfectSquare(6241));
  }

  @Test
  public void isPerfectSquare_notPerfectSquares_returnsFalse() {
    assertFalse(isPerfectSquare(50));
    assertFalse(isPerfectSquare(122));
    assertFalse(isPerfectSquare(10001));
    assertFalse(isPerfectSquare(6242));

    assertFalse(isPerfectSquare(48));
    assertFalse(isPerfectSquare(120));
    assertFalse(isPerfectSquare(9999));
    assertFalse(isPerfectSquare(6240));
  }

  @Test
  public void inverseTriangle_triangularNumbers_returnsExpected() {
    assertEqual(inverseTriangle(6), 3);
    assertEqual(inverseTriangle(5050), 100);
    assertEqual(inverseTriangle(22791), 213);
  }

  @Test
  public void inverseTriangle_notTriangularNumbers_returnsNull() {
    assertNull(inverseTriangle(5));
    assertNull(inverseTriangle(5051));
    assertNull(inverseTriangle(22790));
  }

  @Test
  public void inversePentagon_pentagonalNumbers_returnsExpected() {
    assertEqual(inversePentagon(5), 2);
    assertEqual(inversePentagon(35), 5);
    assertEqual(inversePentagon(40755), 165);
  }

  @Test
  public void inversePentagon_notPentagonalNumbers_returnsNull() {
    assertNull(inversePentagon(6));
    assertNull(inversePentagon(34));
    assertNull(inversePentagon(40756));
  }

  @Test
  public void inverseHexagon_hexagonalNumbers_returnExpected() {
    assertEqual(inverseHexagon(6), 2);
    assertEqual(inverseHexagon(45), 5);
    assertEqual(inverseHexagon(19900), 100);
  }

  @Test
  public void inverseHexagonal_notHexagonalNumbers_returnNull() {
    assertNull(inverseHexagon(5));
    assertNull(inverseHexagon(16));
    assertNull(inverseHexagon(40754));
  }

  @Test
  public void powerModN_returnsExpected() {
    assertEqual((int) NumericUtils.powerModN(17, 35, 14), 5);
  }
}
