/* Mancala
 * 		An implementation of Mancala in Java
 * 		By Kevin Moore
 */

 
import java.util.*;
 
public class Mancala {
 
    public static void main(String[] args) {

        //p1 and 2 names
        String p1, p2;
        int hole = 0, turn = 1, currHole = 0, eTurn = 0;
         
        //arrays to hold the total beads in p1 and 2 holes. array position 0-5 are holes, hole 6 is each player's store.;
        int[] p1Holes = {4,4,4,4,4,4,0};
        int[] p2Holes = {4,4,4,4,4,4,0};

         
        //p1 and 2 stores and counters to check if either p1 or 2 side is empty;
        int p1Side = 0, p2Side = 0, p1Test =0, p2Test =0;
         
        Scanner in = new Scanner(System.in);
        //Enter player one and two names
        System.out.print("Welcome to Mancala!\nEnter Player 1 name: ");
        p1 = in.next();
        System.out.print("Enter player 2 name: ");
        p2 = in.next();
        /*
         * this for loop checks for if either p1 or p2's side si empty.
         */
        for (int i =0;i<6;i++)
        {
            p1Side += p1Holes[i];
            p2Side += p2Holes[i];
        }
         
        /*
         * This while loop displays all info for the game board including hole numbering for each player,
         * player names and stores, and current number of beads in each hole.
         */
        while (p1Side != 0 && p2Side != 0)                         
        {
            System.out.printf("\tstore: %d\t%s \nHole #\t", p1Holes[6], p1);
            for (int i=6 ; i>=1 ; i--)
                { System.out.print((i) + " ");}
            System.out.print("\n\n\t");
            for (int i=5 ; i>=0 ; i--)
                { System.out.printf(p1Holes[i] + " ");}
            System.out.print("\n\t");
            for (int i=0 ; i<=5 ; i++)
                { System.out.printf(p2Holes[i] + " ");}
            System.out.print("\n\n");
            System.out.print("Hole #\t");
            for (int i=1 ; i<=6 ; i++)
                { System.out.print((i) + " ");}
            System.out.printf("\n\tstore: %d\t%s", p2Holes[6], p2);
            System.out.print("\n\n");
            if (turn == 1)
                {System.out.printf("%s choose a hole (1-6): ", p1);}
            else
                {System.out.printf("%s choose a hole (1-6): ", p2);}
            hole = in.nextInt();
            /*
             * check to see if a valid hole number was entered.
             * Fixed 9/6, caused out of bounds error if 0 was entered
             */
            while (hole <1 || hole >6)
            {
                System.out.println("Invalid choice. Choose 1-6: ");
                hole = in.nextInt();
            }
            /*
             * all beads from the chosen hole of current player
             * are deposited into succeeding holes one at a time in counter-clockwise order.
             *
             * If the last bead falls in the store of that player they get an extra turn.
             */
                    //collect beads from hole of players choice
            if (turn==1)
                currHole = p1Holes[hole-1];
            else
                currHole = p2Holes[hole-1];
                //set number of beads in chosen hole to 0
            if (turn==1)
                p1Holes[hole-1] = 0;
            else
                p2Holes[hole-1] = 0;
                //deposit beads in counter-clockwise order including that player's store.
            while (currHole >0)
            {
                outerloop:
                for (int i = hole;i<=6;i++)
                {
                    //if out of beads break out
                    if (currHole < 1)
                    {
                        break outerloop;
                    }
                    
                    //go to next hole
                    if (turn==1)
                        p1Holes[i]++;
                    else
                        p2Holes[i]++;
                    
                    // checks to see if the last bead is to be deposited in an empty hole on the current players side, and if so captures
                    // the beads opposite the hole and the last bead deposited.
                    if (currHole == 1 && i != 6 && (p1Holes[i] == 1 || p2Holes[i] == 1)){
                      if (turn == 1){
                        System.out.println("\nCaptured " + (p2Holes[5-i]) +  "of Player 2's pieces");
                        p1Holes[6] += 1;
                        p1Holes[6] += p2Holes[5-i];
                        p2Holes[5-i] = 0;
                        p1Holes[i] = 0;
                      }
                      else{
                        System.out.println("\nCaptured " + (p1Holes[5-i]) + " of Player 1's pieces");
                        p2Holes[6] += 1;
                        p2Holes[6] += p1Holes[5-i];
                        p1Holes[5-i] = 0;
                        p2Holes[i] = 0;
                      }
                    }
                   
                    //subtract one from beads in hand
                    currHole--;
                                        
                    // checks to see if the last bead is deposited in the store, if so grants extra turn (eTurn)
                    if (i==6 && currHole == 0)
                      eTurn = 1;
                    else
                      eTurn = 0;

                 }
            //loops through opposing player holes but skips their store.
                outerloop:
                for (int j = 0; j<=5; j++)
                {             
                    if (currHole < 1)
                    {
                        break outerloop;
                    }
                    if (turn==1)
                        p2Holes[j]++;
                    else
                        p1Holes[j]++;
                    
                    if (j==5)
                      hole = 0;
                                         
                    currHole--;
                 
                } 
            }

            //set turn to player two
            //after checking for extra turns
            if (eTurn ==1 && turn ==1)
                turn =1;
            else if (eTurn ==1 && turn ==2)
                turn =2;
            else if (turn ==1)
                turn = 2;
            else
                turn = 1;
            
            p1Test = 0;
            p2Test = 0;            
            for (int i =0;i<=5;i++){
              p1Test += p1Holes[i];
              p2Test += p2Holes[i];
            }    
            p1Side = p1Test;
            p2Side = p2Test;
            
            if (eTurn == 1 && (p1Side != 0 && p2Side != 0))
              System.out.println("\nBonus Turn!\n");
        }
        p1Side =0;
        p2Side =0;
        for (int i =0;i<=6;i++){
          p1Side += p1Holes[i];
          p2Side += p2Holes[i];
        }
        if (p1Side == p2Side)
          System.out.println("\nTie! Good match. " + p1Side + " points to " + p2Side + " points!");        
        else if (p1Side > p2Side)
          System.out.println("\nPlayer: " + p1 + " wins! " + p1Side + " points to " + p2Side + " points!");
        else if (p2Side > p1Side)
          System.out.println("\nPlayer: " + p2 + " wins! " + p2Side + " points to " + p1Side + " points!");
        else
          System.out.println("\nSomething went horribly wrong!");
        in.close(); 

    }   
}