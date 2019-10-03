import java.util.ArrayList;

public class Maze {

  private ArrayList<Location> locations;
  private Location fLocation;
  private int moves;

  public Maze(ArrayList<Location> locations, Location fLocation, int moves) {
    this.locations = locations;
    this.fLocation = fLocation;
    this.moves = moves;
  }

  public void addMove() {
    moves++;
  }

  public int getMoves() {
    return moves;
  }

  public ArrayList<Location> getLocations() {
    return locations;
  }

  public Location getFinish() {
    return fLocation;
  }

  public boolean contains(Location location) {
    for (Location loc : locations) {
      if (loc.equals(location))
        return true;
    }
    return false;
  }
}