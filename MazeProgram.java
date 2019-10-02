import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class MazeProgram extends JPanel implements KeyListener, MouseListener {
  JFrame frame;
  Wall[][] walls = new Wall[15][30];
  int x = 100, y = 100;
  Explorer explorer = new Explorer(new Location(0, 0));
  boolean up = false;
  boolean left = false;
  boolean right = false;
  boolean down = false;

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
        if (w != null) {
          g.fillRect(w.getCol() * w.getWidth(), w.getRow() * w.getHeight(), w.getWidth(), w.getHeight());
        }
      }
    }

    // x & y would be used to located your playable character
    // values would be set below
    // call the x & y values from your Explorer class
    // explorer.getX() and explorer.getY()
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
          }
        }
        r++;
      }
    } catch (IOException io) {
      System.err.println("File error");
    }

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
    if (e.getKeyCode() == 87) {
      up = false;
    }
    if (e.getKeyCode() == 83) {
      down = false;
    }
    if (e.getKeyCode() == 65) {
      left = false;
    }
    if (e.getKeyCode() == 68) {
      right = false;
    }
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 87) {
      up = true;
    }
    if (e.getKeyCode() == 83) {
      down = true;
    }
    if (e.getKeyCode() == 65) {
      left = true;
    }
    if (e.getKeyCode() == 68) {
      right = true;
    }
  }

  // public void run() {
  // while (gameOn) {
  // if (up) {
  // explorer.move("u", walls);
  // }
  // if (down) {
  // explorer.move("d", walls);
  // }
  // if (left) {
  // explorer.move("l", walls);
  // }
  // if (right) {
  // explorer.move("r", walls);
  // }
  // try {
  // thread.sleep(5);
  // repaint();
  // } catch (InterruptedException e) {
  // }
  // }
  // }

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