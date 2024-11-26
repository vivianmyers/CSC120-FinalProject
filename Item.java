public class Item {
    
    private String name;
    private int dangerLevel;
    private String description;

    public Item(String name, int dangerLevel, String description) {
        this.name = name;
        this.dangerLevel = dangerLevel;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }
    
    public int getDangerLevel() {
        return this.dangerLevel;
    }

    public String getDescription(){
        return this.description;
    }
}
