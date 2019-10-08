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

  public int getSpace(Maze maze) {
    int space = 0;
    Location checkLoc = new Location(0, 0);
    int rot = rotation % 360;

    do {
      if (rot == 0)
        checkLoc = new Location(location.getRow() - (space + 1), location.getCol());
      if (rot == 90 || rot == -270)
        checkLoc = new Location(location.getRow(), location.getCol() + (space + 1));
      if (rot == 180 || rot == -180)
        checkLoc = new Location(location.getRow() + (space + 1), location.getCol());
      if (rot == 270 || rot == -90)
        checkLoc = new Location(location.getRow(), location.getCol() - (space + 1));

      space++;
    } while (!maze.contains(checkLoc));

    if (space > 4) {
      space = 4;
    }

    return space;
  }

  public void move(Maze maze) {
    Location checkLoc = new Location(0, 0);
    int rot = rotation % 360;

    // checks the orientation of the explorer and moves in that direction
    if (rot == 0)
      checkLoc = new Location(location.getRow() - 1, location.getCol());
    if (rot == 90 || rot == -270)
      checkLoc = new Location(location.getRow(), location.getCol() + 1);
    if (rot == 180 || rot == -180)
      checkLoc = new Location(location.getRow() + 1, location.getCol());
    if (rot == 270 || rot == -90)
      checkLoc = new Location(location.getRow(), location.getCol() - 1);

    // checks if the space explorer is trying to take is a wall
    if (!maze.contains(checkLoc)) {
      maze.addMove();
      location = checkLoc;
    }
  }

}