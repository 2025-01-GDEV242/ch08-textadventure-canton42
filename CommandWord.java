/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * Added commands:
 * BACK: returns player to previous room.
 * TAKE: takes an item in the room.
 * DROP: drops an item in the player's bag.
 * BAG: shows the player's inventory.
 * LOOK: show's the player's current location.
 * 
 * @author  Nolan Canto
 * @version 2025.04.02
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), BACK("back"), TAKE("take"), DROP("drop"), BAG("bag"), UNKNOWN("?");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
