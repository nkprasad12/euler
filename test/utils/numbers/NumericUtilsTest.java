package utils.numbers;

import static assertions.Assertions.assertEqual;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static utils.numbers.NumericUtils.gcd;
import static utils.numbers.NumericUtils.inverseHexagon;
import static utils.numbers.NumericUtils.inversePentagon;
import static utils.numbers.NumericUtils.inverseTriangle;
import static utils.numbers.NumericUtils.isPerfectSquare;

import org.junit.Test;

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
    assertEqual(NumericUtils.powerModN(17, 35, 14), 5l);
  }

  @Test
  public void powerModN_largeNumbers_throws() {
    assertThrows(RuntimeException.class, () -> NumericUtils.powerModN(12, 43545, 10000000000l));
  }

  @Test
  public void gcd_primes_isOne() {
    assertEqual(gcd(7, 13), 1l);
  }

  @Test
  public void gcd_coprimeComposites_isOne() {
    assertEqual(gcd(98, 15), 1l);
  }

  @Test
  public void gcd_sameNumber_isNumber() {
    assertEqual(gcd(456437, 456437), 456437l);
  }

  @Test
  public void gcd_isCommutative() {
    assertEqual(gcd(98, 15), gcd(15, 98));
    assertEqual(gcd(8, 1), gcd(1, 8));
    assertEqual(gcd(908, 215), gcd(215, 908));
    assertEqual(gcd(5118, 758), gcd(758, 5118));
    assertEqual(gcd(938, 125), gcd(125, 938));
  }

  @Test
  public void gcd_composites_isExpected() {
    assertEqual(gcd(72, 81), 9l);
  }

  @Test
  public void gcd_largeComposites_isExpected() {
    assertEqual(gcd(5549544, 322959), 1911l);
  }
}
