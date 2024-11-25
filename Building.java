public class Building extends Place{
    
    private boolean unlocked;
    private int numFloors;
    private String insideDescription;

    public Building(String name, String description, Item item, NPC npc, boolean unlocked, int numFloors, String insideDescription){
        super(name, description, item, npc);
        this.unlocked = unlocked;
        this.numFloors = numFloors;
        this.insideDescription = insideDescription;
    }

    public boolean isUnlocked(){
        return unlocked;
    }

    public int getNumFloors(){
        return this.numFloors;
    }
}
