import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

/**
 * Class representing a room in the game.
 * Rooms can contain items and are connected to other rooms via exits.
 */
public class Room {
    private String description;
    private HashMap<String, Room> exits; // Stores exits of this room.
    private HashMap<String, Item> items; // Stores items in this room.
    private String roomname;
    private List<Npc> npcs;

    public Room(String description) {
        roomname = new String();
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
        npcs = new ArrayList<>();
    }

    public void addNpc(Npc npc) {
    npcs.add(npc);
    }
    
    public List<Npc> getNpcs() {
    return npcs;
    }
    
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString() + "\n" + getItemString();
    }

    private String getExitString() {
        String returnString = "\nExits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    private String getItemString() {
        if (items.isEmpty()) {
            return "No items here.";
        } else {
            String itemString = "Items:";
            for (Item item : items.values()) {
                itemString += " " + item.getName();
            }
            return itemString;
        }
    }
    
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    public void removeNpc(Npc npc) {
        npcs.remove(npc);
    }
    
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    public void removeItem(String itemName) {
        items.remove(itemName);
    }

    public Item getItem(String itemName) {
        return items.get(itemName);
    }
}
