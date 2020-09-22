package com.henridev;
import java.util.Scanner;
import java.util.Random;

public class GameZone {
    /**
     * Function to print the menu options and also retrieve the selected option selected from the user.
     * @return will be returning the menu option selected by the user.
     */
    public static int printMenu(){
        int selectedOption;
        Scanner sc = new Scanner(System.in);
        System.out.println("******************************************");
        System.out.println("*        Welcome to the Game Zone        *");
        System.out.println("******************************************");
        System.out.println("What would you like to play?");
        System.out.println("1. Twenty-one");
        System.out.println("2. Rock Paper Scissors");
        System.out.println("3. Neither - I'm done!");
        System.out.print("Please enter the number of your choice: ");
        selectedOption = sc.nextInt();

        return selectedOption;
    }

    /**
     * Function for twenty-one game.
     * userDraw, userTotal, dealerDraw, and dealer Total will contain draws per session and total draws at the end.
     * user_continues will determine whether or not the user wants to continue to draw.
     *
     */
    public static void twentyOne(){
        Scanner sc = new Scanner(System.in);
        int userDraw = 0;
        int userTotal = 0;
        int dealerDraw = 0;
        int dealerTotal = 0;
        String user_continues = null;

        // Loop that will run each drawing session until the user loses, or decides to stop drawing.
        do{
            Random rand = new Random();
            // Random number range 1 - 11
            userDraw = 1 + rand.nextInt(11);
            userTotal += userDraw;
            dealerDraw += 1 + rand.nextInt(11);
            dealerTotal += dealerDraw;
            // Printing user results
            System.out.printf("You drew %d.\n", userDraw);
            System.out.printf("Your current total is %d.\n",userTotal);
            // If statement in case user wins or loses during drawing sessions
            if(userTotal == 21){
                System.out.println("You won for drawing 21.");
                break;
            } else if(userTotal > 21){
                System.out.println("You lost for drawing over 21.");
                break;
            }
            // Asking user to draw another card to continue
            System.out.println("Do you want to draw another card? ");
            user_continues = sc.nextLine();

        } while("y".equals(user_continues));

        // If statement in case user decides to stop drawing and checks results against computer
        // Will only run if the userTotal != 21 and userTotal is not greater than 21
        if(userTotal < 21) {
            if (userTotal > dealerTotal) {
                System.out.printf("The computer drew %d.\n", dealerTotal);
                System.out.println("You won!");
            } else if (userTotal < dealerTotal) {
                System.out.printf("The computer drew %d.\n", dealerTotal);
                System.out.println("You lost.");
            } else {
                System.out.printf("The computer drew %d.\n", dealerTotal);
                System.out.println("It is a tie!");
            }
        }
    }

    /**
     * Function to play rockPaperScissors
     * Will randomly generate 2 integers and store inside userNumber and computer
     * The numbers generated will determine 1- rock, 2-paper, 3-scissors.
     * Will print results of game at the end to display winner/loser.
     */
    public static void rockPaperScissors(){
        Random rnd = new Random();
        // Random generated number for user and computer between 1-3.
        int userNumber = 1 + rnd.nextInt(3);
        int computer = 1 + rnd.nextInt(3);
        System.out.printf("Random user generated: %d.\n", userNumber);
        System.out.printf("Random computer generated: %d.\n", computer);
        // Rock = 1 , Paper = 2 , Scissors = 3
        if(userNumber == 1 && computer == 2){ // Rock against paper
            System.out.println("You played Rock, and the computer played Paper.");
            System.out.println("You lost.");
        } else if(userNumber == 1 && computer == 3){ // Rock against Scissors
            System.out.println("You played Rock, and the computer played Scissors.");
            System.out.println("You won.");
        } else if(userNumber == 2 && computer == 1){ // Paper against Rock
            System.out.println("You played Paper, and the computer played Rock.");
            System.out.println("You won.");
        } else if(userNumber == 2 && computer == 3) { // Paper against Scissors
            System.out.println("You played Paper, and the computer played Scissors.");
            System.out.println("You lost.");
        }else if(userNumber == 3 && computer == 1) { // Paper against Scissors
            System.out.println("You played Scissors, and the computer played Rock.");
            System.out.println("You lost.");
        } else if(userNumber == 3 && computer == 2) { // Paper against Scissors
            System.out.println("You played Scissors, and the computer played Paper.");
            System.out.println("You won.");
        }else{ // Tie if userNumber == computer
            System.out.println("It was a tie.");
        }
    }

    /**
     * Main function that will run the program.
     * Will contain a loop that iterates over as long as the user selects a valid menu option.
     */

    public static void main(String[] args) {
        int selectedMenuOption = printMenu();

        while(selectedMenuOption != 3){
            // If user selects 1 form the menu options, then the twentyOne() game will begin.
            // If user selects 2 from menu options, then the rockPaperScissors() game will begin.
            // Else, then close the game.
            if(selectedMenuOption == 1){
                twentyOne();
                selectedMenuOption = printMenu();
            } else if(selectedMenuOption == 2){
                rockPaperScissors();
                selectedMenuOption = printMenu();
            } else { // Break if invalid menu option.
                System.out.println("Thank you for playing.");
                break;
            }
        }
    }
}
