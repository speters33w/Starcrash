/*
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package game.data;

/*
 * Here you can initialize the objects (rooms, treasures etc.) of the game
 */
import gameobjects.Actor;
import gameobjects.ContainerThing;
import gameobjects.GameThing;
import gameobjects.GenericThing;
import gameobjects.LockableThing;
import gameobjects.Room;
import gameobjects.Thing;
import globals.Dir;
import globals.Mass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameData implements java.io.Serializable {

    // NOTE: 
    // map: 
    // This is not needed for the functioning of this game
    // since the player (an Actor object) can move from Room to Room
    // autonomously. However, a sequential list of all Rooms is useful
    // for debugging. 
    // 
    // things: 
    // The HashMap of things lets you find a thing (by its key) 
    // when you need to verify its state (open, closed, etc.) Again this
    // is not needed for the functioning of the game but it's useful
    // when debugging.
    public static ArrayList<Room> map; // the map - an ArrayList of Rooms    
    public static HashMap<String, Thing> things; // treasures
    public static Actor player;  // player - provides 'first person perspective'  
    private static String introtext = ""; // intro description

    public static void initGame() {

        // To construct a new Game, you should create and initialze objects 
        // in the order show. That's because some objects require other
        // objects - for example, all Ro9oms must be created before they
        // are initialized because Rooms refernce other Rooms for their 'exits'
        // --- construct a new adventure ---
        // 1) --- Create rooms ---
        Room readyRoom = new Room("Ready Room");
        Room bridge = new Room("Bridge");
        Room meetingRoom = new Room("Meeting Room");
        Room engineRoom = new Room("Engine Room");
        Room holoDeck = new Room("Holo Deck");
        Room corridor = new Room("Corridor");
        Room basement = new Room("Basement");
        Room diningRoom = new Room("Dining Room");
        Room hydroponicsNorth = new Room("Hydroponics");
        Room hydroponicsCentral = new Room("Hydroponics");
        Room hydroponicsSouth = new Room("Hydroponics");
        Room greenhouse = new Room("Greenhouse");

        // 2) --- Create Containers ---
        //   small cardboard box
        ContainerThing cardboard_box = new ContainerThing("box", "small cardboard box", Mass.SMALL);
        cardboard_box.setOpenable(true);
        cardboard_box.setOpen(false);
        cardboard_box.addAdjectives(new String[]{"small", "cardboard"});

        //  big wooden box
        ContainerThing wooden_box = new ContainerThing("box", "big wooden box", Mass.MEDIUM);
        wooden_box.setOpenable(true);
        wooden_box.setOpen(false);
        wooden_box.addAdjectives(new String[]{"big", "wooden"});

        //   tiny cardboard box
        ContainerThing tinybox = new ContainerThing("box", "tiny onyx box", Mass.TINY);
        tinybox.setOpenable(true);
        tinybox.setOpen(false);
        tinybox.addAdjectives(new String[]{"tiny", "onyx"});
        tinybox.setLongDescription("tiny box made of exquisitely-carved onyx");

        ContainerThing slot = new ContainerThing("slot", "small slot", Mass.TINY, false, false, false, true);
        slot.addAdjectives(new String[]{"small"});
        slot.setShow(false);

        //   cupboard (locked)
        LockableThing cupboard = new LockableThing("cupboard", "cupboard on the wall", Mass.MEDIUM, false, false, true, false, true);
        cupboard.setShow(false);

        // pitcher plant
        ContainerThing pitcherPlant = new ContainerThing("plant", "pitcher plant", Mass.SMALL);
        pitcherPlant.setOpen(true);
        pitcherPlant.setTakable(false);
        pitcherPlant.addAdjectives(new String[]{"pitcher"});

        // 3) --- Create Game Things (and Generic Things) --- 
        GameThing desk = new GameThing("desk", "metal control desk", Mass.BIG, false, false);
        desk.addAdjectives(new String[]{"metal", "control"});
        desk.setLongDescription("metal control desk. There is a console built into the desk.\n"
                              + "There is a small slot beneath the console\n");
        desk.setShow(false);

        GameThing console = new GameThing("console", "display console", Mass.MEDIUM, false, false);
        console.addAdjectives(new String[]{"display"});
        console.setLongDescription("display console built into the desk.\n"
                                 + "It is displaying this message:\n"
                                 + "\"Place activation card into slot to engage faster-than-light drive.\"");
        console.setShow(false);

        GameThing goldKey = new GameThing("key", "gold key", Mass.TINY);
        goldKey.addAdjectives(new String[]{"small", "gold"});
        goldKey.setLongDescription("small, gold key");

        GameThing silverKey = new GameThing("key", "silver key", Mass.TINY);
        silverKey.setAdjectives(new ArrayList<>(Arrays.asList("small", "silver")));
        silverKey.setLongDescription("small, silver key");

        GameThing activationCard = new GameThing("card", "small, plastic activation card", Mass.TINY);
        activationCard.addAdjectives(new String[]{"small", "activation", "plastic"});

        GameThing viewscreen = new GameThing("viewscreen", "huge viewscreen", Mass.HUGE);
        viewscreen.setTakable(false);
        viewscreen.setShow(false);
        viewscreen.addAdjectives(new String[]{"huge"});
        viewscreen.setLongDescription("huge viewscreen showing innumerable stars");

        // GenericThings (scenery objects - not intended to be 'used' or taken)
        GenericThing lights = new GenericThing("lights", "flashing lights set into the walls", true);
        GenericThing plants = new GenericThing("plants", "huge spiky plants", true);
        GenericThing plant = new GenericThing("plant", "huge spiky plant", false);

        plant.addAdjectives(new String[]{"huge", "spiky"});
        plants.addAdjectives(new String[]{"huge", "spiky"});
        lights.addAdjectives(new String[]{"flashing"});

        // 4) --- Add Things to Containers (that is, put them "into" Containers)
        wooden_box.addThing(cardboard_box);
        cardboard_box.addThing(tinybox);
        tinybox.addThing(silverKey);
        pitcherPlant.addThing(goldKey);
        cupboard.addThing(activationCard);

        // 5) --- Add objects to Rooms                       
        readyRoom.addThing(wooden_box);
        readyRoom.addThing(cupboard);

        bridge.addThing(lights);
        bridge.addThing(desk);
        bridge.addThing(console);
        bridge.addThing(slot);
        bridge.addThing(viewscreen);

        hydroponicsNorth.addThing(plant);
        hydroponicsCentral.addThing(plant);
        hydroponicsSouth.addThing(plant);
        hydroponicsNorth.addThing(plants);
        hydroponicsCentral.addThing(plants);
        hydroponicsSouth.addThing(plants);
        hydroponicsSouth.addThing(pitcherPlant);

        // 6) Set any 'special' attributes
        cupboard.canBeUnlockedWith(goldKey);

        // 7) Optional but recommended - add all game objects to things
        // --- add all things to HashMap (for debugging)
        things = new HashMap<>();
        things.put("cardboard_box", cardboard_box);
        things.put("wooden_box", wooden_box);
        things.put("tinybox", tinybox);
        things.put("cupboard", cupboard);
        things.put("activationCard", activationCard);
        things.put("goldKey", goldKey);
        things.put("pitcherPlant", pitcherPlant);
        things.put("silverKey", silverKey);
        things.put("lights", lights);
        things.put("plant", plant);
        things.put("plants", plants);
        things.put("desk", desk);
        things.put("console", console);
        things.put("slot", slot);
        things.put("viewscreen", viewscreen);

        // 8) Initialize rooms (including adding others room objects as 'exits'
        // and adding the pre-created lists (which may contain objects or may be empty)
        //           N,        S,           W,         E,       [Up],      [Down])
        // readyRoom
        readyRoom.init("a small but neat room. \n"
                                  + "There is a cupboard on the wall\n",
                Dir.NOEXIT, Dir.NOEXIT, Dir.NOEXIT, bridge);
        // bridge
        bridge.init("the bridge of the HMS Starcrash. A huge viewscreen dominates one wall\n",
                Dir.NOEXIT, Dir.NOEXIT, readyRoom, Dir.NOEXIT, Dir.NOEXIT, meetingRoom);
        bridge.setLongDescription("an ultra-modern starship bridge.\n" +   
                "A huge viewscreen dominating one wall shows space in this quadrant.\n"
                + "There is a console on a desk with a slot beneath it.");
        // meetingRoom
        meetingRoom.init("typical boring-looking meeting room",
                Dir.NOEXIT, Dir.NOEXIT, Dir.NOEXIT, corridor, bridge, engineRoom
        );
        // engineRoom
        engineRoom.init("big, clanky, oily room full of strange, chugging machinery",
                Dir.NOEXIT, Dir.NOEXIT, Dir.NOEXIT, basement, meetingRoom, Dir.NOEXIT
        );
        // corridor
        corridor.init("long corridor with doors to the left and right and a stairway leading down",
                Dir.NOEXIT, Dir.NOEXIT, meetingRoom, diningRoom, Dir.NOEXIT, basement
        );
        // basement
        basement.init("dark room at the bottom of the ship",
                Dir.NOEXIT, Dir.NOEXIT, engineRoom, hydroponicsNorth, corridor, Dir.NOEXIT
        );
        // holoDeck
        holoDeck.init("big, empty room",
                Dir.NOEXIT, diningRoom, Dir.NOEXIT, Dir.NOEXIT
        );
        // diningRoom
        diningRoom.init("room full of tables and chairs",
                holoDeck, Dir.NOEXIT, corridor, Dir.NOEXIT
        );
        // hydroponicsNorth
        hydroponicsNorth.init("steamy hydroponics area with plants everywhere",
                Dir.NOEXIT, hydroponicsCentral, basement, hydroponicsCentral
        );
        // hydroponicsCentral
        hydroponicsCentral.init("steamy hydroponics area with plants everywhere",
                hydroponicsNorth, hydroponicsSouth, hydroponicsSouth, hydroponicsNorth
        );
        // hydroponicsSouth
        hydroponicsSouth.init("steamy hydroponics area with plants everywhere",
                hydroponicsCentral, greenhouse, hydroponicsCentral, Dir.NOEXIT
        );
        // greenhouse
        greenhouse.init("huge glass structure filled with plants.\n"
                                   + "The air is hot and steamy.\n"
                                   + "You hear the sound of birds",
                hydroponicsSouth, Dir.NOEXIT, Dir.NOEXIT, Dir.NOEXIT
        );

        // 9) Optional but recommended - add Rooms to map
        // create list of rooms (for debugging)
        map = new ArrayList<>();
        map.add(readyRoom);
        map.add(bridge);
        map.add(meetingRoom);
        map.add(engineRoom);
        map.add(holoDeck);
        map.add(corridor);
        map.add(basement);
        map.add(diningRoom);
        map.add(hydroponicsNorth);
        map.add(hydroponicsCentral);
        map.add(hydroponicsSouth);
        map.add(greenhouse);

        // 10) create player and set location
        player = new Actor("player", "loveable game-player", bridge);

        // 11) call method to define introductory text to show when game starts
        defineIntroText();
    }

    // Intro - add any text to be shown when game starts
    private static void defineIntroText() {
        introtext = "You find yourself on the bridge of the HMS Starcrash,\n"
                + "an elite-class starship of Her Majesty's Royal Fleet.\n"
                + "What do you want to do?\n\n";
    }

    public static String introText() {
        return introtext;
    }
}
