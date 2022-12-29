import java.util.Set;
import java.util.HashSet;

class board {
  public static final int empty = 0;
  public static final int queen = 1;
  public static final int restricted = 2;
  public static final int boardSize = 8;

  public int[][] newBoard() {
    int[][] board = new int[boardSize][boardSize];
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        board[i][j] = empty;
      }
    }
    return board;
  }

  public void findSolutions() {
    // some solutions are currently the same
    int ct = 0;
    /*
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        //int[][] x = ;
        printBoard(PlaceQueens(newBoard(), boardSize, i, j));
      }
    } */
    PlaceQueens(newBoard(), boardSize, 3, 7);
    //System.out.printf("%d solutions found.", ct);
  }

  public int[][] PlaceQueens(int[][] b, int q, int x, int y) {
    if (q == 0) {
      // why is it returning and printing different things here?
      printBoard(b);
      return b;
    }
    while (emptySq(b)) {
      // if first queen is placed randomly is it all random
      if (q != boardSize) {
        for (int i = 0; i < b.length; i++) {
          for (int j = 0; j < b[i].length; j++) {
            if (b[i][j] == empty) {
              x = i; y = j;
              break;
            }
          }
        }
      }
      //copy board with queen in picked spot
      int[][] m = PlaceQueens(placeQueen(b, x, y), q-1, -1, -1);
      if (m == null) {
        b[x][y] = restricted;
      } else {
        return b;
      }
    }
    return null;
  } 

  public void printBoard(int[][] b) {
    System.out.println("SOLUTION: ");
    for (int[] p : b) {
      for (int x : p) {
        if (x == queen) {
          System.out.print("Q ");
        } else {
          System.out.print("* ");
        }
      }
      System.out.println();
    }
  }

  private boolean emptySq(int[][] b) {
    for (int i = 0; i < b.length; i++) {
      for (int j = 0; j < b[i].length; j++) {
        if (b[i][j] == empty) {
          return true;
        }
      }
    }
    return false;
  }

  private int[][] placeQueen(int[][] b, int x, int y) {
    int[][] copy = new int[b.length][b[0].length];
    for (int i = 0; i < b.length; i++) {
      for (int j = 0; j < b[i].length; j++) {
        copy[i][j] = b[i][j];
        if (i == x || j == y) {
          copy[i][j] = restricted;
        }
      }
    }
    copy[x][y] = queen;
    // make diagonals restricted
    int i = x+1; int j = y+1;
    while (i < boardSize && j < boardSize) {
      copy[i][j] = restricted;
      i++;
      j++;
    }
    i = x-1; j = y-1;
    while (i >= 0 && j >= 0) {
      copy[i][j] = restricted;
      i--;
      j--;
    }
    i = x+1; j = y-1;
    while (i < boardSize && j >= 0) {
      copy[i][j] = restricted;
      i++;
      j--;
    }
    i = x-1; j = y+1;
    while (i >= 0 && j < boardSize) {
      copy[i][j] = restricted;
      i--;
      j++;
    }
    return copy;
  }

  public boolean verifySol(int[][] b) {
    if (b==null) {
      return false;
    }
    Set<Integer> queenX = new HashSet<Integer>();
    Set<Integer> queenY = new HashSet<Integer>();
    Set<Integer> diffs = new HashSet<Integer>();
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (b[i][j] == queen) {
          queenX.add(i);
          queenY.add(j);
          diffs.add(i-j);
        }
      }
    }
    //System.out.println(b);
    return (queenX.size() > 0);
  }
}