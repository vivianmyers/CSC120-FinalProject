import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMain {

    //items
    static Item sword = new Item("SWORD", 8, "You spot a beautiful, gleaming, sharp sword lying on the ground. 🗡️ ");
    static Item banana = new Item("BANANA", 0, "A vibrant, ripe, glowing banana resides on the floor. 🍌 ");
    static Item burger = new Item("BURGER", 0, "The most beautiful, delicious, juicy burger is laying in front of you. 🍔 ");

    // npcs
    static NPC bat = new NPC("BAT", 2, "Shiny, foaming at the mouth bat");
    static NPC wolf = new NPC("WOLF", 7, "Beautiful, giant white teeth.");
    static NPC thief = new NPC("THIEF", 9, "Dark mysterious figure.");
    static NPC monkey = new NPC("MONKEY", 3, "OOO OOO AHAHAHAHA");

    // place
    static Place cave = new Place("Cave", "Dark", sword, bat);
    static Place forestPath = new Place("Forest path", "You are on a path in the forest.", banana, null);
    static Place forestClearing = new Place("Forest clearing", "You are in a forest clearing.", burger, null);
    static Place forestTree = new Place("Forest w/ monkey", "You see a monkey.", null, monkey);
        static Place field = new Place("Empty Field", "Field", null, null);
        static Place start = new Place("Start", "starting place", null, null);
        static Place barn = new Place("Barn", "ending place", null, null);

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
                        player.go(input);
                        System.out.println(map[player.getCurX()][player.getCurY()].describe());
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
