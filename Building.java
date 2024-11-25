public class Building extends Place{
    
    private boolean unlocked;

    public Building(String name, String description, Item item, NPC npc, boolean unlocked){
        super(name, description, item, npc);
        this.unlocked = unlocked;
    }

    public boolean enter(){
        if(this.unlocked){
            
        }
    }
}
