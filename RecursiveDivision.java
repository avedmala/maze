import java.util.*;

public class RecursiveDivision {

  int rows;
  int cols;
  int act_rows;
  int act_cols;
  char[][] board;

  public RecursiveDivision(int row, int col) {
    rows = row * 2 + 1;
    cols = col * 2 + 1;
    act_rows = row;
    act_cols = col;
    board = new char[rows][cols];
  }

  public void makeMaze() {
    // set the maze to empty
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        board[i][j] = ' ';
      }
    }

    // make the outer walls
    for (int i = 0; i < rows; i++) {
      board[i][0] = '#';
      board[i][cols - 1] = '#';
    }
    for (int i = 0; i < cols; i++) {
      board[0][i] = '#';
      board[rows - 1][i] = '#';
    }

    makeMaze(0, cols - 1, 0, rows - 1);
    makeExit();
  }

  private void makeMaze(int left, int right, int top, int bottom) {
    int width = right - left;
    int height = bottom - top;

    // makes sure there is still room to divide, then picks the best direction to
    // divide into
    if (width > 2 && height > 2) {

      if (width > height)
        divideVertical(left, right, top, bottom);

      else if (height > width)
        divideHorizontal(left, right, top, bottom);

      else if (height == width) {
        Random rand = new Random();
        boolean bool = rand.nextBoolean();

        if (bool)
          divideVertical(left, right, top, bottom);
        else
          divideHorizontal(left, right, top, bottom);
      }
    } else if (width > 2 && height <= 2) {
      divideVertical(left, right, top, bottom);
    } else if (width <= 2 && height > 2) {
      divideHorizontal(left, right, top, bottom);
    }
  }

  private void divideVertical(int left, int right, int top, int bottom) {
    Random rand = new Random();

    // find a random point to divide at
    // must be even to draw a wall there
    int divide = left + 2 + rand.nextInt((right - left - 1) / 2) * 2;

    // draw a line at the halfway point
    for (int i = top; i < bottom; i++)
      board[i][divide] = '#';

    // get a random odd integer between top and bottom and clear it
    int clearSpace = top + rand.nextInt((bottom - top) / 2) * 2 + 1;

    board[clearSpace][divide] = ' ';

    makeMaze(left, divide, top, bottom);
    makeMaze(divide, right, top, bottom);
  }

  private void divideHorizontal(int left, int right, int top, int bottom) {
    Random rand = new Random();

    // find a random point to divide at
    // must be even to draw a wall there
    int divide = top + 2 + rand.nextInt((bottom - top - 1) / 2) * 2;
    if (divide % 2 == 1)
      divide++;

    // draw a line at the halfway point
    for (int i = left; i < right; i++) {
      board[divide][i] = '#';
    }

    // get a random odd integer between left and right and clear it
    int clearSpace = left + rand.nextInt((right - left) / 2) * 2 + 1;

    board[divide][clearSpace] = ' ';

    // recursion for both parts of the newly split section
    makeMaze(left, right, top, divide);
    makeMaze(left, right, divide, bottom);
  }

  public void makeExit() {
    Random rand = new Random();
    int exit_row = rand.nextInt(act_rows - 1) * 2 + 1;
    board[exit_row][cols - 1] = 'E';
  }

  public void printMaze() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }

  public char[][] getMaze() {
    return board;
  }

}