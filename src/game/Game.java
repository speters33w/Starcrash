/*
 * BIFF (Bitwise Interactive Fiction Framework)
 * Bitwise Books & Courses
 * http://www.bitwisebooks
 * http://www.bitwisecourses.com
 *
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package game;

import game.data.VocabularyData;
import game.data.GameData;
import game.grammar.NounPhrase;
import gameobjects.Actor;
import globals.Dir;

public class Game implements java.io.Serializable {

    private Actor player;  // the player - provides 'first person perspective'  

    public Game() {
        VocabularyData.initVocab();
        GameData.initGame();
        player = GameData.player;
    }

    public String openObWithSomething(NounPhrase np, NounPhrase np2) {
        return player.openWith(np, np2);
    }

    public String lockObWithSomething(NounPhrase np, NounPhrase np2) {
        return player.lockWith(np, np2);
    }

    public String unlockObWithSomething(NounPhrase np, NounPhrase np2) {
        return player.unlockWith(np, np2);
    }

    public String putObInContainer(NounPhrase np, NounPhrase np2) {
        return player.putInto(np, np2);
    }

    public String openOb(NounPhrase np) {
        return player.openOb(np);
    }

    public String closeOb(NounPhrase np) {
        return player.closeOb(np);
    }

    public String lockOb(NounPhrase np) {
        return player.lockOb(np);
    }

    public String unlockOb(NounPhrase np) {
        return player.unlockOb(np);
    }

    String takeOb(NounPhrase np) {
        String retStr;

        retStr = player.take(np);
        return retStr;
    }

    String dropOb(NounPhrase np) {
        String retStr;

        retStr = player.drop(np);
        return retStr;
    }

    void movePlayerTo(Dir dir) {
        if (player.moveTo(dir)) {
            showStr(player.describeLocation(false));
        } else {
            showStr("No Exit!");
        }
    }

    void goN() {
        movePlayerTo(Dir.NORTH);
    }

    void goS() {
        movePlayerTo(Dir.SOUTH);
    }

    void goW() {
        movePlayerTo(Dir.WEST);
    }

    void goE() {
        movePlayerTo(Dir.EAST);
    }

    void goUp() {
        movePlayerTo(Dir.UP);
    }

    void goDown() {
        movePlayerTo(Dir.DOWN);
    }

    void look() {
        showStr(player.describeLocation(true));
    }

    // utility method to display string if not empty
    // stripping any trailing newlines
    void showStr(String s) {
        if (s.endsWith("\n")) {
            s = s.substring(0, s.length() - 1);
        }
        if (!s.isEmpty()) {
            System.out.println(s);
        }
    }

    void showInventory() {
        showStr(player.inventory());
    }

    String lookAtOb(NounPhrase np) {
        return player.lookAt(np);
    }

    String lookInOb(NounPhrase np) {
        return player.lookIn(np);
    }

    public void showIntro() {
        showStr(GameData.introText());
    }

    public String runCommand(String inputstr) {
        String s;
        String lowstr;

        s = "ok";
        lowstr = inputstr.trim().toLowerCase();

        if (!lowstr.equals("q")) {
            if (lowstr.equals("")) {
                s = "You must enter a command";
            } else {
                s = Parser.runCommand(inputstr);
            }
        }
        return s;
    }

    // Test..... BEGIN
    void showTest(String s) {
        showStr("> " + s);
        showStr(runCommand(s));
    }

    // just in case I want to check the mass of an object
    void showMass(int m) {
        showStr("Mass=" + m);
    }

    void test() {

        // utility method to let me try out bits of code while developing the game  
        // Here I can call showStr() with a string that is processed just as if
        // it had been entered at the prompt        
        showTest("look at console");
        showTest("w");
        showTest("open the box");
        showTest("open the wooden box");
        showTest("open the cardboard box");
        showTest("open the tiny onyx box");
        showTest("take the cardboard box");
        showTest("take the silver key");
        showTest("open the cupboard");
        showTest("unlock the cupboard");
        showTest("unlock the cupboard with the silver key");
        showTest("e");
        showTest("d");
        showTest("e");
        showTest("d");
        showTest("look");
        showTest("e");
        showTest("s");
        showTest("s");
        showTest("look at pitcher plant");
        showTest("look in pitcher plant");
        showTest("take gold key");
        showTest("n");
        showTest("n");
        showTest("w");
        showTest("w");
        showTest("up");
        showTest("up");
        showTest("w");
        showTest("unlock cupboard with key");
        showTest("unlock cupboard with gold key");
        showTest("open cupboard");
        showTest("look into the cupboard");
        showTest("take the activation card");
        showTest("e");
        showTest("put card into slot");
        showTest("look at console");       
    }

    // Test..... END
}
