public class Point{
  private int x,y;
  public Point(int X, int Y){
    x=X;
    y=Y;
  }
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public String toString() {
    return "(" + x + "," + y + ")";
  }
}
