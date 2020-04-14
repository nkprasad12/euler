package test.utils.numbers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static test.Assertions.assertEqual;
import static src.utils.numbers.NumericUtils.inverseTriangle;
import static src.utils.numbers.NumericUtils.isPerfectSquare;

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
    public void inverseTriangle_triangularNumbers_returnsNull() {
        assertNull(inverseTriangle(5));
        assertNull(inverseTriangle(5051));
        assertNull(inverseTriangle(22790));
    }

    @Test 
    public void powerModN_returnsExpected() {
        assertEqual((int) NumericUtils.powerModN(17, 35, 14), 5);
    }
}