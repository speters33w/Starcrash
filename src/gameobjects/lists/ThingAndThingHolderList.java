/*
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package gameobjects.lists;

import gameobjects.Thing;
import globals.ThingAndThingHolder;
import java.util.ArrayList;

public class ThingAndThingHolderList extends ArrayList<ThingAndThingHolder> implements java.io.Serializable {

    public ThingAndThingHolderList() {
        super();
    }
    
    public boolean isNullOrEmpty(){
       return ((this == null) || (this.isEmpty()));
    }
}
