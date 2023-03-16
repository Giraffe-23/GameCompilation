import java.util.Scanner;

public class TicTacToe extends GameObject {
  private int turn = 0;
  private String[][] board = new String[3][3];

  public TicTacToe() {
    // change your board to have "-" in each cell
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = "-   ";
      }
    }
  }

  public void runGame(Scanner input) {
    printBoard();
    while (checkWin() == false && getTurn() < 9) {
      System.out.println(getTurnType() + "'s Turn: Please input row and column for next move");
      int row = input.nextInt();
      int col = input.nextInt();
      takeTurn(row, col);
      System.out.print("\033[H\033[2J");
      printBoard();
    }
    input.close();
    if (getTurn() >= 9 && !checkWin()) {
      System.out.println("It's a Tie!");
    } else {
      if (getTurn() % 2 == 1) {
        System.out.println("X Wins!!");
      } else {
        System.out.println("O Wins!!");
      }
    }
  }

  // this method returns the current turn
  public int getTurn() {
    return turn;
  }

  public String getTurnType() {
    if (turn % 2 == 0) {
      return "X";
    } else {
      return "O";
    }
  }

  /*
   * This method prints out the board array on to the console
   */
  public void printBoard() {
    System.out.println("  0   1   2");
    for (int i = 0; i < board.length; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }

  // This method returns true if space row, col is a valid space
  public boolean pickLocation(int row, int col) {
    return board[row][col].equals("-   ");
  }

  // This method places an X or O at location row,col based on the int turn
  public void takeTurn(int row, int col) {
    if (pickLocation(row, col)) {
      if (turn % 2 == 0) {
        board[row][col] = "X   ";
      } else {
        board[row][col] = "O   ";
      }
      turn++;
    } else {
      System.out.println("Invalid Square :3c");
    }
  }

  // This method returns a boolean that returns true if a row has three X or O's
  // in a row
  public boolean checkRow() {
    for (int i = 0; i < board.length; i++) {
      if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && board[i][0].equals("-   ") == false) {
        return true;
      }
    }
    return false;
  }

  // This method returns a boolean that returns true if a col has three X or O's
  public boolean checkCol() {
    for (int i = 0; i < board.length; i++) {
      if (board[0][i].equals(board[1][i]) && board[2][i].equals(board[0][i]) && board[0][i].equals("-   ") == false) {
        return true;
      }
    }
    return false;
  }

  // This method returns a boolean that returns true if either diagonal has three
  // X or O's
  public boolean checkDiag() {
    if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && board[0][0].equals("-   ") == false) {
      return true;
    }
    if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && board[2][0].equals("-   ") == false) {
      return true;
    }
    return false;
  }

  // This method returns a boolean that checks if someone has won the game
  public boolean checkWin() {
    return checkRow() || checkCol() || checkDiag();
  }
}
