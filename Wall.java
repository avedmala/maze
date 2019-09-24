public class Wall {

  private Location location;
  private int height;
  private int width;

  public Wall(Location location, int height, int width) {
    this.location = location;
    this.height = height;
    this.width = width;
  }

  public Location getLocation() {
    return location;
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