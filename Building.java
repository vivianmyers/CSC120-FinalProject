public class Building extends Place {
    // Attributes
    public boolean unlocked; // Whether or not a building is unlocked/enterable without a key
    private String insideDescription; // Description of building inside
    private boolean hasCharacter; // Whether or not the player is inside the building

    /**
     * Constructs a building by calling the super constructor (call to place)
     * @param name of the building
     * @param description of the outside of the building
     * @param item at that place
     * @param npc at that place
     * @param unlocked t/f if the building is unlocked
     * @param insideDescription description of inside of the building
     * @param forcedConversation whether or not conversation is automatic
     */
    public Building(String name, String description, Item item, NPC npc, boolean unlocked, String insideDescription,
            boolean forcedConversation) {
        super(name, description, item, npc, forcedConversation);
        this.unlocked = unlocked;
        this.insideDescription = insideDescription;
    }

    /**
     * Overide the place describe method
     * If the character is inside the building it describes the inside, otherwise it calls the place describe method
     * @return String for the description
     */
    public String describe() {
        String desc = "";
        if (this.hasCharacter) { // inside
            desc += insideDescription;
            if (this.getNPC() != null) {
                desc += " ";
                desc += this.getNPC().getDescription();
                desc += " ";
            }

            if (super.items.size() != 0) {
                for (Item i : this.items) {
                    if (i != null) {
                        desc += i.getDescription();
                    }
                }
            }

        } else { // outside
            desc = super.getDescription();
        }
        return desc;
    }

    /**
     * Accessor for unlocked attribute
     * @return t/f for unlocked
     */
    public boolean isUnlocked() {
        return unlocked;
    }

    /**
     * Accessor for npc (overide place method)
     * @return NPC for npc at that place
     */
    public NPC getNPC() {
        return super.getNPC();
    }

    /**
     * Override place method to set status based on input
     * @param status t/f for locked or unlocked
     */
    @Override
    public void setLockStatus(boolean status) {
        super.setLockStatus(status);
        this.unlocked = status;
    }

    /**
     * Accessor for inside description
     * @return String for inside description
     */
    public String getInsideDesc1() {
        return insideDescription;
    }

    /**
     * Set character to inside or outside the building
     * @param inside t/f if the character is in or out of the building
     */
    public void setCharacter(boolean inside) {
        this.hasCharacter = inside;
    }

}
