import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class GameMain {

    // items
    static Item sword = new Item("SWORD", 8, "You spot a beautiful, gleaming, sharp sword lying on the ground. 🗡️ ");
    static Item banana = new Item("BANANA", 0, "A vibrant, ripe, glowing banana resides on the floor. 🍌 ");
    static Item burger = new Item("BURGER", 0,
            "The most beautiful, delicious, juicy burger is laying in front of you. 🍔 ");
    static Item hammerScrewdriver = new Item ("HAMMERSCREWDRIVER", 3, "A hammerscrewdriver with a sparkly pink handle sits, abandoned by an engineering first year. 🔨🪛");
    static Item dagger = new Item("DAGGER", 5, "A slender, silver dagger glints in the dappled sunlight, its hilt wrapped in worn leather. 🗡️");
    static Item mushroom = new Item("MUSHROOM", 10, "A neon glowing mushroom peaks out from beneath the leaves.");
    // npcs
    static NPC bat = new NPC("BAT", 2,
            " 🦇 A bat hovers in the shadows, its eyes gleaming with a predatory gleam as it lets out an eerie screech.",
            false);
    static NPC wolf = new NPC("WOLF", 7,
            " 🐺 A lone grey wolf emerges from the shadows of the rubble, its piercing eyes locked onto you, unreadable and wild.",
            true);
    static NPC thief = new NPC("THIEF", 9, " 🥷 You spot a dark, cloaked figure lingering in the shadows.", true);
    static NPC monkey = new NPC("MONKEY", 3,
            " 🐒 OOH OOH AAH AHH! A monkey peers at you from the dense foliage of the large tree, its curious eyes glinting with mischief.",
            true);
    static NPC mcDonald = new NPC("MCDONALD", 0, "🧍A kind old man stands by the stove.", true);
    static NPC riddler = new NPC("RIDDLER", 10,
            "🧙 An wizened man sits criss cross applesauce on the ground. His wide eyes blink up at you.", true);
    static NPC casinoOwner = new NPC("CASINO OWNER", 8, "A man in a suit stands next to the wheel.", true);

    static NPC engineeringStudent = new NPC("ENGINEERING STUDENT", 0, "A distracted, worn out college student hovers over diagrams and engineering textbooks. It is an exhausted engineering student.", true);

    // place
    static Building cave = new Building("Cave",
            " 🪨 You stand at a cave's entrance, peering into the darkness, where shadows seem to shift and secrets await. The faint sound of something stirring sends a chill down your spine.",
            null, bat, true,
            "The cave's interior is cloaked in darkness, the air thick with dampness and the faint scent of earth.",
            false);
    static Building toolshed = new Building("Toolshed",
            " 🏠 You stumble upon an ancient toolshed. Its small, weathered door sways gently in the breeze, the rusted hinges making a mournful creak. You hear some suspicious, rustling sound from the inside!",
            null, thief, true,
            "The broken light flickers erratically, casting shifting shadows, and in the dim glow, something looms before you.",
            false);
    static Building cabin = new Building("Cabin",
            " 🏡 You are standing on the steps of a log cabin, smoke gently curling from the chimney. The door is slightly ajar.",
            burger, mcDonald, true, "You are in a simple living room.", true);
    static Place wastelands = new Place("Wastelands",
            " 🍃🌿  A vast desert of gray ash and charred bone expands before you, where the wind carries an unearthly howl.",
            null, wolf, true);
    static Place forestPath = new Place("Forest Path",
            " 🍃🌿 You step onto a forest path, where the trees arch overhead, their shadows hiding whispers of the unknown ahead.",
            banana, null, false);
    static Place forestClearing = new Place("Forest Clearing",
            " 🌱🌳☀️ You step into the forest clearing, sunlight spilling through the canopy.", null, riddler, false);
    static Place forestTree = new Place("Forest w/ monkey",
            " 🌲🪵 A towering tree stands before you, its massive trunk scarred by time and its branches stretching high into the sky, whispering secrets of the forest through its rustling leaves.",
            null, monkey, false);
    static Place field = new Place("Empty Field",
            " 🌾 You stand in an empty field, its quiet stillness broken only by the soft whisper of the wind.", null,
            null, false);
    static Place start = new Place("Start", " 🪨 A large sheep-shaped rock stands before you.", null, null, false);
    static Building barn = new Building("Barn",
            " 🚪 A red barn stands before you, its large door secured with chains and a large lock.", null, null, false,
            "", false);
    static Building lagoon = new Building("Lagoon", "A glassy, dark lagoon stretches in front of you. It's black water laps gently on the shore.", null, null, true, "You enter the water. Your sheep attempt to save you from the waves. You escape, but one sheep sinks beneath the waves.", false);
    static Place marsh = new Place("Marsh", "Your nostrils are assaulted by the smell of sulfur and death. In front of you is a bubbling, boil marsh. Twisted trees rise from the murky water.", hammerScrewdriver, null, false);
    static Place dunes = new Place("Dunes", "Suddenly your boots meet sand. In front of you, a dune rises, bright sunlight reflecting off its top.", null, null, true);
    static Place flowerGarden = new Place("Flower Garden", "An expanse of color graces your eyes. The largest flower garden you have ever seen.", dagger, null, false);
    static Place forest = new Place("Forest", "Beautiful trees rise around you, their canopy filtering the sunlight. Birds chirp faintly and squirrels cling to tree trunks.", mushroom, null, false);
    static Building casino = new Building("Casino", "A casino looms before you, a towering fortress of neon and gold, its glowing signs flickering like a siren's call in the shadowy night.", null, casinoOwner, true, "At the center of the casino stands a towering wheel, its shimmering gold and emerald wedges boldly marked with sheep icons 🐑 or red Xs ❌.", true);
    static Place waterfall = new Place("Waterfall", "Suddenly, the roar of rushing water fills your ears. Cold droplets hit your cheeks from a massive waterfall, pouring relentlessly in front of you.", null, engineeringStudent, true);

    static Place[][] map = {
            { start, field, toolshed, flowerGarden, cabin },
            { field, cave, field, field, lagoon },
            { forestPath, forestClearing, forest, wastelands, field },
            { forestPath, forestTree, field, casino, field },
            { field, marsh, field, dunes, barn }

    };

    public static void main(String[] args) {

        // This is a "flag" to let us know when the loop should end
        boolean stillPlaying = true;
        boolean inIntroduction = true;

        // We'll use this to get input from the user.
        Scanner userInput = new Scanner(System.in);

        // Storage for user's responses
        String userResponse = "";

        System.out.println("What is your name? ");
        userResponse = userInput.nextLine();
        Character player = new Character(userResponse);
        System.out.println("Welcome " + userResponse + "!");

        String[] directions = { "NORTH", "SOUTH", "EAST", "WEST" };
        String[] commands = { "GRAB", "DROP", "EAT", "FIGHT", "ENTER", "EXIT", "UNLOCK", "TALK", "HELP", "SHEEP",
                "INVENTORY", "READ" };

        System.out.println();

        System.out.println(
                "You wake up dazed, your vision blurring. As you get up, you realize you're surrounded by 10 white, fluffy sheep. In front of you stands a large sheep-shaped rock glistening magesticaly in the sunlight.");
        System.out.println("On the ground in front of you lies a mysterious letter.\n");

        while (inIntroduction) {
            userResponse = userInput.nextLine().toUpperCase();

            if (userResponse.equals("READ LETTER")) {
                break;
            } else {
                System.out.println("You must read the letter first.\n");

            }
        } // end intro

        System.out.println(
                "Letter: Dear Sheep-herder, My name is the Headmaster. I have been having trouble sleeping and need to count sheep to cure my insomnia. You must find your way back to the barn with 7 sheep or else.");

        do {
            System.out.println();
            try{
                userResponse = userInput.nextLine().toUpperCase();
            }catch(NoSuchElementException e){
                System.out.println();
            }
            String[] inputWords = userResponse.split(" ");
            System.out.println();
            for (String input : inputWords) {
                for (String direction : directions) {
                    if (input.equals(direction)) {
                        try {
                            player.go(input);
                            System.out.println(map[player.getCurX()][player.getCurY()].describe());
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }

                for (String command : commands) {
                    if (input.equals(command)) {
                        int index = Arrays.asList(inputWords).indexOf(input);
                        switch (input) {
                            case "EAT":
                                try {
                                    String objectToEat = inputWords[index + 1];
                                    player.eat(objectToEat);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Eat what?");
                                } catch (RuntimeException e){
                                    stillPlaying = false;
                                    System.out.println(e);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case "GRAB":
                                try {
                                    String objectToGrab = inputWords[index + 1];
                                    player.grab(objectToGrab);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Grab what?");
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case "DROP":
                                try {
                                    String objectToDrop = inputWords[index + 1];
                                    player.drop(objectToDrop);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Drop what?");
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case "FIGHT":
                                try {
                                    String nextWord = inputWords[index + 1];
                                    if (nextWord.equals("WITH")) {
                                        String objectToFightWith = inputWords[index + 2];
                                        stillPlaying = player.fight(objectToFightWith);
                                    } else {
                                        System.out.println("Fight with what?");
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Fight with what?");
                                } catch (NoSheepException e) {
                                    stillPlaying = false;
                                    System.out.println(e);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case "ENTER":
                                try {
                                    player.enter();
                                    if (GameMain.map[player.getCurX()][player.getCurY()].getForced()) {
                                        String curNPC = GameMain.map[player.getCurX()][player.getCurY()].getNPC()
                                                .getName();
                                        player.talk(curNPC);
                                    }
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case "EXIT":
                                try {
                                    player.exit();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case "UNLOCK":
                                try {
                                    player.unlock();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case "TALK":
                                try {
                                    NPC curNPC = GameMain.map[player.getCurX()][player.getCurY()].getNPC();
                                    if(GameMain.map[player.getCurX()][player.getCurY()].getNPC() == null){ //no npc in place (ex field)
                                        throw new NullPointerException();
                                    }
                                    player.talk(curNPC.getName());

                                }catch (NullPointerException e) {
                                    System.out.println("There is no one to talk to here.");
                                } catch (NoSheepException e) { //for encounters where sheep may be depleted
                                    stillPlaying = false;
                                    System.out.println(e);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case "SHEEP":
                                player.printSheep();
                                break;
                            case "HELP":
                                System.out.println("**********COMMAND LIST**********");
                                for (String dir : directions) {
                                    System.out.println("- " + dir);
                                }
                                for (String str : commands) {
                                    System.out.println("- " + str);
                                }
                                break;
                            case "INVENTORY":
                                player.printInventory();
                                break;
                            case "READ":
                                try {
                                    String objectToRead = inputWords[index + 1];
                                    player.read(objectToRead);
                                } catch (ArrayIndexOutOfBoundsException e){
                                    System.out.println("Read what?");
                                }catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                                
                            default:
                                System.out.println("This is not a valid command.");
                                break;
                        }
                    }

                }
            }

            if (player.isInside() && player.getCurX() == 4 && player.getCurY() == 4) { // player has made it to barn
                stillPlaying = false;

                System.out.println("You step into the large barn and in front of you is a raised platform made of gold. Upon the platform a cloaked figure lounges on a throne.");
                try{
                    TimeUnit.SECONDS.sleep(1);
                } catch(Exception e){
                }
                System.out.println("Headmaster: Approach and allow me to count your sheep.");
                System.out.println("***Headmaster begins to count***");
                for(int i = 1; i<=player.getNumSheep(); i ++){
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch(Exception e){
                    }
                    System.out.print(i + "🐑...");
                    
                }
                if (player.getNumSheep() > 7) {
                    // WINNING PRIZE
                    System.out.println();
                    System.out.println("The Headmaster's eyes begin to flutter and he falls into a deep slumber💤. You won!");
                } else {
                    // LOSING PUNISHMENT
                    
                    System.out.println("Headmaster: YOU FAILED! I CANNOT FALL ASLEEP WITHOUT AT LEAST 7 SHEEP TO COUNT!");
                    System.out.println("The Headmaster lunges toward you...your vision goes black.");
                    System.out.println("END.");
                    try{
                        TimeUnit.SECONDS.sleep(10);
                    } catch(Exception e){
                    }
                    System.out.print("\033[H\033[2J");
                }
            }

        } while (stillPlaying);

        
        userInput.close();

    }
}
