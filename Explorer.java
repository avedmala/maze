public class Explorer {

  private Location location;

  public Explorer(Location location) {
    this.location = location;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location newLocation) {
    location = newLocation;
  }

  public boolean canMove(String dir, Maze maze) {
    if (dir.equals("u")) {
      Location checkLoc = new Location(location.getX(), location.getY() + 1);
      if (maze.getLocations().contains(checkLoc))
        return false;
    }
    if (dir.equals("d")) {
      Location checkLoc = new Location(location.getX(), location.getY() - 1);
      if (maze.getLocations().contains(checkLoc))
        return false;
    }
    if (dir.equals("l")) {
      Location checkLoc = new Location(location.getX() - 1, location.getY());
      if (maze.getLocations().contains(checkLoc))
        return false;
    }
    if (dir.equals("r")) {
      Location checkLoc = new Location(location.getX() + 1, location.getY());
      if (maze.getLocations().contains(checkLoc))
        return false;
    }
    return true;
  }

  public void move(String dir, Maze maze) {
    if (canMove(dir, maze)) {
      if (dir.equals("u")) {
        location = new Location(location.getX(), location.getY() + 1);
      }
      if (dir.equals("d")) {
        location = new Location(location.getX(), location.getY() - 1);
      }
      if (dir.equals("l")) {
        location = new Location(location.getX() - 1, location.getY());
      }
      if (dir.equals("r")) {
        location = new Location(location.getX() + 1, location.getY());
      }
    }
  }

}