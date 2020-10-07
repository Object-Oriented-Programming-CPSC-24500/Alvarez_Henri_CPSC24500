package com.henridev;
import java.io.File;
import java.util.*;

public class BaseballStandings {
    /**
     * Function to print the header for the program and retrieve the file name from user input via Scanner.
     * @param scan will be the variable for the Scanner.
     * @return will return the file name .txt extension to loop over.
     */
    public static String getFileName(Scanner scan){
        String fileName;
        System.out.println("*****************************************");
        System.out.println("*      BASEBALL STANDINGS ANALYZER      *");
        System.out.println("*****************************************\n");
        System.out.print("\nEnter the name of the standings file: ");
        fileName = scan.nextLine();
        System.out.print("\n");

        return fileName;
    }
    /**
     * Function that will print the menu choices and return the selected choice from the user input via Scanner.
     * This function is the main control that determines whether to continue to loop over the menu options or not.
     * Once the string option is selected from the user, there is an if statement at the end of the function
     * that will determine whether or not to call the standings function, which is where all the rest of the
     * program will run and print the results. If the user does not type "8", then the program will continue
     * to iterate over the menu options until the user types "8" for Exit.
     * @param scan will be the variable for the Scanner.
     */
    public static void printChoiceMenu(Scanner scan, String fileName){
        int choice ;
        String strChoice;
        System.out.println("Which standings would you like to see?");
        System.out.println("1. AL East");
        System.out.println("2. AL Central");
        System.out.println("3. AL West");
        System.out.println("4. NL East");
        System.out.println("5. NL Central");
        System.out.println("6. NL West");
        System.out.println("7. Overall");
        System.out.println("8. Exit");
        System.out.print("Enter the number of your choice: ");
        choice = scan.nextInt();
        System.out.print("\n");

        if(choice == 1){
            strChoice = "League AL East";
        }else if(choice == 2){
            strChoice = "League AL Central";
        }else if(choice == 3){
            strChoice = "League AL West";
        }else if(choice == 4){
            strChoice = "League NL East";
        } else if(choice == 5){
            strChoice = "League NL Central";
        } else if(choice ==6){
            strChoice = "League NL West";
        } else if(choice == 7){
            strChoice = "Overall";
        } else {
            strChoice = "Exit";
        }

        if(!"Exit".equals(strChoice)){
            standings(fileName, strChoice, scan);
        }else{
            System.out.println("END OF PROGRAM!");
        }
    }

