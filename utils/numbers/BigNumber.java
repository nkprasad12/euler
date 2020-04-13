package utils.numbers;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

import utils.generators.Generators;

import java.util.Collections;

public final class BigNumber {

  private final ArrayList<Integer> digits;

  public BigNumber(List<Integer> digits) {
    this.digits = new ArrayList<>();
    int lastNonZero = 0;
    for (int i = 0; i < digits.size(); i++) {
      if (digits.get(i) != 0) {
        lastNonZero = i;
      }
    }
    for (int i = 0; i <= lastNonZero; i++) {
      this.digits.add(digits.get(i));
    }
  }

  public List<Integer> digits() {
    return Collections.unmodifiableList(digits);
  }

  public static BigNumber fromLong(long m) {
    long n = m;
    ArrayList<Integer> digits = new ArrayList<>();
    while (n > 0) {
      digits.add((int) n % 10);
      n /= 10;
    }
    return new BigNumber(digits);
  }

  public static BigNumber fromString(String num) {
    ArrayList<Integer> digits = new ArrayList<>();
    Generators.range(0, num.length() - 1)
        .map(i -> num.length() - 1 - i)
        .map(i -> Character.getNumericValue(num.charAt(i)))
        .collectInto(digits);
    return new BigNumber(digits);
  }

  public static BigNumber factorialOf(long n) {
    BigNumber product = fromLong(1);
    for (long i = 1; i <= n; i++) {
      product = product.multiplyBy(i);
    }
    return product;
  }

  public BigNumber copy() {
    return new BigNumber(digits());
  }

  public long sumOfDigits() {
    long sum = 0;
    for (Integer digit : digits) {
      sum += digit;
    }
    return sum;
  }

  public BigNumber toPower(int exponent) {
    if (exponent == 0) {
      return BigNumber.fromLong(1);
    } else if (exponent % 2 == 0) {
      BigNumber halfPower = toPower(exponent / 2);
      return halfPower.multiplyBy(halfPower);
    } else {
      return this.toPower(exponent - 1).multiplyBy(this);
    }
  }

  public BigNumber addTo(BigNumber other) {
    // Initialize
    ArrayList<Integer> result = new ArrayList<Integer>();
    List<Integer> thisDigits = this.digits();
    List<Integer> otherDigits = other.digits();
    int thisSize = thisDigits.size();
    int otherSize = otherDigits.size();
    for (int i = 0; i < Math.max(thisSize, otherSize) + 1; i++) {
      result.add(0);
    }    
    // Perform addition
    for (int i = 0; i < Math.max(thisSize, otherSize); i++) {
      int sum = 0;
      if (i < thisSize) {
        sum += thisDigits.get(i);
      }
      if (i < otherSize) {
        sum += otherDigits.get(i);
      }
      result.set(i, sum);
    }
    // Perform carry
    int carry = 0;
    for (int i = 0; i <= Math.max(thisSize, otherSize); i++) {
      int value = carry + result.get(i);
      carry = 0;
      while (value > 9) {
        value -= 10;
        carry += 1;
      }
      result.set(i, value);
    }
    return new BigNumber(result);
  }

  BigNumber multiplyBy(BigNumber other) {
    // Initialize
    ArrayList<Integer> result = new ArrayList<Integer>();
    List<Integer> thisDigits = this.digits();
    List<Integer> otherDigits = other.digits();
    int thisSize = thisDigits.size();
    int otherSize = otherDigits.size();
    for (int i = 0; i < thisSize + otherSize + 1; i++) {
      result.add(0);
    }
    // Perform multiplication
    for (int i = 0; i < thisSize; i++) {
      for (int j = 0; j < otherSize; j++) {
        int place = i + j;
        int value = thisDigits.get(i) * otherDigits.get(j);
        result.set(place, result.get(place) + value);
      }
    }
    // Perform carry
    for (int i = 0; i < result.size() - 1; i++) {
      int value = result.get(i);
      int carried = 0;
      while (value > 9) {
        carried++;
        value = value - 10;
      }
      result.set(i + 1, result.get(i + 1) + carried);
      result.set(i, value);
    }
    return new BigNumber(result);
  }

  BigNumber multiplyBy(long other) {
    return this.multiplyBy(BigNumber.fromLong(other));
  }

  @Override
  public String toString() {
    String str = "";
    for (Integer digit : digits) {
      str = digit + "" + str;
    }
    return str;
  }

  @Override 
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    BigNumber other = (BigNumber) o;
    if (this.digits().size() != other.digits().size()) {
      return false;
    }
    for (int i = 0; i < this.digits().size(); i++) {
      if (this.digits().get(i) != other.digits().get(i)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

}