public class NPC {

    /**
     * Attributes
     */
    private String name;
    private int strengthLevel;
    private String description;
    public boolean conversable;

    /**
     * Constructs an NPC object
     * @param name String for the name of the npc
     * @param strengthLevel int for the strength level (used in fights)
     * @param description String to describe npc
     * @param conversable t/f if the npc can respond to talk command
     */
    public NPC(String name, int strengthLevel, String description, boolean conversable) {
        this.name = name;
        this.strengthLevel = strengthLevel;
        this.description = description;
        this.conversable = conversable;
    }

    /**
     * Accessor for name attribute
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor for strength level attribute
     * @return int strength level
     */
    public int getStrengthLevel() {
        return this.strengthLevel;
    }

    /**
     * Accessor for description
     * @return String for description
     */
    public String getDescription() {
        return this.description;
    }
}