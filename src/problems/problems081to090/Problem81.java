package problems.problems081to090;

import static utils.generators.Generators.from;
import static utils.generators.Generators.fromTextFile;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Problem81 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    List<List<Long>> grid = getGrid();
    long startTime = System.nanoTime();
    System.out.println(loop(grid));
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
    startTime = System.nanoTime();
    System.out.println(solution(grid));
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution(List<List<Long>> inputs) {
    long[][] solution = new long[80][80];
    for (int i = 0; i < 80; i++) {
      for (int j = 0; j < 80; j++) {
        long current = inputs.get(i).get(j);
        if (i == 0 && j == 0) {
          solution[i][j] = current;
          continue;
        }
        if (i == 0) {
          solution[i][j] = solution[i][j - 1] + current;
          continue;
        }
        if (j == 0) {
          solution[i][j] = solution[i - 1][j] + current;
          continue;
        }
        solution[i][j] = current + Math.min(solution[i - 1][j], solution[i][j - 1]);
      }
    }
    return String.valueOf(solution[79][79]);
  }

  public static String loop(List<List<Long>> grid) {
    HashMap<String,Long> lookup = new HashMap<String,Long>();
    lookup.put("0,0", 4445l);
    lookup.put("1,0", 7142l);
    lookup.put("0,1", 5541l);
    for (int i = 0; i< grid.size(); i++) {
        for(int j = 0; j < grid.size(); j++) {
            Long prevUp = lookup.get(i+","+(j-1));
            Long prevLeft = lookup.get((i-1)+","+j);
            Long value = grid.get(i).get(j);
            if (i == 0 && j == 0){
                lookup.put("0,0", value);
            } else if (i == 0) {
                lookup.put(i+","+j, prevUp+value);
            } else if (j == 0) {
                lookup.put(i+","+j, prevLeft+value);
            }else {
                lookup.put(i +"," + j, Math.min(value+prevUp, value+prevLeft));
            }
        }
    }
    return lookup.get("79,79").toString();
  }

  static List<List<Long>> getGrid() {
    return fromTextFile("problem81.txt") 
        .map(line -> line.split(","))
        .map(tokens -> from(Arrays.asList(tokens)).map(Long::parseLong).list())
        .list();
  }
}
