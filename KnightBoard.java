public class KnightBoard {
  private int[][] board;
  public int[][] Poss;
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
