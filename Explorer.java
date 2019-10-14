public class Explorer {

  private Location location;
  private int rotation;
  private int visibleDist;

  public Explorer(Location location, int rotation, int visibleDist) {
    this.location = location;
    this.rotation = rotation;
    this.visibleDist = visibleDist;
  }

  public Location getLocation() {
    return location;
  }

  public int getRotation() {
    return rotation;
  }

  public int getVisibleDist() {
    return visibleDist;
  }

  public void setVisibleDist(int newDist) {
    visibleDist = newDist;
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

  public boolean isFree(Maze maze, int spaces, int rotOffset) {
    Location checkLoc = new Location(0, 0);
    int rot = (rotation % 360);

    if (rot == 0)
      checkLoc = new Location(location.getRow() - spaces, location.getCol());
    if (rot == 90 || rot == -270)
      checkLoc = new Location(location.getRow(), location.getCol() + spaces);
    if (rot == 180 || rot == -180)
      checkLoc = new Location(location.getRow() + spaces, location.getCol());
    if (rot == 270 || rot == -90)
      checkLoc = new Location(location.getRow(), location.getCol() - spaces);

    rot += rotOffset;

    if (rot == 0)
      checkLoc = new Location(checkLoc.getRow() - 1, checkLoc.getCol());
    if (rot == 90 || rot == -270)
      checkLoc = new Location(checkLoc.getRow(), checkLoc.getCol() + 1);
    if (rot == 180 || rot == -180)
      checkLoc = new Location(checkLoc.getRow() + 1, checkLoc.getCol());
    if (rot == 270 || rot == -90)
      checkLoc = new Location(checkLoc.getRow(), checkLoc.getCol() - 1);

    if (maze.contains(checkLoc))
      return true;

    return false;
  }

  public int getSpace(Maze maze) {
    int space = 0;
    Location checkLoc = new Location(0, 0);
    int rot = (rotation % 360);

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

      if (checkLoc.equals(maze.getFinish()) || space == visibleDist)
        break;

    } while (!maze.contains(checkLoc));

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