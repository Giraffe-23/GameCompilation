import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.print("\033[H\033[2J");
    // Create and run game
    Scanner input = new Scanner(System.in);
    System.out.println("1: Hangman 2: TicTacToe");
    int choice = input.nextInt();
    if(choice % 2 == 1){
      
      System.out.print("\033[H\033[2J");
      System.out.println("What is the secret word?");
      String word = input.nextLine();
       GameObject gameObj = new Hangman(word);
        gameObj.runGame(input);
    } else{
      System.out.print("\033[H\033[2J");
      GameObject gameObj = new TicTacToe();
      gameObj.runGame(input);
    }
  }
}