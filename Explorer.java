public class Explorer {

  private Location location;

  public Explorer(Location location) {
    this.location = location;
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

  public void setLocation(Location newLocation) {
    location = newLocation;
  }

  public void move(String dir, Maze maze) {
    Location checkLoc = new Location(0, 0);
    if (dir.equals("u"))
      checkLoc = new Location(location.getRow() - 1, location.getCol());
    if (dir.equals("d"))
      checkLoc = new Location(location.getRow() + 1, location.getCol());
    if (dir.equals("l"))
      checkLoc = new Location(location.getRow(), location.getCol() - 1);
    if (dir.equals("r"))
      checkLoc = new Location(location.getRow(), location.getCol() + 1);

    if (checkLoc.equals(maze.getFinish())) {
      location = checkLoc;
      System.out.println("It only took you " + maze.getMoves() + " moves to get out...");
      System.exit(0);
    } else if (!maze.contains(checkLoc))
      location = checkLoc;
  }

}