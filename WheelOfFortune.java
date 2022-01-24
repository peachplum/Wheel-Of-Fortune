/**
 * Name: Kelly Chen
 * Date: Nov 19, 2018
 * Description: A program based on the game Wheel of Fortune and lasts for 3 rounds. It is played between the user and a computer.
 */

import java.util.Scanner;
import java.util.Random;

public class WheelOfFortune
{
  public static void printPuzzle (char[] emptyPuzzle) throws Exception {
    System.out.println("The puzzle is:");
    
    for (int j = 0; j < emptyPuzzle.length; j++) {
      System.out.print(emptyPuzzle[j] + " ");
    } 
    
    Thread.sleep(1000);
  }
  
  public static int spinWheel(int topDollar)
  {
    int landedSpace, result;
    int[] wheel = {500, 500, 500, 550, 550, 550, 600, 600, 600, 600, 650, 650, 700, 700, 700, 800, 800, 900, 900, topDollar, 0, 0, 1}; // create a list for every spot on the wheel
    Random ranGen = new Random();
    result = (ranGen.nextInt(23)); // randomly generate a number from 0 - 23
    landedSpace = wheel[result]; // set variable as the value on the wheel at the randomly generated number
    return landedSpace; // return the value on the wheel
  }
  
  public static int countLetters(char[] emptyPuzzle, char letter) {
    int counter = 0;
    for (int i = 0; i < emptyPuzzle.length; i++){ // repeat for as many letters there are in the puzzle
      if ((emptyPuzzle[i])==(letter)){ // if a character in the puzzle is the same as the letter guessed, increment the counter by one
        counter++;
      }
    }
    return counter; // return the number of letters guessed there are in the puzzle
  }
  
  public static String selectPuzzle(String previousPuzzle1, String previousPuzzle2)
  {
    String[] books = {"jane eyre", "of mice and men", "to kill a mockingbird", "the great gatsby", "the catcher in the rye", "wuthering heights", "animal farm", "pride and prejudice", "little women", "lord of the flies", "a wind in the willows", "invisible man", "the hobbit", "lord of the rings", "howards end", "the little prince", "anne of green gables", "crime and punishment", "treasure island", "charlotte's web", "oliver twist", "the old man and the sea", "heart of darkness", "a tree grows in brooklyn", "great expectations", "nineteen-eighty four", "ulysses", "alice's adventures in wonderland"};
    String puzzle;
    Random ranGen = new Random();
    do 
    {
      int num = (ranGen.nextInt(28)); // randomly generate a number from 0 - 28, the number of books in the list
      puzzle = books[num]; // let the puzzle be the book that was randomly generated
    } while (puzzle.equals(previousPuzzle1) || puzzle.equals(previousPuzzle2)); // repeat if the puzzle is the same as the previous two puzzles
    return puzzle; 
  }
  
  public static char[] updatePuzzle(char[] emptyPuzzle, String puzzle, char letter) 
  {
    for (int i = 0; i < puzzle.length(); i++) { // repeat for as many characters as there are in the puzzle
      if (puzzle.charAt(i) == letter){ // if a character in the puzzle is the same as the letter gussed
        emptyPuzzle[i] = letter; // set the same character in the empty puzzle to the letter
      }
    }
    return emptyPuzzle;
  }
  
  public static char computerGuess(String consonantsUsed) 
  {
    char[] guessCommon = {'r', 's', 't', 'l', 'n'}; // a list for the commonly appearing letters
    char[] otherGuess = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'v', 'w', 'x', 'y', 'z'}; // list for the other letters
    char consonant;
    int result, counter;
    counter = 0;
    
    do 
    {
      Random ranGen = new Random();
      result = ranGen.nextInt(4); // randomly generate a number from 0 - 4
      consonant = guessCommon[result]; // set consonant as the consonant in the list at the randomly generated number 
      
      for (int i = 0; i < consonantsUsed.length(); i++) { // repeat for as long as the number of used consonants
        if (consonant == consonantsUsed.charAt(i)) { // if the consonant is in the list of consonants used
          consonant = '0';
          counter++;
          break;
        } else if (i == (consonantsUsed.length() - 1)) { // otherwise if the end of the list is reached
          counter = 10;
          break;
        }
      }
    } while (counter != 10); // repeat 10 times to get a common consonant
    
