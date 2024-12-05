import java.util.ArrayList;
import java.util.Scanner;

public class Character {

    private String name;
    private ArrayList<Item> inventory;
    private int curX;
    private int curY;
    private int numSheep;
    private boolean inside;

    public Character(String name) {
        this.name = name;
        this.inventory = new ArrayList<Item>();
        this.curX = 0;
        this.curY = 0;
        this.numSheep = 10;
        this.inside = false;
    }

    public int getCurX() {
        return this.curX;
    }

    public int getCurY() {
        return this.curY;
    }

    public void grab(String item) {
        Place curPlace = GameMain.map[this.curX][this.curY];
        Item curPlaceItem = curPlace.findItemInPlace(item);
        if (curPlaceItem != null) {
            this.inventory.add(curPlaceItem);
            curPlace.items.remove(curPlaceItem);
            System.out.println("You grabbed a " + item.toLowerCase());
        } else {
            throw new RuntimeException("You cannot pick up an item that does not exist here.");
        }
    }

    public void drop(String item) {
        Place curPlace = GameMain.map[this.curX][this.curY];
        Item curItemInventory = this.findItemInInventory(item);
        if (curItemInventory != null) {
            this.inventory.remove(curItemInventory);
            curPlace.items.add(curItemInventory);
            System.out.println("You dropped a " + item.toLowerCase());
        } else {
            throw new RuntimeException("You cannot drop an item you do not own.");
        }

        if (curPlace.getNPC().getName().equals("MONKEY")) {
            if (curPlace.getName().equals("Forest w/ monkey") && item.equals("BANANA")) {
                System.out.println("The monkey jumps down and grabs the banana!");
                System.out.println("Monkey: Yum, thanks! I love bananas! Would you like a hint?");

                Scanner scanner2 = new Scanner(System.in); // we cannot close this without an error in main
                String input = "";

                while (!input.equals("YES") && !input.equals("NO")) {
                    System.out.print("Enter yes or no: ");
                    input = scanner2.nextLine().toUpperCase();
                }

                if (input.equals("YES")) {
                    System.out.println("Monkey: The barn is southeast from here. Good luck!");
                } else {
                    System.out.println("OOH OOH AHH AHH! Good luck anyways!");
                }
            }
        }

    }

    public void go(String direction) {
        Place curPlace = GameMain.map[this.curX][this.curY];
        if (inside) {
            throw new RuntimeException("You must exit the " + curPlace.getName() + " first.");
        }

        switch (direction) {
            case "NORTH":
                this.curX--;
                if (this.curX < 0) {
                    this.curX++;
                    throw new RuntimeException(
                            "There are impassable mountains to the North. 🏔️ You cannot go farther.");
                }
                break;
            case "SOUTH":
                this.curX++;
                if (this.curX > 4) {
                    this.curX--;
                    throw new RuntimeException("There is an expansive ocean to the South. 🌊 You cannot go farther.");
                }
                break;
            case "EAST":
                this.curY++;
                if (this.curY > 4) {
                    this.curY--;
                    throw new RuntimeException("There is burning hot lava to the East. 🔥 You cannot go farther.");
                }
                break;
            case "WEST":
                this.curY--;
                if (this.curY < 0) {
                    this.curY++;
                    throw new RuntimeException("There is an endless abyss to the West. 🕳️ You cannot go farther.");
                }
                break;
        }

    }

    public boolean fight(String item) {
        Item weapon = this.findItemInInventory(item);
        Place curPlace = GameMain.map[curX][curY];
        NPC curNPC = curPlace.getNPC();
        if (weapon != null) {
            if (curNPC != null) {
                if (weapon.getDangerLevel() > curNPC.getStrengthLevel()) {
                    System.out.println("You killed " + curNPC.getName().toLowerCase());
                    curPlace.killNPC();
                    return true;
                } else {
                    System.out.println("You are dead.");
                    return false;
                }
            } else {
                System.out.println("BAAAAAAAHH!!!!!💀 You swung your " + weapon.getName().toLowerCase()
                        + " at nothing and killed 1 sheep.");
                this.numSheep--;
                System.out.print("You have ");
                for (int i = 0; i < this.numSheep; i++) {
                    System.out.print("🐑 ");
                }
                System.out.println("remaining 🕊️ 🪦.");
                return true;
            }
        } else {
            throw new RuntimeException("You cannot fight with this item.");
        }
    }

