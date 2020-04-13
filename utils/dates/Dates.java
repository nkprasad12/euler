package utils.dates;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.RuntimeException;

public final class Dates {

  public static final Date REFERENCE_DATE = new Date(1, 1, 1900);
  public static final Weekday REFERENCE_WEEKDAY = Weekday.MONDAY;

  public static final List<Integer> MONTH_LIST = 
      Collections.unmodifiableList(
          new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)));

  public static Weekday weekdayOf(Date date) {
    int daysFromReference = daysBetween(REFERENCE_DATE, date);
    return Weekday.forValue((daysFromReference + REFERENCE_WEEKDAY.value()) % 7);
  }

  /**
   * Returns the number of days between one date and another. 
   * 
   * The number of days between a date and itself is 0, while the number
   * of days between a date and the following day is 1.
   */
  public static int daysBetween(Date first, Date second) {
    if (first.compareTo(second) == 1) {
      return -1 * daysBetween(second, first);
    }
    int daysBetweenYears = 0;
    for (int i = first.year(); i < second.year(); i++) {
      daysBetweenYears += daysInYear(i);
    }
    return daysBetweenYears + dayOfYear(second) - dayOfYear(first);
  }

  public static int dayOfYear(Date date) {
    int days = 0;
    for (Integer month : MONTH_LIST) {
      if (date.month() == month) {
        return days + date.day();
      } else {
        days += daysInMonth(month, date.year());
      }
    }
    throw new RuntimeException("Month of date was not in Month list!");
  }

  public static boolean isLeapYear(int year) {
    if (year % 400 == 0) {
      return true;
    } else if (year % 100 == 0) {
      return false;
    } else {
      return year % 4 == 0;
    }
  }

  public static int daysInYear(int year) {
    return isLeapYear(year) ? 366 : 365;
  }

  public static boolean isValidMonth(int month) {
    return month >= 1 && month <= 12;
  }

  public static void checkValidMonth(int month) {
    if (!isValidMonth(month)) {
      String error = String.format("month must be in range [0, 12], was %d", month);
      throw new RuntimeException(error);
    }
  }

  public static int daysInMonth(int month, int year) {
    checkValidMonth(month);
    // April, June, September, November
    if (month == 4 || month == 6 || month == 9 || month == 11) {
      return 30;
    } else if (month == 2) {
      return isLeapYear(year) ? 29 : 28;
    } else { 
      return 31;
    }
  }

  public static boolean isDayValid(int day, int month, int year) {
    return day >= 1 && day <= daysInMonth(month, year);
  }

  public static void checkValidDate(int day, int month, int year) {
    checkValidMonth(month);
    if (!isDayValid(day, month, year)) {
      String error = String.format("Invalid date (DD-MM-YYYY) %d-%d-%d", day, month, year);
      throw new RuntimeException(error);
    }
  }
}