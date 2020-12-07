package com.henridev;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Quizzer class that will take care of displaying the quiz and tracking the score of questions answered correctly.
 */
public class Quizzer {
    /**
     * Function that will display the quiz and track answers that were answered correctly from the user.
     * @param questions will take an ArrayList of Question(s) which will be used to access data to display the quiz.
     * @param numberOfQuestions is the value of how many questions the user decided to complete during the quiz.
     * @return will return the answeredCorrectly integer to later display to the screen.
     */
    public int displayRandomQuestionsAndTrackAnswers(ArrayList<Question> questions,
                                                     int numberOfQuestions){
        Scanner sc = new Scanner(System.in); // Creating scanner to receive user input.
        Random rand = new Random(); //instance of random class
        int answeredCorrectly = 0; // Tracking how many correct answers the user guessed.
        String answeredGuessed; // Taking string input of user's guess.

        for(int i = 0; i < numberOfQuestions; i++){
            int int_random = rand.nextInt(18); //generate random values from 0-18 (17 questions to rand display).
            System.out.println(questions.get(int_random));
            System.out.print("Type the letter of your choice: ");
            answeredGuessed = sc.nextLine();
            if (answeredGuessed.equals(questions.get(int_random).getAnswer())){ //increment answeredCorrectly
                answeredCorrectly += 1;
                System.out.println("That is correct!");
            } else{ // display correct answer.
                System.out.println("Sorry. The correct answer is " + questions.get(int_random).getAnswer() + ".");
            }
            System.out.println("");
        }
        return answeredCorrectly;
    }
}
