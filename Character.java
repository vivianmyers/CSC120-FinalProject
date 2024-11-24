import java.util.ArrayList;
public class Character {
    
    private String name;
    private ArrayList<Item> inventory;
    private int curX;
    private int curY;
    private int numSheep;

    public Character(String name){
        this.name = name;
        this.inventory = new ArrayList<Item>();
        this.curX = 0;
        this.curY = 0;
        this.numSheep = 10;

    }

    public void grab(Item item){
        

    }

    public void drop(Item item){

    }

    public void go(String direction){
        

    }

    public void fight(Item item){

    }

    public void eat(Item item){

    }

}
