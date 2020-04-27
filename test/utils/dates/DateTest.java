package utils.dates;

import static org.junit.Assert.assertThrows;
import static assertions.Assertions.assertEqual;

import org.junit.Test;
import utils.dates.Date;

public class DateTest {

  @Test
  public void invalidMonth_throwsException() {
    assertThrows(RuntimeException.class, () -> new Date(5, 0, 2017));
    assertThrows(RuntimeException.class, () -> new Date(5, 13, 2017));
  }

  @Test
  public void invalidDay_endOfFebruary_throwsException() {
    assertThrows(RuntimeException.class, () -> new Date(29, 2, 1900));
    assertThrows(RuntimeException.class, () -> new Date(30, 2, 2000));
    assertThrows(RuntimeException.class, () -> new Date(30, 2, 2008));
    assertThrows(RuntimeException.class, () -> new Date(29, 2, 2007));
  }

  @Test
  public void invalidDay_endOfMonths_throwsException() {
    assertThrows(RuntimeException.class, () -> new Date(32, 1, 2000));
    assertThrows(RuntimeException.class, () -> new Date(32, 3, 2000));
    assertThrows(RuntimeException.class, () -> new Date(31, 4, 2000));
    assertThrows(RuntimeException.class, () -> new Date(32, 5, 2000));
    assertThrows(RuntimeException.class, () -> new Date(31, 6, 2000));
    assertThrows(RuntimeException.class, () -> new Date(32, 7, 2000));
    assertThrows(RuntimeException.class, () -> new Date(32, 8, 2000));
    assertThrows(RuntimeException.class, () -> new Date(31, 9, 2000));
    assertThrows(RuntimeException.class, () -> new Date(32, 10, 2000));
    assertThrows(RuntimeException.class, () -> new Date(31, 11, 2000));
    assertThrows(RuntimeException.class, () -> new Date(32, 12, 2000));
  }

  @Test
  public void invalidDay_startOfMonths_throwsException() {
    for (int i = 1; i <= 12; i++) {
      final int month = i;
      assertThrows(RuntimeException.class, () -> new Date(0, month, 1900));
      assertThrows(RuntimeException.class, () -> new Date(-1, month, 1900));
    }
  }

  @Test
  public void feb29_leapYears_doesNotThrow() {
    new Date(29, 2, 2000);
    new Date(29, 2, 1600);
    new Date(29, 2, 2008);
    new Date(29, 2, 1592);
  }

  @Test
  public void typicalValidDays_doesNotThrow() {
    new Date(14, 2, 2001);
    new Date(29, 7, 1605);
    new Date(4, 12, 20123);
    new Date(9, 7, 1492);
  }

  @Test
  public void createdDate_hasExpectedFields() {
    Date date = new Date(14, 2, 2001);
    assertEqual(date.day(), 14);
    assertEqual(date.month(), 2);
    assertEqual(date.year(), 2001);
  }
}
