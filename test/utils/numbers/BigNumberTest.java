package test.utils.numbers;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import src.utils.numbers.BigNumber;

public class BigNumberTest {

    @Test
    public void fromLong_hasExpectedDigits() {
        BigNumber number = BigNumber.fromLong(104l);
        List<Integer> digits = number.digits();

        assertTrue(digits.size() == 3);
        assertTrue(digits.get(0) == 4);
        assertTrue(digits.get(1) == 0);
        assertTrue(digits.get(2) == 1);
    }

    @Test
    public void fromString_hasExpectedDigits() {
        BigNumber number = BigNumber.fromString("104");
        List<Integer> digits = number.digits();

        assertTrue(digits.size() == 3);
        assertTrue(digits.get(0) == 4);
        assertTrue(digits.get(1) == 0);
        assertTrue(digits.get(2) == 1);
    }

    @Test
    public void bigNumber_removesLeadingZeros() {
        BigNumber number = BigNumber.fromString("000104");
        List<Integer> digits = number.digits();

        assertTrue(digits.size() == 3);
        assertTrue(digits.get(0) == 4);
        assertTrue(digits.get(1) == 0);
        assertTrue(digits.get(2) == 1);
    }

    @Test
    public void digitsList_isUnmodifiable() {
        BigNumber number = BigNumber.fromLong(104l);
        List<Integer> digits = number.digits();

        assertThrows(
            UnsupportedOperationException.class,
            () -> digits.set(0, 17));
        assertThrows(
            UnsupportedOperationException.class,
            () -> digits.add(5));
    }

    @Test
    public void addTo_returnsExpectedResult() {
        BigNumber number = BigNumber.fromLong(104l).addTo(BigNumber.fromString("99"));
        List<Integer> digits = number.digits();

        assertTrue(digits.size() == 3);
        assertTrue(digits.get(0) == 3);
        assertTrue(digits.get(1) == 0);
        assertTrue(digits.get(2) == 2);
    }

    @Test
    public void multiplyBy_returnsExpectedResult() {
        BigNumber number = BigNumber.fromLong(104l).multiplyBy(BigNumber.fromString("19"));
        List<Integer> digits = number.digits();

        assertTrue(digits.size() == 4);
        assertTrue(digits.get(0) == 6);
        assertTrue(digits.get(1) == 7);
        assertTrue(digits.get(2) == 9);
        assertTrue(digits.get(3) == 1);
    }
}