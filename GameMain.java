import java.util.Scanner;
import java.util.Arrays;

public class GameMain {

    //items
    static Item key = new Item("KEY", 0, "You see a shiny, golden key.");
    static Item sword = new Item("SWORD", 8, "You spot a beautiful, gleaming, sharp sword lying on the ground. 🗡️ ");
    static Item banana = new Item("BANANA", 0, "A vibrant, ripe, glowing banana resides on the floor. 🍌 ");
    static Item burger = new Item("BURGER", 0, "The most beautiful, delicious, juicy burger is laying in front of you. 🍔 ");

    // npcs
    static NPC bat = new NPC("BAT", 2, " 🦇 A bat hovers in the shadows, its eyes gleaming with a predatory gleam as it lets out an eerie screech.");
    static NPC wolf = new NPC("WOLF", 7, " 🐺 A lone grey wolf emerges from the shadows of the trees, its piercing eyes locked onto you, unreadable and wild.");
    static NPC thief = new NPC("THIEF", 9, " 🥷 You spot a dark, cloaked figure lingering in the shadows.");
    static NPC monkey = new NPC("MONKEY", 3, " 🐒 OOH OOH AAH AHH! A monkey peers at you from the dense foliage of the large tree, its curious eyes glinting with mischief. ");
    static NPC mcDonald = new NPC("MCDONALD", 0, "A kind old man stands by the stove.");
    // place
    static Building cave = new Building("Cave", " 🪨 You stand at a cave's entrance, peering into the darkness, where shadows seem to shift and secrets await. The faint sound of something stirring sends a chill down your spine.", sword, bat, true, 1, "The cave's interior is cloaked in darkness, the air thick with dampness and the faint scent of earth.");
    static Building cabin = new Building("Cabin", "You are standing on the steps of a log cabin, smoke gently curling from the chimney. The door is slightly ajar.", null, null, true, 2, ""); //how will we deal with multiple descriptions for each floor inside, npc on what floor?
    static Place forestPath = new Place("Forest Path", " 🍃🌿 You step onto a forest path, where the trees arch overhead, their shadows hiding whispers of the unknown ahead.", banana, null);
    static Place forestClearing = new Place("Forest Clearing", " 🌱🌳☀️ You step into the forest clearing, sunlight spilling through the canopy.", burger, null);
    static Place forestTree = new Place("Forest w/ monkey", " 🌲🪵 A towering tree stands before you, its massive trunk scarred by time and its branches stretching high into the sky, whispering secrets of the forest through its rustling leaves.", null, monkey);
    static Place field = new Place("Empty Field", " 🌾 You stand in an empty field, its quiet stillness broken only by the soft whisper of the wind.", null, null);
    static Place start = new Place("Start", " 🪨 A large sheep-shaped rock stands before you.", null, null);
    static Place barn = new Place("Barn", " 🚪 A red barn stands before you, its large door secured with chains and a large lock.", null, null);
    

    static Place[][] map = { { start, field, field, field, field },
            { field, field, cave, field, field },
            { forestPath, forestClearing, field, field },
            { forestPath, forestTree, field, field },
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
        String[] commands = { "GRAB", "DROP", "EAT", "FIGHT" };

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
                                    System.out.println("You must put a word after eat");
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case "GRAB":
                                try {
                                    String objectToGrab = inputWords[index + 1];
                                    player.grab(objectToGrab);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("You must put a word after grab");
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case "DROP":
                                try {
                                    String objectToDrop = inputWords[index + 1];
                                    player.drop(objectToDrop);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("You must put a word after drop");
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
                                        System.out.println("You must fight with an item.");
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                        System.out.println("You must put a word after with.");
                                } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                break;
                        }
                    }

                    
                }
            }

        } while (stillPlaying);

        userInput.close();

    }
}
