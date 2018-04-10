package huntthewumpus;

/*
 * CSCI-162 - Foundations of Computer Science II
 * Programming Assignment 5 - Wumpus Game
 * Class Description : This is the player object class of the Wumpus game.
 * Author            : Benjamin Kleynhans
 * Date              : April 4, 2017
 * Filename          : Player.java
 */

/**
 * <h1>
 * CSCI-162 - Foundations of Computer Science II
 * </h1>
 * <p>
 * This is the player object class of the Wumpus game.
 * </p>
 *
 * @version 1.0
 * @since April 4, 2017
 */

/* 
 * Start of main class, ConsoleWumpus class
 */
public class Player {

    private String name = "";
    private int gridSize = 4;

    private boolean stillAlive = true;
    private boolean gameWon = false;

    private int x_coord = 0;
    private int y_coord = (this.getGridSize() - 1);

    private static int arrows = 1;
    
    private static final String HEAD     = " O ";
    private static final String TORSO    = "/|\\";
    private static final String LEGS     = "/ \\";

    /**
     * Constructor 1 for Wumpus game
     */
    public Player() {

        this.setGridSize(4);

    }
    
    /**
     * Constructor 2 for the Wumpus game
     * 
     * @param username (String) Name of the user
     */    

    public Player(String username) {

        this.setName(username);
        this.setGridSize(4);

    }
    
    /**
     * Constructor 3 for the Wumpus game
     * 
     * @param username (String) Name of the user
     * @param gridSize (int) Size of the grid
     */

    public Player(String username, int gridSize) {

        this.setName(username);
        this.setGridSize(gridSize);
    }
    
    /**
     * Set the name of the player
     * 
     * @param inputName (String) 
     */

    public void setName(String inputName) {
        this.name = inputName;
    }

    /**
     * Returns the name of the player
     * 
     * @return this.name (String)
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Sets the size of the game grid
     * 
     * @param inputGridSize (int)
     */

    public void setGridSize(int inputGridSize) {

        if ((inputGridSize >= 4) && (inputGridSize <= 8)) {

            this.gridSize = inputGridSize;
            this.setX(0);
            this.setY(this.getGridSize() - 1);

        }
    }
    
    /**
     * Returns the size of the game grid
     * 
     * @return (int)
     */

    public int getGridSize() {
        return this.gridSize;
    }
    
    /**
     * Takes the life of the player
     */

    public void takeLife() {
        this.stillAlive = false;
    }
    
    /**
     * Returns whether the player is alive
     * 
     * @return this.stillAlive (boolean) True when alive
     */

    public boolean isAlive() {
        return this.stillAlive;
    }
    
    /**
     * Sets the game to WON status
     */

    public void setGameWon() {
        this.gameWon = true;
    }
    
    /**
     * Returns whether the game is won
     * 
     * @return this.gameWon (boolean) True when won
     */

    public boolean getGameWon() {
        return this.gameWon;
    }
    
    /**
     * Set the X-Coordinate of the player
     * 
     * @param inputX (int)
     */

    public void setX(int inputX) {
        this.x_coord = inputX;
    }
    
    /**
     * Returns the X-Coordinate of the player
     * 
     * @return this.x_coord (int)
     */

    public int getX() {
        return this.x_coord;
    }
    
    /**
     * Set the Y-Coordinate of the player
     * 
     * @param inputY (int)
     */

    public void setY(int inputY) {
        this.y_coord = inputY;
    }
    
    /**
     * Returns the Y-Coordinate of the player
     * 
     * @return this.y_coord (int)
     */

    public int getY() {
        return this.y_coord;
    }
    
    /**
     * Move the player up one square
     */

    public void moveUp() {

        if (this.getX() != 0) {
            this.setX(this.getX() - 1);
        }
    }
    
    /**
     * Move the player down one square
     */

    public void moveDown() {

        if (this.getX() != (this.getGridSize() - 1)) {
            this.setX(this.getX() + 1);
        }
    }
    
    /**
     * Move the player left one square
     */

    public void moveLeft() {

        if (this.getY() != 0) {
            this.setY(this.getY() - 1);
        }
    }
    
    /**
     * Move the player right one square
     */

    public void moveRight() {

        if (this.getY() != (this.getGridSize() - 1)) {
            this.setY(this.getY() + 1);
        }
    }
    
    /**
     * Return the number of arrows the player has left
     * 
     * @return arrows (int)
     */
    
    public int getArrows() {
        return arrows;
    }
    
    /**
     * Use an arrow (arrows reduced by one)
     */

    public void useArrow() {

        if (arrows == 1) {
            arrows--;
        } else {
            throw new IllegalArgumentException("There are no more arrows left");
        }
    }
    
    /**
     * Return the number of arrows the user has left
     * 
     * @return arrows (int)
     */

    public static int getArrow() {
        return arrows;
    }
    
    /**
     * Return the "Head" character
     * 
     * @return HEAD (String)
     */
    
    public static String getHead() {
        return HEAD;
    }
    
    /**
     * Return the "Torso" characters
     * 
     * @return TORSO (String)
     */
    
    public static String getTorso() {
        return TORSO;
    }
    
    /**
     * Return the "Legs" characters
     * 
     * @return LEGS (String)
     */
    
    public static String getLegs() {
        return LEGS;
    }
    
    /**
     * Returns all the properties of the object
     * 
     * @return returnValue (String) All properties of object
     */
    
    @Override
    public String toString() {

        //String returnValue = new String();
        StringBuilder sb = new StringBuilder();
        
        sb.append("name         : ").append(this.getName()).append(System.getProperty("line.separator"));
        sb.append("gridsize     : ").append(this.getGridSize()).append(System.getProperty("line.separator"));
        sb.append("stillAlive   : ").append(this.isAlive()).append(System.getProperty("line.separator"));
        sb.append("gameWon      : ").append(this.getGameWon()).append(System.getProperty("line.separator"));
        sb.append("x_coord      : ").append(this.getX()).append(System.getProperty("line.separator"));
        sb.append("y_coord      : ").append(this.getY()).append(System.getProperty("line.separator"));
        sb.append("arrows       : ").append(this.getArrows()).append(System.getProperty("line.separator"));

        return sb.toString();

    }
}