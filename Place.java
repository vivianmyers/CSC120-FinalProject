import java.util.ArrayList;

public class Place {

    /**
     * Attributes
     */
    private String name;
    private String description;
    public ArrayList<Item> items;
    private NPC npc;
    private boolean forcedConversation;

    /**
     * Constructs a place object
     * @param name of the place
     * @param description printed out when the player arrives at the place
     * @param item item at that place
     * @param npc at that place
     * @param forcedConversation t/f if the npc at that place speaks automatically
     */
    public Place(String name, String description, Item item, NPC npc, boolean forcedConversation) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.items.add(item);
        this.npc = npc;
        this.forcedConversation = forcedConversation;
    }

    /**
     * Accessor for name
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor for npc
     * @return NPC npc at that place
     */
    public NPC getNPC() {
        return this.npc;
    }

    /**
     * Accessor for description
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Describes the place with the npc and item
     * @return String concatenated place, npc, and item descriptions
     */
    public String describe() {
        String desc = description;
        if (this.npc != null) {
            desc += " ";
            desc += this.npc.getDescription();
            desc += " ";
        }
        if (this.items.size() != 0) {
            for (Item i : this.items) {
                if (i != null) {
                    desc += i.getDescription();
                }
            }
        }
        return desc;
    }

    /**
     * Finds an item in a place
     * @param item to be found
     * @return the item if its found or null if it isn't in that place
     */
    public Item findItemInPlace(String item) {
        for (Item i : this.items) {
            if (i != null && i.getName().equals(item)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Removes an npc from a place if they are killed
     */
    public void killNPC() {
        this.npc = null;
        this.forcedConversation = false;
    }

    /**
     * Checks if a place is unlocked 
     * @return always true because places can't lock
     */
    public boolean isUnlocked() {
        return true;
    }

    /**
     * Sets up method to be overwritten in building
     * @param status
     */
    public void setLockStatus(boolean status) {

    }

    /**
     * Sets up method to be overwritten in building
     * @param inside
     */
    public void setCharacter(boolean inside) {

    }

    /**
     * Accessor for forced conversation attribute
     * @return t/f if conversation is forced
     */
    public boolean getForced() {
        return this.forcedConversation;
    }

}