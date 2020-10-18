package com.henridev;
import java.awt.*;
import java.util.Scanner;
import java.io.File;
import java.util.LinkedHashMap;
import javax.swing.*;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

public class CovidPlotter {
    /**
     * Function that will print the header for the program.
     */
    public static void printHeader(){
        System.out.println("**************************************************************");
        System.out.println("*           INTERNATIONAL COVID-19 MORTALITY RATES           *");
        System.out.println("**************************************************************\n");
    }
    /**
     * Function that will read from the file inserted from the user to obtain the data to plot the graphs.
     * @param fsc fill be the file in which has access to the contents.
     * @return will return the LinkedHashMap that contains data to plot the graphs for the program.
     */
    public static LinkedHashMap<String, double[]> readData(Scanner fsc){
        /*
        line => each line of the text file being read.
        parts => will be the line, but inside of an array as the format to obtain data.
        name => will contain the name of the country for each line of data in the text file.
        dayCount => will contain the days of each data included inside of the array - name of country.
        deathValues[] => will contain the values of deaths of the parts array - name of country.
         */
        LinkedHashMap<String, double[]> result = new LinkedHashMap<String, double[]>();
        String line = fsc.nextLine();
        String[] parts = line.split("\t");
        String name;
        int dayCount = parts.length - 1;
        double[] deathValues;
        while(fsc.hasNextLine()){
            line = fsc.nextLine();
            parts = line.split("\t");
            name = parts[0];
            deathValues = new double[dayCount];
            for(int i = 1; i < parts.length; i++){
                deathValues[i-1] = Double.parseDouble(parts[i]);
            }
            // Putting/adding the information for each line of the text file to iterate over (name, deathValues).
            result.put(name,deathValues);
        }
        return result;
    }
    /**
     * Function that will retrieve the numberOfDeaths LinkedHashMap containing the over all data from the text file.
     * This function will take the date from the file, and then create/return a new LinkedHashMap variable that
     * will contain the daily death values.
     * @param numberOfDeaths => the LinkedHashMap passed containing all the values from the text file <name, values[]>
     * @param countryName => Will be the name of the Country in which to retrieve data for.
     * @return => Will return the newly created LinkedHashMap containing the <countryName, dailyDeathValues>
     */
    public static LinkedHashMap<String, double[]> convertDaily(LinkedHashMap<String, double[]> numberOfDeaths,
                                                               String countryName){
        LinkedHashMap<String, double[]> result = new LinkedHashMap<String, double[]>();
        double[] data = numberOfDeaths.get(countryName);
        double[] deathValues = new double[data.length];
        for(int i = 1; i < data.length; i++) {
            if (data[i-1] < data[i]) {
                deathValues[i-1] = data[i] - data[i-1];
            } else if (data[i-1] > data[i]) {
                deathValues[i-1] = data[i-1] - data[i];
            } else {
                deathValues[i-1] = data[i-1];
            }
        }
        result.put(countryName, deathValues);
        return result;
    }
    /**
     * Function will create an array of day numbers to plot.
     * @param howMany will be the length of the data we are looking at.
     * @return will be returning the list that was created to plot.
     */
    public static double[] getDays(int howMany){
        double[] result = new double[howMany];
        for(int i = 0; i < howMany; i++){
            result[i] = i;
        }
        return result;
    }
    /**
     * Function that will take care of setting up the frame and displaying the graph once
     * plot has all the values/information added.
     */
    public static void setUpAndShowFrame(Plot2DPanel plot, String dailyOrCumulative){
        // In case the graph to display is cumulative or daily, set the proper title name.
        String title;
        if(dailyOrCumulative.equalsIgnoreCase("c")){
            title = "Cumulative Deaths";
        } else{
            title = "Daily Deaths";
        }
        // Setting up frame to display along setting the title for the window bar.
        JFrame frm = new JFrame(title);
        frm.setBounds(100,100,500,500);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Will not shut down program.
        Container c = frm.getContentPane();
        c.setLayout(new BorderLayout());
        // Setting base label title for the graph.
        BaseLabel lblTitle = new BaseLabel(title, Color.RED, 0.5, 1.1);
        plot.addPlotable(lblTitle);
        // Setting X, Y coordinate titles
        plot.setAxisLabels("Day" , "Deaths");
        // Adding the plot2D with data onto the JFrame.
        c.add(plot, BorderLayout.CENTER);
        frm.setVisible(true);
    }
    /**
     * Main function for execution
     */
    public static void main(String[] args) {
        // Printing the header for the program.
        printHeader();
        // Creating the scanner variable for the program.
        Scanner sc = new Scanner(System.in);
        // This will hold the cumulative data from the file.
        LinkedHashMap<String, double[]> numberOfDeaths;
        // This will hold the daily data from the file.
        LinkedHashMap<String, double[]> numberOfDailyDeaths;
        // names will hold the names of the countries.
        String names;
        // dailyOrCumulative will hold the string "D" or "C" to determine what type of graph to output.
        String dailyOrCumulative;
        // parts will hold the parts of the data
        String[] parts;
        // Each country deaths data from the text file read.
        double[] data;
        // File reading will be conducted in the try, catch block.
        try{
            // Getting file text/name from the user.
            System.out.print("Enter the name of the data file: ");
            String fileName = sc.nextLine();
            // fsc => variable to access file
            Scanner fsc = new Scanner(new File(fileName));
            // Obtaining the data from the file.
            numberOfDeaths = readData(fsc);
            // Making sure to close file to avoid memory leaks.
            fsc.close();
        }catch(Exception ex){
            // For any exceptions during file reading.
            numberOfDeaths = null;
        }
        if(numberOfDeaths == null){
            System.out.println("I couldn't read the data from the file.");
        } else{
            // Let's work with the data if it loaded properly.
            do{
                System.out.println("Enter countries to plot, separated by commas (or quit to end): ");
                names = sc.nextLine();
                if(!names.equalsIgnoreCase("quit")){
                    System.out.print("[D]aily or [C]umulative? ");
                    dailyOrCumulative = sc.nextLine();
                    Plot2DPanel plot = new Plot2DPanel();
                    plot.addLegend("SOUTH"); // Adding legend to show which countries are being plotted.
                    parts = names.split(",");
                    // Add line plots for each country the user named.
                    for(String part:parts){
                        part = part.trim(); // Getting rid of white spaces.
                        if(!numberOfDeaths.containsKey(part)){
                            System.out.printf("%s is not in the data set.\n",part);
                        }else{
                            // If else block in case of cumulative, or daily graphs to plot.
                            if(dailyOrCumulative.equalsIgnoreCase("c")){
                                data = numberOfDeaths.get(part); // Grab the death count for the part(country) value.
                                // Setting the data to plot.
                                plot.addLinePlot(part, getDays(data.length), data);
                            } else{
                                // This else block is for the daily numbers to add to plot and then show the frame.
                                numberOfDailyDeaths = convertDaily(numberOfDeaths, part);
                                data = numberOfDailyDeaths.get(part); //Grab the death count for the part(country) value
                                // Setting the data to plot.
                                plot.addLinePlot(part, getDays(data.length), data);
                            }
                        }
                    }
                    // Plot will have the death daily or cumulative values. Showing the data plotted JFrame
                    setUpAndShowFrame(plot, dailyOrCumulative);
                } else{ // Else block in case user types "quit" to quit.
                    System.out.println("Your only task ...");
                    System.out.println("               ... is to wear a mask.");
                }
            }while(!names.equalsIgnoreCase("quit"));
        }
    }
}