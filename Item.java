public class Item {

    /**
     * Attributes
     */
    private String name;
    private int dangerLevel;
    private String description;

    /**
     * Constructor to create item object
     * @param name String for name of object
     * @param dangerLevel int for how lethal the weapon is (used in fights)
     * @param description String to describe object
     */
    public Item(String name, int dangerLevel, String description) {
        this.name = name;
        this.dangerLevel = dangerLevel;
        this.description = description;
    }

    /**
     * Accessor for name of object
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor for danger level
     * @return int dangerLevel
     */
    public int getDangerLevel() {
        return this.dangerLevel;
    }

    /**
     * Accessor for description
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }
}
