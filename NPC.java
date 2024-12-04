import java.util.Scanner;

public class NPC{
    private String name;
    private int strengthLevel;
    private String description;
    public boolean conversable;

    public NPC(String name, int strengthLevel, String description, boolean conversable){
        this.name = name;
        this.strengthLevel = strengthLevel;
        this.description = description;
        this.conversable = conversable;
    }

    public void converse(){
        
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