package com.henridev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that will create the Pumpkin frame as the GUI.
 */
class PumpkinCustomFrame extends JFrame {
    // Initializing the private variables.
    // These variables will belong to .this and will be manipulated to change the pumpkin dimensions.
    private int left = 0;
    private int top = 0;
    private int pumpkinWidth;
    private int pumpkinHeight;
    private JTextField txtLeft;
    private JTextField txtTop;
    private JTextField txtWidth;
    private JTextField txtHeight;
    private JTextField txtEye;
    private JTextField txtNose;
    private JTextField txtMouth;

    /**
     * Function to set the look of the JFrame and its panels.
     * @param title will pass the title to name the JFrame window.
     * @param initialLeft will pass the left position of the JFrame window.
     * @param initialTop will pass the top position of the JFrame window.
     * @param initialWidth will pass the width of the JFrame window.
     * @param initialHeight will pass the height of the JFrame window.
     */
    public void setLook(String title, int initialLeft, int initialTop, int initialWidth, int initialHeight){
        setTitle(title);
        setBounds(initialLeft, initialTop, initialWidth, initialHeight); // Setting the window dimensions
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        // drawing panel for the pumpkin.
        DrawingPanel panCenter = new DrawingPanel();
        c.add(panCenter, BorderLayout.CENTER);
        // BOTTOM PANEL FOR USER INPUTS.
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new FlowLayout());
        // left label and text field.
        JLabel lblLeft = new JLabel("Left: ");
        panSouth.add(lblLeft);
        txtLeft = new JTextField(3);
        panSouth.add(txtLeft);
        // top label and text field.
        JLabel lblTop = new JLabel("Top: ");
        panSouth.add(lblTop);
        txtTop = new JTextField(3);
        panSouth.add(txtTop);
        // width label and text field.
        JLabel lblWidth = new JLabel("width: ");
        panSouth.add(lblWidth);
        txtWidth = new JTextField(3);
        panSouth.add(txtWidth);
        // height label and text field.
        JLabel lblHeight = new JLabel("Height: ");
        panSouth.add(lblHeight);
        txtHeight = new JTextField(3);
        panSouth.add(txtHeight);
        // Eye (C S T) label and text field.
        JLabel lblEye = new JLabel("Eye (C S T): ");
        panSouth.add(lblEye);
        txtEye = new JTextField(3);
        panSouth.add(txtEye);
        // Nose (C S T) label and text field.
        JLabel lblNose = new JLabel("Nose (C S T): ");
        panSouth.add(lblNose);
        txtNose = new JTextField(3);
        panSouth.add(txtNose);
        // Mouth (O R) label and text field.
        JLabel lblMouth = new JLabel("Mouth (O R): ");
        panSouth.add(lblMouth);
        txtMouth = new JTextField(3);
        panSouth.add(txtMouth);
        // Draw button
        JButton btnDraw = new JButton("Draw");
        panSouth.add(btnDraw);
        btnDraw.addActionListener(new ActionListener() { //Action/Event listener when button is clicked by user.
            @Override
            public void actionPerformed(ActionEvent e) {
                try{ //In case no errors occur (all text field values entered correctly).
                    String title = "Pumpkin Maker";
                    left = Integer.parseInt(txtLeft.getText());
                    top = Integer.parseInt(txtTop.getText());
                    pumpkinWidth = Integer.parseInt(txtWidth.getText());
                    pumpkinHeight = Integer.parseInt(txtHeight.getText());
                    panCenter.setxPosition(left);
                    panCenter.setyPosition(top);
                    panCenter.setPumpkinWidth(pumpkinWidth);
                    panCenter.setPumpkinHeight(pumpkinHeight);
                    // Taking care of the error if the eye type, nose type, and mouth type are entered as integers.
                    // Or do not have appropriate characters entered.
                    if("C".equalsIgnoreCase(txtEye.getText()) ||
                            "S".equalsIgnoreCase(txtEye.getText()) ||
                            "T".equalsIgnoreCase(txtEye.getText())){
                        panCenter.setEyeShape(txtEye.getText());

                    }else{
                        JOptionPane.showMessageDialog(null, "Please make sure eye type is of (C S T).");
                    }
                    if(txtNose.getText().equalsIgnoreCase("C") ||
                            txtNose.getText().equalsIgnoreCase("S") ||
                            txtNose.getText().equalsIgnoreCase("T")){
                        panCenter.setNoseShape(txtNose.getText());

                    } else{
                        JOptionPane.showMessageDialog(null, "Please make sure nose type is of (C S T).");
                    }
                    if(txtMouth.getText().equalsIgnoreCase("O") ||
                            txtMouth.getText().equalsIgnoreCase("R")){
                        panCenter.setMouthShape(txtMouth.getText());

                    } else{
                        JOptionPane.showMessageDialog(null, "Please make sure mouth type is of (O R).");
                    }
                    repaint();

                }catch(Exception ex){ // In case any integer errors do occur, make sure to display a window to alert.
                    JOptionPane.showMessageDialog(null, "Please make sure an integer" +
                            " value is entered for Left, Top, Width, and Height.");
                }
            }
        }); // Action event listener for btnDraw component from frame itself.
        // Adding panSouth to the container
        c.add(panSouth, BorderLayout.SOUTH);
    }

    /**
     * Constructor for PumpkinCustomFrame class.
     * The constructor will set the default x,y coordinates and the initial pumpkin look displayed.
     */
    public PumpkinCustomFrame(){
        setLook("Pumpkin Maker", 0, 0, 800, 480);
        if(this.left == 0 && this.top == 0){
            setLocationRelativeTo(null); // Will initially set the window location to be centered on the screen.
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

/**
 * class for DrawingPanel to draw the shapes/pumpkin.
 */
class DrawingPanel extends JPanel{
    // Private variables for positioning (x,y,width,height of pumpkin).
    private int xPosition;
    private int yPosition;
    private int pumpkinWidth;
    private int pumpkinHeight;
    // Private variables to determine shape
    private String eyeShape;
    private String noseShape;
    private String mouthShape;
    //Setters (Will be used in ActionListener to set new values for the pumpkin).
    public void setxPosition(int xPos){
        this.xPosition = xPos;
    }
    public void setyPosition(int yPos){
        this.yPosition = yPos;
    }
    public void setPumpkinWidth(int pumpkinWidth){
        this.pumpkinWidth = pumpkinWidth;
    }
    public void setPumpkinHeight(int pumpkinHeight){
        this.pumpkinHeight = pumpkinHeight;
    }
    public void setEyeShape(String eyeShape){
        this.eyeShape = eyeShape;
    }
    public void setNoseShape(String noseShape){
        this.noseShape = noseShape;
    }
    public void setMouthShape(String mouthShape){
        this.mouthShape = mouthShape;
    }

    /**
     * Constructor => default values for the x,y, width, height positions.
     * default values for the eyeShape, noseShape, mouthShape determinations.
     */
    DrawingPanel(){
        xPosition = 200;
        yPosition = 100;
        pumpkinWidth = 100;
        pumpkinHeight = 100;
        eyeShape = "C";
        noseShape = "S";
        mouthShape = "O";
    }
    @Override
    // Method to override to draw the pumpkin.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // color switch
        g.setColor(Color.ORANGE);
        // pumpkin shape
        g.fillOval(xPosition,yPosition,pumpkinWidth,pumpkinHeight);
        // color switch
        g.setColor(Color.WHITE);
        // Eye shape
        if(eyeShape.equalsIgnoreCase("C")){ // Circle eyes
            // left eye
            g.fillOval(xPosition+(pumpkinWidth/5),yPosition+17,pumpkinWidth/5,pumpkinHeight/5);
            // right eye
            g.fillOval((int) (xPosition+(pumpkinWidth)/1.6),yPosition+17,pumpkinWidth/5,pumpkinHeight/5);
        }else if(eyeShape.equalsIgnoreCase("S")){ // Square eyes
            // left eye
            g.fillRect(xPosition+(pumpkinWidth/5),yPosition+17,pumpkinWidth/5,pumpkinHeight/5);
            // right eye
            g.fillRect((int) (xPosition+(pumpkinWidth)/1.6),yPosition+17,pumpkinWidth/5,pumpkinHeight/5);
        } else if(eyeShape.equalsIgnoreCase("T")){ // Triangle eyes
            // left eye
            int[] xcoords = new int[3];
            int[] ycoords = new int[3];
            xcoords[0] = xPosition+(pumpkinWidth/5);
            ycoords[0] = yPosition+34;
            xcoords[1] = xPosition+(pumpkinWidth/5) + 7;
            ycoords[1] = yPosition+12;
            xcoords[2] = xPosition+(pumpkinWidth/5) + 15;
            ycoords[2] = yPosition+34;
            g.fillPolygon(xcoords, ycoords, 3);
            // right eye
            int[] xcoords2 = new int[3];
            int[] ycoords2 = new int[3];
            xcoords2[0] = (int) (xPosition+(pumpkinWidth)/1.6);
            ycoords2[0] = yPosition+34;
            xcoords2[1] = (int) (xPosition+(pumpkinWidth)/1.6) + 7;
            ycoords2[1] = yPosition+12;
            xcoords2[2] = (int) (xPosition+(pumpkinWidth)/1.6) + 15;
            ycoords2[2] = yPosition+34;
            g.fillPolygon(xcoords2, ycoords2, 3);
        }
        // stem
        g.fillRect((int) (xPosition+(pumpkinWidth/2.5)), yPosition-10, pumpkinWidth/5, 11);
        // Nose shape
        if(noseShape.equalsIgnoreCase("S")){ // Square nose
            // nose
            g.fillRect((int) (xPosition+(pumpkinWidth/2.5)) + 3, (int) ((yPosition+pumpkinHeight)/1.4)-5, pumpkinWidth-85,
                    pumpkinHeight/8);
        }else if(noseShape.equalsIgnoreCase("C")){ // Circle nose
            g.fillOval((int) (xPosition+(pumpkinWidth/2.5)) + 3, (int) ((yPosition+pumpkinHeight)/1.4)-5,
                    pumpkinWidth-85,
                    pumpkinHeight/8);
        }else if(noseShape.equalsIgnoreCase("T")){ // Triangle nose
            int[] xcoords = new int[3];
            int[] ycoords = new int[3];
            xcoords[0] = (int) (xPosition+(pumpkinWidth/2.5));
            ycoords[0] = (int) ((yPosition+pumpkinHeight)/1.4)+7;
            xcoords[1] = (int) (xPosition+(pumpkinWidth/2.5)) + 8;
            ycoords[1] = (int) ((yPosition+pumpkinHeight)/1.4)-10;
            xcoords[2] = (int) (xPosition+(pumpkinWidth/2.5)) + 18;
            ycoords[2] = (int) ((yPosition+pumpkinHeight)/1.4)+7;
            g.fillPolygon(xcoords, ycoords, 3);
        }
        // Mouth shape
        if(mouthShape.equalsIgnoreCase("O")){ // Oval mouth
            // mouth
            g.fillOval(xPosition+20, (int) ((yPosition+pumpkinHeight)/1.2), pumpkinWidth-40, pumpkinHeight/10);
        }else if(mouthShape.equalsIgnoreCase("R")){ // Rectangle mouth
            g.fillRect(xPosition+20, (int) ((yPosition+pumpkinHeight)/1.2), pumpkinWidth-40, pumpkinHeight/10);
        }
    }
}

/**
 * Main class holding the main function.
 */
public class PumpkinMaker{
    /**
     * Main Function program.
     *
     */
    public static void main(String[] args) {
	    // Main program.
        PumpkinCustomFrame pumpkinFrm = new PumpkinCustomFrame();
        pumpkinFrm.setVisible(true);
    }
}







