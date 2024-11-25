public class Item {
    
    private String name;
    private int dangerLevel;

    public Item(String name, int dangerLevel) {
        this.name = name;
        this.dangerLevel = dangerLevel;
    }

    public String getName() {
        return this.name;
    }
    
    public int getDangerLevel() {
        return this.dangerLevel;
    }
}
