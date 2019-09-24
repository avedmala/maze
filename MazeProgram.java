import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class MazeProgram extends JPanel implements KeyListener, MouseListener {
  JFrame frame;
  // declare an array to store the maze - Store Wall(s) in the array
  int x = 100, y = 100;

  public MazeProgram() {
    // setBoard();
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
    g.setColor(Color.BLACK); // this will set the background color
    g.fillRect(0, 0, 1000, 800); // since the screen size is 1000x800
                                 // it will fill the whole visible part
                                 // of the screen with a black rectangle

    // drawBoard here!

    g.setColor(Color.WHITE);
    g.drawRect(x + 500, y, 100, 100); // x & y would be used to located your
    // playable character
    // values would be set below
    // call the x & y values from your Explorer class
    // explorer.getX() and explorer.getY()

    // other commands that might come in handy
    // g.setFont("Times New Roman",Font.PLAIN,18);
    // you can also use Font.BOLD, Font.ITALIC, Font.BOLD|Font.Italic
    // g.drawOval(x,y,10,10);
    // g.fillRect(x,y,100,100);
    // g.fillOval(x,y,10,10);
  }

  public void setBoard() {
    // choose your maze design

    // pre-fill maze array here

    File name = new File("maze1.txt");
    int r = 0;
    try {
      BufferedReader input = new BufferedReader(new FileReader(name));
      String text;
      while ((text = input.readLine()) != null) {
        System.out.println(text);
        // your code goes in here to chop up the maze design
        // fill maze array with actual maze stored in text file
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

  public void keyPressed(KeyEvent e) {
    System.out.println(e.getKeyCode());
    if (e.getKeyCode() == 37)
      x -= 10;
    repaint();

  }

  public void keyReleased(KeyEvent e) {
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