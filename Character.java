import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.Iterator;

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

        if (curPlace instanceof Building && !inside) { // trying to pick up item thats inside without entering
            throw new RuntimeException("You cannot pick up an item that does not exist here.");
        }

        if (inventory.size() > 5) {
            throw new RuntimeException("Your backpack is too heavy. Try dropping an item first.");
        }

        if (curPlaceItem != null) {
            this.inventory.add(curPlaceItem);
            curPlace.items.remove(curPlaceItem);
            System.out.println("You grabbed a " + item.toLowerCase() + ".");
        } else {
            throw new RuntimeException("You cannot pick up an item that does not exist here.");
        }
    }

    public void drop(String item) {
        Place curPlace = GameMain.map[this.curX][this.curY];
        Item curItemInventory = this.findItemInInventory(item);

        if (curPlace instanceof Building && !inside) { // Idk how sensible this is....but i think its nessecary
            throw new RuntimeException("You cannot drop an item outside a building.");
        }

        if (curItemInventory != null) {
            this.inventory.remove(curItemInventory);
            curPlace.items.add(curItemInventory);
            System.out.println("You dropped a " + item.toLowerCase());
        } else {
            throw new RuntimeException("You cannot drop an item you do not own.");
        }

        // monkey event
        if (curPlace.getNPC().getName().equals("MONKEY")) {
            if (curPlace.getName().equals("Forest w/ monkey") && item.equals("BANANA")) {
                System.out.println("The monkey jumps down and grabs the banana!");
                System.out.println("🐒Monkey: Yum, thanks! I love bananas! Would you like a hint?");

                Scanner scanner2 = new Scanner(System.in); // we cannot close this without an error in main
                String input = "";

                while (!input.equals("YES") && !input.equals("NO")) {
                    System.out.print("Enter yes or no: ");
                    input = scanner2.nextLine().toUpperCase();
                }

                if (input.equals("YES")) {
                    System.out.println("🐒Monkey: The barn is southeast from here. Good luck!");
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
                    System.out.println("You killed " + curNPC.getName().toLowerCase() + ".");
                    curPlace.killNPC();
                    return true;
                } else {
                    System.out.println("You are dead.");
                    return false;
                }
            } else {
                System.out.println("BAAAAAAAHH!!!!!💀 You swung your " + weapon.getName().toLowerCase()
                        + " at nothing and killed 1 sheep.");
                this.subtractSheep();
                this.printSheep();

                return true;
            }
        } else {
            throw new RuntimeException("You cannot fight with this item.");
        }
    }

    public void eat(String item) {
        Iterator<Item> iterator = inventory.iterator();
        boolean itemFound = false;

        while (iterator.hasNext()) {
            Item i = iterator.next();
            if (i.getName().equals(item)) {
                itemFound = true;
                if (i.getDangerLevel() == 0) {
                    if (Math.random() * 10 == 0) {
                        iterator.remove();
                        System.out.println("Oh no! You got choked on " + item.toLowerCase()
                                + ". One of your sheep tries to save you by doing a Heimlich maneuver. The sheep is too heavy that you fall backwards onto it. Fortunately, the blockage came out as you fell, but the poor sheep was crushed to death under your body.");
                        this.subtractSheep();
                        this.printSheep();
                    } else {
                        System.out.println("Successfully eaten a " + item.toLowerCase() + ".");
                    }
                } else {
                    throw new RuntimeException("You ate a dangerous " + item.toLowerCase() + " and died.");
                }
                break;
            }
        }

        if (!itemFound) {
            System.out.println("You do not have a " + item.toLowerCase() + ".");
        }

    }

    public void enter() {

        Place curPlace = GameMain.map[this.curX][this.curY];

        if (inside) {
            throw new RuntimeException("You are already inside this location.");
        }

        if (curPlace instanceof Building) {
            if (curPlace.isUnlocked()) {
                inside = true;
                System.out.println("----------------- " + curPlace.getName() + " -----------------");
                System.out.println("You are now inside " + curPlace.getName() + ".");
                curPlace.setCharacter(true);
                if(curPlace.getName().equals("Lagoon")){
                    System.out.println(curPlace.describe());
                    this.subtractSheep();
                    this.printSheep();
                    this.exit();
                    return;
                }

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
                curPlace.setLockStatus(true);
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

        if (curPlace instanceof Building && !this.inside) {
            throw new RuntimeException("There is no one here to talk to.");
        }

        if (curNPC != null && curNPC.conversable) {

            Scanner scanner = new Scanner(System.in);
            String input = "";

            // the code below can be copied and changed for each npc!
            if (curNPC.getName().equals("MCDONALD")) {
                 // we cannot close this without an error in main
                String input = "";

                System.out.println(
                        "McDonald: Hello! My name is McDonald, welcome to my home. Would you like to try one of my sheep-burgers?");

                while (!input.equals("YES") && !input.equals("NO")) {
                    System.out.print("Enter yes or no: "); // maybe remove this prompt?
                    input = scanner.nextLine().toUpperCase();
                }

                if (input.equals("YES")) {
                    System.out.println("McDonald: Enjoy!");
                    this.grab("BURGER");
                } else {
                    System.out.println("McDonald: Alright. Come back any time when you change your mind!");
                }

            }
            if (curNPC.getName().equals("THIEF")) {
                
                String input3 = "";

                System.out.println("Thief: **draws a knife** Give me your sheep and you will not get hurt."); 

                while (!input.equals("YES") && !input.equals("NO")) {
                    System.out.print("Enter yes or no if the thief can have a sheep: "); // maybe remove this prompt?
                    input = scanner.nextLine().toUpperCase();
                }

                if (input.equals("YES")) {
                    System.out.println("🥷Thief: Heh...that was easy.");
                    this.subtractSheep();
                    printSheep();

                } else {
                    System.out.println("🥷Thief: Prepare to die!");
                    System.out.println("You have 5 seconds to DODGE the thief's attack!");

                    ExecutorService executor = Executors.newSingleThreadExecutor();

                    Callable<String> inputTask = () -> {
                        System.out.print("Type dodge: "); // weird error when u type something that isnt dodge when
                                                          // scanner is closed
                        return scanner.nextLine();
                    };

                    Future<String> future = executor.submit(inputTask);

                    try {
                        String input1 = future.get(5, TimeUnit.SECONDS);
                        if(input1.toUpperCase().equals("DODGE")){
                            System.out.println("You successfuly dodged the thief! The thief falls to the ground and your sheep eat him.");
                            curPlace.killNPC();
                        }else{
                            throw new Exception("You did not type dodge! The thief knocked you out and stole a sheep.");
                        }
                    } catch (TimeoutException e) {
                        System.out.println("You did not type dodge! The thief knocked you out and stole a sheep!");
                        subtractSheep();
                        printSheep();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        subtractSheep();
                        printSheep();
                    } finally {
                        executor.shutdownNow();
                    }

                    //for some reason, after executing the catch statements above, the code will wait for another input before you can actually input any commands
                    scanner.close();
                }

            }
            if (curNPC.getName().equals("ENGINEERING STUDENT")) {
                String input2 = "";
                System.out.println(
                        "Engineering Student: I don't have time to talk! I lost my hammerscrewdriver and it is due soon!");
                System.out.println("Help the engineering student? Type yes or no: ");

                input = scanner.nextLine().toUpperCase();
                if (input.equals("YES")) {
                    if (inventory.size() > 0) {
                        for (int i = 0; i<inventory.size(); i++) {
                            Item current = inventory.get(i);
                            if (current.getName().equals("HAMMERSCREWDRIVER")) {
                                System.out.println(
                                        "You have a hammerscrewdriver! Do you want to drop it for them? Enter yes or no: ");
                                input = scanner.nextLine().toUpperCase();
                                if (input.equals("YES")) {
                                    this.inventory.remove(current);
                                    System.out.println(
                                            "Engineering Student: Oh my god thank you!! In return I shall give you a sheep.");
                                    this.numSheep++;
                                    this.printSheep();
                                } else {
                                    System.out.println(
                                            "Engineering Student: If you're just gonna stand there and do nothing go away!!!");
                                }
                            }
                        }
                    } else{
                        System.out.println(
                                     "You don't seem to have the hammerscrewdriver they are looking for...maybe you can find it somewhere.");
                    }
                }
            }

            if (curNPC.getName().equals("RIDDLER")) {
                int numCorrect = 0;

                System.out.println("The Riddler: Well now, what do we have here? Is it riddles you desire, young one? Choose wisely, for the forest listens closely."); 

                while (!input.equals("YES") && !input.equals("NO")) {
                    System.out.print("Enter yes or no: "); // maybe remove this prompt?
                    input = scanner.nextLine().toUpperCase();
                }

                if (input.equals("YES")) {
                    System.out.println("The Riddler: Excellent! Prepare yourself, traveler. Here comes your first riddle...");
                    
                    //riddle 1
                    System.out.println("Riddle 1: What is a baby sheep called?");
                    String answer1 = scanner.nextLine().toUpperCase();

                    if (answer1.equals("LAMB")) {
                        System.out.println("🧙The Riddler: Correct! The forest approves of your wisdom.");
                        numCorrect++;
                    } else {
                        System.out.println("🧙The Riddler: Wrong! The forest is not pleased.");
                    }

                    //riddle 2
                    System.out.println("Riddle 2: True or False: Sheep have no upper teeth.");
                    String answer2 = scanner.nextLine().toUpperCase();

                    if (answer2.equals("TRUE")) {
                        System.out.println("🧙The Riddler: Correct! The forest is pleased.");
                        numCorrect++;
                    } else {
                        System.out.println("🧙The Riddler: Wrong!");
                    }

                    //riddle 2
                    System.out.println("Riddle 2: True or False: Sheep are the best animals in the world.");
                    String answer3 = scanner.nextLine().toUpperCase();

                    if (answer3.equals("TRUE")) {
                        System.out.println("🧙The Riddler: Correct!");
                        numCorrect++;
                    } else {
                        System.out.println("🧙The Riddler: Wrong!");
                    }

                    // handle cases
                    if (numCorrect == 3) { // give player key
                        System.out
                                .println("🧙The Riddler: I'm impressed. Your wisdom has won you a key. Use it wisely.");
                        curPlace.items.add(new Item("KEY", 0, "You see a shiny, golden key."));
                        this.grab("KEY");
                    }else{
                        System.out.println("The Riddler: Alas, you have failed to get all 3 riddles correct. The forest demands a sacrifice... a sheep will be taken.");
                        subtractSheep();
                        printSheep();
                    }
                } else {
                    System.out.println("The Riddler: So be it, traveler. The forest is not for everyone. May you find your path elsewhere.");
                }

            } // end riddler

            // start wolf
            if (curNPC.getName().equals("WOLF")) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                }
                System.out.println("The wolf lunges at your sheep and kills one, then retreats back to the shadows.");
                subtractSheep();
                printSheep();
            }

            if (curNPC.getName().equals("MCDONALD")) {
                int numCorrect = 0;
                String input = "";

                System.out.println("McDonald: Hi there! Would you like a free sheep burger?"); 

                while (!input.equals("YES") && !input.equals("NO")) {
                    input = scanner.nextLine().toUpperCase();

                    if (input.equals("YES")) {
                        double randomChance = Math.random();
                        System.out.println("The wheel spins");
                        for (int i = 0; i < 3; i++) {
                            try {
                                TimeUnit.SECONDS.sleep(1);
                                System.out.println(".");
                            } catch (Exception e) {
                            }
                        }

                        if (randomChance < 0.45) {

                            System.out.println("🤵Casino Owner: Congratulations! You have won a sheep!");
                            numSheep++;

                        } else {

                            System.out.println("🤵Casino Owner: Oh no! You lost a sheep!");
                            subtractSheep();
                        }
                        printSheep();

                        System.out.println("🤵Casino Owner: Would you like to spin again?");

                    } else if (input.equals("NO")) {
                        System.out.println(
                                "🤵Casino Owner: Did you know that 99% of gamblers quit right before they win big? Your loss!");
                        break;
                    } else {
                        System.out.println("Enter yes or no.");
                    }
                }
            }

        } else {
            throw new RuntimeException("There is nothing here that you can talk to.");
        }
    }

    public void read(String str) {
        if (!str.equals("MAP")) {
            throw new RuntimeException("You cannot read this.");
        }

        if (this.findItemInInventory(str) != null) {
            System.out.println("--- Map ---");
            System.out.println("🪨 🌾 🏠 🌸 🏡 \n" + "🌾 🕳️ 🌾 🌾 🌊 \n" + "👣 🌱 🌳 🏚️ 🌾 \n" + "👣 🌲 🌾 🎰 🌾 \n"
                    + "🌾 🪷 🌾 🏜️ 🐑 ");
        } else {
            throw new RuntimeException("You don't have a map.");
        }
        System.out.println();
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

    public void subtractSheep() {
        if (numSheep > 0) {
            numSheep--;
        }else{
            throw new NoSheepException("You have run out of sheep.");
        }

    }

    public void printSheep() {
        System.out.print("You have ");
        for (int i = 0; i < this.numSheep; i++) {
            System.out.print("🐑 ");
        }
        System.out.println("(" + numSheep + ")" + " sheep remaining.");
    }

    public void printInventory() {
        String retStr = "";

        if (inventory.size() == 0) {
            retStr += "Your inventory is empty.";
        } else {
            retStr += "Inventory: ";
            for (Item item : inventory) {
                retStr += "\n- " + item.getName();
            }
        }

        System.out.println(retStr);
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
