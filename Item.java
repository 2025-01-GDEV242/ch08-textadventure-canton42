
/**
 * Creates an item with a description and weight (in lbs)
 * that will be placed in each room in the game.
 *
 * @author Nolan Canto
 * @version 2025.03.31
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

    public String getDescription() {
        return description;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public String getLongDescription() {
        return description + " (weight: " + weight + "lbs)";
    }
}
