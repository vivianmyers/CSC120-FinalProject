import java.util.ArrayList;
public class NPC{

    private String name;
    private int strengthLevel;

    public NPC(String name, int strengthLevel){
        this.name = name;
        this.strengthLevel = strengthLevel;
    }

    public String getName(){
        return this.name;
    }
    
    public int getStrengthLevel(){
        return this.strengthLevel;
    }
}