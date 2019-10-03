import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MazeProgram extends JPanel implements KeyListener, MouseListener {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  JFrame frame;
  Maze maze;
  Wall[][] walls = new Wall[15][30];
  int x = 100, y = 100;
  Explorer explorer = new Explorer(new Location(0, 0));
  Location fLocation = new Location(0, 0);

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
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, 1000, 800);
    g.setColor(Color.WHITE);

    for (int i = 0; i < walls.length; i++) {
      for (int j = 0; j < walls[i].length; j++) {
        Wall w = walls[i][j];
        if (w != null)
          g.fillRect(w.getCol() * w.getWidth(), w.getRow() * w.getHeight(), w.getWidth(), w.getHeight());
      }
    }

    g.setColor(Color.RED);
    g.fillRect(explorer.getCol() * 30, explorer.getRow() * 30, 30, 30);

    // other commands that might come in handy
    // g.setFont("Times New Roman",Font.PLAIN,18);
    // you can also use Font.BOLD, Font.ITALIC, Font.BOLD|Font.Italic
    // g.drawOval(x,y,10,10);
    // g.fillRect(x,y,100,100);
    // g.fillOval(x,y,10,10);
  }

  public void setBoard() {
    File name = new File("maze3.txt");
    try {
      BufferedReader input = new BufferedReader(new FileReader(name));
      String text;
      int r = 0;
      while ((text = input.readLine()) != null) {
        for (int c = 0; c < text.length(); c++) {
          if (text.charAt(c) == 35) {
            walls[r][c] = new Wall(new Location(r, c), 30, 30);
          } else if (text.charAt(c) == 83) {
            explorer.setLocation(new Location(r, c));
          } else if (text.charAt(c) == 69) {
            fLocation = new Location(r, c);
          }
        }
        r++;
      }
    } catch (IOException io) {
      System.err.println("File error");
    }

    ArrayList<Location> locations = new ArrayList<Location>();
    for (int i = 0; i < walls.length; i++) {
      for (int j = 0; j < walls[i].length; j++) {
        if (walls[i][j] != null)
          locations.add(walls[i][j].getLocation());
      }
    }
    maze = new Maze(locations, fLocation, 0);

    // setWalls();
  }

  public void setWalls() {
    // when you're ready for the 3D part
    // int[] c1X={150,550,450,250};
    // int[] c1Y={50,50,100,100};
    // Wall ceiling1=new Wall(c1X,c1Y,4); //needs to be set as a global!
    // parameters - x coordinates, y coordinates, # of points
  }

  public void keyReleased(KeyEvent e) {

  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 87) {
      explorer.move("u", maze);
    }
    if (e.getKeyCode() == 83) {
      explorer.move("d", maze);
    }
    if (e.getKeyCode() == 65) {
      explorer.move("l", maze);
    }
    if (e.getKeyCode() == 68) {
      explorer.move("r", maze);
    }
    repaint();
    maze.addMove();
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
    MazeProgram app = new MazeProgram();
  }
}