    public boolean eat(String item) {
        for (Item i : inventory) {
            if (i.getName().equals(item)) {
                if (i.getDangerLevel() == 0) {
                    inventory.remove(i);
                    System.out.println("Successfully eaten a " + item);

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

    public void enter() {

        Place curPlace = GameMain.map[this.curX][this.curY];

        if (inside) {
            throw new RuntimeException("You are already inside this location.");
        }

        if (curPlace instanceof Building) {
            if (curPlace.isUnlocked()) {
                inside = true;
                System.out.println("You are now inside " + curPlace.getName() + ".");
                curPlace.setCharacter(true);
                System.out.println(curPlace.describe());
            } else { // building is locked
                System.out.println("The " + curPlace.getName() + " is locked.");
            }
        } else { // curPlace is not a building
            throw new RuntimeException("This is not a valid command.");
        }

    }

    public void exit() {
        Place curPlace = GameMain.map[this.curX][this.curY];

        if (!inside) {
            throw new RuntimeException("You are not inside. Must call enter() before exit().");
        }

        System.out.println("You have come out of " + curPlace.getName() + ".");
        this.inside = false;
        curPlace.setCharacter(false);

    }

    public void unlock() {
        Place curPlace = GameMain.map[this.curX][this.curY];
        if (curPlace.isUnlocked()) {
            System.out.println("Already unlocked!");
            return;
        }

        if (curPlace instanceof Building) {
            if (findItemInInventory("KEY") != null) { // has key?
                curPlace.setlockStatus(true);
                System.out.println("Successfully unlocked door.");

            } else {
                throw new RuntimeException("You don't have a key.");
            }
        } else { // not a building
            throw new RuntimeException("You cannot unlock this.");
        }
    }

    public void talk(String npc) {

        Place curPlace = GameMain.map[this.curX][this.curY];
        NPC curNPC = curPlace.getNPC();

        if (curNPC != null && curNPC.conversable) {

            if (!npc.equals(curNPC.getName())) { // not a valid NPC (ex. talk to vivian)
                throw new RuntimeException("Talk to who?");
            }

            // the code below can be copied and changed for each npc!
            if (curNPC.getName().equals("MCDONALD")) {
                Scanner scanner = new Scanner(System.in); // we cannot close this without an error in main
                String input = "";

                System.out.println(
                        "McDonald: Hello! My name is McDonald, welcome to my home. Would you like to try one of my burgers?"); // idk
                                                                                                                               // this
                                                                                                                               // can
                                                                                                                               // be
                                                                                                                               // changed,
                                                                                                                               // just
                                                                                                                               // a
                                                                                                                               // blueprint

                while (!input.equals("YES") && !input.equals("NO")) {
                    System.out.print("Enter yes or no: "); // maybe remove this prompt?
                    input = scanner.nextLine().toUpperCase();
                }

                if (input.equals("YES")) {
                    System.out.println("McDonald: Ok here");
                    this.grab("Burger");
                } else {
                    System.out.println("McDonald: OK bye");
                }

            }
            if (curNPC.getName().equals("THIEF")) {
                Scanner scanner = new Scanner(System.in); // we cannot close this without an error in main
                String input = "";

                System.out.println("Thief: **draws a knife** Give me your sheep and you will not get hurt."); // idk
                                                                                                              // this
                                                                                                              // can be
                                                                                                              // changed,
                                                                                                              // just a
                                                                                                              // blueprint

                while (!input.equals("YES") && !input.equals("NO")) {
                    System.out.print("Enter yes or no if the thief can have a sheep: "); // maybe remove this prompt?
                    input = scanner.nextLine().toUpperCase();
                }

                if (input.equals("YES")) {
                    System.out.println("Thief: Heh...that was easy.");
                    this.numSheep--;
                    System.out.print("You have ");
                    for (int i = 0; i < this.numSheep; i++) {
                        System.out.print("🐑 ");
                    }
                    System.out.println("remaining 🕊️ 🪦.");
                } else {
                    System.out.println("Thief: Prepare to die!");
                }

            }
            if (curNPC.getName().equals("THIEF")) {
                Scanner scanner = new Scanner(System.in); // we cannot close this without an error in main
                String input = "";

                System.out.println("Thief: **draws a knife** Give me your sheep and you will not get hurt."); // idk
                                                                                                              // this
                                                                                                              // can be
                                                                                                              // changed,
                                                                                                              // just a
                                                                                                              // blueprint

                while (!input.equals("YES") && !input.equals("NO")) {
                    System.out.print("Enter yes or no if the thief can have a sheep: "); // maybe remove this prompt?
                    input = scanner.nextLine().toUpperCase();
                }

                if (input.equals("YES")) {
                    System.out.println("Thief: Heh...that was easy.");
                    this.numSheep--;
                    System.out.print("You have ");
                    for (int i = 0; i < this.numSheep; i++) {
                        System.out.print("🐑 ");
                    }
                    System.out.println("remaining 🕊️ 🪦.");
                } else {
                    System.out.println("Thief: Prepare to die!");
                }

            }

        } else {
            throw new RuntimeException("There is nothing here that you can talk to.");
        }
    }

    public void setInside() {
        this.inside = true;
    }

    public boolean isInside() {
        return inside;
    }

    public int getNumSheep() {
        return numSheep;
    }

    public Item findItemInInventory(String item) {
        for (Item i : this.inventory) {
            if (i.getName().equals(item)) {
                return i;
            }
        }
        return null;
    }

}
