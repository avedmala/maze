import java.util.ArrayList;

public class Maze {

  private ArrayList<Location> locations;

  public Maze(ArrayList<Location> locations) {
    this.locations = locations;
  }

  public ArrayList<Location> getLocations() {
    return locations;
  }
}