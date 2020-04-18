package src.utils.dates;

import java.lang.RuntimeException;

public enum Weekday {
  MONDAY(0),
  TUESDAY(1),
  WEDNESDAY(2),
  THURSDAY(3),
  FRIDAY(4),
  SATURDAY(5),
  SUNDAY(6);

  private int value;

  private Weekday(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  public static Weekday forValue(int n) {
    if (n == 0) {
      return MONDAY;
    } 
    if (n == 1) {
      return TUESDAY;
    }
    if (n == 2) {
      return WEDNESDAY;
    }
    if (n == 3) {
      return THURSDAY;
    }
    if (n == 4) {
      return FRIDAY;
    }
    if (n == 5) {
      return SATURDAY;
    }
    if (n == 6) {
      return SUNDAY;
    }
    throw new RuntimeException("Invalid value " + n + " for Weekday");
  }
}  