
/**
 * Represents the player in the game. This class is a partial implementation
 * that currently tracks the player's location and allows the player to pick
 * up and drop a single item.
 *
 * @author Nolan Canto
 * @version 2025.04.02
 */
public class Player
{
    
    private Room currentRoom;
    private Item carriedItem;

    /**
     * Create a player with a starting room and an empty hand.
     * 
     * @param startingRoom the initial room for the player.
     */
    public Player(Room startingRoom)
    {
        this.currentRoom = startingRoom;
        this.carriedItem = null;
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
     * Returns the player's current item.
     * 
     * @return current item
     */
    public Item getCarriedItem() {
        return carriedItem;
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
     * Allows the player to take an item from the current room.
     * 
     * @param itemName name of item to take.
     * @return true if item was taken, false otherwise.
     */
    public boolean takeItem(String itemName) {
        if (carriedItem != null) {
            System.out.println("You already have an item in your bag. " + 
            "\nYou must drop it first to carry a new one.");
            
            return false;
        }
        
        Item item = currentRoom.removeItem(itemName);
        if (item != null) {
            carriedItem = item;
            System.out.println("You took: " + item.getDescription());
            return true;
        }
        return false;
    }
    
    /**
     * Allows the player to drop their current item.
     */
    public void dropItem() {
        if (carriedItem == null) {
            System.out.println("Your bag is empty.");
            return;
        }
        
        currentRoom.addItem(carriedItem);
        System.out.println("You dropped: " + carriedItem.getDescription());
        carriedItem = null;
    }
    
}
