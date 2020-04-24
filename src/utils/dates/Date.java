package src.utils.dates;

public final class Date {

  private final int year;
  private final int month;
  private final int day;

  public Date(int day, int month, int year) {
    Dates.checkValidDate(day, month, year);
    this.year = year;
    this.month = month;
    this.day = day;
  }

  public int year() {
    return year;
  }

  public int month() {
    return month;
  }

  public int day() {
    return day;
  }

  public int compareTo(Date other) {
    if (year > other.year()) {
      return 1;
    } else if (year < other.year()) {
      return -1;
    }
    if (month > other.month()) {
      return 1;
    } else if (month < other.month()) {
      return -1;
    }
    if (day > other.day()) {
      return 1;
    } else if (day < other.day()) {
      return -1;
    }
    return 0;
  }
}
