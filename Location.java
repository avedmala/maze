public class Location {

  private int col;
  private int row;

  public Location(int r, int c) {
    this.row = r;
    this.col = c;
  }

  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  public String toString() {
    return col + " " + row;
  }

  public void setCol(int c) {
    col = c;
  }

  public void setRow(int r) {
    row = r;
  }
}