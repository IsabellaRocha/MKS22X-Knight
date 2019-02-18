import java.util.ArrayList;
public class Possibility {
  private int row;
  private int col;
  private int moves;
  public Possibility(int row, int col, int moves) {
    this.row = row;
    this.col = col;
    this.moves = moves;
  }
  public int getRow() {
    return row;
  }
  public int getCol() {
    return col;
  }
  public int getMoves() {
    return moves;
  }
  public void changeMoves(int moves) {
    this.moves = moves;
  }
  public void moved() {
    moves--;
  }
}
