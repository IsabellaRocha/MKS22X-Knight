import java.util.ArrayList;
public class KnightBoard {
  private int[][] board;
  private int knight;
//  public ArrayList<Point> Poss;
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
    knight = 1;
//    Poss = new ArrayList<Point>();
//    Point one = new Point(-2, -1);
//    Point two = new Point(-2, 1);
//    Point three = new Point(-1, -2);
//    Point four = new Point(-1, 2);
//    Point five = new Point(1, -2);
//    Point six = new Point(1, 2);
//    Point seven = new Point(2, -1);
//    Point eight = new Point(2, 1);
//    Poss.add(one); Poss.add(two); Poss.add(three); Poss.add(four);
//    Poss.add(five); Poss.add(six); Poss.add(seven); Poss.add(eight);
  }
  private boolean addKnight(int ogx, int ogy, int movex, int movey) {
    if (ogx + movex >= 0 && ogx + movex < board.length && ogy + movey >= 0 && ogy + movey < board[ogx + movex].length) {
      if (board[ogx + movex][ogy + movey] == 0) {
        board[ogx + movex][ogy + movey] = knight;
        knight += 1;
        return true;
      }
    }
    return false;
  }
  private boolean removeKnight(int ogx, int ogy, int movex, int movey) {
    if (ogx + movex >= 0 && ogx + movex < board.length && ogy + movey >= 0 && ogy + movey < board[ogx + movex].length) {
      if (board[ogx + movex][ogy + movey] != 0) {
        board[ogx + movex][ogy + movey] = 0;
        knight -= 1;
        return true;
      }
    }
    return false;
  }
  public boolean solve(int startingRow, int startingCol) {
    return solveH(0, 0);
  }
  private boolean solveH(int row, int col) {
    if (knight == board.length * board[0].length) {
      return true;
    }
    else {
      addKnight(0, 0, row, col);
      for (int r = -2; r < 2; r++) {
        for (int c = -2; c < 2; c++) {
          if ((Math.abs(r) % 2 == 0 && Math.abs(c) % 2 != 0) || (Math.abs(r) % 2 != 0 && Math.abs(c) % 2 == 0)) {
            if (addKnight(row, col, row + r, col + c)) {
              if (solveH(row + r, col + c)) {
                return true;
              }
              else {
                removeKnight(row, col, row + r, col + c);
              }
            }
          }
        }
      }
    }
    return false;
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
          output += board[idx][x] + " ";
        }
      }
    }
    return output;
  }
  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(8, 8);
    System.out.println(k);
    k.addKnight(0, 0, 2, 1);
    System.out.println(k);
    k.addKnight(2, 1, 2, 1);
    System.out.println(k);
    k.removeKnight(2, 1, 2, 1);
    System.out.println(k);
    k.addKnight(2, 1, 1, 2);
    System.out.println(k);
  }
}
