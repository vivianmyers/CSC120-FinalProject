import java.util.ArrayList;
public class Character {
    
    private String name;
    private ArrayList<Item> inventory;
    private int curX;
    private int curY;
    private int numSheep;
    private boolean inside;

    public Character(String name){
        this.name = name;
        this.inventory = new ArrayList<Item>();
        this.curX = 0;
        this.curY = 0;
        this.numSheep = 10;
        this.inside = false;
    }

    public int getCurX(){
        return this.curX;
    }

    public int getCurY(){
        return this.curY;
    }

    public void grab(String item){
        Place curPlace = GameMain.map[this.curX][this.curY];
        Item curPlaceItem = curPlace.findItemInPlace(item);
        if(curPlaceItem !=null){
            this.inventory.add(curPlaceItem);
            curPlace.items.remove(curPlaceItem);
            System.out.println("You grabbed a " + item.toLowerCase());
        }else{
            throw new RuntimeException("You cannot pick up an item that does not exist here.");
        }
    }

    public void drop(String item){
        Place curPlace = GameMain.map[this.curX][this.curY];
        Item curItemInventory = this.findItemInInventory(item);
        if(curItemInventory !=null){
            this.inventory.remove(curItemInventory);
            curPlace.items.add(curItemInventory);
            System.out.println("You dropped a " + item.toLowerCase());
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

    public boolean fight(String item){
        Item weapon = this.findItemInInventory(item);
        Place curPlace = GameMain.map[curX][curY];
        NPC curNPC = curPlace.getNPC();
        if(weapon != null){
            if(curNPC != null){
                if(weapon.getDangerLevel() > curNPC.getStrengthLevel()){
                    System.out.println("You killed " + curNPC.getName());
                    curPlace.killNPC();
                    return true;
                } else{
                    System.out.println("You are dead.");
                    return false;
                }
            } else{
                System.out.println("BAAAAAAAHH!!!!!💀 You swung your " + weapon.getName().toLowerCase() + " at nothing and killed 1 sheep.");
                this.numSheep --;
                System.out.print("You have ");
                for(int i = 0; i<this.numSheep; i++){
                    System.out.print("🐑 ");
                }
                System.out.println("remaining 🕊️ 🪦.");
                return true;
            }
        } else{
            throw new RuntimeException("You cannot fight with this item.");
        }
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

    public Item findItemInInventory(String item){
        for(Item i: this.inventory){
            if(i.getName().equals(item)){
                return i;
            }
        }
        return null;
    }

}
