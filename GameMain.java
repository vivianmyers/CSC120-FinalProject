import java.util.Scanner;
import java.util.Arrays;

public class GameMain {

    //items
    static Item key = new Item("KEY", 0, "You see a shiny, golden key.");
    static Item sword = new Item("SWORD", 8, "You spot a beautiful, gleaming, sharp sword lying on the ground. 🗡️ ");
    static Item banana = new Item("BANANA", 0, "A vibrant, ripe, glowing banana resides on the floor. 🍌 ");
    static Item burger = new Item("BURGER", 0, "The most beautiful, delicious, juicy burger is laying in front of you. 🍔 ");

    // npcs
    static NPC bat = new NPC("BAT", 2, " 🦇 A bat hovers in the shadows, its eyes gleaming with a predatory gleam as it lets out an eerie screech.", false);
    static NPC wolf = new NPC("WOLF", 7, " 🐺 A lone grey wolf emerges from the shadows of the trees, its piercing eyes locked onto you, unreadable and wild.", false);
    static NPC thief = new NPC("THIEF", 9, " 🥷 You spot a dark, cloaked figure lingering in the shadows.", true);
    static NPC monkey = new NPC("MONKEY", 3, " 🐒 OOH OOH AAH AHH! A monkey peers at you from the dense foliage of the large tree, its curious eyes glinting with mischief.", true);
    static NPC mcDonald = new NPC("MCDONALD", 0, "A kind old man stands by the stove.", true);
    // place
    static Building cave = new Building("Cave", " 🪨 You stand at a cave's entrance, peering into the darkness, where shadows seem to shift and secrets await. The faint sound of something stirring sends a chill down your spine.", sword, bat, true, "The cave's interior is cloaked in darkness, the air thick with dampness and the faint scent of earth.");
    static Building cabin = new Building("Cabin", "You are standing on the steps of a log cabin, smoke gently curling from the chimney. The door is slightly ajar.", burger, mcDonald, true, "You are in a simple living room."); 
    static Place forestPath = new Place("Forest Path", " 🍃🌿 You step onto a forest path, where the trees arch overhead, their shadows hiding whispers of the unknown ahead.", banana, null);
    static Place forestClearing = new Place("Forest Clearing", " 🌱🌳☀️ You step into the forest clearing, sunlight spilling through the canopy.", key, null);
    static Place forestTree = new Place("Forest w/ monkey", " 🌲🪵 A towering tree stands before you, its massive trunk scarred by time and its branches stretching high into the sky, whispering secrets of the forest through its rustling leaves.", null, monkey);
    static Place field = new Place("Empty Field", " 🌾 You stand in an empty field, its quiet stillness broken only by the soft whisper of the wind.", null, null);
    static Place start = new Place("Start", " 🪨 A large sheep-shaped rock stands before you.", null, null);
    static Building barn = new Building("Barn", " 🚪 A red barn stands before you, its large door secured with chains and a large lock.", null, null, false, "The end!");
    

    static Place[][] map = { 
            { start, field, field, field, cabin },
            { field, field, cave, field, field },
            { forestPath, forestClearing, field, field, field },
            { forestPath, forestTree, field, field, field },
            { field, field, field, field, barn }

    };

    public static void main(String[] args) {

        // This is a "flag" to let us know when the loop should end
        boolean stillPlaying = true;

        // We'll use this to get input from the user.
        Scanner userInput = new Scanner(System.in);

        // Storage for user's responses
        String userResponse = "";

        System.out.println("What is your name? ");
        userResponse = userInput.nextLine();
        Character player = new Character(userResponse);
        System.out.println("Welcome " + userResponse + "!");

        String[] directions = { "NORTH", "SOUTH", "EAST", "WEST" };
        String[] commands = { "GRAB", "DROP", "EAT", "FIGHT", "ENTER", "EXIT", "UNLOCK", "TALK", "HELP" };

        System.out.println(
                "You are a sheep herder with 10 sheep. You must find your way back to the barn with at least 7 sheep.");

        do {

            userResponse = userInput.nextLine().toUpperCase();
            String[] inputWords = userResponse.split(" ");

            for (String input : inputWords) {
                for (String direction : directions) {
                    if (input.equals(direction)) {
                        try{
                            player.go(input);
                            System.out.println(map[player.getCurX()][player.getCurY()].describe());
                        }catch(Exception e){
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
                                    stillPlaying = player.eat(objectToEat);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Eat what?");
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
                                    if(nextWord.equals("WITH")){
                                        String objectToFightWith = inputWords[index + 2];
                                        stillPlaying = player.fight(objectToFightWith);
                                    } else{
                                        System.out.println("Fight with what?");
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                        System.out.println("Fight with what?");
                                } catch (Exception e) {
                                        System.out.println(e);
                                }
                                break;
                            case "ENTER":
                                try {
                                    player.enter();
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
                                try{
                                    player.unlock();
                                }catch(Exception e){
                                    System.out.println(e);
                                }
                                break;
                            case "TALK":
                                try {
                                    String nextWord = inputWords[index + 1];
                                    if(nextWord.equals("TO")){
                                        String npc = inputWords[index + 2];
                                        player.talk(npc);
                                    } else{
                                        System.out.println("Talk to who?");
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                        System.out.println("Talk to who?");
                                } catch (Exception e) {
                                        System.out.println(e);
                                }
                                break;
                            case "HELP":
                                System.out.println("**********COMMAND LIST**********");
                                System.out.println("-North\n-South\n-East\n-West\n-Enter\n-Exit\n-Grab\n-Drop\n-Fight\n-Eat\n-Unlock\n-Help");
                                break;
                            default:
                                System.out.println("This is not a valid command.");
                                break;
                        }
                    }

                    
                }
            }

            if(player.isInside() && player.getCurX() == 5 && player.getCurY() == 5){ //player has made it to barn
                stillPlaying = false;
                if(player.getNumSheep() > 7){ 
                    //WINNING PRIZE
                }else{
                    //LOSING PUNISHMENT
                }
            }

        } while (stillPlaying);

        userInput.close();

    }
}
