package com.henridev;

import java.util.ArrayList;
import java.util.Scanner;

public class QuizApp {
    /**
     * Function to print header and retrieve the file name containing questions.
     * @param sc Scanner used to receive the user filename.
     * @return string of file name
     */
    public static String printHeaderAndGetFile(Scanner sc){
        String fileName;
        System.out.println("*          What could possibly be more fun than this?          *");
        System.out.println("****************************************************************");
        System.out.println("*               OOP Theory and Concept Questions               *");
        System.out.println("****************************************************************");
        System.out.println("*          Nothing. Nothing at all. Nope. Nada. Nunca.         *");
        System.out.println("\n");
        System.out.print("Enter name of file containing questions: ");
        fileName = sc.nextLine();
        System.out.println("");

        return fileName;
    }

    /**
     * Function to print menu options and retrieve the choice from the user.
     * @param sc Scanner used to receive user input.
     * @return the choice from the menu option selected by the user.
     */
    public static int printMenuAndGetChoice(Scanner sc){
        int choice;
        System.out.println("Here are your choices:");
        System.out.println("1. Take a quiz.");
        System.out.println("2. See questions and answers.");
        System.out.println("3. Exit.");
        System.out.print("Enter the number of your choice: ");
        choice = Integer.parseInt(sc.nextLine());
        System.out.println("");

        return choice;
    }

    /**
     * Function to print the footer for the end of the program once user decides to quit.
     */
    public static void printFooter(){
        System.out.println("****************************************************************");
        System.out.println("*               Thank you for taking CPSC 24500                *");
        System.out.println("****************************************************************");
    }
    /**
     * Function running main program.
     *
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Creating scanner
        String fileName; // Initializing fileName to hold name of file.
        fileName = printHeaderAndGetFile(sc); // Getting filename (file path) from function call.
        int choice; // Initializing choice to then be assigned to function call printMenuAndGetChoice
        QuestionReader qr = new QuestionReader(); // Creating object to read JSON.
        ArrayList<Question> questions = new ArrayList<Question>(); // Creating ArrayList<Question> to retrieve the
        Quizzer qz = new Quizzer(); // Creating instance of Quizzer to display quiz and track answers.
        // data from .JSON file and storing it inside an instance of Question, which serves as a model to store info.
        QuestionPrinter qp = new QuestionPrinter(); // Will create an instance of a QuestionPrinter to print answers.
        questions = qr.readFromJSON(fileName); // questions holds ArrayList of Questions objects after function call.
        do{
            choice = printMenuAndGetChoice(sc); // Getting choice selected from user.
            if (choice == 1){ // Display quiz, track scores.
                int howManyQuestions;
                int answeredCorrectly;
                System.out.print("How many questions would you like? ");
                howManyQuestions = Integer.parseInt(sc.nextLine());
                answeredCorrectly = qz.displayRandomQuestionsAndTrackAnswers(questions,howManyQuestions);
                System.out.println("You answered " + answeredCorrectly + " correct out of " + howManyQuestions +
                        " questions asked.");
                System.out.println("");
            }else if(choice == 2){ // Show answers to questions.
                qp.printResults(questions);
            }
        } while(choice != 3); // Will break out of program once user select 3 in menu choice.
        printFooter();
    }
}