import java.util.ArrayList;
public class Possibility {
  private ArrayList<Integer> opt;
  public Possibility(int row, int col) {
    opt = new ArrayList<>();
    opt.add(row);
    opt.add(col);
  }
  public int getRow() {
    return opt.get(0);
  }
  public int getCol() {
    return opt.get(1);
  }
}
