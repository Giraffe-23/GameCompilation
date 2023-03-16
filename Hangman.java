import java.util.Scanner;

public class Hangman extends GameObject {
  private char[] lettersGuessed = new char[26];
  private char[] hiddenWord;
  private boolean[] letterGuessedStatus;
  private int guessNum = 0;
  private int numMisses = 0;
  private int numCorrect = 0;
  private int numSpaces = 0;

  public Hangman(String word) {
    word = word.toLowerCase();
    hiddenWord = word.toCharArray();
    letterGuessedStatus = new boolean[hiddenWord.length];
    for (char c : hiddenWord) {
      if (c == ' ') {
        numSpaces++;
      }
    }
  }

  // Game Loop
  public void runGame(Scanner input) {
    while (!checkWin() && !checkLoss()) {
      System.out.print("\033[H\033[2J");
      printGraphic(numMisses);
      printGuessedLetters();
      printLine();
      System.out.println("What letter would you like to guess?");
      char guessedLetter = input.nextLine().toLowerCase().charAt(0);
      if (checkLetter(guessedLetter)) {
        guessLetter(guessedLetter);
      }
    }
    if (checkWin()) {
      System.out.print("\033[H\033[2J");
      printGraphic(numMisses);
      printGuessedLetters();
      printLine();
      System.out.println("You Win!");
    } else if(checkLoss()){
      System.out.print("\033[H\033[2J");
      printGraphic(numMisses);
      printGuessedLetters();
      for(char c : hiddenWord){
        System.out.print(c);
      }
      System.out.println("\nBetter luck next time");
    }
  }

  // Changes the appropriate value in the letterGuessedStatus array if the guessed
  // letter is in the hidden word
  public void guessLetter(char c) {
    lettersGuessed[guessNum] = c;
    guessNum++;
    boolean isCorrect = false;
    for (int i = 0; i < hiddenWord.length; i++) {
      if (hiddenWord[i] == c) {
        letterGuessedStatus[i] = true;
        numCorrect++;
        isCorrect = true;
      }
    }
    if (!isCorrect) {
      numMisses++;
    }

  }

  public boolean checkLetter(char c) {
    for (int i = 0; i < lettersGuessed.length; i++) {
      if (c == lettersGuessed[i]) {
        return false;
      }
    }
    return true;
  }

  public boolean checkWin() {
    if (numCorrect == hiddenWord.length - numSpaces) {
      return true;
    } else {
      return false;
    }
  }

  public boolean checkLoss() {
    if (numMisses >= 5) {
      return true;
    } else {
      return false;
    }
  }

  public void printGuessedLetters() {
    for (char c : lettersGuessed) {
      if (c != '\u0000')
        System.out.print(c + " ");
    }
    System.out.println();
  }

  public void printLine() {
    for (int i = 0; i < hiddenWord.length; i++) {
      if (hiddenWord[i] == ' ' || letterGuessedStatus[i]) {
        System.out.print(hiddenWord[i] + " ");
      } else {
        System.out.print("_ ");
      }
    }
    System.out.println();
  }

  public void printGraphic(int numMisses) {
    if (numMisses == 0) {
      System.out.println(" ______\n|/     |\n|\n|\n|\n|\n|");
    } else if (numMisses == 1) {
      System.out.println(" ______\n|/     |\n|     ( )\n|\n|\n|\n|");
    } else if (numMisses == 2) {
      System.out.println(" ______\n|/     |\n|     ( )\n|    \\ |\n|\n|\n|");
    } else if (numMisses == 3) {
      System.out.println(" ______\n|/     |\n|     ( )\n|    \\ | /\n|\n|\n|");
    } else if (numMisses == 4) {
      System.out.println(" ______\n|/     |\n|     ( )\n|    \\ | /\n|      |\n|     /\n|");
    } else if (numMisses == 5) {
      System.out.println(" ______\n|/     |\n|     ( )\n|    \\ | /\n|      |\n|     / \\\n|");
    }
  }
}