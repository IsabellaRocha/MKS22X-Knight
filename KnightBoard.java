import java.util.ArrayList;
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
    int[][] Poss2 = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    Poss = Poss2;
  }
//  private boolean addKnight(int ogx, int ogy, int movex, int movey, int moveNumber) {
//    if (ogx + movex >= 0 && ogx + movex < board.length && ogy + movey >= 0 && ogy + movey < board[ogx + movex].length) {
//      if (board[ogx + movex][ogy + movey] == 0) {
//        board[ogx + movex][ogy + movey] = moveNumber;
//        return true;
//      }
//    }
//    return false;
//  }
//  private boolean removeKnight(int ogx, int ogy, int movex, int movey) {
//    if (ogx + movex >= 0 && ogx + movex < board.length && ogy + movey >= 0 && ogy + movey < board[ogx + movex].length) {
//      if (board[ogx + movex][ogy + movey] != 0) {
//        board[ogx + movex][ogy + movey] = 0;
//        return true;
//      }
//    }
//    return false;
//  }
  public boolean solve(int startingRow, int startingCol) {
    if (startingRow < 0 || startingCol < 0 || startingRow >= board.length || startingCol >= board[0].length) {
      throw new IllegalArgumentException();
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
    boolean check = false;
    if (board[row][col] == 0 && moveNumber < board.length * board[0].length) {
      board[row][col] = moveNumber;
      for (int idx = 0; idx <= 7; idx++) {
        if (row + Poss[idx][0] >= 0 && col + Poss[idx][1] >= 0 && row + Poss[idx][0] < board.length && col + Poss[idx][1] < board[0].length) {
          if(solveH(row + Poss[idx][0], col + Poss[idx][1], moveNumber + 1)) {
            check = true;
            return true;
          }
        }
      }
      if (check == false) {
        board[row][col] = 0;
      }
    }
    return false;
  }

  //  else {
  //    for (int idx = 0; idx <= 7; idx++) {
  //      if (row + Poss[idx][0] >= 0 && col + Poss[idx][1] >= 0 && row + Poss[idx][0] < board.length && col + Poss[idx][1] < board[0].length) {
  //        if (solveH(row + Poss[idx][0], col + Poss[idx][1], moveNumber + 1)) {
  //          return true;
  //        }
    //      else {
    //        board[row][col] = 0;
      //      moveNumber--;
    //      }
  //    boolean one = solveH(row + Poss[0][0], col + Poss[0][1], moveNumber++);
  //    boolean two = solveH(row + Poss[1][0], col + Poss[1][1], moveNumber++);
  //    boolean three = solveH(row + Poss[2][0], col + Poss[2][1], moveNumber++);
  //    boolean four = solveH(row + Poss[3][0], col + Poss[3][1], moveNumber++);
  //    boolean five = solveH(row + Poss[4][0], col + Poss[4][1], moveNumber++);
  //    boolean six = solveH(row + Poss[5][0], col + Poss[5][1], moveNumber++);
  //    boolean seven = solveH(row + Poss[6][0], col + Poss[6][1], moveNumber++);
  //    boolean eight = solveH(row + Poss[7][0], col + Poss[7][1], moveNumber++);
  //    return (one || two || three || four || five || six || seven || eight);
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
    KnightBoard k = new KnightBoard(5, 4);
    System.out.println(k);
//    k.addKnight(0, 0, 2, 1);
//    System.out.println(k);
//    k.addKnight(2, 1, 2, 1);
//    System.out.println(k);
//    k.removeKnight(2, 1, 2, 1);
//    System.out.println(k);
//    k.addKnight(2, 1, 1, 2);
    k.solve(0, 0);
    System.out.println(k);
  }
}
