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
  public boolean isOpen() {
    return open;
  }
  public void change(int moves) {
    this.moves = moves;
  }
  public void update() {
    moves--;
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
