import java.util.ArrayList;
public class KnightBoard {
  private int[][] board;
  public ArrayList<Point> Poss;
  public KnightBoard(int startingRows, int startingCols) {
    board = new int[startingRows][startingCols];
    for (int idx = 0; idx < board.length; idx++) {
      for (int x = 0; x < board[idx].length; x++) {
        board[idx][x] = 0;
      }
    }
    Poss = new ArrayList<Point>();
    Point one = new Point(-2, -1);
    Point two = new Point(-2, 1);
    Point three = new Point(-1, -2);
    Point four = new Point(-1, 2);
    Point five = new Point(1, -2);
    Point six = new Point(1, 2);
    Point seven = new Point(2, -1);
    Point eight = new Point(2, 1);
    Poss.add(one); Poss.add(two); Poss.add(three); Poss.add(four);
    Poss.add(five); Poss.add(six); Poss.add(seven); Poss.add(eight);
  }
//  private boolean addKnight(Point) {

//  }
}
