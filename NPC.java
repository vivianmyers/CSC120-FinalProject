import java.util.ArrayList;
public class NPC{

    private String name;
    private int strengthLevel;
    private String description;

    public NPC(String name, int strengthLevel, String description){
        this.name = name;
        this.strengthLevel = strengthLevel;
        this.description = description;
    }

    public String getName(){
        return this.name;
    }
    
    public int getStrengthLevel(){
        return this.strengthLevel;
    }

    public String getDescription(){
        return this.description;
    }
}