    if (consonant == '0'){ // if the consonant is not in the used consonants list
      
      do
      {
        Random ranGen = new Random();
        result = ranGen.nextInt(17); // randomly generate a number from 0 - 17
        consonant = otherGuess[result]; // set consonant as the letter in the other consonants list at the randomly generated number
        
        for (int i = 0; i < consonantsUsed.length(); i++) { // repeat as long as the number of used consonants
          if (consonant == consonantsUsed.charAt(i)) { // if the consonant is in the list of used consonants
            break; // break the loop to stop looking in the used consonants list
          } else if (i == (consonantsUsed.length() - 1)){ // if the end of the list is reached
            counter = 0; // to break the loop to stop generating random consonants
            break; // break the loop to stop looking in the used consonants list
          } 
        }
      } while (counter > 1); 
    } 
    
    return consonant; 
  }
  
  public static char compBuyVowel(int compRoundMoney, String vowelsUsed) {
    char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    char vowel;
    boolean repeat = true;
    Random ranGen = new Random();
    int result = ranGen.nextInt(4); // randomly generate a number from 0 - 4
    
    if (result == 1 && compRoundMoney >= 250) { // let the computer have a 1/5 chance to buy a vowel if its money is greater than -$250
      
      do
      {
        result = ranGen.nextInt(4); // randomly generate a number from 0 - 4, the number of vowels
        vowel = vowels[result]; // set vowel as the vowel in the list at the randomly generated number
        
        for (int i = 0; i < vowelsUsed.length(); i++) { // repeat for as long as the length of the used vowels list
          if (vowel == vowelsUsed.charAt(i)) { // if the vowel in the used vowels list
            break; // stop repeating to stop looking in the used consonants list
          } else if (i == (vowelsUsed.length() - 1)){
            repeat = false; // to break the loop to stop generating random vowels
            break; // break the loop to stop looking in the used vowels list
          }
        }
      } while (repeat == true); 
    } else {
      vowel = ' '; // otherwise don't generate a vowel
    }
    return vowel;
  }
  
  public static void main(String[] args) throws Exception
  {
    Scanner scan = new Scanner(System.in);
    
    String rules, puzzle, guess, consonantsUsed, vowelsUsed, playAgain, previousPuzzle1, previousPuzzle2, userInput;
    int space, topDollar, compRoundMoney, compTotalMoney, roundMoney, totalMoney, roundCounter, turnCounter, counter, numOfLetters, option;
    char consonant, vowel;
    boolean win, repeat;
    
    do
    {
      // initialize variables to reset after each game
      
      compTotalMoney = 0; 
      totalMoney = 0;
      roundCounter = 1;
      counter = 0;
      previousPuzzle1 = "";
      previousPuzzle2 = "";
      rules = "";
      consonant = ' ';
      option = 3;
      
      do 
      {
        try { // start of try-catch block
          
          System.out.println("Would you like to learn how to play? (y/n)");
          rules = scan.next();
          
          if (!rules.equalsIgnoreCase("y") && !rules.equalsIgnoreCase("n")) { // if the user inputs an invalid option, they must try again
            System.out.println("Invalid, try again.");
            System.out.println();
            scan.nextLine(); // clear out invalid input
          } else if (rules.equalsIgnoreCase("y")) { 
            
            // output rules to user
            
            System.out.println("The theme of this game is classical book titles and you must try to guess these titles before your opponent does. First you will spin a wheel to determine a dollar value, then guess a consonant.");
            Thread.sleep(3000); // add a delay in code for 3000 millisecond or 3 seconds
            System.out.println("If the consonant is in the title, it will appear and you will receive the amount of money you got on the wheel times the amount of times the consonant appeared on the board.");
            Thread.sleep(3000);
            System.out.println("If you guess a letter right you keep spinning and guessing, but if  you guess it wrong, it�s your opponent�s turn.");
            Thread.sleep(2000);
            System.out.println("After guessing a letter right, you have the chance to buy a vowel for $250. If the vowel is in the title, you can continue to buy, spin again, or try to guess the word.");
            Thread.sleep(3000);
            System.out.println("If the vowel isn�t in the title, you lose your turn. If you think you know the title, you can guess it.");
            Thread.sleep(2000);
            System.out.println("If you correctly guess the word, you will keep the money you earned that round.");
            Thread.sleep(2000);
            System.out.println("If you win the round but won less than $1000, $1000 will be rewarded to you as total game money.");
            Thread.sleep(2000);
            System.out.println("You will be playing against a computer for 3 rounds and at the end, whoever has the most money wins.");
            Thread.sleep(2000);
          } 
        }
        catch (Exception e) { // catches invalid data types which are thrown by java
          System.out.println("Invalid, try again.");
          scan.nextLine(); // clear out erroneous input    
        } 
      } while (!rules.equalsIgnoreCase("y") && !rules.equalsIgnoreCase("n"));
      
      do
      {
        puzzle = selectPuzzle(previousPuzzle1, previousPuzzle2); // set puzzle by calling on method selectPuzzle
        
        // create a list for the puzzle with empty spaces for the letters
        
        char[] emptyPuzzle = new char[puzzle.length()]; 
        
        for (int i = 0; i < puzzle.length(); i++) {
          if (puzzle.charAt(i) != (' ') && puzzle.charAt(i) != ('-') && puzzle.charAt(i) != ('\'')) { // as long as the character is a letter
            emptyPuzzle[i] = '_'; // set the letter to an underscore
          } else if (puzzle.charAt(i) == ('-')){
            emptyPuzzle[i] = '-'; // set the hyphens as the same
          } else if (puzzle.charAt(i) == ('\'')) {
            emptyPuzzle[i] = '\''; // set the apostrophes as the same
          }
        }
        
        System.out.println();
        System.out.println("Round " + roundCounter + " begins."); // output the round number
        Thread.sleep(1000);
        
        // initialize the variables so they reset after each round
        
        consonantsUsed = " ";
        vowelsUsed = " ";
        win = false;
        repeat = true;
        turnCounter = 1;
        roundMoney = 0;
        compRoundMoney = 0;
        
        do
        {
          System.out.println();
          
          // output the puzzle with no letters filled in
          
          printPuzzle(emptyPuzzle);
          
          Thread.sleep(1000);
          
          System.out.println(); // output a line after puzzle
          System.out.println(); // output a separation line
          System.out.println("///////////////////////////////////////////////////////////////////"); // output a separator
          System.out.println(); // output a separation line
          Thread.sleep(1000);
          System.out.println("It is your turn.");
          Thread.sleep(1000);
          
          do 
          {
            repeat = true; // reset repeat to true every turn
            
            System.out.println();
            System.out.println("You are spinning the wheel.");
            Thread.sleep(1000);
            
            // set the round counter based on the round
            
            if (roundCounter == 1) { // if it is the first round
              topDollar = 2500; // set the top amout as $2500
            } else if (roundCounter == 2) { // if it is the second round
              topDollar = 3000; // set the top amount as $3000
            } else { // if it is the third round
              topDollar = 5000; // set the top amount as $5000
            }
            
            space = spinWheel(topDollar); // set the space that was landed on the wheel by calling the method spinWheel
            
            if (space == 0) { // if the space is zero, it represents a bankrupt space
              System.out.println("You landed on bankrupt and lost all your money from this round, you lost your turn.");
              Thread.sleep(2000);
              roundMoney = 0;
              turnCounter++; // add one to the turn counter
              break; // end the player's turn 
            } else if (space == 1) { // if the space is one, it represents a lose a turn space
              System.out.println("You landed on lose a turn and have lost your turn.");
              Thread.sleep(1000);
              turnCounter++; // add one to the turn counter
              break; // end the player's turn
            } else {
              System.out.println("You have landed on $" + space + ".");
              Thread.sleep(1000);
              System.out.println(); // output a separation line
              System.out.println("The consonants that have been used this round are:" + consonantsUsed);
              Thread.sleep(2000);
              System.out.println(); // output a separation line
              
              do
              {
                System.out.println("What consonant would you like to guess? (Or to guess the word, enter '0')");
                userInput = scan.next(); 
                consonant = Character.toLowerCase(userInput.charAt(0)); // change user input to a lowercase character 
                
                if (consonant == 'a' || consonant == 'e' || consonant == 'i' || consonant == 'o' || consonant == 'u') {
                  System.out.println("Invalid, try again.");
                  scan.nextLine();
                }
              } while (consonant == 'a' || consonant == 'e' || consonant == 'i' || consonant == 'o' || consonant == 'u'); // repeat as long as user enters a consonant
              
              if (consonant == '0') { // if player entered '0' let the player guess the puzzle
                scan.nextLine();
                
                System.out.println("Enter your guess:");
                guess = scan.nextLine();
                
                if (guess.equalsIgnoreCase(puzzle)) { // if the player guessed the puzzle correctly
                  System.out.println("You guessed the title correctly, you won the round.");
                  Thread.sleep(2000);
                  roundCounter++;
                  roundMoney = 0;
                  compRoundMoney = 0;
                  win = true; // end the round
                  repeat = false; // end the player's turn
                  
                  // set puzzle as a previous puzzle so it can not be used again
                  if (roundCounter == 1) {
                    previousPuzzle1 = puzzle;
                  } else if (roundCounter == 2) {
                    previousPuzzle2 = puzzle;
                  }
                  
                  if (roundMoney < 1000) { // if the user didn't earn any money
                    totalMoney += 1000; // add $1000 to the total money
                  } else {
                    totalMoney += roundMoney; // add round money to the total money
                  }
                  
                } else {
                  System.out.println("You guessed the title wrong and lost your turn.");
                  Thread.sleep(1000);
                  turnCounter++;
                  repeat = false; // end the player's turn
                } 
              } else {
                for (int i = 0; i < consonantsUsed.length(); i++) {
                  if (consonant == consonantsUsed.charAt(i)) { // if the consonant is in the consonants used list
                    System.out.println("That consonant has been used before, you lost your turn.");
                    Thread.sleep(1000);
                    turnCounter++;
                    repeat = false; // end the player's turn
                    break;
                  } 
                }
                
                if (repeat == true) { // if it is still the player's turn
                  for (int i = 0; i < puzzle.length(); i++) {
                    if (consonant == puzzle.charAt(i)) { // if the consonant is the same as the character in the puzzle
                      consonantsUsed = consonantsUsed + " " + consonant; // add the consonant to the list of consonants used
                      emptyPuzzle = updatePuzzle(emptyPuzzle, puzzle, consonant); // update the empty puzzle with the consonant
                      numOfLetters = countLetters(emptyPuzzle, consonant); // count the number of times the consonant is in the puzzle
                      
                      // output the updated puzzle
                      
                      System.out.println("There are " + numOfLetters + " " + consonant + "'s in the puzzle");
                      Thread.sleep(1000);
                      System.out.println();
                      
                      printPuzzle(emptyPuzzle);
                      
                      // update the player's score
                      
                      System.out.println();
                      roundMoney = roundMoney + space * numOfLetters;
                      System.out.println("Your round money: $" + roundMoney);
                      Thread.sleep(1000);
                      
                      break; 
                    } else if (i == puzzle.length() - 1) { // if the end of the list is reached
                      System.out.println("That letter is not in the puzzle, you lost your turn.");
                      Thread.sleep(1000);
                      consonantsUsed = consonantsUsed + " " + consonant; // add the consonant to the list of consonants used
                      turnCounter++;
                      repeat = false; // end the player's turn
                      break;
                    }
                  }
                }
              }
              
              while (repeat == true) { // while it is still the player's turn
                
                option = 3; // reset the option
                
                do 
                { 
                  try {
                    scan.nextLine();// clear out input
                    System.out.println();
                    System.out.println("What would you like to do next? 1. Spin again 2. Buy a vowel 0. Guess the book title (Enter the number)");
                    option = scan.nextInt();
                    if (option != 0 && option != 1 && option != 2) {
                      System.out.println("Invalid, try again.");
                    }
                  } catch (Exception e) { // catch and throw exception
                    System.out.println("Invalid, try again.");
                  }
                } while (option != 0 && option != 1 && option != 2); // to ensure the user inputs valid data
                
                if (option == 1){
                  repeat = false; // stop asking the user for their choice
                } else if (option == 2) {
                  if (roundMoney < 250) { // check if the user has enough money to buy a vowel
                    System.out.println("You don't have enough money to buy a vowel, they cost $250, you lost your turn.");
                    Thread.sleep(2000);
                    turnCounter++; // end the user's turn
                    repeat = false; // stop asking the user for their choice
                  } else { // if the user has enough money
                    System.out.println();
                    System.out.println("The vowels that have been used this round are:" + vowelsUsed);
                    Thread.sleep(2000);
                    
                    do
                    {
                      System.out.println();
                      System.out.println("What vowel would you like to buy?");
                      userInput = scan.next();  
                      vowel = Character.toLowerCase(userInput.charAt(0)); // change user input to a lowercase character 
                      if (vowel != 'a' && vowel != 'e' && vowel != 'i' && vowel != 'o' && vowel != 'u') {
                        System.out.println("Invalid, try again.");
                        scan.nextLine();
                      }
                    } while (vowel != 'a' && vowel != 'e' && vowel != 'i' && vowel != 'o' && vowel != 'u'); // check if input is a vowel
                    
                    roundMoney = roundMoney - 250; // subtract cost of a vowel from the user's round money
                    
                    for (int j = 0; j < vowelsUsed.length(); j++) { 
                      if (vowel == vowelsUsed.charAt(j)) { // if the vowel is in the used vowels list
                        System.out.println("That vowel has been used before, you lost your turn.");
                        Thread.sleep(1000);
                        repeat = false; // stop asking the user for their choice
                        turnCounter++; // end the user's turn
                        break; // stop checking the used vowels list
                      }
                    }
                    
                    if (repeat = true) { // if it is still the user's turn
                      for (int j = 0; j < puzzle.length(); j++) {
                        if (vowel == puzzle.charAt(j)) { // if the vowel is in the puzzle
                          emptyPuzzle = updatePuzzle(emptyPuzzle, puzzle, vowel); // update the board
                          numOfLetters = countLetters(emptyPuzzle, vowel); // count the number of vowels in the puzzle
                          
                          System.out.println("There are " + numOfLetters + " " + vowel + "'s in the puzzle");
                          Thread.sleep(1000);
                          System.out.println();
                          
                          printPuzzle(emptyPuzzle);
                          
                          // output the updated puzzle
                          
                          System.out.println();
                          System.out.println("Your round money: $" + roundMoney); // update the score
                          Thread.sleep(1000);
                          vowelsUsed += " " + vowel; // add the vowel to the used vowels list
                          break; // stop checking the puzzle
                        } else if (j == puzzle.length() - 1) { // if the end of the list is reached
                          System.out.println("That vowel is not in the puzzle, you lost your turn.");
                          vowelsUsed += " " + vowel;
                          Thread.sleep(1000);
                          turnCounter++; // end the user's turn 
                          repeat = false; // stop asking the user for their choice
                          break; // stop checking the puzzle
                        }
                      }
                    }
                  } 
                } else if (option == 0) {
                  
                  scan.nextLine();
                  
                  System.out.println("Enter your guess:");
                  guess = scan.nextLine();
                  
                  if (guess.equalsIgnoreCase(puzzle)) { // if the guess correct
                    System.out.println("You guessed the title correctly, you won the round.");
                    Thread.sleep(1000);
                    roundCounter++; 
                    win = true; // end the round
                    repeat = false; 
                    
                    // set the puzzle as a previous puzzle
                    
                    if (roundCounter == 1) {
                      previousPuzzle1 = puzzle;
                    } else if (roundCounter == 2) {
                      previousPuzzle2 = puzzle;
                    }
                    
                    if (roundMoney < 1000) { // if the user didn't earn any money
                      totalMoney += 1000; // add $1000 to the total money
                    } else {
                      totalMoney += roundMoney; // add round money to the total money
                    }
                    
                  } else { // if the guess is incorrect
                    System.out.println("You guessed the title wrong and lost your turn.");
                    Thread.sleep(1000);
                    turnCounter++; // end the user's turn
                    repeat = false; 
                  }
                }
              } 
            }
          } while (win == false && (turnCounter%2 == 1));
          
          while (win == false && (turnCounter%2 == 0)) {
            
            counter = 0; // reset the counter each turn
            
            System.out.println();
            System.out.println("///////////////////////////////////////////////////////////////////"); // output a line separator
            Thread.sleep(1000);
            System.out.println();
            System.out.println("It is the computer's turn.");
            Thread.sleep(1000);
            
            do 
            {
              repeat = true; // reset repeat to true each round
              
              System.out.println();
              
              // set round counter based on the round
              
              if (roundCounter == 1) {
                topDollar = 2500;
              } else if (roundCounter == 2) {
                topDollar = 3000;
              } else {
                topDollar = 5000;
              }
              
              for (int j = 0; j < emptyPuzzle.length; j++) {
                if (emptyPuzzle[j] == '_') { // if the character is a underscore
                  counter++; 
                }
              }
              
              if (counter < 5) { // if there are less than 5 spaces unfilled, let the computer guess
                System.out.println("The computer is guessing the puzzle.");
                Thread.sleep(1000);
                System.out.println("The computer has correctly guessed: " + puzzle );
                Thread.sleep(2000);
                roundCounter++; 
                win = true; // end the round
                
                if (compRoundMoney < 1000) { // if the computer didn't earn any money
                  compTotalMoney += 1000; // add $1000 to the total money
                } else {
                  compTotalMoney += compRoundMoney; // add round money to the total money
                }
                break;
              }
              
              System.out.println("The computer is spinning the wheel.");
              Thread.sleep(1000);
              space = spinWheel(topDollar);
              
              if (space == 0) { // if space is 0, it represents a bankrupt
                System.out.println("The computer landed on bankrupt and lost all their money from this round, they lost their turn.");
                Thread.sleep(2000);
                turnCounter++; // end the turn
                compRoundMoney = 0; 
                break;
              } else if (space == 1) { // if space is 1, it represents a lose a turn
                System.out.println("The computer landed on lose a turn and have lost their turn.");
                Thread.sleep(1000);
                turnCounter++; // end the turn
                break; 
              } else {
                System.out.println("The computer landed on $" + space +".");
                Thread.sleep(1000);
                
                consonant = computerGuess(consonantsUsed);
                
                System.out.println();
                System.out.println("The computer guessed " + consonant + ".");  
                Thread.sleep(1000);
                consonantsUsed += " " + consonant; // add consonant to the list of used consonants
                
                for (int i = 0; i < puzzle.length(); i++) {
                  if (consonant == puzzle.charAt(i)) { // if consonant is in the puzzle
                    emptyPuzzle = updatePuzzle(emptyPuzzle, puzzle, consonant); // update the puzzle
                    numOfLetters = countLetters(emptyPuzzle, consonant); // count the number appearances of the consonant in the puzzle
                    
                    System.out.println("There are " + numOfLetters + " " + consonant + "'s in the puzzle");
                    Thread.sleep(1000);
                    System.out.println();
                    
                    // output the updated board
                    
                    printPuzzle(emptyPuzzle);
                    
                    Thread.sleep(1000);
                    
                    // update the computer's score
                    
                    System.out.println();
                    compRoundMoney = compRoundMoney + space * numOfLetters;
                    System.out.println("The computer's money this round: $" + compRoundMoney);
                    Thread.sleep(1000);
                    
                    // let the computer have a chance to buy a vowel
                    
                    vowel = compBuyVowel(compRoundMoney, vowelsUsed);
                    
                    if (vowel != ' ') { // if a vowel was generated
                      System.out.println();
                      System.out.println("The computer has bought the vowel " + vowel +".");
                      Thread.sleep(1000);
                      emptyPuzzle = updatePuzzle(emptyPuzzle, puzzle, vowel);
                      numOfLetters = countLetters(emptyPuzzle, vowel);
                      
                      System.out.println();
                      
                      compRoundMoney = compRoundMoney - 250;
                      System.out.println("The computer's money this round: $" + compRoundMoney);
                      Thread.sleep(1000);
                      
                      for (int j = 0; j < vowelsUsed.length(); j++) {
                        if (vowel == vowelsUsed.charAt(j)) { // if the vowel is in the list used vowels
                          System.out.println("That vowel has been used before, the computer lost their turn.");
                          Thread.sleep(1000);
                          repeat = false; // stop the computer's turn
                          turnCounter++;
                          break; // stop looking in the used vowels list
                        }
                      }
                      
                      if (repeat == true) { // while it is still the computer's turn
                        if (numOfLetters == 0) { // if the vowel doesn't appear in the puzzle
                          System.out.println("That vowel is not in the puzzle, the computer lost their turn.");
                          Thread.sleep(1000);
                          turnCounter++;
                          repeat = false; // stop the computer's turn 
                          vowelsUsed += " " + vowel;
                        } else { 
                          
                          vowelsUsed += " " + vowel;
                          
                          System.out.println("There are " + numOfLetters + " " + vowel + "'s.");
                          Thread.sleep(1000);
                          System.out.println();
                          printPuzzle(emptyPuzzle);
                          Thread.sleep(1000);
                          System.out.println();
                        }
                      }
                    }
                    break; //stop looking in the puzzle
                  } else if (i == puzzle.length() - 1) { // if the end of the list is reached
                    System.out.println("That letter is not in the puzzle, the computer lost their turn.");
                    Thread.sleep(1000);
                    turnCounter++;
                    repeat = false; // end the computer's turn
                    break; // stop checking the puzzle
                  }
                }
              } 
            } while (repeat == true); 
          } 
        } while (win == false); // repeat until the puzzle is guessed
        
        System.out.println();
        System.out.println("Here are the scores:");
        System.out.println("Computer's game money: $" + compTotalMoney);
        System.out.println("Your game money: $" + totalMoney);
        
      } while (roundCounter < 4); // repeat for 3 rounds
      
      System.out.println();
      System.out.println("All three rounds have been completed and the game is finished.");
      Thread.sleep(2000);
      System.out.println();
      
      //determine the outcome of the game and who won
      
      if (totalMoney < compTotalMoney) { 
        System.out.println("The computer has won the game.");
        Thread.sleep(1000);
      } else if (totalMoney == compTotalMoney) {
        System.out.println("You and the computer have tied.");
        Thread.sleep(1000);
      } else {
        System.out.println("You have won the game!");
        Thread.sleep(1000);
      }
      
      do
      {
        System.out.println("Would you like to play again? (y/n)"); // let user repeat the game
        playAgain = scan.next();
        if (!playAgain.equalsIgnoreCase("y") || !playAgain.equalsIgnoreCase("n")) {
          System.out.println("Invalid, try again.");
        } else if (playAgain.equals("n")) {
          System.out.println("That's the end of the game, thanks for playing!");
        }
      } while (!playAgain.equalsIgnoreCase("y") && !playAgain.equalsIgnoreCase("n")); // check for valid input
      
    } while (playAgain.equalsIgnoreCase("y")); // repeat if the user wants to play again
    
    scan.close();
  }
}


