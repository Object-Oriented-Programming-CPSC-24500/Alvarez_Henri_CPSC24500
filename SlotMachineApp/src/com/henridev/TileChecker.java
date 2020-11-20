package com.henridev;

import java.util.ArrayList;

/**
 * TileChecker class will check for winning combinations in both shapes and color, or just in colord depending on
 * the case.
 */
public class TileChecker {
    /**
     * Detects the winning combinations, if any.
     * @param tiles will be an ArrayList of tiles to iterate and check for any winning combinations.
     * @param scorePassed will be the score entered before button is clicked.
     * @return the new score if any winning combinations are detected.
     */
    public double detectCombinations(ArrayList<Tile> tiles, double scorePassed){
        int[] shapes = new int[4]; // Will contain an array of the shape codes from each tile of the ArrayList.
        int [] colors = new int[4]; // Will contain an array of the color codes from each tile of the ArrayList.
        int indx = 0; // Initial index start for the for loop to iterate over tiles ArrayList.
        double newScore; // The new score for any winning combination, if any, will be stored in this int to return.

        // for loop that will add the shape and color codes onto their respective arrays.
        for (Tile tile : tiles) {
            shapes[indx] = tile.getShape();
            colors[indx] = tile.getColor();
            indx += 1;
        }

        // Initializing boolean values to determine if the matches are true or false.
        boolean matchingShapes = true;
        boolean matchingColors = true;
        // for loop that iterates over the shapes and color code arrays and determines false if one integer
        // != the next integer in the array, meaning the array does not hold all matches of either shapes or colors.
        for(int i = 0; i < shapes.length - 1; i++){
            if(shapes[i] != shapes[i+1]){
                matchingShapes = false;
            }
            if(colors[i] != colors[i+1]){
                matchingColors = false;
            }
        }

        // If statement to determine the newScore to return after determining the matches, if any.
        if(matchingShapes && matchingColors){
            return newScore = scorePassed * 100;
        } else if(matchingColors){
            return newScore = scorePassed * 25;
        }

        return newScore = 0; // return 0 if no winning combinations are found.
    }
}
