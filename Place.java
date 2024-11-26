import java.util.ArrayList;
public class Place{

    private String name;
    private String description;
    public ArrayList<Item> items;
    private NPC npc;

    public Place(String name, String description, Item item, NPC npc){
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.items.add(item);
        this.npc = npc;
    }

    public String getName(){
        return this.name;
    }

    public NPC getNPC(){
        return this.npc;
    }

    public String describe(){
        String desc = description;
        if(this.npc != null){
            desc += " ";
            desc += this.npc.getDescription();
            desc += " ";
        }
        if(this.items.size()!= 0){
            for(Item i : this.items){
                if(i != null){
                    desc += i.getDescription();
                }
            }
        }
        return desc;
    }

    public Item findItemInPlace(String item){
        for(Item i: this.items){
            if(i.getName().equals(item)){
                return i;
            }
        }
        return null;
    }

    public void killNPC(){
        this.npc = null;
    }

    public boolean isUnlocked(){
        return true;
    }
}