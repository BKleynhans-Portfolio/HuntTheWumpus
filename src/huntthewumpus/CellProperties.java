package huntthewumpus;

/*
 * CSCI-162 - Foundations of Computer Science II
 * Programming Assignment 5 - Wumpus Game
 * Class Description : This is the cell properties object class of the Wumpus game.
 * Author            : Benjamin Kleynhans
 * Date              : April 4, 2017
 * Filename          : CellProperties.java
 */

/**
 * <h1>
 * CSCI-162 - Foundations of Computer Science II
 * </h1>
 * <p>
 * This is the cell properties object class of the Wumpus game.
 * </p>
 *
 * @version 1.0
 * @since April 4, 2017
 */

/* 
 * Start of main class, ConsoleWumpus class
 */

public class CellProperties {

    private boolean gold    = false;
    private boolean wumpus  = false;
    private boolean pit     = false;    
    private boolean shimmer = false;
    private boolean smell   = false;
    private boolean breeze  = false;

    public static final String PRINTGOLD    = "Gold";
    public static final String PRINTWUMPUS  = "Wumpus";
    public static final String PRINTPIT     = "Pit";
    public static final String PRINTSHIMMER = "Shimmer";
    public static final String PRINTSMELL   = "Smell";
    public static final String PRINTBREEZE  = "Breeze";
    public static final String EMPTYCELL    = "";

    /**
     * Empty Constructor
     */
    
    public CellProperties() {

    }

    /**
     * Set the gold property to true
     */
    public void setGold() {
        this.gold = true;
    }

    /**
     * Returns the gold property
     * 
     * @return this.gold (boolean) True if set
     */
    public boolean getGold() {
        return this.gold;
    }
    
    /**
     * Set the wumpus property to true
     */

    public void setWumpus() {
        this.wumpus = true;
    }
    
    /**
     * Returns the wumpus property
     * 
     * @return this.wumpus (boolean) True if set
     */

    public boolean getWumpus() {
        return this.wumpus;
    }
    
    /**
     * Set the pit property to true
     */

    public void setPit() {
        this.pit = true;
    }
    
    /**
     * Returns the pit property
     * 
     * @return this.pit (boolean) True if set
     */

    public boolean getPit() {
        return this.pit;
    }
    
    /**
     * Sets the breeze property to true
     */

    public void setBreeze() {
        this.breeze = true;
    }
    
    /**
     * Returns the breeze property
     * 
     * @return this.breeze (boolean) True if set
     */

    public boolean getBreeze() {
        return this.breeze;
    }
    
    /**
     * Sets the shimmer property to true
     */

    public void setShimmer() {
        this.shimmer = true;
    }
    
    /**
     * Returns the shimmer property
     * 
     * @return this.shimmer (boolean) True if set
     */

    public boolean getShimmer() {
        return this.shimmer;
    }
    
    /**
     * Sets the smell property to true
     */

    public void setSmell() {
        this.smell = true;
    }
    
    /**
     * Returs the smell property
     * 
     * @return this.smell (boolean) true if set
     */

    public boolean getSmell() {
        return this.smell;
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
        
        sb.append("Gold     : ").append(this.getGold()).append(System.getProperty("line.separator"));
        sb.append("Wumpus   : ").append(this.getWumpus()).append(System.getProperty("line.separator"));
        sb.append("Pit      : ").append(this.getPit()).append(System.getProperty("line.separator"));
        sb.append("Shimmer  : ").append(this.getShimmer()).append(System.getProperty("line.separator"));
        sb.append("Smell    : ").append(this.getSmell()).append(System.getProperty("line.separator"));
        sb.append("Breeze   : ").append(this.getBreeze()).append(System.getProperty("line.separator"));

        return sb.toString();

    }
}
