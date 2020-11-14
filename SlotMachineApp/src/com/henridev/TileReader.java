package com.henridev;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TileReader class to take care of reading files that hold model data to set for the TilePanel class and display.
 */
public class TileReader {
    /**
     *
     * @param fname name of file to read
     * @return delegating responsibility to function returned.
     */
    public ArrayList<Tile> readFromText(String fname) {
        File f = new File(fname);
        return readFromText(f);
    }

    /**
     *
     * @param f name of file to read
     * @return result, an ArrayList<Tile> that holds the list of Tile objects to eventually display on Load.
     */
    public ArrayList<Tile> readFromText(File f) {
        try {
            ArrayList<Tile> result = new ArrayList<Tile>();
            Scanner fsc = new Scanner(f);
            String line;
            String[] parts;
            int color, shape;
            Tile Tile;
            while (fsc.hasNextLine()) {
                line = fsc.nextLine().trim();  // get rid of whitespace at the end
                if (line.length() > 0) {  // prevent processing a blank line
                    parts = line.split(" ");
                    color = Integer.parseInt(parts[0]);
                    shape = Integer.parseInt(parts[1]);
                    Tile = new Tile(color, shape);
                    result.add(Tile);
                }
            }
            fsc.close();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;  // object equivalent to false 
        }
    }
    /**
     *
     * @param fname name of file to read
     * @return delegating responsibility to function returned.
     */
    public ArrayList<Tile> readFromBinary(String fname) {
        File f = new File(fname);
        return readFromBinary(f);
    }
    /**
     *
     * @param f name of file to read
     * @return TilesRead, an ArrayList<Tile> that holds the list of Tile objects to eventually display on Load.
     */
    public ArrayList<Tile> readFromBinary(File f) {
        try {
            ArrayList<Tile> TilesRead;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            TilesRead = (ArrayList<Tile>)ois.readObject();
            ois.close();
            return TilesRead;
        } catch (Exception ex) {
            return null;
        }
    }
    /**
     *
     * @param f name of file to read
     * @return tilesRead, an ArrayList<Tile> that holds the list of Tile objects to eventually display on Load.
     */
    public ArrayList<Tile> readFromXML(File f) {
        try {
            ArrayList<Tile> tilesRead;
            XMLDecoder dec = new XMLDecoder(
                    new BufferedInputStream(new FileInputStream(f)));
            tilesRead = (ArrayList<Tile>)dec.readObject();
            dec.close();
            return tilesRead;
        } catch (Exception ex) {
            return null;
        }
    }
    /**
     *
     * @param fname name of file to read
     * @return delegating responsibility to function returned.
     */
    public ArrayList<Tile> read(String fname) {
        File f = new File(fname);
        return read(f);
    }

    /**
     *
     * @param f name of file to read
     * @return depending extension of the file, return the correct function to read the file.
     */
    public ArrayList<Tile> read(File f) {
        String fname = f.getName().toUpperCase();
        if (fname.endsWith(".TXT")) {
            return readFromText(f);
        }
        if (fname.endsWith(".BIN")) {
            return readFromBinary(f);
        }
        if (fname.endsWith(".XML")) {
            return readFromXML(f);
        }
        return null;  // unrecognized file type.
    }
}
