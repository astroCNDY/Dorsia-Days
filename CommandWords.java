/**
 * Class that holds a list of all valid command words.
 * Used for command recognition in the game.
 */
public class CommandWords {
    
    
    private static final String[] validCommands = {
        "go", "quit", "help", "take", "drop", "inventory", "use", "back", "talk_to", "give"
    };

    public boolean isCommand(String aString) {
        for (String command : validCommands) {
            if (command.equals(aString)) {
                return true;
            }
        }
        return false;
    }

    public void showAll() {
        for (String command : validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
