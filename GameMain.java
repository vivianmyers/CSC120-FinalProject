import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMain {

    public static Place[][] map;

    public GameMain() {
        Item sword = new Item("Sword", 10);
        Item banana = new Item("Banana", 0);
        Item burger = new Item("Burger", 0);

        // npcs
        NPC bat = new NPC("Bat");
        NPC wolf = new NPC("Wolf");
        NPC thief = new NPC("Thief");
        NPC monkey = new NPC("Monkey");

        // place
        Place cave = new Place("Cave", "Dark", sword, bat);
        Place forestPath = new Place("Forest path", "You are on a path in the forest.", banana, null);
        Place forestClearing = new Place("Forest clearing", "You are in a forest clearing.", burger, null);
        Place forestTree = new Place("Forest w/ monkey", "You see a monkey.", null, monkey);
        Place field = new Place("Empty Field", "Field", null, null);
        Place start = new Place("Start", "starting place", null, null);
        Place barn = new Place("Barn", "ending place", null, null);

        Place[][] map = { { start, field, field, field, field },
                { field, field, cave, field, field },
                { forestPath, forestClearing, field, field },
                { forestPath, forestTree, field, field },
                { field, field, field, field, barn }

        };
    }

    public static void main(String[] args) {

        GameMain newGame = new GameMain();

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
                        System.out.println(GameMain.map[player.getCurX()][player.getCurY()].describe());
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
                                    player.grab(objectToDrop);
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("You must put a word after drop");
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                        }

                    } else {
                        // request input again
                    }
                }
            }

        } while (stillPlaying);

        userInput.close();

    }
}
