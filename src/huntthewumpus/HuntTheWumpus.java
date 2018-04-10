package huntthewumpus;

/*
 * CSCI-162 - Foundations of Computer Science II
 * Programming Assignment 5 - Wumpus Game
 * Class Description : This is the main method of the Wumpus game.
 * Author            : Benjamin Kleynhans
 * Date              : April 4, 2017
 * Filename          : HuntTheWumpus.java
 */

import java.util.Scanner;

/**
 * <h1>
 * CSCI-162 - Foundations of Computer Science II
 * </h1>
 * <p>
 * This is the main method of the Wumpus game.
 * </p>
 *
 * @version 1.0
 * @since April 4, 2017
 */

/* 
 * Start of main class, HuntTheWumpus class
 */
public class HuntTheWumpus {

    /**
     * Main method for Wumpus Game
     *
     * @param args none
     */
    private static Scanner kbInput = new Scanner(System.in);                                    // Initialize Scanner object
    private static Player player = new Player();                                                // Initialize Player object
    private static CellProperties[][] propertiesArray = initializeProperties(                   // Initialize CellProperties object
            new CellProperties[player.getGridSize()][player.getGridSize()]);
    private static String[] columnTitles = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};// Initialize String object

    public static void main(String[] args) {

        insertLines();                                                                          // Insert lines for display purposes
        displayMessage("WelcomeMessage");                                                       // Display Welcome message
        playGame();                                                                             // Launch the game

    } // End of Main Method

    /**
     * Initializes the object array
     *
     * @param propertiesArray (CellProperties) New array, yet uninitialized
     * @return propertiesArray (CellProperties) Array containing all the cell
     * objects created
     */
    private static CellProperties[][] initializeProperties(CellProperties[][] propertiesArray) {

        int i;
        int j;

        for (i = 0; i < player.getGridSize(); i++) {                                            // Initialize all the objects in the object array
            for (j = 0; j < player.getGridSize(); j++) {
                propertiesArray[i][j] = new CellProperties();
            }
        }

        return propertiesArray;

    } // End of initializeProperties Method

    /**
     * Intermediate method to place the game objects in the board and start the
     * game play
     */
    private static void playGame() {

        placeObjects();                                                                         // Define the locations of the Gold, Wumpus and Pits
        getPlayerMove();                                                                        // Get a move input from the player

    } // End of playGame Method

    /**
     * Places the objects in the game grid
     */
    private static void placeObjects() {

        boolean validCell = false;

        int numberOfChests = 1;
        int createdChests = 0;

        int numberOfWumpusses = 1;
        int createdWumpusses = 0;

        int numberOfPits = 2;
        int createdPits = 0;

        int cycleCounter = 0;

        int x = 0;
        int y = 0;

        do {
            for (createdChests = 0; createdChests < numberOfChests; createdChests++) {          // Place the gold chests

                validCell = false;

                do {
                    x = getRandom(0, (player.getGridSize() - 1));
                    y = getRandom(0, (player.getGridSize() - 1));

                    if ((!propertiesArray[x][y].getGold())
                            && ((x != 0) || (y != (player.getGridSize() - 1)))) {

                        propertiesArray[x][y].setGold();
                        validCell = true;

                        System.out.println("Gold - " + x + ":" + y);
                    }

                } while (validCell = false);

                setObjectProperty("gold", x, y);
            }

            for (createdWumpusses = 0; createdWumpusses < numberOfWumpusses; createdWumpusses++) {  // Place the Wumpusses

                validCell = false;

                do {
                    x = getRandom(0, (player.getGridSize() - 1));
                    y = getRandom(0, (player.getGridSize() - 1));

                    if (!propertiesArray[x][y].getGold()
                            && !propertiesArray[x][y].getWumpus()
                            && ((x != 0) || (y != (player.getGridSize() - 1)))) {

                        propertiesArray[x][y].setWumpus();
                        validCell = true;

                        System.out.println("Wumpus - " + x + ":" + y);
                    }

                } while (validCell = false);

                setObjectProperty("wumpus", x, y);
            }

            for (createdPits = 0; createdPits < numberOfPits; createdPits++) {                  // Place the pits

                validCell = false;

                do {
                    x = getRandom(0, (player.getGridSize() - 1));
                    y = getRandom(0, (player.getGridSize() - 1));

                    if (!propertiesArray[x][y].getGold()
                            && !propertiesArray[x][y].getPit()
                            && ((x != 0) || (y != (player.getGridSize() - 1)))) {

                        propertiesArray[x][y].setPit();
                        validCell = true;

                        System.out.println("Pit - " + x + ":" + y);
                    }

                } while (validCell == false);

                setObjectProperty("pit", x, y);
            }

        } while (validCell == false);
    } // End of placeObjects Method

    /**
     * Each object, Gold, Wumpus and Pit has an indicative property (shimmer,
     * smel and breeze. This method sets the properties to true in the relevant
     * objects.
     *
     * @param property (String) The property (gold, wumpus or pit) whose
     * property to set
     * @param x (int) X-Coordinate of relevant property
     * @param y (int) Y-Coordinate of relevant property
     */
    private static void setObjectProperty(String property, int x, int y) {

        switch (property) {
            case "gold":                                                                        // Set shimmer for gold locations
                if (((x - 1) >= 0) && (!hasObject((x - 1), y))) {
                    propertiesArray[(x - 1)][y].setShimmer();
                }

                if (((x + 1) < player.getGridSize()) && (!hasObject((x + 1), y))) {
                    propertiesArray[(x + 1)][y].setShimmer();
                }

                if (((y - 1) >= 0) && (!hasObject(x, (y - 1)))) {
                    propertiesArray[x][(y - 1)].setShimmer();
                }

                if (((y + 1) < player.getGridSize()) && (!hasObject(x, (y + 1)))) {
                    propertiesArray[x][(y + 1)].setShimmer();
                }

                break;
            case "wumpus":                                                                      // Set smell for wumpus locations
                if (((x - 1) >= 0) && (!hasObject((x - 1), y))) {
                    propertiesArray[(x - 1)][y].setSmell();
                }

                if (((x + 1) < player.getGridSize()) && (!hasObject((x + 1), y))) {
                    propertiesArray[(x + 1)][y].setSmell();
                }

                if (((y - 1) >= 0) && (!hasObject(x, (y - 1)))) {
                    propertiesArray[x][(y - 1)].setSmell();
                }

                if (((y + 1) < player.getGridSize()) && (!hasObject(x, (y + 1)))) {
                    propertiesArray[x][(y + 1)].setSmell();
                }

                break;
            case "pit":                                                                         // Set breeze for pit locations
                if (((x - 1) >= 0) && (!hasObject((x - 1), y))) {
                    propertiesArray[(x - 1)][y].setBreeze();
                }

                if (((x + 1) < player.getGridSize()) && (!hasObject((x + 1), y))) {
                    propertiesArray[(x + 1)][y].setBreeze();
                }

                if (((y - 1) >= 0) && (!hasObject(x, (y - 1)))) {
                    propertiesArray[x][(y - 1)].setBreeze();
                }

                if (((y + 1) < player.getGridSize()) && (!hasObject(x, (y + 1)))) {
                    propertiesArray[x][(y + 1)].setBreeze();
                }

                break;
            default:
                break;
        }
    } // End of setObjectProperty Method

    /**
     * Determines whether there is an object (gold, wumpus or pit) in the
     * referenced cell.
     *
     * @param x (int) X-Coordinate of relevant property
     * @param y (int) Y-Coordinate of relevant property
     * @return returnValue (boolean) true if there is an object and false if
     * there is not
     */
    private static boolean hasObject(int x, int y) {

        boolean returnValue = false;

        if (propertiesArray[x][y].getGold()                                                     // If there is gold, a wumpus or a pit in the 
                || propertiesArray[x][y].getWumpus()                                                // location, return true
                || propertiesArray[x][y].getPit()) {
            returnValue = true;
        }

        return returnValue;
    } // End of hasObject Method

    /**
     * Print the actual grid with all properties to screen
     */
    private static void printGrid() {

        int columns;
        int rows;

        System.out.print("   ");

        for (columns = 0; columns < player.getGridSize(); columns++) {
            System.out.printf("%8s", columnTitles[columns]);                                    // Print the column headers
            System.out.printf("%7s", CellProperties.EMPTYCELL);
        }

        System.out.println();

        System.out.print(" ");
        printElement(" ", "-");
        System.out.println();

        for (rows = 0; rows < player.getGridSize(); rows++) {
            printProperty(rows);                                                                // Define the grid rows
            System.out.println();
        }

        System.out.println();
    } // End of printGrid Method

    /**
     * Get the player's move from keyboard input
     *
     * @return continueGame (boolean) false if the game should end
     */
    private static boolean getPlayerMove() {

        boolean continueGame = true;
        boolean validInput = false;

        String playerMove = new String("");

        String column = "";
        int row = 0;

        int xCoordinate = 0;

        int i = 0;

        do {
            insertLines();
            printGrid();                                                                        // Display the game grid

            if ((player.isAlive() && (!player.getGameWon()))) {

                do {
                    validInput = false;

                    System.out.print("Please make a move in the form of \"A1\", \"B2\", or press \"X\" to exit : ");
                    playerMove = kbInput.nextLine();
                    System.out.println();

                    try {
                        column = playerMove.substring(0, 1).toUpperCase();

                        if (!column.equals("X")) {                                              // If the user did not ask to exit the game, continue
                            row = Integer.parseInt(playerMove.substring(1, 2));
                        }

                        if (column.equals("X")) {                                               // Don't process this block if the player wants to exit
                            validInput = true;
                        } else {
                            if ((row >= 0) && (row < player.getGridSize())) {                   // If the rows specified is within range
                                for (i = 0; i < player.getGridSize(); i++) {                    // For the length of the grid size
                                    if (validInput == false) {                                  // While the input has not be validated
                                        if (columnTitles[i].equals(column)) {                   // Check if the column specified is within range
                                            validInput = true;

                                            xCoordinate = i;                                    // Set to the index of the letter coordinate entered
                                        }
                                    }
                                }
                            }
                        }

                        if (column.equals("X")) {                                               // Don't process this block if the player wants to exit
                            //Do nothing
                        } else if (!validInput && !isValid(column, row)) {
                            displayMessage("WrongDataTypeMessage");
                        } else if (validInput && !isValid(column, row)) {
                            displayMessage("InvalidCellSelected");
                            validInput = false;
                        }

                    } catch (Exception e) {
                        String thrownException = new String("RowIndexIntegerConversion," + e);

                        exceptionHandler(thrownException);
                    }
                } while (validInput == false);

                if (!column.equals("X")) {                                                      // Don't process this block if the player wants to exit

                    player.setX(xCoordinate);
                    player.setY(row);

                } else {
                    continueGame = false;

                    displayMessage("FarewellMessage");                                          // Display a farewell message
                }
            }

        } while ((player.isAlive()) && (continueGame == true) && (!player.getGameWon()));       // Continue the game whlie the player is alive, or hasn't died

        if (player.getGameWon()) {
            player.takeLife();
            continueGame = false;                                                               // If the player won the game, display won message and exit

            displayMessage("WonGame");
        } else if (!player.isAlive()) {
            displayMessage("LostGame");                                                         // If the player died, display died message and exit
        }

        return continueGame;
    } // End of getPlayerMove Method

    /**
     * Determines whether the user supplied coordinates are valid based on the
     * direction that a player may move
     *
     * @param x (int) X-coordinate of cell user wants to move to
     * @param y (int) Y-coordinate of cell user wants to move to
     * @return validCoords (boolean) true if the coordinates provided are valid
     */
    private static boolean isValid(String x, int y) {

        int i;

        boolean validCoords = false;

        for (i = 0; i < player.getGridSize(); i++) {                                            // Test if the x- and y-values entered by the user
            if (columnTitles[i].equals(x)) {                                                       // are within the borders of the game grid
                if (((i == (player.getX() - 1)) && (y == (player.getY())))
                        || ((i == (player.getX() + 1)) && (y == (player.getY())))
                        || ((i == (player.getX()) && (y == (player.getY() - 1))))
                        || ((i == (player.getX()) && (y == (player.getY() + 1))))) {

                    validCoords = true;
                }
            }
        }
        return validCoords;
    } // End of isValid Method

    /**
     * Method used to print the required amount of elements to screen in a
     * straight line
     *
     * @param elementA (string) Could be any string based on requirement
     * @param elementB (string) Could be any string based on requirement
     */
    private static void printElement(String elementA, String elementB) {

        int i;

        for (i = 0; i < ((player.getGridSize() * 15) + 3); i++) {
            if ((i == 0) || (i == 1) || (i == 2) || (i == ((player.getGridSize() * 15) + 2))) {
                System.out.print(elementA);
            } else {
                System.out.print(elementB);
            }
        }
    } // End of printElement Method

    /**
     * Print the actual grid with the defined elements to screen
     *
     * @param rows (int) Number of the grid row to print to screen
     */
    private static void printProperty(int rows) {

        int columns = 0;

        for (columns = 0; columns < player.getGridSize(); columns++) {
            if (columns == 0) {                                                                 // If this is the first column
                if ((player.getX() == columns) && (player.getY() == rows)) {
                    if ((propertiesArray[columns][rows].getWumpus())                            // If there is a wumpus and not a pit
                            && (!propertiesArray[columns][rows].getPit())) {
                        player.takeLife();
                        System.out.printf("   |%14s", CellProperties.PRINTWUMPUS);
                    }

                    if ((propertiesArray[columns][rows].getPit())                               // If there is a pit and not a wumpus
                            && (!propertiesArray[columns][rows].getWumpus())) {
                        player.takeLife();
                        System.out.printf("   |%14s", CellProperties.PRINTPIT);
                    }

                    if ((propertiesArray[columns][rows].getPit())                               // If there is a pit and a wumpus
                            && (propertiesArray[columns][rows].getWumpus())) {
                        player.takeLife();
                        System.out.printf("   |%7s%7s", CellProperties.PRINTPIT, CellProperties.PRINTWUMPUS);
                    }

                    if (propertiesArray[columns][rows].getGold()) {                             // If there is gold
                        player.setGameWon();
                        System.out.printf("   |%14s", CellProperties.PRINTGOLD);
                    }

                    if (!propertiesArray[columns][rows].getWumpus()                             // If there is no pit, gold or wumpus
                            && !propertiesArray[columns][rows].getPit()
                            && !propertiesArray[columns][rows].getGold()) {

                        System.out.printf("   |%14s", CellProperties.EMPTYCELL);
                    }
                } else {
                    System.out.printf("   |%14s", CellProperties.EMPTYCELL);
                }
            } else {                                                                            // If this is any other column than the first column
                if ((player.getX() == columns) && (player.getY() == rows)) {
                    if ((propertiesArray[columns][rows].getWumpus())                            // If there is a wumpus and not a pit
                            && (!propertiesArray[columns][rows].getPit())) {
                        player.takeLife();
                        System.out.printf("|%14s", CellProperties.PRINTWUMPUS);
                    }

                    if ((propertiesArray[columns][rows].getPit())                               // If there is a pit and not a wumpus
                            && (!propertiesArray[columns][rows].getWumpus())) {
                        player.takeLife();
                        System.out.printf("|%14s", CellProperties.PRINTPIT);
                    }

                    if ((propertiesArray[columns][rows].getPit())                               // If there is a pit and a wumpus
                            && (propertiesArray[columns][rows].getWumpus())) {
                        player.takeLife();
                        System.out.printf("|%7s%7s", CellProperties.PRINTPIT, CellProperties.PRINTWUMPUS);
                    }

                    if (propertiesArray[columns][rows].getGold()) {                             // If there is gold
                        player.setGameWon();
                        System.out.printf("|%14s", CellProperties.PRINTGOLD);
                    }

                    if (!propertiesArray[columns][rows].getWumpus()                             // If there is no pit, gold or wumpus
                            && !propertiesArray[columns][rows].getPit()
                            && !propertiesArray[columns][rows].getGold()) {

                        System.out.printf("|%14s", CellProperties.EMPTYCELL);
                    }
                } else {
                    System.out.printf("|%14s", CellProperties.EMPTYCELL);
                }
            }
        }

        System.out.println("|");

        for (columns = 0; columns < player.getGridSize(); columns++) {
            if (columns == 0) {                                                                 // If this is the first column
                if ((player.getX() == columns) && (player.getY() == rows)) {
                    if (propertiesArray[columns][rows].getShimmer()) {                          // If there is a shimmer
                        System.out.printf("   |%4s%10s", player.getHead(), CellProperties.PRINTSHIMMER);
                    } else {                                                                    // If there is NOT a shimmer
                        System.out.printf("   |%4s%10s", player.getHead(), CellProperties.EMPTYCELL);
                    }
                } else {
                    System.out.printf("   |%14s", CellProperties.EMPTYCELL);
                }
            } else {                                                                            // If this is any other column than the first column
                if ((player.getX() == columns) && (player.getY() == rows)) {
                    if (propertiesArray[columns][rows].getShimmer()) {                          // If there is a shimmer
                        System.out.printf("|%4s%10s", player.getHead(), CellProperties.PRINTSHIMMER);
                    } else {                                                                    // If there is NOT a shimmer
                        System.out.printf("|%4s%10s", player.getHead(), CellProperties.EMPTYCELL);
                    }
                } else {
                    System.out.printf("|%14s", CellProperties.EMPTYCELL);
                }
            }
        }

        System.out.println("|");

        for (columns = 0; columns < player.getGridSize(); columns++) {
            if (columns == 0) {                                                                 // If this is the first column
                if ((player.getX() == columns) && (player.getY() == rows)) {
                    if (propertiesArray[columns][rows].getSmell()) {                            // If there is a smell
                        System.out.printf("%2d |%4s%10s", rows, player.getTorso(), CellProperties.PRINTSMELL);
                    } else {                                                                    // If there is NOT a smell
                        System.out.printf("%2d |%4s%10s", rows, player.getTorso(), CellProperties.EMPTYCELL);
                    }
                } else {
                    System.out.printf("%2d |%14s", rows, CellProperties.EMPTYCELL);
                }
            } else {                                                                            // If this is any other column than the first column
                if ((player.getX() == columns) && (player.getY() == rows)) {
                    if (propertiesArray[columns][rows].getSmell()) {                            // If there is a smell
                        System.out.printf("|%4s%10s", player.getTorso(), CellProperties.PRINTSMELL);
                    } else {                                                                    // If there is NOT a smell
                        System.out.printf("|%4s%10s", player.getTorso(), CellProperties.EMPTYCELL);
                    }
                } else {
                    System.out.printf("|%14s", CellProperties.EMPTYCELL);
                }
            }
        }

        System.out.println("|");

        for (columns = 0; columns < player.getGridSize(); columns++) {
            if (columns == 0) {                                                                 // If this is the first column
                if ((player.getX() == columns) && (player.getY() == rows)) {
                    if (propertiesArray[columns][rows].getBreeze()) {                           // If there is a breeze
                        System.out.printf("   |%4s%10s", player.getLegs(), CellProperties.PRINTBREEZE);
                    } else {                                                                    // If there is NOT a breeze
                        System.out.printf("   |%4s%10s", player.getLegs(), CellProperties.EMPTYCELL);
                    }
                } else {
                    System.out.printf("   |%14s", CellProperties.EMPTYCELL);
                }
            } else {                                                                            // If this is any other column than the first column
                if ((player.getX() == columns) && (player.getY() == rows)) {
                    if (propertiesArray[columns][rows].getBreeze()) {                           // If there is a breeze
                        System.out.printf("|%4s%10s", player.getLegs(), CellProperties.PRINTBREEZE);
                    } else {                                                                    // If there is NOT a breeze
                        System.out.printf("|%4s%10s", player.getLegs(), CellProperties.EMPTYCELL);
                    }
                } else {
                    System.out.printf("|%14s", CellProperties.EMPTYCELL);
                }
            }
        }

        System.out.println("|");

        for (columns = 0; columns < player.getGridSize(); columns++) {
            if (columns == 0) {                                                                 // If this is the first column
                System.out.printf("   |%14s", CellProperties.EMPTYCELL);
            } else {                                                                            // If this is any other column than the first column
                System.out.printf("|%14s", CellProperties.EMPTYCELL);
            }
        }

        System.out.println("|");

        System.out.print(" ");
        printElement(" ", "-");
    } // End of printProperty Method

    /**
     * Generates a random number
     *
     * @param lowerLimit (int) The lower end of game grid
     * @param upperLimit (int) The upper end of game grid
     *
     * @return returnValue (int) Returns a random number
     */
    public static int getRandom(int lowerLimit, int upperLimit) {

        int returnValue;

        returnValue = (int) ((Math.random() * (upperLimit - lowerLimit + 1)) + lowerLimit);

        return returnValue;

    } // End of GetRandom method

    /**
     * Start of Welcome message method - Provides friendly messages to the users
     *
     * @param kbInput (Scanner) Scanner object for keyboard input
     * @param messageOptionString (String) Specifies which message to use
     *
     */
    private static void displayMessage(String messageOptionString) {

        switch (messageOptionString) {
            case "WelcomeMessage":                                                              // Welcome message 

                System.out.println();
                System.out.println("**********************************************************************************");
                System.out.println("*                                                                                *");
                System.out.println("*                       Welcome to the Hunt the Wumpus Game                      *");
                System.out.println("*                                                                                *");
                System.out.println("**********************************************************************************");
                System.out.println("*                                                                                *");
                System.out.println("*  NOTE: For best results, please use a display with at least 130 columns        *");
                System.out.println("*                                                                                *");
                System.out.println("*      The goal of the game is to find the gold.  You will enter column row      *");
                System.out.println("*    combinations which will move your character, but be careful as there are    *");
                System.out.println("*       many dangers in Wumpusland.  These dangers include bottomless pits       *");
                System.out.println("*  and a dangerous creature called, you guessed it, the Wumpus.  Not to worry    *");
                System.out.println("*     thought because you will be warned when you get close to one of them.      *");
                System.out.println("*                                                                                *");
                System.out.println("*       You will receive the following hints when you get next to a object       *");
                System.out.println("*                                                                                *");
                System.out.println("*       Wumpus          Bad             Loose Game              Smell            *");
                System.out.println("*       Pit             Bad             Loose Game              Breeze           *");
                System.out.println("*       Gold            Good            Win Game                Breeze           *");
                System.out.println("*                                                                                *");
                System.out.println("*                     Good luck, be careful and enjoy the game                   *");
                System.out.println("*                                                                                *");
                System.out.println("**********************************************************************************");

                displayMessage("AnyKey");                                                       // Display AnyKey message

                break;
            case "AnyKey":                                                                      // Any Key message

                System.out.println();
                System.out.print("Please press Enter to continue");
                kbInput.nextLine();
                System.out.println();

                break;
            case "FarewellMessage":                                                             // Farewell message

                System.out.println();
                System.out.println("**********************************************************************************");
                System.out.println("*                     Thank you for Hunting the Wumpus                           *");
                System.out.println("**********************************************************************************");
                System.out.println();

                break;
            case "Winner":                                                                      // Winner message

                System.out.print("     *** WINNER ***");

                break;
            case "WrongDataTypeMessage":                                                        // Wrong Data Type message

                System.out.println();
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("!               You entered an invalid move index, please try again              !");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println();

                displayMessage("AnyKey");
                insertLines();

                printGrid();

                break;
            case "InvalidCellSelected":                                                         // Wrong Data Type message

                System.out.println();
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("!             Your selected cell is out of bounds, you can only move             !");
                System.out.println("!                  North, South, East and West, please try again                 !");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println();

                displayMessage("AnyKey");
                insertLines();

                insertLines();
                printGrid();

                break;
            case "WonGame":                                                                     // CONGRATULATIONS Message
                System.out.println("*************************************************************************************************************************");
                System.out.println("*                                                       YOU WON!!!                                                      *");
                System.out.println("*                                                                                                                       *");
                System.out.println("*   CCCC    OOOO   N    N   GGGG   RRRR    AAAA  TTTTTTT  U    U  L        AAAA  TTTTTTT IIIIIII OOOO   N    N   SSSS   *");
                System.out.println("*  C    C  O    O  NN   N  G    G  R   R  A    A    T     U    U  L       A    A    T       I   O    O  NN   N  S    S  *");
                System.out.println("*  C       O    O  N N  N  G       R   R  A    A    T     U    U  L       A    A    T       I   O    O  N N  N  S       *");
                System.out.println("*  C       O    O  N  N N  G  GGG  RRRR   A AA A    T     U    U  L       A AA A    T       I   O    O  N  N N   SSSS   *");
                System.out.println("*  C       O    O  N   NN  G    G  R R    A    A    T     U    U  L       A    A    T       I   O    O  N   NN       S  *");
                System.out.println("*  C    C  O    O  N    N  G    G  R  R   A    A    T     U    U  L       A    A    T       I   O    O  N    N  S    S  *");
                System.out.println("*   CCCC    OOOO   N    N   GGGG   R   R  A    A    T      UUUU   LLLLLL  A    A    T    IIIIIII OOOO   N    N   SSSS   *");
                System.out.println("*                                                                                                                       *");
                System.out.println("*************************************************************************************************************************");

                break;
            case "LostGame":                                                                    // GAME OVER Message
                System.out.println("**********************************************************************************");
                System.out.println("*                                                                                *");
                System.out.println("*          GGGG     AAAA    MM MM    EEEEE     OOOO   V    V   EEEEE  RRRR       *");
                System.out.println("*         G    G   A    A  M  M  M  E         O    O  V    V  E       R   R      *");
                System.out.println("*         G        A    A  M  M  M  E         O    O  V    V  E       R   R      *");
                System.out.println("*         G  GGG   A AA A  M     M   EEE      O    O  V    V   EEE    RRRR       *");
                System.out.println("*         G    G   A    A  M     M  E         O    O  V    V  E       R R        *");
                System.out.println("*         G    G   A    A  M     M  E         O    O   V  V   E       R  R       *");
                System.out.println("*          GGGG    A    A  M     M   EEEEE     OOOO     VV     EEEEE  R   R      *");
                System.out.println("*                                                                                *");
                System.out.println("**********************************************************************************");

                break;
            default:
                break;
        } // End messageOptionString switch
    } // End DisplayMessage method

    private static void insertLines() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    /**
     * ExceptionHandler Method - Handle exceptions
     *
     * @param kbInput (Scanner) Scanner object for keyboard input
     * @param thrownException (String) Composed string specifying how to handle
     * exception
     *
     */
    private static void exceptionHandler(String thrownException) {

        String sourceString = new String(thrownException.substring(0, thrownException.indexOf(",") - 1));
        String switchString = new String(thrownException.substring((thrownException.lastIndexOf(".") + 1)));

        switchString = switchString.substring(0, switchString.indexOf(":"));

        switch (switchString) {                                                                   // Handles input exceptions
            case "InputMismatchException":
                displayMessage("WrongDataTypeMessage");

                break;
            case "NumberFormatException":
                displayMessage("WrongDataTypeMessage");

                break;
            case "ArrayIndexOutOfBoundsException":
                displayMessage("WrongDataTypeMessage");

                break;
            case "StringIndexOutOfBoundsException":
                displayMessage("WrongDataTypeMessage");

                break;
            default:
                System.out.println("Unrecognized Error Received In " + sourceString + " : " + switchString);
                break;
        }
    } // End of ExceptionHandler method    

}
