/**
 *
 * @author Saige Madden
 * @version 1.0
 * @since 2/12/19
 * Hangman.java
 *
 * This program allows a user to play Hangman with the computer.
 * The program will display a string of asterisks then asks the user 
 * to input their letter guess. The user gets to have 7 incorrect tries 
 * before the game ends and the correct title is revealed.
 * The program will then display the user's entered letter in the string 
 * of asterisks if it is a part of the hidden movie title. If it is not in
 * the title, the user's tries left will go down. The game ends when either 
 * the user guesses all of the letters of the movie title or runs out of tries.
 */
import java.util.*;
import java.lang.*;

public class Hangman
{
   static String movieList[] = {"Star Wars: The Last Jedi","Alita: Battle Angel","Ocean's Eight","The Avengers","Wonder Woman",
    "The Lion King","Die Hard","Black Panther","The Breakfast Club","The Martian",
    "Lady and the Tramp","Bring It On", "Bohemian Rhapsody","Mary Queen of Scots","The Princess Bride"};

   public static void main(String[] args)
   {
      Random gen = new Random();
      Scanner sc = new Scanner(System.in);
      boolean done, again, in;
      String guess;
      int wrongCounter;
      again = true;

      while(again==true)	//while the user would like to play again
      {

        done = false;		//the word has not been guessed (the user is not done guessing)
        wrongCounter = 0;	//reset wrongCounter
        in = false;			//the guess is not in the hidden title

        String movie = movieList[gen.nextInt(movieList.length)];  			//selects a random movie
        StringBuilder currentGuess = new StringBuilder(movie.length());		//creates a string builder the same length as the selected movie
        
        for(int i=0; i<movie.length(); i++) //prints out the string builder with * at every letter 
        {
          char c = movie.charAt(i);
          if (Character.isLetter(c)) 		//if the character is a letter then put *, if not then show the character
            {
              currentGuess.append("*");
            } else 
            {
              currentGuess.append(c);
            }
        }

        System.out.println("\n"+currentGuess); 						//currently empty (*****)
        System.out.println("Tries left: "+(7-wrongCounter));		//prints how many tries the user has left 
        System.out.print("Enter your letter guess: ");				


        while(done == false) 			//while word still hasn't been solved OR 7 guesses haven't been made
        {
          guess = sc.next(); 			//saves the guess inputted by the user

          for(int i=0; i<guess.length(); i++) 				//this loop records whether the guess is in the movie title or not, and if it is, it moves on, if it is not, it increments wrongCounter
          {
            for(int z=0; z<movie.length(); z++)				//loops through movie to match characters to characters from guess 
            {
              if (guess.charAt(i) == movie.charAt(z) || 
              	guess.charAt(i) == movie.charAt(z)+32 || 
              	guess.charAt(i)==movie.charAt(z)-32){		//accounts for upper and lower case letters
                in = true;									//if the letter is in the movie title then in is true and break the loop
                break;
              } else {
                in = false;
              }
            }
            if(in==false){		//if the letter is not in the movie then that is a wrong answer and the counter goes up
              wrongCounter++;
            }
          }
          

          for(int i=0; i<movie.length(); i++) 				//this loop replaces the * in the string builder currentGuess to the corresponding letter of the guess is correct
          {
            for(int z=0; z<guess.length(); z++)				//loops through movie to match characters to characters from guess 
            {
              String n = Character.toString(movie.charAt(i));	//changes the character in the movie title to a string so it can add that string to the string builder
              if(guess.charAt(z)==movie.charAt(i)|| 		//accounts for upper and lower case - FIND BETTER WAY (.UPPER METHOD??)
                guess.charAt(z)==movie.charAt(i)+32 || 				//if the character in the string entered equals the character 
                guess.charAt(z)==movie.charAt(i)-32) 				//in the movie then it will reveal the movie character
              {
                currentGuess.replace(i, i+1, n);
              }
            }
          }
          

          for(int i=0; i<movie.length(); i++)				//loops through movie
          {
            if (currentGuess.charAt(i)==movie.charAt(i))	//if the characters at the matching indeces are the same then the user has guessed the word and is done
              {
                done = true;
                
              } else if (wrongCounter>=7) {					//if the user has has 7 wrong tries then the game is over
                done=true;
                System.out.println("\n"+currentGuess); 		//prints the string builder with both * and any characters the user has already guessed
                System.out.println("The correct answer was: " +movie);
                break;
              } else {								//the user has not guessed the movie and has not has seven wrong tries, so the user gets to guess again
                done = false;
                System.out.println("\n"+currentGuess); 		//prints the string builder with both * and any characters the user has already guessed
                System.out.print("Tries left: "+(7-wrongCounter)+"\nEnter your letter guess: ");
                break;
              }
          }
          if(done==true) 		//if the user has guessed the movie or lost, asks if user would like to play again
          {
              if(wrongCounter<7){		//if the user guessed the movie and did not lose
                System.out.println("\nYou win! The movie title was "+movie);
              }
              System.out.print("Would you like to play again? (1=Yes, 0=No): ");
              int yn = sc.nextInt();
              if(yn == 0)
              {
                again = false;	//if they say no then again is false, otherwise again stays true and the program runs again
                System.out.println("\nThanks for playing!\n");
              }
          }         
        }
      }
      
   }
}
