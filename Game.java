import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It has been expanded upon
 *  to have more rooms, items, a player inventory, more commands, and other
 *  improvements. 
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method. There is also a "main" method that will allow you to play the game
 *  outside of BlueJ.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, items, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Nolan Canto
 * @version 2025.04.02
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Stack<Room> roomHistory;
        
    /**
     * Creates the game, room history, player and their starting location, and initialises its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        roomHistory = new Stack<>();
        
        Room startingRoom = createRooms();
        
        player = new Player(startingRoom);
        
        
    }

    /**
     * Creates all rooms, their items, and links their exits together.
     */
    private Room createRooms()
    {
        Room outside, theater, pub, lab, office, gym, hallway, closet;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // extra rooms
        gym = new Room("in the gymnasium");
        hallway = new Room("in the main hallway");
        closet = new Room("in the utility closet"); 
        
        // create the items
        outside.addItem(new Item("key : a suspicious key", 1));
        outside.addItem(new Item("map : a ripped campus map", 0));
        
        theater.addItem(new Item("mentos : a pack of mentos", 0));
        theater.addItem(new Item("usb : a USB stick", 1));
        
        pub.addItem(new Item("soda : a bottle of soda", 1));
        pub.addItem(new Item("menu : a pub menu", 0));
        
        lab.addItem(new Item("textbook : a programming textbook", 5));
        lab.addItem(new Item("note : a mysterious sticky note", 0));
        
        office.addItem(new Item("folder : an office folder", 1));
        office.addItem(new Item("pencil : a pencil", 0));
        
        hallway.addItem(new Item("gum : a pack of gum", 0));
        hallway.addItem(new Item("book : a historical-fiction book", 4));
        
        closet.addItem(new Item("drill : a power drill", 5));
        closet.addItem(new Item("ladder : a huge foldable ladder", 30));
        
        gym.addItem(new Item("basketball : a deflated basketball", 1));
        gym.addItem(new Item("racket : a badminton racket", 0));
        
        
        // initialise room exits
        outside.setExit("north", hallway);
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        hallway.setExit("north", gym);
        hallway.setExit("south", outside);
        hallway.setExit("east", closet);
        
        closet.setExit("west", hallway);
        
        gym.setExit("south", hallway);
        
        return outside;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly (not so) boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
            
            case LOOK:
                look();
                break;
            
            case BACK:
                goBack();
                break;
            
            case TAKE:
                take(command);
                break;
                
            case DROP:
                drop(command);
                break;
            
            case BAG:
                player.showBag();
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:
    
    /**
     * Prints the player's current room and the items present in the room.
     */
    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    /**
     * Try to go back to the previous room using stack
     */
    private void goBack() {
        if (roomHistory.isEmpty()) {
            System.out.println("You have not traveled anywhere yet.");
            return;
        }
        
        Room previousRoom = roomHistory.peek();
        player.setCurrentRoom(previousRoom);
        roomHistory.pop();
        
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param command the user's command.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            roomHistory.push(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Allows the player to take an item in the current room. 
     * If the player does not have a second word in their command, 
     * no items will be picked up.
     * 
     * @param command the user's command.
     */
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
        } else {
            player.takeItem(command.getSecondWord());
        }
    }
    
    /**
     * Allows the player to drop an item in the current room.
     * If the player does not have a second word in their command,
     * no items will be dropped.
     * 
     * @param command the user's command.
     */
    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
        } else {
            player.dropItem(command.getSecondWord());
        }
    }
    
    /**
     * main method that allows the game to be played outside of BlueJ.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
