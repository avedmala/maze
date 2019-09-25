public class Location {

  private int col;
  private int row;

  public Location(int c, int r) {
    this.col = c;
    this.row = r;
  }

  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  public void setCol(int c) {
    col = c;
  }

  public void setRow(int r) {
    row = r;
  }
}