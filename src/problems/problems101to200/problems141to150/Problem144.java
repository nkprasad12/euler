package problems.problems101to200.problems141to150;

import java.lang.invoke.MethodHandles;
import utils.linalg.Vector;

public class Problem144 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {

    double laserSlope = -14.0714285714;
    double[] intercept = {1.4, -9.6};
    long reflections = 1;

    while (true) {
      laserSlope = getOutgoingLaserSlope(laserSlope, intercept);
      intercept = getNextIntercept(laserSlope, intercept);

      if (Math.abs(intercept[0]) < 0.01d && intercept[1] >= 0d) {
        break;
      }
      reflections++;
    }

    return Long.toString(reflections);
  }

  static double getOutgoingLaserSlope(double laserSlope, double[] intercept) {
    double x = intercept[0];
    double y = intercept[1];
    double m = laserSlope;

    // Reflect the vector of the initial laser beam across the normal of the tangent
    // If the slope is m, (1, m) is a vector in the direction of the incoming laser.
    Vector v = new Vector(1, m);
    // If the intercept point is (x, y), then the slope of the tangent is -4 x / y
    // Therefore, the tangent is in the direction of (1, -4 x / y)
    // Then a vector perpendicular to the tangent is (4 x / y, 1)
    Vector n = new Vector(4 * x, y);
    // Project the incoming vector on the normal
    Vector proj = n.scale(v.dot(n) / n.dot(n));
    // Subtract twice the projection on to the normal from the vector to
    // get its reflection across the normal.
    Vector w = v.minus(proj.scale(2));

    return w.c(1) / w.c(0);
  }

  static double[] getNextIntercept(double newSlope, double[] oldIntercept) {
    double m = newSlope;
    double x0 = oldIntercept[0];
    double y0 = oldIntercept[1];

    // y - y0 = m (x - x0) -> y = mx - m x0 + y0;
    double b = y0 - m * x0;

    // Want to solve:
    //  y = mx + b
    //  4x^2 + y^2 = 100
    double denom = m * m + 4;
    double negB = -1 * b * m;
    double root = 2 * Math.sqrt(100 + 25 * m * m - b * b);

    double x_p = (negB + root) / denom;
    double x_m = (negB - root) / denom;

    double x_new = Math.abs(x_p - x0) > Math.abs(x_m - x0) ? x_p : x_m;
    double[] result = {x_new, m * x_new + b};

    return result;
  }
}
