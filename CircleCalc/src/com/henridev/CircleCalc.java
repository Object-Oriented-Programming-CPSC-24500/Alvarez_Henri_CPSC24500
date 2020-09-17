package com.henridev;
import java.util.Random;

/**
 * The main class - CircleCalc.
 * @author Henri
 */

public class CircleCalc {
    /**
     * This function will calculate and print the area to the screen.
     * @param radius retrieves the random generated radius from the main method to calculate/print the area.
     */
    static void calculateArea(double radius){
        // Will get the area using Math.PI to get PI and prints.
        double area = Math.PI * (radius * radius);
        System.out.printf("Area: %f. \n", area);
    }

    /**
     * This function calculates the circumference and prints to the screen.
     * @param radius retrieves the random generated radius from the main method to calculate/print the circumference.
     */
    static void calculateCircumference(double radius){
        // Will calculate the circumference and prints.
        double circum = Math.PI * 2 * radius;
        System.out.printf("Circumference: %f. \n", circum);
    }

    /**
     * The main class for the program where all code is executed.
     * The main class will first generate the random radius between 1-10.
     * Holds two method calls, the calculateArea and calculateCircumference to print the data.
     */
    public static void main(String[] args) {
        // Randomly generate the radius to be anywhere from 1-10.
        Random rnd = new Random();
        int radius = 1 + rnd.nextInt(11);

        // Calling the functions.
        calculateArea(radius);
        calculateCircumference(radius);
    }
}

