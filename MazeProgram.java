import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MazeProgram extends JPanel implements KeyListener, MouseListener {
  private static final long serialVersionUID = 1L;
  JFrame frame;
  Maze maze;
  Wall[][] walls = new Wall[999][999]; // don't have to guess the size of the maze
  ArrayList<Wall> wallList = new ArrayList<Wall>();
  Explorer explorer = new Explorer(new Location(0, 0), 90);
  Location fLocation = new Location(0, 0);
  int scale = 25;

  public MazeProgram() {
    setBoard();
    frame = new JFrame();
    frame.add(this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 800);
    frame.setVisible(true);
    frame.addKeyListener(this);
    // this.addMouseListener(this); //in case you need mouse clicking
  }

  public void paintComponent(Graphics g) {
    if (false) { // 2d stuff
      super.paintComponent(g);
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, 1000, 800);
      g.setColor(Color.WHITE);

      // loops through the 2d array and draws each wall
      for (int i = 0; i < walls.length; i++) {
        for (int j = 0; j < walls[i].length; j++) {
          Wall w = walls[i][j];
          if (w != null)
            g.fillRect(w.getCol() * w.getWidth(), w.getRow() * w.getHeight(), w.getWidth(), w.getHeight());
        }
      }

      // draws explorer
      g.setColor(Color.RED);
      g.fillOval(explorer.getCol() * scale, explorer.getRow() * scale, scale, scale);

      // draw finish
      g.setColor(Color.CYAN);
      g.fillRect(fLocation.getCol() * scale, fLocation.getRow() * scale, scale, scale);

    } else if (!explorer.getLocation().equals(fLocation)) { // 3d stuff
      super.paintComponent(g);

      for (Wall w : wallList) {
        Polygon p = new Polygon();
        for (int i = 0; i < w.getX().length; i++) {
          p = new Polygon(w.getX(), w.getY(), w.getX().length);
        }
        g.setColor(Color.RED);
        g.drawPolygon(p);

        // if (count == 1 || count == 3)
        // g.setColor(Color.DARK_GRAY);
        // else if (count > 3)
        // g.setColor(Color.LIGHT_GRAY);
        // else
        g.setColor(Color.GRAY);

        g.fillPolygon(p);
      }
    } else { // game over screen
      super.paintComponent(g);
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, 1000, 800);
      g.setColor(Color.WHITE);

      g.setFont(new Font("Arial", Font.BOLD, 36));
      g.drawString("It only took you " + maze.getMoves() + " moves to escape...", 750, 400);
    }
  }

  public void setBoard() {
    File name = new File("m1.txt");
    try {
      BufferedReader input = new BufferedReader(new FileReader(name));
      String text;
      int r = 0;
      while ((text = input.readLine()) != null) {
        for (int c = 0; c < text.length(); c++) {
          if (text.charAt(c) == 35) { // normal wall
            walls[r][c] = new Wall(new Location(r, c), scale, scale);
          } else if (text.charAt(c) == 83) { // explorer start position
            explorer.setLocation(new Location(r, c));
          } else if (text.charAt(c) == 69) { // end of maze
            fLocation = new Location(r, c);
          }
        }
        r++;
      }
      input.close();
    } catch (IOException io) {
      System.err.println("File error");
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

    int xMax = 1000;
    int yMax = 800;
    int iter = 50;
    int lvl = 3;

    // ceiling
    for (int i = 0; i < lvl; i++) {
      int[] x = { i * iter, xMax - i * iter, xMax - iter - i * iter, iter + i * iter };
      int[] y = { i * iter, i * iter, iter + i * iter, iter + i * iter };
      wallList.add(new Wall(x, y));
    }

    // floor
    for (int i = 0; i < lvl; i++) {
      int[] x = { i * iter, xMax - i * iter, xMax - iter - i * iter, iter + i * iter };
      int[] y = { yMax - i * iter, yMax - i * iter, yMax - iter - i * iter, yMax - iter - i * iter };
      wallList.add(new Wall(x, y));
    }

    // right wall
    for (int i = 0; i < lvl; i++) {
      int[] x = { xMax - i * iter, xMax - iter - i * iter, xMax - iter - i * iter, xMax - i * iter };
      int[] y = { i * iter, i * iter + iter, yMax - iter - i * iter, yMax - i * iter };
      wallList.add(new Wall(x, y));
    }

    // left wall
    for (int i = 0; i < lvl; i++) {
      int[] x = { i * iter, iter + i * iter, iter + i * iter, i * iter };
      int[] y = { i * iter, i * iter + iter, yMax - iter - i * iter, yMax - i * iter };
      wallList.add(new Wall(x, y));
    }

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