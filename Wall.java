public class Wall {

  private Location location;
  private int[] x;
  private int[] y;
  private int height;
  private int width;

  public Wall(Location location, int height, int width) {
    this.location = location;
    this.height = height;
    this.width = width;
  }

  public Wall(int[] x, int[] y) {
    this.x = x;
    this.y = y;
  }

  public int[] getX() {
    return x;
  }

  public int[] getY() {
    return y;
  }

  public Location getLocation() {
    return location;
  }

  public int getCol() {
    return location.getCol();
  }

  public int getRow() {
    return location.getRow();
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setSize(int w, int h) {
    width = w;
    height = h;
  }
}