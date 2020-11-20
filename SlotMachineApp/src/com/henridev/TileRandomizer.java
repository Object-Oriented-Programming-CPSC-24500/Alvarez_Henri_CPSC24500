package com.henridev;

import java.util.ArrayList;

/**
 * TileRandomizer class will take care of randomizing the slots at the start of the application and whenever the user
 * clicks on the Max, Mid, or Min buttons.
 *
 */
public class TileRandomizer {

    /**
     * Function that will take care of randomizing the tiles at start and on button clicks.
     * @param tiles will take an ArrayList of tiles.
     */
    public void randomizeTiles(ArrayList<Tile> tiles){
        for (Tile tile : tiles) { // Iterate over each tile and set to random color, shape values.
            tile.setRandomly();
        }
    }

}
