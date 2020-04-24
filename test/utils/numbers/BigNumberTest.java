package test.utils.numbers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import src.utils.numbers.BigNumber;
import test.Assertions;

public class BigNumberTest {

  @Test
  public void fromLong_hasExpectedDigits() {
    BigNumber number = BigNumber.fromLong(104l);

    Assertions.assertListMatches(number.digits(), 4, 0, 1);
  }

  @Test
  public void fromLong_baseTwo_hasExpectedDigitsAndBase() {
    BigNumber number = BigNumber.fromLong(104l, 2);

    Assertions.assertListMatches(number.digits(), 0, 0, 0, 1, 0, 1, 1);
    Assertions.assertEqual(number.base(), 2);
  }

  @Test
  public void fromString_hasExpectedDigits() {
    BigNumber number = BigNumber.fromString("104");

    Assertions.assertListMatches(number.digits(), 4, 0, 1);
  }

  @Test
  public void fromDigitList_hasExpectedDigits() {
    List<Integer> inputList = new ArrayList<>(Arrays.asList(4, 0, 1));
    List<Integer> digits = new BigNumber(inputList).digits();

    Assertions.assertListsMatch(inputList, digits);
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

    Assertions.assertListMatches(number.digits(), 4, 0, 1);
  }

  @Test
  public void digitsList_isUnmodifiable() {
    BigNumber number = BigNumber.fromLong(104l);
    List<Integer> digits = number.digits();

    assertThrows(UnsupportedOperationException.class, () -> digits.set(0, 17));
    assertThrows(UnsupportedOperationException.class, () -> digits.add(5));
  }

  @Test
  public void addTo_returnsExpectedResult() {
    BigNumber number = BigNumber.fromLong(104l).addTo(BigNumber.fromString("99"));

    Assertions.assertListMatches(number.digits(), 3, 0, 2);
  }

  @Test
  public void addTo_baseTwo_returnsExpectedResult() {
    BigNumber number = BigNumber.fromLong(104l, 2).addTo(BigNumber.fromLong(99l, 2));

    Assertions.assertListMatches(number.digits(), 1, 1, 0, 1, 0, 0, 1, 1);
  }

  @Test
  public void multiplyBy_returnsExpectedResult() {
    BigNumber number = BigNumber.fromLong(104l).multiplyBy(BigNumber.fromString("19"));

    Assertions.assertListMatches(number.digits(), 6, 7, 9, 1);
  }

  @Test
  public void addTo_baseThree_returnsExpectedResult() {
    BigNumber number = BigNumber.fromLong(64l, 3).multiplyBy(BigNumber.fromLong(17l, 3));

    Assertions.assertListMatches(number.digits(), 2, 2, 0, 1, 1, 1, 1);
    Assertions.assertEqual(number.base(), 3);
  }

  @Test
  public void factorialOf_hasExpectedDigits() {
    BigNumber number = BigNumber.factorialOf(6);

    Assertions.assertListMatches(number.digits(), 0, 2, 7);
  }

  @Test
  public void toPower_zero_hasExpectedDigits() {
    BigNumber number = BigNumber.fromLong(17).toPower(0);

    Assertions.assertListMatches(number.digits(), 1);
  }

  @Test
  public void toPower_nonZero_hasExpectedDigits() {
    BigNumber number = BigNumber.fromLong(17).toPower(5);

    Assertions.assertListMatches(number.digits(), 7, 5, 8, 9, 1, 4, 1);
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
}
