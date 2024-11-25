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

    public String describe(){
        return this.description;
    }

    public Item findItemInPlace(String item){
        for(Item i: this.items){
            if(i.getName().equals(item)){
                return i;
            }
        }
        return null;
    }
}