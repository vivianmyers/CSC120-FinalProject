public class Building extends Place{
    
    public boolean unlocked;
    private String insideDescription;
    private boolean hasCharacter;
    

    public Building(String name, String description, Item item, NPC npc, boolean unlocked, String insideDescription, boolean forcedConversation){
        super(name, description, item, npc, forcedConversation);
        this.unlocked = unlocked;
        this.insideDescription = insideDescription;
    }

    public String describe(){
        String desc = "";
        if(this.hasCharacter){ //inside
            desc += insideDescription;
            if(this.getNPC() != null){
                desc += " ";
                desc += this.getNPC().getDescription();
                desc += " ";
            }

            if(super.items.size()!= 0){
                for(Item i : this.items){
                    if(i != null){
                        desc += i.getDescription();
                    }
                }
            }

        }else{ //outside
            desc = super.getDescription();
        }
        return desc;
    }

    public boolean isUnlocked(){
        return unlocked;
    }

    public NPC getNPC(){
        return super.getNPC();
    }

    public void setLockStatus(boolean status){
        unlocked = status;
    }

    public String getInsideDesc1(){
        return insideDescription;
    }

    public void setCharacter(boolean inside){
        this.hasCharacter = inside;
    }

}
