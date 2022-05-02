/*
 * Bitwise Books & Courses - sample Java code
 * http://www.bitwisebooks
 * http://www.bitwisecourses.com
 */
package gameobjects;

import gameobjects.lists.ThingList;
import globals.Dir;
import globals.Mass;

public class Room extends ThingHolder implements java.io.Serializable {

    private Room n, s, w, e, up, down;

    public Room(String aName) {
        super(aName, "", Mass.UNKNOWN, null); // init superclass
        this.n = null;
        this.s = null;
        this.w = null;
        this.e = null;
        this.up = null;
        this.down = null;
        setThings(new ThingList(aName + " List"));
    }

    public void init(String aDescription,
            Room aN, Room aS, Room aW, Room aE, Room anUp, Room aDown) {
        setDescription(aDescription);
        this.n = aN;
        this.s = aS;
        this.w = aW;
        this.e = aE;
        this.up = anUp;
        this.down = aDown;
    }

    public void init(String aDescription,
            Room aN, Room aS, Room aW, Room aE
    ) {
        setDescription(aDescription);
        this.n = aN;
        this.s = aS;
        this.w = aW;
        this.e = aE;
        this.up = Dir.NOEXIT;
        this.down = Dir.NOEXIT;
    }

    // --- accessor methods ---
    // n
    public Room getN() {
        return n;
    }

    public void setN(Room aN) {
        this.n = aN;
    }

    // s
    public Room getS() {
        return s;
    }

    public void setS(Room aS) {
        this.s = aS;
    }

    // e
    public Room getE() {
        return e;
    }

    public void setE(Room aE) {
        this.e = aE;
    }

    // w
    public Room getW() {
        return w;
    }

    public void setW(Room aW) {
        this.w = aW;
    }

    public Room getUp() {
        return up;
    }

    public void setUp(Room up) {
        this.up = up;
    }

    public Room getDown() {
        return down;
    }

    public void setDown(Room down) {
        this.down = down;
    }

    public String describe(boolean useLongDescription) {
        String roomdesc;
        String thingsdesc;
        if (useLongDescription) {
            roomdesc = String.format("This is %s.",  getLongDescription());
        } else {
            roomdesc = String.format("%s: This is %s.", getName(), getDescription());
        }
        thingsdesc = describeTopLevelThings(); // don't describe things in other things
        if (!thingsdesc.isEmpty()) {
            roomdesc += "\nThings here:\n" + thingsdesc;
        }
        return roomdesc;
    }

}
