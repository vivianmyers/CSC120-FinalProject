import java.util.ArrayList;
public class Place{

    String name;
    String description;
    int placeX;
    int placeY;
    ArrayList<Item> items;
    NPC npc;

    public Place(String name, String description, int x, int y, ArrayList<Item> items, NPC npc){
        this.name = name;
        this.description = description;
        this.placeX = x;
        this.placeY = y;
        this.items = items;
        this.npc = npc;
    }

    public String describe(){
        return this.description;
    }
}