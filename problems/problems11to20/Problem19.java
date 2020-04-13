package problems.problems11to20;

import java.lang.invoke.MethodHandles;

import utils.dates.Date;
import utils.dates.Dates;
import utils.dates.Weekday;

public class Problem19 {
  
  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());

    int sundays = 0;
    for (int year = 1901; year <= 2000; year++) {
      for (Integer month : Dates.MONTH_LIST) {
        Weekday weekday = Dates.weekdayOf(new Date(1, month, year));
        boolean isSunday = Weekday.SUNDAY.equals(weekday);
        sundays += isSunday ? 1 : 0;
      }
    }
    System.out.println(sundays);
  }
}