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
    int dif1 = row - loc.getRow();
    if (dif1 < 0)
      dif1 *= -1;

    int dif2 = col - loc.getCol();
    if (dif2 < 0)
      dif2 *= -1;

    return Math.sqrt((dif1 * dif1) + (dif2 * dif2));
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