package com.henridev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * TilePanel will take care of displaying the tiles to the panel. Will implement MouseListener to add action listeners
 * to the tiles.
 */
public class TilePanel extends JPanel implements MouseListener, MouseMotionListener {
    private ArrayList<Tile> tiles; // Will hold 4 total Tile objects initially added in the constructor.

    /**
     * TilePanel constructor will take care of initializing the tiles ArrayList, adding 4 Tiles to the ArrayList and
     * add the mouseListener to read from the JPanel.
     */
    public TilePanel(){
        addMouseListener(this);
        addMouseMotionListener(this);
        tiles = new ArrayList<Tile>();
        Tile tile1 = new Tile();
        Tile tile2 = new Tile();
        Tile tile3 = new Tile();
        Tile tile4 = new Tile();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
    }

    /**
     * Will get the tiles ArrayList.
     * @return ArrayList tiles.
     */
    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    /**
     *
     * @param tiles will pass in an ArrayList of tiles to load the tiles and set to the TilePanel.
     */
    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    /**
     * Will clear the tiles ArrayList.
     */
    public void clearTiles() {
        tiles.clear();
    }
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        // Iterate over each Tile from the tile ArrayList.
        for(int i = 0; i < tiles.size(); i++){
            // Checks for the color of the current index Tile.
            if (tiles.get(i).getColor() == 0) {
                g.setColor(Color.ORANGE);
            }else if(tiles.get(i).getColor() == 1){
                g.setColor(Color.BLUE);
            }else if(tiles.get(i).getColor() == 2){
                g.setColor(Color.RED);
            }else if(tiles.get(i).getColor() == 3){
                g.setColor(Color.RED);
            }else if(tiles.get(i).getColor() == 4){
                g.setColor(Color.YELLOW);
            }
            // Checks the positions of the tile in the ArrayList and checks the shape. Draw depending on case.
            if(i == 0){   // if index = 0, position = 1;
                if(tiles.get(i).getShape() == 0){ // 0 circle
                    g.fillOval(110 ,25,100, 100);
                } else if(tiles.get(i).getShape() == 1){ // 1 rectangle
                    g.fillRect(110 ,25,100, 100);
                }
            } else if(i == 1){
                if(tiles.get(i).getShape() == 0){ // 0 circle
                    g.fillOval(230 ,25,100, 100);
                } else if(tiles.get(i).getShape() == 1){ // 1 rectangle
                    g.fillRect(230 ,25,100, 100);
                }
            }else if (i == 2){
                if(tiles.get(i).getShape() == 0){ // 0 circle
                    g.fillOval(350 ,25,100, 100);
                } else if(tiles.get(i).getShape() == 1){ // 1 rectangle
                    g.fillRect(350 ,25,100, 100);
                }
            } else if(i == 3){
                if(tiles.get(i).getShape() == 0){ // 0 circle
                    g.fillOval(470 ,25,100, 100);
                } else if(tiles.get(i).getShape() == 1){ // 1 rectangle
                    g.fillRect(470 ,25,100, 100);
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        repaint();
    }
    @Override
    public void mouseExited(MouseEvent e) {

        repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(); // getting x coordinate of mouse on click
        int y = e.getY(); // getting y coordinate of mouse on click
        Random rand = new Random();
        int rand_color = rand.nextInt(5); // Will be used for color.
        int rand_shape = rand.nextInt(2); // Will be used for shape.
        if(x >= 110 && x <= 210 && y >= 25 && y <= 125){ // For position 1 tile
            tiles.get(0).setColor(rand_color);
            tiles.get(0).setShape(rand_shape);
        } else if(x >= 230 && x <= 330 && y >= 25 && y <= 125){ // For position 2 tile
            tiles.get(1).setColor(rand_color);
            tiles.get(1).setShape(rand_shape);
        } else if(x >= 350 && x <= 450 && y >= 25 && y <= 125){ // For position 3 tile
            tiles.get(2).setColor(rand_color);
            tiles.get(2).setShape(rand_shape);
        } else if( x >= 470 && x <= 570 && y >= 25 && y <= 125){ // For position 4 tile
            tiles.get(3).setColor(rand_color);
            tiles.get(3).setShape(rand_shape);
        }
        repaint();
    }
    @Override
    public void mousePressed(MouseEvent e) {

        repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {

        repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) {

        repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {

        repaint();
    }
}
