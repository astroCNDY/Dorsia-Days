import java.util.HashMap;
import java.util.Stack;
/**
 * Class representing the player.
 * Manages the inventory and weight limits for carried items.
 */
public class Player {
    private HashMap<String, Item> inventory;
    private double maxWeight;
    private double currentWeight;
    private Room currentRoom;
    private Stack<Room> roomHistory;
    

     public Player() {
        inventory = new HashMap<>();
        maxWeight = 10.0; // Maximum weight the player can carry.
        currentWeight = 0.0;
        roomHistory = new Stack<>();
    }

    public boolean addItem(Item item) {
        if (currentWeight + item.getWeight() <= maxWeight) {
            inventory.put(item.getName(), item);
            currentWeight += item.getWeight();
            return true;
        } else {
            return false;
        }
    }

    public HashMap<String, Item> getinventory()
    {
        return inventory;
    }
    
    public void removeItem(String itemName) {
        Item item = inventory.remove(itemName);
        if (item != null) {
            currentWeight -= item.getWeight();
        }
    }

    public Item getItem(String itemName) {
        return inventory.get(itemName);
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            for (Item item : inventory.values()) {
                System.out.println(item);
            }
            System.out.println("Total weight: " + currentWeight + " kg");
        }
    }
    
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    
    //Boolean returns to find different items are in inventory or not
    
    public boolean checkifkey()
    {
        if(inventory.containsKey("key"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }    
    public boolean checkifphone()
    {
        if(inventory.containsKey("phone"));
        {
            return true;
        }
    }
    public boolean checkifcharger()
    {    
        if(inventory.containsKey("charger"))
        {
            return true;
        }
        else
        {
            return false;
        }
    } 
    public boolean checkifkeypad()
    {    
        if(inventory.containsKey("safe_keypad"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
