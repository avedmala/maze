import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class MazeProgram extends JPanel implements KeyListener, MouseListener {
  private static final long serialVersionUID = 1L;
  JFrame frame;
  Maze maze;

  int scale = 15; // scale of 2d maze
  int row = 10; // size of the maze
  int col = 10; // size of the maze
  int mapSize = 7; // area shown on the 2d minimap

  Wall[][] walls = new Wall[(row * 2) + 1][(row * 2) + 1];
  ArrayList<Wall> wallList = new ArrayList<Wall>();
  Explorer explorer = new Explorer(new Location(1, 1), 0, 3);
  Location fLocation = new Location((row * 2) - 1, (row * 2) - 1);

  int xMax = 800; // horizontal window res - 600
  int yMax = 800; // vertical window res
  int iterX = xMax / 11; // size of iterations of each level for X
  int iterY = yMax / 11; // size of iterations of each level for Y
  // NOTE: if visibleDist goes up to 5, then the xMax must be divided by 15

  public MazeProgram() {
    setBoard();
    frame = new JFrame();
    frame.add(this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(xMax + 600, yMax);
    frame.setVisible(true);
    frame.addKeyListener(this);
    // this.addMouseListener(this); // in case you need mouse clicking
  }

  public void paintComponent(Graphics g) {
    if (!explorer.getLocation().equals(fLocation)) { // 3d stuff
      super.paintComponent(g);
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, xMax + 600, yMax);
      for (int i = 0; i < wallList.size(); i++) {
        Polygon p = new Polygon();
        Wall w = wallList.get(i);

        for (int j = 0; j < w.getX().length; j++)
          p = new Polygon(w.getX(), w.getY(), w.getX().length);

        // draws borders of the trapezoids
        g.setColor(Color.CYAN);
        g.drawPolygon(p);

        // makes each level a darker gray
        g.setColor(new Color(220 - (i * 10), 220 - (i * 10), 220 - (i * 10)));
        g.fillPolygon(p);
      }

      // 2d
      g.setColor(Color.DARK_GRAY);

      // loops through the 2d array and draws each wall
      for (int i = 0; i < walls.length; i++) {
        for (int j = 0; j < walls[i].length; j++) {
          Wall w = walls[i][j];
          if (w != null && explorer.getLocation().getDistance(w.getLocation()) < mapSize)
            g.fillRect(w.getCol() * w.getWidth() + 1000, w.getRow() * w.getHeight() + 250, w.getWidth(), w.getHeight());
        }
      }

      // draws explorer
      g.setColor(Color.RED);
      g.fillOval(explorer.getCol() * scale + 1000, explorer.getRow() * scale + 250, scale, scale);

      // draw finish
      if (explorer.getLocation().getDistance(fLocation) < mapSize) {
        g.setColor(Color.GREEN);
        g.fillRect(fLocation.getCol() * scale + 1000, fLocation.getRow() * scale + 250, scale, scale);
      }

    } else { // game over screen
      super.paintComponent(g);
      g.setColor(Color.LIGHT_GRAY);
      g.fillRect(0, 0, xMax + 600, yMax);
      g.setColor(Color.BLACK);

      g.setFont(new Font("Arial", Font.BOLD, 36));
      g.drawString("It only took you " + maze.getMoves() + " moves to escape...", 350, 400);
    }
  }

  public void setBoard() {
    // File name = new File("m1.txt");
    // try {
    // BufferedReader input = new BufferedReader(new FileReader(name));
    // String text;
    // int r = 0;
    // while ((text = input.readLine()) != null) {
    // for (int c = 0; c < text.length(); c++) {
    // if (text.charAt(c) == 35) { // normal wall
    // walls[r][c] = new Wall(new Location(r, c), scale, scale);
    // } else if (text.charAt(c) == 83) { // explorer start position
    // explorer.setLocation(new Location(r, c));
    // } else if (text.charAt(c) == 69) { // end of maze
    // fLocation = new Location(r, c);
    // }
    // }
    // r++;
    // }
    // input.close();
    // } catch (IOException io) {
    // System.err.println("File error");
    // }

    RecursiveDivision recDiv = new RecursiveDivision(row, col);
    recDiv.makeMaze();
    // recDiv.printMaze();
    char[][] board = recDiv.getMaze();
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
        if (board[r][c] == 35) {
          walls[r][c] = new Wall(new Location(r, c), scale, scale);
        } else if (board[r][c] == 83) {
          explorer.setLocation(new Location(r, c));
        } else if (board[r][c] == 69) {
          fLocation = new Location(r, c);
        }
      }
    }

    // creates maze object to store the walls in
    ArrayList<Location> locations = new ArrayList<Location>();
    for (int i = 0; i < walls.length; i++) {
      for (int j = 0; j < walls[i].length; j++) {
        if (walls[i][j] != null)
          locations.add(walls[i][j].getLocation());
      }
    }
    maze = new Maze(locations, fLocation, 0);

    setWalls();
  }

  public void setWalls() {
    ArrayList<int[]> xList = new ArrayList<>();
    ArrayList<int[]> yList = new ArrayList<>();

    wallList.clear();

    for (int i = 0; i < explorer.getSpace(maze); i++) {
      // ceiling
      xList.add(new int[] { i * iterX, xMax - i * iterX, xMax - iterX - i * iterX, iterX + i * iterX });
      yList.add(new int[] { i * iterY, i * iterY, iterY + i * iterY, iterY + i * iterY });
      // floor
      xList.add(new int[] { i * iterX, xMax - i * iterX, xMax - iterX - i * iterX, iterX + i * iterX });
      yList.add(new int[] { yMax - i * iterY, yMax - i * iterY, yMax - iterY - i * iterY, yMax - iterY - i * iterY });
      // right
      if (explorer.isFree(maze, i, 90)) {
        xList.add(new int[] { xMax - i * iterX, xMax - iterX - i * iterX, xMax - iterX - i * iterX, xMax - i * iterX });
        yList.add(new int[] { i * iterY, i * iterY + iterY, yMax - iterY - i * iterY, yMax - i * iterY });
      }
      // left
      if (explorer.isFree(maze, i, -90)) {
        xList.add(new int[] { i * iterX, iterX + i * iterX, iterX + i * iterX, i * iterX });
        yList.add(new int[] { i * iterY, i * iterY + iterY, yMax - iterY - i * iterY, yMax - i * iterY });
      }
      // back wall
      if (i == explorer.getSpace(maze) - 1) {
        xList.add(
            new int[] { xMax - iterX - i * iterX, iterX + i * iterX, iterX + i * iterX, xMax - iterX - i * iterX });
        yList.add(
            new int[] { yMax - iterY - i * iterY, yMax - iterY - i * iterY, iterY + i * iterY, iterY + i * iterY });
      }
    }

    for (int i = 0; i < xList.size(); i++)
      wallList.add(new Wall(xList.get(i), yList.get(i)));

  }

  public void keyReleased(KeyEvent e) {

  }

  public void keyPressed(KeyEvent e) {
    if (!explorer.getLocation().equals(fLocation)) {
      if (e.getKeyCode() == 87) // moves explorer in direction its facing
        explorer.move(maze);
      if (e.getKeyCode() == 65) // turns 90 degrees counterclockwise
        explorer.turn(-90);
      if (e.getKeyCode() == 68) // turns 90 degrees clockwise
        explorer.turn(90);
      if (e.getKeyCode() == 32) {
        if (explorer.getVisibleDist() == 3)
          explorer.setVisibleDist(5);
        else if (explorer.getVisibleDist() == 5)
          explorer.setVisibleDist(3);
      }
      setWalls();
      repaint();
    }
  }

  public void keyTyped(KeyEvent e) {
  }

  public void mouseClicked(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public static void main(String args[]) {
    new MazeProgram();
  }
}
