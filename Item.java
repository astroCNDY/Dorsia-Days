/**
 * Class representing an item in the game.
 * Each item has a name, weight, and a property indicating if it can be carried.
 */
public class Item {
    private String name;
    private double weight;
    private boolean canBeCarried;

    /**
     * Constructor to create an item.
     * @param name The name of the item.
     * @param weight The weight of the item.
     * @param canBeCarried Indicates if the item can be carried by the player.
     */
    public Item(String name, double weight, boolean canBeCarried) {
        this.name = name;
        this.weight = weight;
        this.canBeCarried = canBeCarried;
    }
    //below are return statements to return the different attribites of the items
    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public boolean canBeCarried() {
        return canBeCarried;
    }

    @Override
    public String toString() {
        return name + " (Weight: " + weight + " kg)";
    }
}
