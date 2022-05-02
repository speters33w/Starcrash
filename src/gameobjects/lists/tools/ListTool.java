/*
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package gameobjects.lists.tools;

import gameobjects.lists.ThingAndThingHolderList;
import globals.ThingAndThingHolder;
import java.util.ArrayList;

public class ListTool {

    private static String listMultipleThings(ArrayList<ThingAndThingHolder> things) {
        String s = "";

        for (ThingAndThingHolder tth : things) {
            s += tth.getThing().getDescription() + "\n";
        }
        return s;
    }

    /*
     * check there is exactly 1 thing in things list
     * and 1 exactly container in container_things list
     * If so, return "", otherwise return error message
     *
     * This checks objects found in response to commands such as:
     * "Put X into Y"
     * where there must be 1 of both X and Y
     *
     * X (thing1Desc) assumed to be in inventory - so you can so something with it
     * Y (thing2Desc) assumed to be 'here' so something can be done to it
     */
    public static String oneThingAndOneContainerInLists(
            ThingAndThingHolderList things,
            ThingAndThingHolderList container_things,
            String thing1Desc,
            String thing2Desc,
            String verb, String preposition) {
        String s = "";

        if (things.isNullOrEmpty()) {
            s = "You haven't got " + thing1Desc;
        } else if (container_things.isNullOrEmpty()) {
            s = "There is no " + thing2Desc + " here!";
        } else if (things.size() > 1) {
            s = "Which of these do you want to " + verb + " " + preposition + " the " + thing2Desc + "?\n";
            s += listMultipleThings(things);
        } else if (container_things.size() > 1) {
            s = "Which " + thing2Desc + " do you want to put the " + thing1Desc + " into?\n";
            s += listMultipleThings(container_things);
        }

        return s;
    }

    // Check that there is only one Thing is this list
    // If there is 0 or >1 then the Thing is either not available
    // or is ambiguous (e.g. It's a box but there are two boxes here)
    public static String oneThingInList(ThingAndThingHolderList things, String thingDesc,
            String verb, boolean mustBeInInventory) { //mustBeInInventory true for "drop" or "put into..."
        String s = "";

        if (things.isNullOrEmpty()) {
            if (mustBeInInventory) {
                s = "You haven't got " + thingDesc;
            } else {
                s = "Can't see " + thingDesc + " here.";
            }
        } else if (things.size() > 1) {
            s = "Which of these do you want to " + verb + "?\n";
            s += listMultipleThings(things);
        }
        return s;
    }

    // overloaded method omits final parameter mustBeInInventory 
    // (setting it to false) 
    public static String oneThingInList(ThingAndThingHolderList things, String thingDesc,
            String verb) {
        String s = "";
        return oneThingInList(things, thingDesc, verb, false);
    }

}
