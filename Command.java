/**
 * Class representing a command issued by the player.
 * Contains a command word and an optional second word.
 */
public class Command {
    private String commandWord;
    private String secondWord;
    private String thirdWord;

    public Command(String commandWord, String secondWord, String thirdWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }
    
    public String getThirdWord() {
        return thirdWord;
    }

    public boolean isUnknown() {
        return (commandWord == null);
    }

    public boolean hasSecondWord() {
        return (secondWord != null);
    }
    
    public boolean hasThirdWord() {
        return (thirdWord != null);
    }
}
