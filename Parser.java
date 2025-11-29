import java.util.Scanner;

/**
 * Class that reads input from the player and interprets it as a command.
 */
public class Parser {
    private CommandWords commands;
    private Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        System.out.print("> ");
        String inputLine = reader.nextLine();
        String word1 = null;
        String word2 = null;
        String word3 = null;

        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next(); // Get first word.
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next(); // Get second word.
                if (tokenizer.hasNext()) {
                word3 = tokenizer.next(); // Get third word.
                }
            }
        }

        if (commands.isCommand(word1)) {
            return new Command(word1, word2, word3);
        } else {
            return new Command(null, word2, word3);
        }
    }

    public void showCommands() {
        commands.showAll();
    }
}
