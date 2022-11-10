import java.util.Scanner;

public class Hangman {
  public static void main(String[] args) {
    String answer = Words.getWord();
    Scanner in = new Scanner(System.in);
    String lettersUsed = "";
    boolean[] revealed = new boolean[answer.length()];
    int livesLeft = 7;
    String letter;

    while (livesLeft > 0) {
      System.out.print("Guess a letter: ");
      letter = in.nextLine();

      for (int i = 0; i < revealed.length; i++) {
        if (answer.charAt(i) == letter.charAt(0)) {
          revealed[i] = true;
        }
      }

      if (answer.indexOf(letter) == -1) {
        livesLeft--;
        lettersUsed += letter + " ";
      }

      for (int i = 0; i < answer.length(); i++) {
        if (revealed[i]) {
          System.out.print(answer.charAt(i) + " ");
        } else {
          System.out.print("- ");
        }
      }

      printFooter(livesLeft, lettersUsed);

      if (hasWon(revealed)) {
        break;
      }
    }

    printFinalMessage(livesLeft, answer);
    in.close();
  }

  public static boolean hasWon(boolean[] revealed) {
    boolean allTrue = true;
    for (boolean element : revealed) {
      if (!element) {
        allTrue = false;
      }
    }
    return allTrue;
  }

  public static void printFooter(int livesLeft, String lettersUsed) {
    System.out.println();
    System.out.println(livesLeft + " lives left.");
    System.out.println("Used letters: " + lettersUsed);
    System.out.println();
  }

  public static void printFinalMessage(int livesLeft, String answer) {
    if (livesLeft == 0) {
      System.out.println("You loose! The word was '" + answer + "'.");
    } else {
      System.out.println("Nice! You guessed the word '" + answer + "'.");
    }
  }
}
