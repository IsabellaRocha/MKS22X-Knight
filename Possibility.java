import java.util.ArrayList;
public class Possibility {
  private int row;
  private int col;
  private int moves;
  private int value;
  private boolean open;
  public Possibility(int row, int col, int moves) {
    this.row = row;
    this.col = col;
    this.moves = moves;
    value = 0;
    open = true;
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
  public int getValue() {
    return value;
  }
  public boolean open() {
    return open;
  }
  public int compareTo(Possibility other) {
    if (getMoves() > other.getMoves()) {
      return 1;
    }
    if (getMoves() == other.getMoves()) {
      return 0;
    }
    return -1;
  }
  public void change(int moves) {
    this.moves = moves;
  }
  public void move(int value) {
    this.value = value;
    open = false;
  }
  public void remove() {
    value = 0;
    open = true;
  }
}
