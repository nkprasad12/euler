package problems.problems101to200.problems101to110;

import static java.lang.Double.valueOf;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;

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
    return Generators.fromTextFile("problem102.txt", "\n").filter(points -> containsOrigin(points))
       .reducing(0, (ct, next) -> ct + 1).lastValue().toString();
  }

  static boolean containsOrigin(String input) {
    String[] points = input.split(",");
    Vector a = new Vector(Arrays.asList(valueOf(points[0]), valueOf(points[1])));
    Vector b = new Vector(Arrays.asList(valueOf(points[2]), valueOf(points[3])));
    Vector c = new Vector(Arrays.asList(valueOf(points[4]), valueOf(points[5])));
    int totalOrientation = originOrientation(a, b) + originOrientation(b, c) + originOrientation(c, a);
    return (totalOrientation == 3) || (totalOrientation == -3);
  }
  
  public static int originOrientation(Vector a, Vector b) {
    // If the points are colinear with the origin
    if (a.component(0) * b.component(1) == b.component(0) * a.component(1)) {
      return 0;
    }
    // Vector between the two points
    Vector v = b.minus(a);
    // Vector perpendicular to the line f(t) = a + t * v
    Vector r = a.plus(v.scale((v.dot(a) * 1.0) / v.dot(v)));
    // det |v r| computes the z axis of the cross product, which gives the orientation. 
    double result = v.component(0) * r.component(1) - v.component(1) * r.component(0);
    if (result > 0) {
      return 1;
    } else if (result < 0) {
      return -1;
    }
    throw new RuntimeException("Got unexpected 0 orientation");
  }
}
