package com.henridev;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class TileWriter {
    /**
     *
     * @param fname the name of the file
     * @param tiles the list of tiles to write
     * @return true if successful, false if an exception happens
     */
    public boolean writeToText(String fname, ArrayList<Tile> tiles) {
        File f = new File(fname);
        return writeToText(f,tiles);   // delegation - I am passing the responsibility
        // to the other writeToText function that takes in a File and an ArrayList
        // rather than a String and an array list.
    }

    /**
     *
     * @param f the name of the file
     * @param tiles the list of tiles to write
     * @return true if successful, false if an exception happens
     */
    public boolean writeToText(File f, ArrayList<Tile> tiles) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            for (Tile tile : tiles) {
                pw.println(tile);  // tiles toString() will return a string
            }
            pw.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    /**
     *
     * @param fname the name of the file
     * @param tiles the list of tiles to write
     * @return true if successful, false if an exception happens
     */
    public boolean writeToBinary(String fname, ArrayList<Tile> tiles) {
        File f = new File(fname);
        return writeToBinary(f,tiles);
    }
    /**
     *
     * @param f the name of the file
     * @param tiles the list of tiles to write
     * @return true if successful, false if an exception happens
     */
    public boolean writeToBinary(File f, ArrayList<Tile> tiles) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(tiles);
            oos.close();
            return true; // success
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     *
     * @param fname the name of the file
     * @param tiles the list of tiles to write
     * @return true if successful, false if an exception happens
     */
    public boolean writeToXML(String fname, ArrayList<Tile> tiles) {
        File f = new File(fname);
        return writeToXML(f,tiles);
    }
    /**
     *
     * @param f the name of the file
     * @param tiles the list of tiles to write
     * @return true if successful, false if an exception happens
     */
    public boolean writeToXML(File f, ArrayList<Tile> tiles) {
        try {
            XMLEncoder enc = new XMLEncoder(new
                    BufferedOutputStream(new FileOutputStream(f)));
            enc.writeObject(tiles);
            enc.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     *
     * @param fname the name of the file
     * @param tiles the list of tiles to write
     * @return ture if successful, false if an exception happes.
     */
    public boolean write(String fname, ArrayList<Tile> tiles) {
        File f = new File(fname);
        return write(f,tiles);
    }
    /**
     * This writes tile data to whatever file format the user wants
     * based on the extension of the file's name
     * .txt - text
     * .bin = binary
     * .xml - xml
     * .jsn or .json - json
     * @param f the file object
     * @param tiles the list of tiles to write
     * @return true if successful, false otherwise
     */
    public boolean write(File f, ArrayList<Tile> tiles) {
        String fname = f.getName().toUpperCase();
        if (fname.endsWith(".TXT")) {
            return writeToText(f,tiles);
        }
        if (fname.endsWith(".BIN")) {
            return writeToBinary(f,tiles);
        }
        if (fname.endsWith(".XML")) {
            return writeToXML(f,tiles);
        }
        return false;  // invalid or unrecognized file type
    }
}
