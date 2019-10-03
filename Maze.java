import java.util.ArrayList;

public class Maze {

  private ArrayList<Location> locations;

  public Maze(ArrayList<Location> locations) {
    this.locations = locations;
  }

  public ArrayList<Location> getLocations() {
    return locations;
  }

  public boolean contains(Location location) {
    for (Location loc : locations) {
      if (loc.getCol() == location.getCol() && loc.getRow() == location.getRow())
        return true;
    }
    return false;
  }
}