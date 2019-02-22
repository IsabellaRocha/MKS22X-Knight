import java.util.ArrayList;
import java.util.Collections;
public class KnightBoard {
  private int[][] board;
  public int[][] Poss;
  public Possibility[][] Optimal;
  public KnightBoard(int startingRows, int startingCols) {
    if (startingRows <= 0 || startingCols <= 0) {
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
    for (int idx = 0; idx < board.length; idx++) {
      for (int x = 0; x < board[idx].length; x++) {
        board[idx][x] = 0;
      }
    }
    int[][] Poss2 = {{-2, -1}, {-2, 1},
                     {-1, -2}, {-1, 2},
                     {1, -2}, {1, 2},
                     {2, -1}, {2, 1}};
    Poss = Poss2;
    Optimal = new Possibility[startingRows][startingCols];
    fillMoves();
  }
  public void fillMoves() {
    for (int idx = 0; idx < Optimal.length; idx++) {
      for (int x = 0; x < Optimal[0].length; x++) {
        Optimal[idx][x] = new Possibility(idx, x, 8);
        if (idx == 0 || x == 0 || idx == Optimal.length - 1 || x == Optimal[0].length - 1) {
          Optimal[idx][x] = new Possibility(idx, x, 4);
        }
        if (idx == 1 || x == 1 || idx == Optimal.length - 2 || x == Optimal[0].length - 2) {
          Optimal[idx][x] = new Possibility(idx, x, 6);
        }
        if ((idx == 0 && x == 0) || (idx == 0 && x == Optimal[0].length - 1) || (idx == Optimal.length - 1 && x == 0) || (idx == Optimal.length - 1 && x == Optimal[0].length - 1)) {
          Optimal[idx][x] = new Possibility(idx, x, 2);
        }
        if ((idx == 0 && x == 1) || (idx == 0 && x == Optimal[0].length - 1) || (idx == 1 && x == 0) || (idx == 1 && x == Optimal[0].length - 1)
        || (idx == Optimal.length - 2 && x == 0)|| (idx == Optimal.length - 2 && x == Optimal[0].length - 1) || (idx == Optimal.length - 1 && x == 1) || (idx == Optimal.length - 1 && x == Optimal[0].length - 2)) {
          Optimal[idx][x] = new Possibility(idx, x, 3);
        }
        Optimal[1][1].change(4); Optimal[1][Optimal[0].length - 2].change(4);
        Optimal[Optimal.length - 2][1].change(4); Optimal[Optimal.length - 2][Optimal[0].length - 2].change(4);
      }
    }
  }
  public boolean solve(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
      throw new IllegalArgumentException();
    }
    for (int idx = 0; idx < board.length; idx++) {
      for (int x = 0; x < board[idx].length; x++) {
        if (board[idx][x] != 0) {
          throw new IllegalStateException();
        }
      }
    }
    return solveH(startingRow, startingCol, 1);
  }
  private boolean solveH(int row, int col, int moveNumber) {
    if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
      return false;
    }
    if (moveNumber >= (board.length * board[0].length)) {
      if (board[row][col] == 0) {
        board[row][col] = moveNumber;
        return true;
      }
      else {
        return false;
      }
    }
    if (board[row][col] == 0 && moveNumber < board.length * board[0].length) {
      board[row][col] = moveNumber;
      for (int idx = 0; idx <= 7; idx++) {
        if (row + Poss[idx][0] >= 0 && col + Poss[idx][1] >= 0 && row + Poss[idx][0] < board.length && col + Poss[idx][1] < board[0].length) {
          if(solveH(row + Poss[idx][0], col + Poss[idx][1], moveNumber + 1)) {
            return true;
          }
        }
      }
      board[row][col] = 0;
    }
    return false;
  }
  public boolean solveOpt(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow >= Optimal.length || startingCol >= Optimal[0].length) {
      throw new IllegalArgumentException();
    }
    for (int idx = 0; idx < Optimal.length; idx++) {
      for (int x = 0; x < Optimal[idx].length; x++) {
        if (Optimal[idx][x].getValue() != 0) {
          throw new IllegalStateException();
        }
      }
    }
    return solveOptH(startingRow, startingCol, 1);
  }
  public boolean solveOptH(int row, int col, int moveNumber) {
    if (row < 0 || col < 0 || row >= Optimal.length || col >= Optimal[0].length) {
      return false;
    }
    if (moveNumber == Optimal.length * Optimal[0].length) {
      return true;
    }
    ArrayList<Possibility> Possibilities = new ArrayList<Possibility>();
    for (int idx = 0; idx <= 7; idx++) {
      if (moveKnight(row, col, idx, moveNumber + 1)) {
        Possibilities.add(Optimal[row + Poss[idx][0]][col + Poss[idx][1]]);
        Optimal[row + Poss[idx][0]][col + Poss[idx][1]].remove();
      }
    }
    Sort(Possibilities);
    for (int idx = 0; idx < Possibilities.size(); idx++) {
      Possibilities.get(idx).move(moveNumber + 1);
      if(solveOptH(Possibilities.get(idx).getRow(), Possibilities.get(idx).getCol(), moveNumber + 1)) {
        return true;
      }
    }
    Optimal[row][col].remove();
    return false;
  }
  public static void Sort(ArrayList<Possibility> ary) {
    for (int idx = 1; idx < ary.size(); idx++) {
      int current = ary.get(idx).getMoves();
      int curIdx = idx - 1;
      while (curIdx >= 0 && ary.get(curIdx).getMoves() > current) {
        ary.set(curIdx + 1, ary.get(curIdx));
        curIdx--;
      }
      ary.set(curIdx + 1, ary.get(idx));
    }
  }
  public boolean moveKnight(int row, int col, int x, int moveNumber) {
    try {
      if (x == 0) {
        if (!Optimal[row + Poss[x][0]][col + Poss[x][1]].open()) {
          return false;
        }
        Optimal[row + Poss[x][0]][col + Poss[x][1]].move(moveNumber);
        return true;
      }
      if (x == 1) {
        if (!Optimal[row + Poss[x][0]][col + Poss[x][1]].open()) {
          return false;
        }
        Optimal[row + Poss[x][0]][col + Poss[x][1]].move(moveNumber);
        return true;
      }
      if (x == 2) {
        if (!Optimal[row + Poss[x][0]][col + Poss[x][1]].open()) {
          return false;
        }
        Optimal[row + Poss[x][0]][col + Poss[x][1]].move(moveNumber);
        return true;
      }
      if (x == 3) {
        if (!Optimal[row + Poss[x][0]][col + Poss[x][1]].open()) {
          return false;
        }
        Optimal[row + Poss[x][0]][col + Poss[x][1]].move(moveNumber);
        return true;
      }
      if (x == 4) {
        if (!Optimal[row + Poss[x][0]][col + Poss[x][1]].open()) {
          return false;
        }
        Optimal[row + Poss[x][0]][col + Poss[x][1]].move(moveNumber);
        return true;
      }
      if (x == 5) {
        if (!Optimal[row + Poss[x][0]][col + Poss[x][1]].open()) {
          return false;
        }
        Optimal[row + Poss[x][0]][col + Poss[x][1]].move(moveNumber);
        return true;
      }
      if (x == 6) {
        if (!Optimal[row + Poss[x][0]][col + Poss[x][1]].open()) {
          return false;
        }
        Optimal[row + Poss[x][0]][col + Poss[x][1]].move(moveNumber);
        return true;
      }
      if (x == 7) {
        if (!Optimal[row + Poss[x][0]][col + Poss[x][1]].open()) {
          return false;
        }
        Optimal[row + Poss[x][0]][col + Poss[x][1]].move(moveNumber);
        return true;
      }
    }
    catch(IndexOutOfBoundsException e) {}
    return false;
  }
  public int countSolutions(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
      throw new IllegalArgumentException();
    }
    for (int idx = 0; idx < board.length; idx++) {
      for (int x = 0; x < board[idx].length; x++) {
        if (board[idx][x] != 0) {
          throw new IllegalStateException();
        }
      }
    }
    return solHelp(startingRow, startingCol, 1);
  }
  private int solHelp(int row, int col, int moveNumber) {
    int count = 0;
    if (board[row][col] == 0) {
      if (moveNumber == board.length * board[0].length) {
        return 1;
      }
      for (int idx = 0; idx <= 7; idx++) {
        if (row + Poss[idx][0] >= 0 && col + Poss[idx][1] >= 0 && row + Poss[idx][0] < board.length && col + Poss[idx][1] < board[0].length) {
          board[row][col] = moveNumber;
          count += solHelp(row + Poss[idx][0], col + Poss[idx][1], moveNumber + 1);
          board[row][col] = 0;
        }
      }
    }
    return count;
  }
  public String toString() {
    String output = "";
    for (int idx = 0; idx < board.length; idx++) {
      output += "\n";
      for (int x = 0; x < board[idx].length; x++) {
        if (board[idx][x] == 0) {
          output += "_ ";
        }
        else {
          if (board[idx][x] >= 10) {
            output += board[idx][x] + " ";
          }
          else {
            output += " " + board[idx][x] + " ";
          }
        }
      }
    }
    return output;
  }
  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(5, 5);
    KnightBoard n = new KnightBoard(8, 8);
    KnightBoard a = new KnightBoard(4, 4);
    KnightBoard b = new KnightBoard(2, 2);
//    k.solve(2, 4);
//    System.out.println(k);
//    n.solve(1,1);
//    System.out.println(n);
    System.out.println(k.countSolutions(2, 2));
    System.out.println(a.countSolutions(0, 0));
    System.out.println(b.countSolutions(0, 0));
  }
}
