package com.henridev;

import java.io.Serializable;
import java.util.Random;

/**
 * Tile calls will be the model for the project. All values that describe the tile live in this class.
 */
public class Tile implements Serializable {
    private int color; // 0 => ORANGE, 1 => BLUE, 2 => RED, 3 => GREEN, 4 => YELLOW.
    private int shape; // 0 => Circle, 1 => Rectangle.

    public Tile(int color, int shape){
        setColor(color);
        setShape(shape);
    }
    public Tile(){
        // Will randomly set the color and shape codes.
        setRandomly();
    }
    // Getters and setters for the Tile class.
    public int getColor() {
        return color;
    }
    public int getShape(){
        return shape;
    }
    public void setColor(int color){
        this.color = color;
    }
    public void setShape(int shape) {
        this.shape = shape;
    }

    /**
     * randomly sets random color and shape values to initially display to the TilePanel > JFrame.
     */
    public void setRandomly(){
        Random rand = new Random();
        int rand_int1 = rand.nextInt(5); // Will be used for color.
        int rand_int2 = rand.nextInt(2); // Will be used for shape.
        color = rand_int1;
        shape = rand_int2;
    }

    /**
     *
     * @return a format in which to serialize data to text files.
     */
    @Override
    public String toString() {  // comes from java.lang.Object
        return String.format("%d %d",color,shape);
    }
}
