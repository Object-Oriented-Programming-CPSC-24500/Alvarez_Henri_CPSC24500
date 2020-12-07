package com.henridev;

import java.util.ArrayList;

/**
 * Class that will take care of printing the answers for each of the questions inside of the .JSON file.
 */
public class QuestionPrinter {
    /**
     * Function to print the results of each questions to the quz.
     * @param questions will take an ArrayList of Question(s) that will then be used to iterate and display answers.
     */
    public void printResults(ArrayList<Question> questions) {
        System.out.println("Here are the answers:");
        for(Question question:questions){
            System.out.print(question.getAnswer() + " ");
            System.out.println(question.getQuestion());
        }
        System.out.println("");
    }
}