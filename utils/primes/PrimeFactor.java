package utils.primes;

import java.lang.Comparable;

public final class PrimeFactor implements Comparable<PrimeFactor> {

  private final Long prime;
  private final Integer exponent;

  public PrimeFactor(Long prime, Integer exponent) {
    this.prime = prime;
    this.exponent = exponent;
  }

  public Long prime() {
    return prime;
  }

  public Integer exponent() {
    return exponent;
  }

  @Override 
  public String toString() {
    return prime + "^" + exponent;
  }

  @Override
  public int compareTo(PrimeFactor other) {
    if (this.prime() < other.prime()) {
      return -1;
    } else if (this.prime() == other.prime()) {
      if (this.exponent() < other.exponent()) {
        return -1;
      } else if (this.exponent() == other.exponent) {
        return 0;
      } else {
        return 1;
      }
    } else {
      return 1;
    }
  }

}