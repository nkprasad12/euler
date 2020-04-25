package test.utils.dates;

import static test.Assertions.assertEqual;

import org.junit.Test;
import src.utils.dates.Date;
import src.utils.dates.Dates;
import src.utils.dates.Weekday;

public class DatesTest {

  @Test
  public void weekdayOf_returnsExpectedResult() {
    // Black Tuesday
    assertEqual(Dates.weekdayOf(new Date(29, 10, 1929)), Weekday.TUESDAY);
    // Date this test was written
    assertEqual(Dates.weekdayOf(new Date(18, 4, 2020)), Weekday.SATURDAY);
    // Defenestration of Prague
    assertEqual(Dates.weekdayOf(new Date(23, 5, 1618)), Weekday.WEDNESDAY);
    // Coronation of William the Conquerer
    assertEqual(Dates.weekdayOf(new Date(25, 12, 1066)), Weekday.TUESDAY);
    // Bastille Day
    assertEqual(Dates.weekdayOf(new Date(14, 7, 1789)), Weekday.TUESDAY);
  }
}
