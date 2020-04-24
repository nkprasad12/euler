package test.utils.numbers;

import static test.Assertions.assertEqual;

import org.junit.Test;
import src.utils.numbers.Series;

public class SeriesTest {

  @Test
  public void ofIntegersUpTo_returnsExpected() {
    assertEqual(Series.ofIntegersUpTo(0), 0l);
    assertEqual(Series.ofIntegersUpTo(1), 1l);
    assertEqual(Series.ofIntegersUpTo(5), 15l);
    assertEqual(Series.ofIntegersUpTo(100), 5050l);
  }

  @Test
  public void ofIntegerSquaresUpTo_returnsExpected() {
    assertEqual(Series.ofIntegerSquaresUpTo(0), 0l);
    assertEqual(Series.ofIntegerSquaresUpTo(1), 1l);
    assertEqual(Series.ofIntegerSquaresUpTo(5), 55l);
    assertEqual(Series.ofIntegerSquaresUpTo(100), 338350l);
  }
}
