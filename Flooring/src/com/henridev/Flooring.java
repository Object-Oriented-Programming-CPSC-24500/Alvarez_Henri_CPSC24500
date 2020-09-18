package com.henridev;

import java.util.Scanner;

/**
 * @author Henri
 * Flooring - main java class for program to find and run main function.
 */

public class Flooring {

    /**
     * Function will calculate and retrieve the calculated area.
     * @param length is the length of the room.
     * @param width is the width of the room.
     * @return will be returning the calculated area.
     */
    public static double calculateArea(double length, double width){
        double area = length * width * 12;
        area = (int)(area + (area * 0.20));

        return area;
    }

    /**
     * Function will print the results of amount of packages to buy and total cost.
     * @param packages will contain calculated packages necessary.
     * @param totalCost the calculated total cost of packages.
     */
    public static void printResults(int packages, double totalCost){
        System.out.printf("You need to buy: %d packages of laminate boards.\n", packages);
        System.out.printf("Total cost of packages: $%.2f.", totalCost);
    }

    public static void main(String[] args) {
        /*
         * variable declarations
         *
         */

        Scanner scan = new Scanner(System.in);
        final int board = 30 * 6;
        double length = 0;
        double width = 0;
        double area = 0;
        int boards = 0;
        int packages = 0;
        double totalCost = 0;


        //Retrieving length/width from user input via scanner

        System.out.print("Enter the length in feet: ");
        length = scan.nextDouble();
        System.out.print("Enter the width in feet: ");
        width = scan.nextDouble();

        // Calculating the area function
        area = calculateArea(length, width);

        // Calculating the boards and packages needed
        boards  = (int)Math.ceil((area / board));
        packages = boards / 6;

        totalCost = packages * 24.99;

        // Print results
        printResults(packages, totalCost);
    }
}
