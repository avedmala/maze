import java.awt.Rectangle;

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

  public Rectangle getRectangle() {
    Rectangle rect = new Rectangle((location.getCol() * width) + 50, (location.getRow() * height) + 100, width, height);
    return rect;
  }

  public void setSize(int w, int h) {
    width = w;
    height = h;
  }
}