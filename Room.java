import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * Each room will have multiple items within it.
 * 
 * @author  Nolan Canto
 * @version 2025.03.31
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items; //stores multiple items instead of one

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }
    
    /**
     * Adds an item to the room
     * 
     * @param item the item being added
     */
    public void addItem(Item item) {
        items.add(item);
    }
    
    /**
     * Gets the items in the room
     * 
     * @return the items in the room, null if there is none present.
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room and items in the form:
     *     You are in the kitchen.
     *     You see the following: a fruit basket (weight: 3lbs)
     *     Exits: north west
     * @return A long description of this room and the items
     */
    public String getLongDescription()
    {
        String returnString = "You are " + description + ".\n";
        
        if (!items.isEmpty()) {
            returnString += "You see the following:\n";
            for (Item item : items) {
                returnString += " " + item.getLongDescription() + "\n";
            }
        }
        
        returnString += getExitString();
        return returnString;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

