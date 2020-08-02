package problems.problems101to200.problems101to110;

import static java.lang.Integer.valueOf;

import java.lang.invoke.MethodHandles;

import utils.generators.Generators;
import utils.generators.base.tuples.Tuples;
import utils.generators.base.tuples.Tuples.Pair;

public class Problem102 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    // A(-340,495), B(-153,-910), C(835,-947)
    // X(-175,41), Y(-421,-714), Z(574,-645)
    // System.out.println(containsOrigin("-340,495,-153,-910,835,-947"));
    // System.out.println(containsOrigin("-175,41,-421,-714,574,-645"));
    return Generators.fromTextFile("problem102.txt", "\n").filter(points -> containsOrigin(points))
       .reducing(0, (ct, next) -> ct + 1).lastValue().toString();

    // System.out.println(getIntercepts(Tuples.pair(-153, -910), Tuples.pair(835, -947)));
    // System.out.println(getIntercepts(Tuples.pair(-340, 495), Tuples.pair(-153, -910)));
    // System.out.println(getIntercepts(Tuples.pair(835, -947), Tuples.pair(-340, 495)));

    // return "";
  }

  static boolean containsOrigin(String input) {
    String[] points = input.split(",");
    Pair<Integer, Integer> a = Tuples.pair(valueOf(points[0]), valueOf(points[1]));
    Pair<Integer, Integer> b = Tuples.pair(valueOf(points[2]), valueOf(points[3]));
    Pair<Integer, Integer> c = Tuples.pair(valueOf(points[4]), valueOf(points[5]));
    
    boolean hasPositiveXIntercept = false;
    boolean hasNegativeXIntercept = false;
    boolean hasPositiveYIntercept = false;
    boolean hasNegativeYIntercept = false;

    Pair<Double, Double> intercepts;
    Double xIntercept;
    Double yIntercept;

    intercepts = getIntercepts(a, b);
    xIntercept = intercepts.first();
    yIntercept = intercepts.second();

    if (xIntercept != null) {
      if (xIntercept > 0) {
        hasPositiveXIntercept = true;
      } else if (xIntercept < 0) {
        hasNegativeXIntercept = true;
      }
    }
    if (yIntercept != null) {
      if (yIntercept > 0) {
        hasPositiveYIntercept = true;
      } else if (yIntercept < 0) {
        hasNegativeYIntercept = true;
      }
    }

    intercepts = getIntercepts(b, c);
    xIntercept = intercepts.first();
    yIntercept = intercepts.second();

    if (xIntercept != null) {
      if (xIntercept > 0) {
        hasPositiveXIntercept = true;
      } else if (xIntercept < 0) {
        hasNegativeXIntercept = true;
      }
    }
    if (yIntercept != null) {
      if (yIntercept > 0) {
        hasPositiveYIntercept = true;
      } else if (yIntercept < 0) {
        hasNegativeYIntercept = true;
      }
    }

    intercepts = getIntercepts(c, a);
    xIntercept = intercepts.first();
    yIntercept = intercepts.second();

    if (xIntercept != null) {
      if (xIntercept > 0) {
        hasPositiveXIntercept = true;
      } else if (xIntercept < 0) {
        hasNegativeXIntercept = true;
      }
    }
    if (yIntercept != null) {
      if (yIntercept > 0) {
        hasPositiveYIntercept = true;
      } else if (yIntercept < 0) {
        hasNegativeYIntercept = true;
      }
    }
    
    return hasNegativeXIntercept && hasPositiveXIntercept && hasNegativeYIntercept && hasPositiveYIntercept;
  }

  static Pair<Double, Double> getIntercepts(Pair<Integer, Integer> point1, Pair<Integer, Integer> point2) {
    double slope = 0;
    double b = 0;

    Double yIntercept = null;
    Double xIntercept = null;

    if (point1.first() - point2.first() == 0) {
        return Tuples.pair(1.0*point1.first(), yIntercept);
    }

    slope = (1.0 * (point1.second() - point2.second())) / (point1.first() - point2.first());
    b = point1.second() - slope*point1.first();

    if (((b <= point2.second() && b >= point1.second()) || (b >= point2.second() && b <= point1.second()))) {
        yIntercept = b;
    }
    
    double possibleXIntercept = -1*b / slope;

    if ((possibleXIntercept >= point2.first() && possibleXIntercept <= point1.first()) || possibleXIntercept <=point2.first() && possibleXIntercept >= point1.first()) {
        xIntercept = possibleXIntercept;
    }

    return Tuples.pair(xIntercept, yIntercept);    
  }
}


