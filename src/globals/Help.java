package globals;

import java.util.Scanner;

public class Help {
    public static void instructions(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********Communicating with Interactive Fiction Text Adventures**********\n");
        System.out.println("With Text Adventures, you type your commands in plain English ");
        System.out.println("each time you see the prompt (>).\n");
        
        System.out.println("Stories understand imperative sentences best. ");
        System.out.println("When you have finished typing your input, press the RETURN (or ENTER) key.\n");
        
        System.out.println("The story will then respond, telling you whether your request is possible");
        System.out.println("at this point in the story, and if so what happened as a result.\n");
        
        System.out.println("[ENTER] continues...");
        scanner.nextLine();
        System.out.println("To move around, just type the direction you want to go. ");
        System.out.println("Directions can be abbreviated: ");
        System.out.println("NORTH to N, SOUTH to S, EAST to E, WEST.to W, UP to U, and DOWN to D. ");
        System.out.println("IN and OUT will also work in certain places.\n"); //todo program out, go out
        System.out.println("The command: LOOK will repeat the long description of your location, ");
        System.out.println("and sometimes provide more details about that location.\n");
        System.out.println("EXAMINE (LOOK AT, X) will sometimes give you more detailed descriptions.\n");
        
        System.out.println("GET or TAKE will allow you to pick up objects and carry them with you.");
        System.out.println("INVENTORY will tell you what you are carrying.\n");

        System.out.println("QUIT or Q quits the game.\n");

        System.out.println("[ENTER] continues...");
        scanner.nextLine();
        
        System.out.println("There are many different kinds of sentences used in these games.\n");
        
        System.out.println("**********Here are some examples:**********\n");

        System.out.println(">look");
        System.out.println("This is an ultra-modern starship bridge.");
        System.out.println("A huge viewscreen dominating one wall shows space in this quadrant.");
        System.out.println("There is a console on a desk with a slot beneath it.\n");
        System.out.println(">examine console");
        System.out.println("It is a display console built into the desk.");
        System.out.println("It is displaying this message:");
        System.out.println("\"Place activation card into slot to engage faster-than-light drive.\"\n");
        System.out.println(">put activation card into slot");
        System.out.println("You haven't got activation card\n");
        System.out.println(">inventory");
        System.out.println("You have nothing\n");
        System.out.println(">west");
        System.out.println("Ready Room: This is a small but neat room. ");
        System.out.println("There is a cupboard on the wall.");
        System.out.println("You see a:");
        System.out.println("big wooden box.\n");
        System.out.println(">look in box");
        System.out.println("The big wooden box isn't open.\n");
        System.out.println("************************************************************************");
        System.out.println("*                                                                      *");
        System.out.println("*  Now, back to your game... (typing look might be a good start here). *");
        System.out.println("*                                                                      *");
        System.out.println("************************************************************************\n");
    }
}
