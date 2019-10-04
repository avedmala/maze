public class Explorer {

  private Location location;
  private int rotation;

  public Explorer(Location location, int rotation) {
    this.location = location;
    this.rotation = rotation;
  }

  public Location getLocation() {
    return location;
  }

  public int getRotation() {
    return rotation;
  }

  public void turn(int rot) {
    rotation += rot;
  }

  public void setRotation(int rot) {
    rotation = rot;
  }

  public int getCol() {
    return location.getCol();
  }

  public int getRow() {
    return location.getRow();
  }

  public void setLocation(Location newLocation) {
    location = newLocation;
  }

  public void move(Maze maze) {
    Location checkLoc = new Location(0, 0);
    int rot = rotation % 360;

    if (rot == 0)
      checkLoc = new Location(location.getRow() - 1, location.getCol());
    if (rot == 90 || rot == -270)
      checkLoc = new Location(location.getRow(), location.getCol() + 1);
    if (rot == 180 || rot == -180)
      checkLoc = new Location(location.getRow() + 1, location.getCol());
    if (rot == 270 || rot == -90)
      checkLoc = new Location(location.getRow(), location.getCol() - 1);

    if (!maze.contains(checkLoc)) {
      maze.addMove();
      location = checkLoc;
    }
  }

}