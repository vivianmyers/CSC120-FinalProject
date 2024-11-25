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

    public int getCurX(){
        return this.curX;
    }

    public int getCurY(){
        return this.curY;
    }

    public void grab(String item){
        if(this.checkInventory(item)!=null){
            this.inventory.remove(this.checkInventory(item));
            
        }else{
            throw new RuntimeException("You cannot drop an item you do not own.");
        }
    }

    public void drop(String item){
        if(this.checkInventory(item)!=null){
            this.inventory.remove(this.checkInventory(item));
            
        }else{
            throw new RuntimeException("You cannot drop an item you do not own.");
        }
    }

    public void go(String direction){
        switch(direction) {
            case "NORTH":
                this.curX--;
                break;
            case "SOUTH":
                this.curX++;
                break;
            case "EAST":
                this.curY++;
                break;
            case "WEST": 
                this.curY--;
                break;
        }

    }

    public void fight(Item item){

    }

    public boolean eat(String item){
        for (Item i : inventory) {
            if (i.getName().equals(item)) {
                if (i.getDangerLevel() == 0) {
                    inventory.remove(i);
                    System.out.println("Successfully eaten a "+item);
                    
                } else {
                    System.out.println("You are dead");
                    return false;
                    
                }
                
        } else {
            System.out.println("You ");
        }
        }
        return true;
    }

    public Item checkInventory(String item){
        for(Item i: this.inventory){
            if(i.getName().equals(item)){
                return i;
            }
        }
        return null;
    }

}
