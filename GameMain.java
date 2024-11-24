import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class GameMain{

    public static void main(String[] args){
        //items
        Item sword = new Item("Sword", 10);
        Item banana = new Item("Banana", 0);
        Item burger = new Item("Burger", 0);
    
        //npcs
        NPC bat = new NPC("Bat");
        NPC wolf = new NPC("Wolf");
        NPC thief = new NPC("Thief");
        NPC monkey = new NPC("Monkey");
    
        //place
        Place cave = new Place("Cave", "Dark", 1, 2, sword, bat);
        Place forestPath = new Place("Forest path", "You are on a path in the forest.", 2, 0, banana, null);
        Place forestClearing = new Place("Forest clearing", "You are in a forest clearing.", 3, 1, burger, null);
        Place forestTree = new Place("Forest w/ monkey", "You see a monkey.", 3, 1, null, monkey);
        Place field = new Place("Empty Field", "Field" , 4, 4, null, null);
        Place start = new Place("Start", "starting place" , 0, 0, null, null);
        Place barn = new Place("Barn", "ending place" , 5, 5, null, null);
    
        Place[][] map = {   {start, field, field, field, field}, 
                            {field, field, cave, field, field},
                            {forestPath, forestClearing, field, field},
                            {forestPath, forestTree, field, field},
                            {field, field, field, field, barn}
    
        };

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
        
        String[] directions = {"NORTH", "SOUTH", "EAST", "WEST"};
        String[] commands = {"GRAB", "DROP", "EAT", "FIGHT"};

        System.out.println("You are a sheep herder with 10 sheep. You must find your way back to the barn with at least 7 sheep.");
        
        do {

            userResponse = userInput.nextLine().toUpperCase();
            String[] inputWords = userResponse.split(" ");

            for( String input : inputWords){
                for ( String direction : directions){
                    if (input.equals(direction)){
                        player.go(input);
                        System.out.println(map[player.getCurX()][player.getCurY()].describe());
                    } 
                }
                for( String command : commands){
                    if(input.equals(command)){
                        
                        switch(input) {
                            case "EAT":
                                int index = Arrays.asList(inputWords).indexOf(input);
                                try {
                                    String objectToEat = inputWords[index+1];
                                    stillPlaying = player.eat(objectToEat);
                                } catch (Exception e) {
                                    System.out.println("You must put a word after eat");
                                }
                                
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

  

