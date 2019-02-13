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
  public String toString() {
    String output = "";
    for (int idx = 0; idx < board.length; idx++) {
      output += "\n";
      for (int x = 0; x < board[idx].length; x++) {
        if (board[idx][x] == 0) {
          output += "_ ";
        }
        else {
          output += board[idx][x];
        }
      }
    }
    return output;
  }
}
