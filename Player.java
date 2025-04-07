import java.util.ArrayList;
/**
 * Represents the player in the game. This class is a full implementation
 * that tracks the player's location and allows the player to pick
 * up and drop any number of items.
 *
 * @author Nolan Canto
 * @version 2025.04.02
 */
public class Player
{
    
    private Room currentRoom;
    private ArrayList<Item> bag;

    /**
     * Create a player with a starting room and an empty bag (inventory).
     * 
     * @param startingRoom the initial room for the player.
     */
    public Player(Room startingRoom)
    {
        this.currentRoom = startingRoom;
        this.bag = new ArrayList<>();
    }

    /**
     * Returns the current room.
     * 
     * @return current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * Returns the player's current bag (inventory)
     * 
     * @return current bag
     */
    public ArrayList<Item> getBag() {
        return bag;
    }
    
    /**
     * Set the player's current room.
     * 
     * @param room the new current room.
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
    
    /**
     * Allows the player to take items from the current room.
     * 
     * @param itemName name of item to take.
     * @return true if item was taken, false otherwise.
     */
    public boolean takeItem(String itemName) {
        Item item = currentRoom.removeItem(itemName);
        if (item != null) {
            bag.add(item);
            System.out.println("You took: " + item.getDescription());
            return true;
        }
        System.out.println("That item doesn't exist in this room.");
        return false;
    }
    
    /**
     * Allows the player to drop their items into the current room.
     * 
     * @param itemName name of item being dropped.
     */
    public boolean dropItem(String itemName) {
        for (Item item : bag) {
            if (item.getDescription().contains(itemName)) {
                bag.remove(item);
                currentRoom.addItem(item);
                System.out.println("You dropped: " + item.getDescription());
                return true;
            }
        }
        
        System.out.println("You are not carrying this item.");
        return false;
    }
    
    /**
     * shows the player's current bag (inventory)
     * 
     * @return if bag is empty
     */
    public void showBag() {
        if (bag.isEmpty()) {
            System.out.println("Your bag is empty.");
            return;
        }
        
        System.out.println("Your bag contains: ");
        for (Item item : bag) {
            System.out.println(" " + item.getLongDescription());
        }
    }
    
}
