package com.henridev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class for the JavaFrame to display as the main program's window GUi.
 */
public class SlotMachineFrame extends JFrame {
    private TilePanel pan; // pan will be initialized once the Frame displays to the window at run time.

    /**
     * setupMenu will take care of the components and action listeners for the menu bar of the JFrame.
     */
    public void setupMenu() {
        JMenuBar mbar = new JMenuBar(); // Creating the menu bar.
        JMenu mnuFile = new JMenu("File"); // Creating the File menu.
        JMenuItem miSave = new JMenuItem("Save Tiles"); // Creating the Save Tiles menu item.
        miSave.addActionListener(new ActionListener() { // Adding the action listener to miSave to save the model
            // to a file of selected .extension based off user's choice.
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                TileWriter dw = new TileWriter();
                // If the user decides to continue and saves file, then we write the model to the file.
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) { // the user wants to go ahead
                    if (dw.write(jfc.getSelectedFile(), pan.getTiles())) {
                        JOptionPane.showMessageDialog(null,"Wrote Tiles to file.");
                    } else {
                        JOptionPane.showMessageDialog(null,"Could not write Tiles to file.");
                    }
                }
            }
        });
        mnuFile.add(miSave);
        JMenuItem miLoad = new JMenuItem("Load Tiles"); // Creating Load tiles menu item.
        miLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // Action listener for Load Tiles menu item.
                // Action listener will load the model file and add the values to the tiles once completed.
                TileReader dr = new TileReader();
                JFileChooser jfc = new JFileChooser();
                // If user decides to continue with selected file, then execute.
                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ArrayList<Tile> TilesRead = dr.read(jfc.getSelectedFile());
                    if (TilesRead == null) { // Error if null
                        JOptionPane.showMessageDialog(null, "Could not read Tiles from file.");
                    } else {
                        pan.setTiles(TilesRead);
                        repaint();
                    }
                }
            }
        });
        mnuFile.add(miLoad);
        JMenuItem miPrint = new JMenuItem("Print"); // Creating Print menu item.
        miLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // CODE HERE FOR PART 2 OF ASSIGNMENT.

            }
        });
        mnuFile.add(miPrint);
        JMenuItem miRestart = new JMenuItem("Restart"); // Creating Restart menu item.
        miRestart.addActionListener(new ActionListener() { // Action listener for if the user wants to restart.
            // Will clear the ArrayList of tiles to start fresh.
            public void actionPerformed(ActionEvent e) {

                pan.clearTiles();
                repaint();

            }
        });
        JMenuItem miExit = new JMenuItem("Exit"); // Creating the Exit menu item.
        miExit.addActionListener(new ActionListener() { // Action listener if the user clicks and decides to quit.
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuFile.add(miRestart);
        mnuFile.add(miExit);
        mbar.add(mnuFile);
        JMenu mnuHelp = new JMenu("Help"); // Creating Help menu.
        JMenuItem miAbout = new JMenuItem("About"); // Creating About menu item.
        miAbout.addActionListener(new ActionListener() { // Action listener for about. Will display a Dialog showing
            // author name and github link for the repository.
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Application created by Henri Alvarez.\n" +
                        "https://github.com/Object-Oriented-Programming-CPSC-24500/Alvarez_Henri_CPSC24500");

            }
        });
        mnuHelp.add(miAbout);
        mbar.add(mnuHelp);
        setJMenuBar(mbar);
    }

    /**
     * setupLook function will essentially set up the look of the JFrame.
     * Will be setting up the JFrame components non-related to the menu.
     */
    public void setupLook() {
        setBounds(0,0,700,300); // Setting bounds of window to display.
        setLocationRelativeTo(null); // Centering the window.
        Container c = getContentPane(); // Creating the Container pane for the JFrame.
        c.setLayout(new BorderLayout());
        pan = new TilePanel(); // Initializing pan on window start application.
        c.add(pan,BorderLayout.CENTER);
        JPanel panSouth = new JPanel(); // Creating the JPanel for the south area of the JFrame.
        panSouth.setLayout(new FlowLayout());
        // Creating the buttons for the frame
        JButton btnMAx = new JButton("Max"); // Creating the Max button.
        JButton btnMid = new JButton("Mid"); // Creating the Mid button.
        JButton btnMin = new JButton("Min"); // Creating the Min button.
        panSouth.add(btnMAx);
        panSouth.add(btnMid);
        panSouth.add(btnMin);
        panSouth.add(new JLabel("$"));
        JTextField txtBalance = new JTextField("5",2);
        panSouth.add(txtBalance);
        btnMAx.addActionListener(new ActionListener() { // Action listener for btnMax
            public void actionPerformed(ActionEvent e) {
                // CODE FOR PART 2 HERE
            }
        });
        btnMid.addActionListener(new ActionListener() { // Action listener for btnMid
            public void actionPerformed(ActionEvent e) {
                // CODE FOR PART 2 HERE
            }
        });
        btnMin.addActionListener(new ActionListener() { // Action listener for btnMin
            public void actionPerformed(ActionEvent e) {
                // CODE FOR PART 2 HERE
            }
        });
        c.add(panSouth, BorderLayout.SOUTH);
        setupMenu();
    }
    public SlotMachineFrame() {
        setupLook();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
