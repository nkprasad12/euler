package test.utils.numbers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import src.utils.numbers.BigNumber;

public class BigNumberTest {

    @Test
    public void fromLong_hasExpectedDigits() {
        BigNumber number = BigNumber.fromLong(104l);

        assertListMatches(number.digits(), 4, 0, 1);
    }

    @Test
    public void fromString_hasExpectedDigits() {
        BigNumber number = BigNumber.fromString("104");

        assertListMatches(number.digits(), 4, 0, 1);
    }

    @Test
    public void fromDigitList_hasExpectedDigits() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(4, 0, 1));
        List<Integer> digits = new BigNumber(inputList).digits();

        assertListsMatch(inputList, digits);
    }

    @Test
    public void bigNumber_fromDigitsList_usesDifferentObject() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(4, 0, 1));
        int originalSize = inputList.size();
        BigNumber bigNumber = new BigNumber(inputList);
        assertTrue(bigNumber.digits().size() == originalSize);

        inputList.add(7);
        assertTrue(bigNumber.digits().size() == originalSize);
    }

    @Test
    public void bigNumber_removesLeadingZeros() {
        BigNumber number = BigNumber.fromString("000104");

        assertListMatches(number.digits(), 4, 0, 1);
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

        assertListMatches(number.digits(), 3, 0, 2);
    }

    @Test
    public void multiplyBy_returnsExpectedResult() {
        BigNumber number = BigNumber.fromLong(104l).multiplyBy(BigNumber.fromString("19"));
        
        assertListMatches(number.digits(), 6, 7, 9, 1);
    }

    @Test
    public void factorialOf_hasExpectedDigits() {
        BigNumber number = BigNumber.factorialOf(6);

        assertListMatches(number.digits(), 0, 2, 7);
    }

    @Test
    public void toPower_zero_hasExpectedDigits() {
        BigNumber number = BigNumber.fromLong(17).toPower(0);

        assertListMatches(number.digits(), 1);
    }

    @Test
    public void toPower_nonZero_hasExpectedDigits() {
        BigNumber number = BigNumber.fromLong(17).toPower(5);

        assertListMatches(number.digits(), 7, 5, 8, 9, 1, 4, 1);
    }

    @Test
    public void equals_forEqualLists_returnsTrue() {
        BigNumber a = BigNumber.fromLong(1272394l);
        BigNumber b = BigNumber.fromString("1272394");

        assertTrue(a.equals(b));
    }

    @Test
    public void equals_forUnequalLists_returnsFalse() {
        BigNumber a = BigNumber.fromLong(1272394l);
        BigNumber b = BigNumber.fromLong(2394l);

        assertFalse(a.equals(b));
    }

    private static void assertListMatches(List<Integer> list, Integer ... expected) {
        assertListsMatch(list, Arrays.asList(expected));
    }

    private static void assertListsMatch(List<Integer> actual, List<Integer> expected) {
        int n = expected.size();
        assertIntsEqual(actual.size(), n);

        for (int i = 0; i < n; i++) {
            assertIntsEqual(actual.get(i), expected.get(i), "Index " + i);
        }
    }

    private static void assertIntsEqual(int actual, int expected) {
        assertIntsEqual(actual, expected, "");
    }

    private static void assertIntsEqual(int actual, int expected, String tag) {
        assertTrue(
            String.format(tag + " actual: %d does not match expected: %d", actual, expected),
            actual == expected);
    }
}