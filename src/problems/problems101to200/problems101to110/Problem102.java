package problems.problems101to200.problems101to110;

import static java.lang.Double.valueOf;
import static utils.numbers.NumericUtils.sign;

import java.lang.invoke.MethodHandles;
import utils.generators.Generators;
import utils.linalg.Vector;

public class Problem102 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    return Generators.fromTextFile("problem102.txt", "\n").map(str -> str.split(","))
        .filter(points -> containsOrigin(points)).reducing(0, (ct, next) -> ct + 1).lastValue().toString();
  }

  static boolean containsOrigin(String[] points) {
    Vector a = new Vector(valueOf(points[0]), valueOf(points[1]));
    Vector b = new Vector(valueOf(points[2]), valueOf(points[3]));
    Vector c = new Vector(valueOf(points[4]), valueOf(points[5]));
    int totalOrientation = orientation(a, b) + orientation(b, c) + orientation(c, a);
    return Math.abs(totalOrientation) == 3;
  }

  public static int orientation(Vector a, Vector b) {
    // Vector between the two points
    Vector v = b.minus(a);
    // Vector perpendicular to the line f(t) = a + t * v
    Vector r = a.plus(v.scale((v.dot(a) * 1.0) / v.dot(v)));
    // det |v r| computes the z axis of the cross product, which gives the
    // orientation.
    double orientation = v.component(0) * r.component(1) - v.component(1) * r.component(0);
    return sign(orientation);
  }
}
