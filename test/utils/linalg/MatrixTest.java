package utils.linalg;

import static assertions.Assertions.assertEqual;
import static org.junit.Assert.assertThrows;
import static utils.linalg.Matrix.from;

import org.junit.Test;

public class MatrixTest {

  @Test
  public void multiplyBy_identityFirst_producesExpectedResult() {
    long[][] a = {{1, 0}, {0, 1}};
    long[][] b = {{1, 2}, {3, 4}};

    assertEqual(from(a).multiplyBy(from(b)), from(b));
  }

  @Test
  public void multiplyBy_identitySecond_producesExpectedResult() {
    long[][] a = {{1, 2}, {3, 4}};
    long[][] b = {{1, 0}, {0, 1}};

    assertEqual(from(a).multiplyBy(from(b)), from(a));
  }

  @Test
  public void multiplyBy_nonIdentityMatrices_producesExpectedResult() {
    long[][] a = {{1, 2}, {3, 4}};
    long[][] b = {{3, 1}, {2, 1}};
    long[][] c = {{7, 3}, {17, 7}};

    assertEqual(from(a).multiplyBy(from(b)), from(c));
  }

  @Test
  public void multiplyBy_matrixAndVector_producesExpectedResult() {
    long[][] a = {{1, 2}, {3, 4}};
    long[][] b = {{5}, {2}};
    long[][] c = {{9}, {23}};

    assertEqual(from(a).multiplyBy(from(b)), from(c));
  }

  @Test
  public void multiplyBy_nonSquareMatrices_producesExpectedResult() {
    long[][] a = {{1, 2, 5}, {3, 4, 9}};
    long[][] b = {{5, 1}, {2, 4}, {3, 3}};
    long[][] c = {{24, 24}, {50, 46}};

    assertEqual(from(a).multiplyBy(from(b)), from(c));
  }

  @Test
  public void multiplyBy_incompatible_throwsException() {
    long[][] a = {{1, 2, 5}, {3, 4, 9}};

    assertThrows(RuntimeException.class, () -> from(a).multiplyBy(from(a)));
  }
}
