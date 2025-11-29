import java.util.Scanner;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main class for the "World of Zuul" game.
 * This class is part of the "World of Zuul" application, a simple text-based adventure game.
 * 
 * This class handles the primary game loop and user interactions.
 * 
 * @author  Michael Kölling
 * @version 2016.02.29
 */
public class Game {
    //declaration of instance variables
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Art art;
    public boolean win = false;
    private Interact interact;
    private Stack<Room> roomHistory;
    Room bathroom, bedroom1, dining, kitchen, bedroom2, living, storage, closet, study, transporter;
    String roomname;
    private Npc npc;
    private Room[] allRooms;
    private List<Npc> npcs;
    Npc allen, christy;
    private List<Npc> allNpcs = new ArrayList<>();

    public Game() {
        createRooms();
        parser = new Parser();
        player = new Player();
        interact = new Interact(this, player);
        roomHistory = new Stack<>();
    }

    private void createRooms() 
    {

        // Create the rooms.  //any time the description changes also check the check which room statements
        bathroom = new Room("in the bathroom room, blood runs red across the bathtub.\nCare products litter the counter, your killer has a penchant for Y$L homme");
        bedroom1 = new Room("in a dark bedroom. It seems like the master bedroom, a closet to the east and a closed door to the west.");
        dining = new Room("in the dining room, all the cutlery has been replaced by sharp steel knives.\n Doors in all directions, really makes you feel like you have a choise.");
        kitchen = new Room("in the kitchen, a rotten salad sits on the counter.\nThe kitchen is adorned in masterline professional appliances. A small door goes to the west, or return to the dining.");
        bedroom2 = new Room("in the guest bedroom, this is smaller but also tastefully decorated.");
        storage = new Room("in the storage");
        living = new Room("in the living room, the table still holds the wine glass you remember having earlier.\nThe floor is littered with the style section of the newspaper. Huey lewis and the news faintly plays in the background.\nAnd there stands the main door, 2 locks, 2 keys and the path to your freedom.\nFind them, don't fuck up.");
        closet = new Room("in the walk-in closet in the master bedroom.\nValtentino suits hang from the hangers, you catch a gimpse of a hanging plastic bag.\nYou notice a large Jean Paul Gaultier overnight bag on the floor\nThere seems to be a small trapdoor in the back.");
        study = new Room("in the study");
        transporter = new Room("in the transporter room, you will now get trasnported to a random room");

        // Initialize room exits.
        // initialise room exits
        //bathroom to bedroom
        bathroom.setExit("south", bedroom1);

        //bed 1 to dining and bathroom
        bedroom1.setExit("east", dining);
        bedroom1.setExit("north", bathroom);
        bedroom1.setExit("west", closet);
        
        
        //closet exits
        closet.setExit("east", bedroom1);
        closet.setExit("west", transporter);

        //dining to all 4 exits
        dining.setExit("west", bedroom1);
        dining.setExit("north", kitchen);
        dining.setExit("east", bedroom2);
        dining.setExit("south", living);

        //kitchen to dining and storage
        kitchen.setExit("south", dining);
        kitchen.setExit("east", storage);
        
        storage.setExit("west", kitchen);

        //guest bed to dining
        bedroom2.setExit("west", dining);
        
        //living to dining and outside
        living.setExit("north", dining);
        living.setExit("east", study);
        
        //study exit to living room
        study.setExit("west", living);
        
        //setting exits to transporter
        transporter.setExit("east", closet);

        currentRoom = bathroom;  // start game bathroom
        
        // Adding items to rooms.
        bathroom.addItem(new Item("phone", 2.0, true));
        bathroom.addItem(new Item("axe", 5.0, true));
        
        bedroom1.addItem(new Item("knife", 3.0, true));
        bedroom1.addItem(new Item("id_card", 2.0, true));
        study.addItem(new Item("key", 1.0, true));
        
        dining.addItem(new Item("diary", 4.0, true));
        
        closet.addItem(new Item("safe", 50.0, false));
        
        living.addItem(new Item("computer", 5.0, true));
        living.addItem(new Item("wine_glass", 2.0, true));
        living.addItem(new Item("main_door", 40.0, false));
        
        kitchen.addItem(new Item("wine", 3.0, true));
        kitchen.addItem(new Item("Fridge", 80.0, false));
        kitchen.addItem(new Item("oven", 1.0, true));
        kitchen.addItem(new Item("powder_baggie", 1.0, true));
        
        bedroom2.addItem(new Item("charger", 2.0, true));
        
        storage.addItem(new Item("safe_keypad", 6.0, true));
        

        currentRoom = bathroom;  // Start game outside.
        
        allRooms = new Room[]{bathroom, bedroom1, dining, kitchen, bedroom2, living, storage, closet, study, transporter};
        
        //Create NPCs
        List<Room> allenRooms = new ArrayList<>();
        allenRooms.add(bedroom2);
        allenRooms.add(dining); 
        allenRooms.add(living);
        
        List<Room> christyRooms = new ArrayList<>();
        christyRooms.add(study);
        christyRooms.add(storage);
        christyRooms.add(kitchen);
        
        Npc christy = new Npc("Christy", "Hello player.", new Item("key", 1.0, true), christyRooms);
        Npc allen = new Npc("Allen", "Is there a dog in this apartment.", new Item("paper", 1.0, true), allenRooms);

        
        allNpcs.add(christy);
        allNpcs.add(allen);

        bedroom2.addNpc(allen);
        study.addNpc(christy);
        
        allNpcs = List.of(christy, allen);
        
    }

