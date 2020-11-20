
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
    private JTextField txtBalance; // Will take care of displaying the player's score.
    private double currentScore = 5; // Will take care of tracking the player's score. Initially $5 at start.
    private double scorePassed; // Will take care of tracking the amount the user wants to bid.
    private JButton btnMax; // Will be the button for Max.
    private JButton btnMid; // Will be the button for Mid.
    private JButton btnMin; // Will be the button for Min.
    private TileRandomizer tileRandomizer = new TileRandomizer(); // Object will randomize the tiles to display on click
    TileChecker tileChecker = new TileChecker(); // Creating object to check for any combinations of winning scores.
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
        JMenuItem miPrint = new JMenuItem("Print"); // Creating Print menu item to print tiles ArrayList.
        miPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // Printing tile ArrayList using System.out
                System.out.println(pan.getTiles());
            }
        });
        mnuFile.add(miPrint);
        JMenuItem miRestart = new JMenuItem("Restart"); // Creating Restart menu item.
        miRestart.addActionListener(new ActionListener() { // Action listener for if the user wants to restart.
            // Will clear the ArrayList of tiles to start fresh.
            public void actionPerformed(ActionEvent e) {
                currentScore = 5;
                txtBalance.setText(String.format("%1.2f", currentScore)); // displaying  currentScore.
                btnMax.setEnabled(true);
                btnMid.setEnabled(true);
                btnMin.setEnabled(true);
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
        btnMax = new JButton("Max"); // Creating the Max button.
        btnMid = new JButton("Mid"); // Creating the Mid button.
        btnMin = new JButton("Min"); // Creating the Min button.
        panSouth.add(btnMax);
        panSouth.add(btnMid);
        panSouth.add(btnMin);
        panSouth.add(new JLabel("$"));
        txtBalance = new JTextField("5.00",3);
        panSouth.add(txtBalance);
        btnMax.addActionListener(new ActionListener() { // Action listener for btnMax
            public void actionPerformed(ActionEvent e) {
                calculateNewScore(0); // Calculates new score, checks for combinations, displays new score.
                checkToDisable(btnMax, btnMid, btnMin); // checks to disable the buttons if currentScore == 0.
            }
        });
        btnMid.addActionListener(new ActionListener() { // Action listener for btnMid
            public void actionPerformed(ActionEvent e) {
                calculateNewScore(1); // Calculates new score, checks for combinations, displays new score.
            }
        });
        btnMin.addActionListener(new ActionListener() { // Action listener for btnMin
            public void actionPerformed(ActionEvent e) {
                calculateNewScore(2); // Calculates new score, checks for combinations, displays new score.
            }
        });
        c.add(panSouth, BorderLayout.SOUTH);
        setupMenu();
    }
    public SlotMachineFrame() {
        setupLook();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Function to calculate the new score based off the button clicked.
     * @param check will be the an int value of 0, 1, 3 representing btnMax, btnMid, btnMin to calculate appropriately.
     */
    public void calculateNewScore(int check){
        double combinationScore; // Will take the value for combinations found after randomizing tiles.
        if(check == 0){ // if 0 passed to function, then we execute for max button.
            this.scorePassed = this.currentScore;
        } else if(check == 1){ // if 1 passed to function, then we execute for mid button.
            this.scorePassed = this.currentScore / 2;
        } else if(check == 2){ // if 2 passed to function, then we execute for min button.
            this.scorePassed = .1 * currentScore;
        }
        // Object that will take care of randomizing tiles on click.
        tileRandomizer.randomizeTiles(pan.getTiles()); // Randomize the tiles
        repaint(); // Repaint after randomizing the tiles.
        // Will check for winning combinations when user clicks min, mid, max buttons.
        combinationScore = tileChecker.detectCombinations(pan.getTiles(), this.scorePassed); // Update score number.
        this.currentScore = this.currentScore + combinationScore; // updating the currentScore to then display.
        // If statement will update currentScore depending if we lose or win on a winning tile/color combination.
        if(combinationScore == 0){ // If combinationScore == 0, we know to subtract the bid from the currentScore.
            this.currentScore =  this.currentScore -  this.scorePassed;
        }else{ // else we know to add combinationScore to currentScore, as we won the bid.
            this.currentScore =  this.currentScore + combinationScore;
        }
        this.txtBalance.setText(String.format("%1.2f", this.currentScore)); // displaying new score to view.
    }

    /**
     * Function to disable the buttons if the current score reaches zero.
     * @param btnMax will be the JButton for max.
     * @param btnMid will be the JButton for mid.
     * @param btnMin will be the JButton for min.
     */
    public void checkToDisable(JButton btnMax, JButton btnMid, JButton btnMin){
        if(this.currentScore == 0){
            btnMax.setEnabled(false);
            btnMid.setEnabled(false);
            btnMin.setEnabled(false);
        }
    }
}