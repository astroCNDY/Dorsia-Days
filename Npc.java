import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Npc {
    private String name;
    private String dialogue;
    private Item heldItem; // The item the NPC is holding
    private List<Room> rooms; // Rooms the NPC can move between
    private Room currentRoom;
    private List<Room> assignedRooms;

    public Npc(String name, String dialogue, Item heldItem, List<Room> assignedrooms) {
        this.name = name;
        this.dialogue = dialogue;
        this.heldItem = heldItem;
        this.rooms = rooms;
        this.assignedRooms = assignedRooms;
    }

    public String getName() {
        return name;
    }

    public String getDialogue() {
        return dialogue;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Item getHeldItem() {
        return heldItem;
    }

    public void setHeldItem(Item item) {
        this.heldItem = item;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
    
    public List<Room> getAssignedRooms() {
        return assignedRooms;
    }
    
    
}