    public Room getroom()
    {
        return currentRoom;
    }
    

    private void moveNpcs() {
    for (Npc npc : allNpcs) { // Assume allNpcs is a List of all NPCs in the game
        // Get the current room of the NPC
        Room currentRoom = npc.getCurrentRoom();
        if (currentRoom != null) {
            // Remove NPC from its current room
            currentRoom.removeNpc(npc);
        }

        // Get the list of assigned rooms for the NPC
        List<Room> assignedRooms = npc.getAssignedRooms();
        if (assignedRooms != null && !assignedRooms.isEmpty()) {
            // Move NPC to a random room from its assigned rooms
            Random random = new Random();
            int randomIndex = random.nextInt(assignedRooms.size());
            Room newRoom = assignedRooms.get(randomIndex);

            // Add the NPC to the new room
            newRoom.addNpc(npc);

            // Update the NPC's current room
            npc.setCurrentRoom(newRoom);
        }
    }
    }
    
    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Goodbye!");
            
    }

    private void printWelcome() {
        //Some ascii art
        art.image1();
        //The text first viewd when the game starts up.
        System.out.println();
        System.out.println("Welcome to Dorsia Days");
        System.out.println("Try to get out of this alive, pussies quit, you win.");
        System.out.println("Type 'help' if you need help. (it wont be very helpful)");
        System.out.println("Use commands: 'go', 'quit', 'help', 'take', 'use', 'drop', 'back' and 'inventory'");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        //switch statement being used for the diffrent commands. and their respective functions calling to the command class
        switch (commandWord) {
            case "go":
                goRoom(command);
                moveNpcs();
                break;
            case "help":
                printHelp();
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            case "take":
                takeItem(command);
                break;
            case "drop":
                dropItem(command);
                break;
            case "inventory":
                showInventory();
                break;
            case "use":
                useitem(command);
                break;
            case "back":
                goBack();
                break;
            case "talk_to":
                talkToNpc(command);
                break;
            case "give":
                giveToNpc(command);
                break;    
            default:
                System.out.println("I don't know what you mean...");
                break;
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. The last thing you remember is taking a sip of wine.");
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) 
        {
            System.out.println("There is no door!");
        } 
        
        else if (nextRoom == transporter)
        {
            int randomNumber = (int) (Math.random() * 8); // Generate random room index
            switch (randomNumber) {
                //The different random numbers are all linked to a certain room, so you are transported to a random room.
                case 0: nextRoom = living; break;
                case 1: currentRoom = dining; break;
                case 2: currentRoom = kitchen; break;
                case 3: currentRoom = storage; break;
                case 4: currentRoom = bedroom1; break;
                case 5: currentRoom = bedroom2; break;
                case 6: currentRoom = closet; break;
                case 7: currentRoom = bathroom; break;
                default: currentRoom = study; break;
            }
            roomname = "transporter";
            System.out.println("You have been transported to a random room!");
            System.out.println(currentRoom.getLongDescription());
        }
        
        else 
        {
            //This returns the diffent roomnames as a string
            if(nextRoom == living){roomname = "living";}
            else if(nextRoom == bedroom1){roomname = "bedroom1";}
            else if(nextRoom == bedroom2){roomname = "bedroom2";}
            else if(nextRoom == bathroom){roomname = "bathroom";}
            else if(nextRoom == dining){roomname = "dining";}
            else if(nextRoom == kitchen){roomname = "kitchen";}
            else if(nextRoom == study){roomname = "study";}
            else if(nextRoom == closet){roomname = "closet";}
            else if(nextRoom == storage){roomname = "stroage";}
            roomHistory.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription()+"\n");
            if (roomname .equals("closet")){interact.getsecondkey();}
        }
    }
    
    //
    private void goBack() 
    {
        
        if (roomHistory.isEmpty()) {
            System.out.println("You can't go back any further!");
            return;
        }
        currentRoom = roomHistory.pop(); // Pop the last room from the stack
        System.out.println("You move back to your earlier location.\n");
        System.out.println(currentRoom.getLongDescription());
    }
    
    private void talkToNpc(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Talk to whom?");
            return;
        }
    
        String npcName = command.getSecondWord();
        List<Npc> npcsInRoom = currentRoom.getNpcs();
    
        for (Npc npc : npcsInRoom) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                System.out.println("You talk to " + npc.getName() + ": " + npc.getDialogue());
                if (npc.getName() . equals("allen"))
                {
                    interact.talktoallen();
                }
                else if (npc.getName() . equals("christy"))
                {
                    interact.talktochristy();
                }
                return;
            }
        }
        System.out.println("There is no " + npcName + " here.");
    }


    
    private void giveToNpc(Command command) {
        if (!command.hasSecondWord() || command.getThirdWord() == null) {
            System.out.println("Give what to whom?");
            return;
        }

        String npcName = command.getSecondWord();
        String itemName = command.getThirdWord();
    
        Item item = player.getItem(itemName);
        if (item == null) {
            System.out.println("You don't have " + itemName + ".");
            return;
        }
    
        for (Npc npc : npcs) {
            if (npc.getName().equalsIgnoreCase(npcName) && npc.getCurrentRoom() == currentRoom) {
                npc.setHeldItem(item); // Give the item to the NPC
                player.removeItem(itemName); // Remove the item from the player's inventory
                System.out.println("You gave " + itemName + " to " + npcName + ".");
                return;
            }
        }

        System.out.println(npcName + " is not here.");
    }

    //useitem reads the item to be used and calls the item's respective function in the interact class
    private void useitem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
        }
        String itemName = command.getSecondWord();
        Item item = currentRoom.getItem(itemName);
        
        if (itemName.equals("key")) {
            interact.opendoor();
            return;
        } 
        else if (itemName.equals("charger")) {
            interact.chargephone();
            return;
        }    
        else if (itemName.equals("phone")) {
            interact.usephone();
            return;
        }
        else if (itemName.equals("id_card")) {
            interact.seeid();
            return;
        }
        else if (itemName.equals("safe_keypad")) {
            interact.usekeypad();
            return;
        }
        else if (itemName.equals("diary")) {
            interact.usediary();
            return;
        }
        else if (item == null) {
            System.out.println("Enter the right item to use.");
        }
        
    }
    
    private void takeItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }

        String itemName = command.getSecondWord();
        Item item = currentRoom.getItem(itemName);

        if (item == null) {
            System.out.println("There is no such item here.");
        } else if (!item.canBeCarried()) {
            System.out.println("You cannot carry this item.");
        } else if (!player.addItem(item)) {
            System.out.println("You are carrying too much weight.");
        } else {
            currentRoom.removeItem(itemName);
            System.out.println("You have taken the " + itemName + ".");
        }
    }
    
    
    
    
    private void dropItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }

        String itemName = command.getSecondWord();
        Item item = player.getItem(itemName);

        if (item == null) {
            System.out.println("You don't have such an item.");
        } else {
            player.removeItem(itemName);
            currentRoom.addItem(item);
            System.out.println("You have dropped the " + itemName + ".");
        }
    }

    private void showInventory() {
        System.out.println("You are carrying:");
        player.showInventory();
    }
    
    //check which room you are in.
    
    private String checkroomname(Command command)
    {
        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);
        if(nextRoom == living){
            return "Living";
        }
        else if(nextRoom == bedroom1){
            return "bedroom1";
        }
        else if(nextRoom == bedroom2){
            return "bedroom2";
        }
        else if(nextRoom == bathroom){
            return "bathroom";
        }
        else if(nextRoom == study){
            return "study";
        }
        else if(nextRoom == closet){
            interact.getsecondkey();
            return "closet";
        }
        else if(nextRoom == dining){
            return "dining";
        }
        else if(nextRoom == kitchen){
            return "kitchen";
        }
        else if(nextRoom == storage){
            return "storage";
        }
        else{
            return "";
        }
    }
    
    //returns roomname as a string to be used in the interact class
    public String getroomname()
    {
        String roomName;
        roomName = roomname;
        return roomName;
    }
    
    
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // Signal that we want to quit.
        }
    }
}
