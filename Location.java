public class Location {

  private int col;
  private int row;

  public Location(int r, int c) {
    this.row = r;
    this.col = c;
  }

  public boolean equals(Location location) {
    if (location.getCol() == col && location.getRow() == row)
      return true;
    return false;
  }

  public int getCol() {
    return col;
  }

  public int getRow() {
    return row;
  }

  public double getDistance(Location loc) {
    int dRow = row - loc.getRow();
    int dCol = col - loc.getCol();
    return Math.sqrt((dRow * dRow) + (dCol * dCol));
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