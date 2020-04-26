package test.utils.dates;

import static src.utils.dates.Dates.daysBetween;
import static src.utils.dates.Dates.weekdayOf;
import static test.Assertions.assertEqual;

import org.junit.Test;
import src.utils.dates.Date;
import src.utils.dates.Weekday;

public class DatesTest {

  private static final Date BLACK_TUESDAY = new Date(29, 10, 1929);
  private static final Date TEST_WRITTEN = new Date(18, 4, 2020);
  private static final Date DEFENESTRATION_OF_PRAGUE = new Date(23, 5, 1618);
  private static final Date CORONATION_OF_WILLIAM_THE_CONQUERER = new Date(25, 12, 1066);
  private static final Date BASTILLE_DAY = new Date(13, 7, 1789);
  private static final Date SWEDISH_PEACE_DAY = new Date(14, 8, 1814);

  @Test
  public void weekdayOf_returnsExpectedResult() {
        assertEqual(weekdayOf(BLACK_TUESDAY), Weekday.TUESDAY);
        assertEqual(weekdayOf(TEST_WRITTEN), Weekday.SATURDAY);
        assertEqual(weekdayOf(DEFENESTRATION_OF_PRAGUE), Weekday.WEDNESDAY);
        assertEqual(weekdayOf(CORONATION_OF_WILLIAM_THE_CONQUERER), Weekday.TUESDAY);
        assertEqual(weekdayOf(BASTILLE_DAY), Weekday.TUESDAY);
  }

  @Test
  public void daysBetween_closeDays() {
    assertEqual(daysBetween(BLACK_TUESDAY, BLACK_TUESDAY), 0);
    assertEqual(daysBetween(BLACK_TUESDAY, new Date(30, 10, 1929)), 1);
    assertEqual(daysBetween(new Date(30, 10, 1929), BLACK_TUESDAY), -1);
  }

  @Test
  public void daysBetween_differentYears() {
    assertEqual(daysBetween(BLACK_TUESDAY, new Date(29, 10, 1930)), 365);
    assertEqual(daysBetween(CORONATION_OF_WILLIAM_THE_CONQUERER, new Date(1, 1, 1067)), 7);
    assertEqual(daysBetween(DEFENESTRATION_OF_PRAGUE, BASTILLE_DAY), 62509);
  }
}
