
/**
 * Creates an item with a description and weight (in lbs)
 * that will be placed in each room in the game.
 *
 * @author Nolan Canto
 * @version 2025.04.02
 */
public class Item
{
    
    private String description;
    private int weight;

    /**
     * Constructor for Item class
     * 
     * @param description returns the description field
     * @param weight returns the weight field
     */
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * gets description of item.
     * 
     * @return item description.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * gets weight of item.
     * 
     * @return weight of item.
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * gets long description of item (in pounds).
     * 
     * return long description of item.
     */
    public String getLongDescription() {
        return description + " (weight: " + weight + "lbs)";
    }
}
