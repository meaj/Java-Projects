/* Mastermind
 *	An implementation of the game Mastermind in Java
 *	by Kevin Moore
 */
  
import java.util.*;
 
public class Mastermind {
  
  public static void game(boolean bWin, int iTurn, int iGuesses[][], int iBullsCows[][], int iCode[]){
    // Creates the computer's code to be cracked, currently disabled while error checking is implemented. Tested working.
    Random ran = new Random();
    for(int i=0; i<4; i++){
      iCode[i] = ran.nextInt(10);
    }
         
    //KM// Checks for win or lose condition
    while(iTurn < 9 && bWin == false){
      iTurn++;
      getGuess(iTurn, iGuesses);
      bWin = checkGuess(iTurn, bWin, iGuesses, iBullsCows, iCode);
      printScreen( iTurn, iGuesses, iBullsCows);
    }
    printScreen(bWin, iTurn, iGuesses, iBullsCows, iCode);
  }
  
  // This method runs the game loop.
  public static boolean getPlay(boolean bPlay){
    String sGuess;
    char g;
    Scanner in = new Scanner(System.in);
    System.out.print("Would you like to play again? y/n: ");
    sGuess = in.next();
    g = sGuess.charAt(0);
    in.close();
    if (g == 'y' || g== 'Y')
      return true;
    else
      return false;       
  }
  
  // This method reads in the guesses from the user and stores them in the iGuesses array
  public static void getGuess(int iTurn, int iGuesses[][]){
    String sGuess;
    Scanner in = new Scanner(System.in);
    System.out.print("\nThis is turn "+ (iTurn+1));
    System.out.print("\nEnter your guess:");
    sGuess = in.next();
    for(int i=0; i<4; i++){
      char g = sGuess.charAt(i);
      int iG = Character.getNumericValue(g);  
      iGuesses[iTurn][i] = iG; 
    }
    in.close();
  }
  
  // This method checks the guesses against the computer's code and stores the bulls and cows
  public static boolean checkGuess(int iTurn, boolean bWin, int iGuesses[][], int iBullsCows[][], int iCode[]){
    int c=0, b=0;
    for (int i=0; i<4; i++){
      if (iGuesses[iTurn][i] == iCode[i])
          b++;
      else{
        for (int j=0; j<4; j++){
          if (iGuesses[iTurn][j] == iCode [i] && j != i){
            c++;
            break;
          }
        }
      }
    }
    iBullsCows[iTurn][0] = b;
    iBullsCows[iTurn][1] = c;
    if (b==4)
      bWin = true;
    return bWin;
  }
  
  // This method outputs the screen each turn
  public static void printScreen(int iTurn, int iGuesses[][], int iBullsCows[][]){
    System.out.print("\nTurn number: " + (iTurn+1));
    System.out.print("\nGuess this turn: ");
    for (int i=0; i<4; i++){
      System.out.print(iGuesses[iTurn][i] + " ");
    }
    System.out.print("\nExact matches this turn: " + iBullsCows[iTurn][0]);
    System.out.print("\nNear matches this turn: " + iBullsCows[iTurn][1]);
  }
  
  // This method outputs the screen at the end of the game
  public static void printScreen(boolean bWin, int iTurn, int iGuesses[][], int iBullsCows[][], int iCode[]){
    System.out.print("\n\nThe game took " + (iTurn+1) + " turn(s).");
    if(bWin == true)
      System.out.print("\nYou Win! :)");
    else
      System.out.print("\nYou Lost! :("); 
    System.out.print("\nThe Code: ");
    for (int k=0; k<4; k++){
      System.out.print(iCode[k] + " ");
    }
    if(iTurn > 0)
      System.out.print("\n\nAll Guesses: ");
    for (int i=0; i<=(iTurn); i++){
      System.out.print("\n\nTurn number: " + (i+1));
      System.out.print("\nGuess: ");
      for (int j=0; j<4; j++){
        System.out.print(iGuesses[i][j] + " ");
      }
      System.out.print("\nExact matches this turn: " + iBullsCows[i][0]);
      System.out.print("\nNear matches this turn: " + iBullsCows[i][1]);      
    }
    System.out.print("\nThanks for playing!\n");
  }

  public static void main(String[] args){
    int[][] iGuesses = new int[10][4];
    int[][] iBullsCows = new int[10][2];
    int iCode[]= {1,2,2,3};
    int iTurn = (-1);
    boolean bWin = false, bPlay = true;
    
    while (bPlay){    
    // Introduction and explaination of game
    System.out.print("My code is four digits long, care to guess it?\n" +
                     "I'll help you out.\n" +
                     "I'll tell you how many digits you guess exactly right" + 
                     "and how many digits you guessed are out of place.\n" +
                     "You have 10 turns, good luck ;3");

    game(bWin, iTurn, iGuesses, iBullsCows, iCode);
    bPlay = getPlay(bPlay);
    }
  }
}