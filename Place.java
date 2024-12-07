import java.util.ArrayList;

public class Place {

    private String name;
    private String description;
    public ArrayList<Item> items;
    private NPC npc;
    private boolean forcedConversation;

    public Place(String name, String description, Item item, NPC npc, boolean forcedConversation) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.items.add(item);
        this.npc = npc;
        this.forcedConversation = forcedConversation;
    }

    public String getName() {
        return this.name;
    }

    public NPC getNPC() {
        return this.npc;
    }

    public String getDescription() {
        return description;
    };

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

    public Item findItemInPlace(String item) {
        for (Item i : this.items) {
            if (i != null && i.getName().equals(item)) {
                return i;
            }
        }
        return null;
    }

    public void killNPC() {
        this.npc = null;
        this.forcedConversation = false;
    }

    public boolean isUnlocked() {
        return true;
    }

    public int getNumFloors() {
        return 1;
    }

    public void setlockStatus(boolean status) {
    }

    public String getInsideDesc1() {
        return "";
    }

    public String getInsideDesc2() {
        return "";
    }

    public void setCharacter(boolean inside) {

    }

    public boolean getForced() {
        return this.forcedConversation;
    }

}