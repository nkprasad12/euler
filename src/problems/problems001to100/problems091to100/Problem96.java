package problems.problems001to100.problems091to100;

import static utils.generators.Generators.fromTextFile;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class Problem96 {

  public static void main(String[] args) {
    System.out.println(MethodHandles.lookup().lookupClass());
    long startTime = System.nanoTime();
    System.out.println(solution());
    System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms");
  }

  public static String solution() {
    List<String> rawInput = fromTextFile("problem96.txt", "\n").list();
    String fullSudoku = "";
    int total = 0;
    for (String inputLine : rawInput) {
      if (inputLine.contains("Grid")) {
        if (fullSudoku.length() > 0) {
          total += new Problem96.Sudoku(fullSudoku).getSignature();
        }
        fullSudoku = "";
      } else {
        fullSudoku += inputLine;
      }
    }
    return Integer.toString(total);
  }

  private static int[][] getBoardFromString(String fullSudoku) {
    int[][] board = new int[9][9];
    for (int i = 0; i < fullSudoku.length(); i++) {
      char currentChar = fullSudoku.charAt(i);
      if (!Character.isDigit(currentChar)) {
        continue;
      }
      board[i / 9][i % 9] = Integer.parseInt(currentChar + "");
    }
    return board;
  }

  private static final class Sudoku {

    int[][] board;

    Sudoku(String input) {
      this.board = getBoardFromString(input);
    }

    private void solve() {
      // Solve the sudoku here
    }

    int getSignature() {
      solve();
      return board[0][0] * 100 + board[0][1] * 10 + board[0][2];
    }

    @Override
    public String toString() {
      String result = "";
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          result += "" + board[i][j];
        }
        result += "\n";
      }
      return result;
    }
  }
}