    /**
     * Function standings will retrieve the information to later print out the information.
     * @param textFile will be the given path to the function retrieved from the user.
     * @param choice will be the choice in which the user decides to retrieve information on.
     */
    public static void standings(String textFile, String choice, Scanner scan){
        /*
         * Scanner file => will be the text file in which to scan over to retrieve information.
         * line will be the string for the entire line for each iteration of the loop.
         * parts will be the line, but inside of an array with a delimiter of a single space.
         * initiate will be a boolean value that for when the "League" string is detected, to signal the program
         * to begin adding string values to a list for calculations.
         * division serves as a dynamic list(array) to keep on adding information for every iteration of the loop
         * that needs to be accounted for due to information that is required for later calculations.
          */
        try{
            // Initializing variables for division teams
            Scanner file = new Scanner(new File(textFile));
            String line;
            String[] parts;
            boolean initiate = false;
            List<String> division = new ArrayList<String>();
            List<String> teamNames = new ArrayList<String>();
            List<Double> teamOne = new ArrayList<Double>();
            List<Double> teamTwo = new ArrayList<Double>();
            List<Double> teamThree = new ArrayList<Double>();
            List<Double> teamFour = new ArrayList<Double>();
            List<Double> teamFive = new ArrayList<Double>();

            // Initializing variables for overall
            List<String> divisions = new ArrayList<String>();
            while(file.hasNextLine()){
                line = file.nextLine().trim();
                parts = line.split("\t");

                if(!"Overall".equals(choice)){ //controls options other than overall and exit
                    if(choice.equals(String.join(" ", parts))){
                        initiate = true;
                    }else if(initiate){
                        if(parts[0].equals("League")){
                            break;
                        }
                        // division is a list that adds parts to the list for all the information that is required.
                        division.addAll(Arrays.asList(parts));
                    }
                } else { // Controls overall option
                    if ("League".equals(parts[0])) {
                        continue;
                    } else{
                        divisions.addAll(Arrays.asList(parts)); // divisions will hold all data for overall analysis.
                    }
                }
            }
            // for loop will add the values in the division to the appropriate team's list for calculations.
            if(!"Overall".equals(choice)){
                for(int i = 1; i < 3; i++){
                    teamOne.add(Double.parseDouble(division.get(i)));
                    teamTwo.add(Double.parseDouble(division.get(i+3)));
                    teamThree.add(Double.parseDouble(division.get(i+6)));
                    teamFour.add(Double.parseDouble(division.get(i+9)));
                    teamFive.add(Double.parseDouble(division.get(i+12)));
                }
                // for loop will add the values of the team names onto teamNames <List> to later print with the results.
                for(int i = 0; i < 14; i += 3){
                    teamNames.add(division.get(i));
                }
            }
            if(!"Overall".equals(choice)){
                calculateResults(teamNames, teamOne, teamTwo, teamThree, teamFour, teamFive);
            } else{
                calculateOverall(divisions);
            }
            file.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        printChoiceMenu(scan, textFile);
    }

    /**
     * Function that will calculate the results which includes: winning percentage, games behind.
     * @param teamOne a List in which contains all the values for team one under the division selected by the user.
     * @param teamTwo a List in which contains all the values for team one under the division selected by the user.
     * @param teamThree a List in which contains all the values for team one under the division selected by the user.
     * @param teamFour a List in which contains all the values for team one under the division selected by the user.
     * @param teamFive a List in which contains all the values for team one under the division selected by the user.
     */
    public static void calculateResults(List<String> teamNames, List<Double> teamOne, List<Double> teamTwo,
                                        List<Double> teamThree, List<Double> teamFour, List<Double> teamFive){
        // Initializing variables
        List<Double> percentages = new ArrayList<Double>();
        double percentageOne;
        double percentageTwo;
        double percentageThree;
        double percentageFour;
        double percentageFive;

        List<Double> gamesBehind = new ArrayList<Double>();
        double behindTwo;
        double behindThree;
        double behindFour;
        double behindFive;

        // Will containt the win loss numbers inside their respective array lists.
        List<Integer> wins = new ArrayList<Integer>();
        List<Integer> losses = new ArrayList<Integer>();

        // Calculating winning percentage.
        percentageOne = teamOne.get(0) / (teamOne.get(0) + teamOne.get(1));
        percentageTwo = teamTwo.get(0) / (teamTwo.get(0) + teamTwo.get(1));
        percentageThree = teamThree.get(0) / (teamThree.get(0) + teamThree.get(1));
        percentageFour = teamFour.get(0) / (teamFour.get(0) + teamFour.get(1));
        percentageFive = teamFive.get(0) / (teamFive.get(0) + teamFive.get(1));

        // Calculating games behind.
        behindTwo = (teamOne.get(0) - teamTwo.get(0) + teamTwo.get(1) - teamOne.get(1)) / 2.0;
        behindThree = (teamOne.get(0) - teamThree.get(0) + teamThree.get(1) - teamOne.get(1)) / 2.0;
        behindFour = (teamOne.get(0) - teamFour.get(0) + teamFour.get(1) - teamOne.get(1)) / 2.0;
        behindFive = (teamOne.get(0) - teamFive.get(0) + teamFive.get(1) - teamOne.get(1)) / 2.0;

        // Adding percentages and games behind to their respective lists to then loop over to print results.
        percentages.add(percentageOne);
        percentages.add(percentageTwo);
        percentages.add(percentageThree);
        percentages.add(percentageFour);
        percentages.add(percentageFive);

        gamesBehind.add(null);
        gamesBehind.add(behindTwo);
        gamesBehind.add(behindThree);
        gamesBehind.add(behindFour);
        gamesBehind.add(behindFive);

        // Adding wins/losses to respective Array Lists to later print results.
        wins.add((int)Math.round(teamOne.get(0)));
        wins.add((int)Math.round(teamTwo.get(0)));
        wins.add((int)Math.round(teamThree.get(0)));
        wins.add((int)Math.round(teamFour.get(0)));
        wins.add((int)Math.round(teamFive.get(0)));

        losses.add((int)Math.round(teamOne.get(1)));
        losses.add((int)Math.round(teamTwo.get(1)));
        losses.add((int)Math.round(teamThree.get(1)));
        losses.add((int)Math.round(teamFour.get(1)));
        losses.add((int)Math.round(teamFive.get(1)));

        //Calling printResults function after retrieving and calculating data
        printResults(teamNames, percentages, gamesBehind, wins, losses);
    }

    /**
     * Function that will print the results to the screen for user selected options 1-6.
     * @param teamNames will contain the strings for the name of all the teams under the division the user selects.
     * @param percentages will contain the double values for the winning percentage.
     * @param gamesBehind will contain the double values for the games behind value.
     * @param wins will contain the integer values for the wins for the team.
     * @param losses will contain the integer values for the losses for the team.
     */
    public static void printResults(List<String> teamNames, List<Double> percentages, List<Double> gamesBehind,
                                    List<Integer> wins, List<Integer> losses){
        System.out.println("Team         Wins  Losses    Pct.  Behind");
        System.out.println("-----------------------------------------");
        for(int i = 0; i < 5; i++){
            if(gamesBehind.get(i) == null){
                System.out.printf("%s\t\t%d\t%d\t%.3f\t\n",
                        teamNames.get(i),wins.get(i),losses.get(i),percentages.get(i));
            } else{
                System.out.printf("%s\t\t%d\t%d\t%.3f\t%.1f\n",
                        teamNames.get(i),wins.get(i),losses.get(i),percentages.get(i),gamesBehind.get(i));
            }
        }
    }
    // For over all functions
    public static void calculateOverall(List <String> divisions){
        /*
        Initializing variables
        size will hold the amount of array indices to create, for which is equal to the total teams in the text file.
        teams will contain an array list [of size] total teams in the text file, regardless of separated divisions.
        from will be the starting point from which to extract information for the divisions list.
        to will be the ending point from which to extract information for the divisions list.
         */
        int size = divisions.size() / 3;
        String[][] teams = new String[size][3];
        int from = 0;
        int to = 3;
        // Will take care of separating information from the divisions list to the indices of the teams array.
        for(int i = 0; i < size; i++){
            teams[i] = divisions.subList(from, to).toArray(new String[0]);
            from += 3;
            to += 3;
        }

        // Grouping the names in one list.
        List<String> teamNames = new ArrayList<String>();
        for(int i = 0; i < size; i++){
            String teamName = teams[i][0];
            teamNames.add(teamName);
        }

        // Grouping team scores to then calculate winning percentage for each team.
        Integer[][] groupingScores = new Integer[size][2];
        for(int i = 0; i < size; i++){
            int currentTeamWin = Integer.parseInt(teams[i][1]);
            int currentTeamLoss = Integer.parseInt(teams[i][2]);
            groupingScores[i][0] = currentTeamWin;
            groupingScores[i][1] = currentTeamLoss;
        }

        // Calculating the winning percentage for each team.
        List<Double> winningPercentages = new ArrayList<Double>(); // Will get sorted
        double[] winningPercentagesTwo = new double[size]; // Will not get sorted
        for(int i = 0; i < size; i++){
            double wins = groupingScores[i][0];
            double losses = groupingScores[i][1];
            double winningPercent = (wins / (wins + losses));
            winningPercentages.add(winningPercent);
            winningPercentagesTwo[i] = winningPercent;
        }

        // Creating a new array that adds the groups and winning percentages together.
        String[] temporaryStorage = new String[4];
        String[][] combinedArrayInformation = new String[size][4];
        for(int i = 0; i < size; i++){
            // String variables which will get the values of the file of the teams displayed in order.
            String teamName = teamNames.get(i); //String of team name
            String teamWin = String.valueOf(groupingScores[i][0]); //String of team wins
            String teamLoss = String.valueOf(groupingScores[i][1]); //String of team losses
            String winningPercent = String.valueOf(winningPercentages.get(i)); //String of winning percentage

            // Adding the string variables to the combinedListInformation array.
            temporaryStorage = new String[]{teamName, teamWin, teamLoss, winningPercent};
            combinedArrayInformation[i] = temporaryStorage;
        }

        // Calculating reverse order from highest to lowest for percentages.
        // This will be useful for placing teams from highest to lowest.
        // sorting winningPercentages highest to lowest
        List<Double> updatedPercentageOrder = new ArrayList<Double>(winningPercentages);
        updatedPercentageOrder.sort(Collections.reverseOrder());

        System.out.println(updatedPercentageOrder);

        // normal percentage scores in a list.
        System.out.println(Arrays.toString(winningPercentagesTwo));

        // placing teams from highest to lowest.
        String[][] formattedGroups = new String[size][3];
        for(int i = 0; i < size; i++) {
            double currentHighestNumber = updatedPercentageOrder.get(i);
            for (int x = 0; x < size; x++) {
                double currentTeamNumber = winningPercentagesTwo[x];

                if (currentTeamNumber == currentHighestNumber) {
                    String teamName = teamNames.get(x); //String of team name
                    String teamWin = String.valueOf(groupingScores[x][0]); //String of team wins
                    String teamLoss = String.valueOf(groupingScores[x][1]); //String of team losses

                    // Adding the string variables to the combinedListInformation array.
                    formattedGroups[x] = new String[]{teamName, teamWin, teamLoss};
                }
            }
        }

        System.out.println(Arrays.deepToString(formattedGroups));

        System.out.println("Team         Wins  Losses");
        System.out.println("-------------------------");
        for(int i = 0; i < size; i++){
            System.out.printf("%s\t\t%s\t%s\n",
                    formattedGroups[i][0], formattedGroups[i][1],formattedGroups[i][2]);
        }
    }
    /**
     * Main function in which the program will run.
     *
     */
    public static void main(String[] args){
        /*
        * Initializing variables
        * scan will be used as the scanner for the program,.
        * textFile will retrieve the path of the text file for which to scan over and retrieve results from.
        * choice will retrieve the choice in which the user wants to retrieve the standings for.
         */
        Scanner scan = new Scanner(System.in);
        String textFile = getFileName(scan);
        printChoiceMenu(scan, textFile);
    }
}
