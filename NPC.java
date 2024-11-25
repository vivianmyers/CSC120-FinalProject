import java.util.ArrayList;
public class NPC{

    private String name;
    private int strengthLevel;

    public NPC(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
    
    public int getStrengthLevel(){
        return this.strengthLevel;
    }
}