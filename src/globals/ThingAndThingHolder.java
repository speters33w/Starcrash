/*
 * Sample Java file by Huw Collingbourne
 * 
 * This code (and other sample code) accompanies the book 
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */

package globals;

import gameobjects.Thing;
import gameobjects.ThingHolder;
import gameobjects.lists.ThingList;


public class ThingAndThingHolder implements java.io.Serializable{
    
    private Thing t;
    private ThingHolder th;
    
    public ThingAndThingHolder(Thing aThing, ThingHolder aThingHolder) {
        t = aThing;
        th = aThingHolder;
    }

    public Thing getThing() {
        return t;
    }

    public void setThing(Thing aThing) {
        this.t = aThing;
    }

    public ThingHolder getThingHolder() {
        return th;
    }

    public void setThingHolder(ThingHolder aThingHolder) {
        this.th = aThingHolder;
    }
    
    public ThingList getList() {
        return th.getThings();
    }
     
}
