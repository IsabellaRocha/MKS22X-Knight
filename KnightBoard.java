import java.util.*;
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
        if ((idx == 0 && x == 1) || (idx == 0 && x == Optimal[0].length - 2) || (idx == 1 && x == 0) || (idx == 1 && x == Optimal[0].length - 1)
        || (idx == Optimal.length - 2 && x == 0)|| (idx == Optimal.length - 2 && x == Optimal[0].length - 1) || (idx == Optimal.length - 1 && x == 1) || (idx == Optimal.length - 1 && x == Optimal[0].length - 2)) {
          Optimal[idx][x] = new Possibility(idx, x, 3);
        }
      }
    }
    Optimal[1][1].change(4); Optimal[1][Optimal[0].length - 2].change(4);
    Optimal[Optimal.length - 2][1].change(4); Optimal[Optimal.length - 2][Optimal[0].length - 2].change(4);
  }
  public String toStringDeBug() {
    String output = "";
    for (int idx = 0; idx < Optimal.length; idx++) {
      output += "\n";
      for (int x = 0; x < Optimal[idx].length; x++) {
        output += Optimal[idx][x].getMoves();
        }
      }
    return output;
  }
  public boolean solve(int startingRow, int startingCol) {
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
    return solveH(startingRow, startingCol, 1);
  }
  public boolean solveH(int row, int col, int moveNumber) {
    if (row < 0 || col < 0 || row >= Optimal.length || col >= Optimal[0].length) {
      return false;
    }
    if (moveNumber >= (Optimal.length * Optimal[0].length)) {
      if (Optimal[row][col].getValue() == 0) {
        Optimal[row][col].move(moveNumber);
        return true;
      }
      else {
        return false;
      }
    }
    ArrayList<Possibility> Possibilities = create(row, col);
    if (Optimal[row][col].getValue() == 0 && moveNumber < Optimal.length * Optimal[0].length) {
      Optimal[row][col].move(moveNumber);
      for (int idx = 0; idx < Possibilities.size(); idx++) {
        if(solveH(Possibilities.get(idx).getRow(), Possibilities.get(idx).getCol(), moveNumber + 1)) {
          return true;
        }
      }
      Optimal[row][col].remove();
    }
    return false;
  }
  public boolean canMoveKnight(int row, int col, int idx) {
    try {
      return Optimal[row + Poss[idx][0]][col + Poss[idx][1]].isOpen();
    }
    catch(IndexOutOfBoundsException e) {}
    return false;
  }
  public ArrayList<Possibility> create(int row, int col) {
    ArrayList<Possibility> Possibilities = new ArrayList<Possibility>();
    for (int idx = 0; idx <= 7; idx++) {
      if (canMoveKnight(row, col, idx)) {
        Possibilities.add(Optimal[row + Poss[idx][0]][col + Poss[idx][1]]);
        Optimal[row + Poss[idx][0]][col + Poss[idx][1]].update();
      }
    }
    Collections.sort(Possibilities);
    return Possibilities;
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
    for (int idx = 0; idx < Optimal.length; idx++) {
      output += "\n";
      for (int x = 0; x < Optimal[idx].length; x++) {
        if (Optimal[idx][x].getValue() == 0) {
          output += "_ ";
        }
        else {
          if (Optimal[idx][x].getValue() >= 10) {
            output += Optimal[idx][x].getValue() + " ";
          }
          else {
            output += " " + Optimal[idx][x].getValue() + " ";
          }
        }
      }
    }
    return output;
  }
}
