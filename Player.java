
/**
 * Represents the player in the game. This class is a partial implementation
 * that currently tracks the player's location.
 *
 * @author Nolan Canto
 * @version 2025.04.02
 */
public class Player
{
    
    private Room currentRoom;

    /**
     * Create a player with a name and starting room.
     * 
     * @param startingRoom the initial room for the player.
     */
    public Player(Room startingRoom)
    {
        this.currentRoom = startingRoom;
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
     * Set the player's current room.
     * 
     * @param room the new current room.
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
    
}
