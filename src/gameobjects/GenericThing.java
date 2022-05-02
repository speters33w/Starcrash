/*
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package gameobjects;

import globals.Mass;

/*
 * A generic thing is a non-interactove game thing - part of the 'scenery' such
 * as lights or trees. The player may refer to them but they are not intended
 * to be used
 */
public class GenericThing extends Thing implements java.io.Serializable {

    private boolean plural; // e.g. "lights", "trees"  

    public GenericThing(String aName, String aDescription, boolean aPlural) {
        super(aName, aDescription, Mass.UNKNOWN, false, false);
        plural = aPlural;
        setShow(false); // by default don't show among objects in room
    }

    public boolean isPlural() {
        return plural;
    }

    @Override
    protected String article(String s) {
        String a;
        
        if (this.isPlural()) {
          //  a = "some ";
          a = "";
        } else {
            a = super.article(s);
        }
        return a;
    }

    @Override
    public String describe() {
        String d;
        String s;
        
        d = getLongDescription();
        if (this.isPlural()) {
            s = "They are " + article(d) + d + ".";
        } else {
            s = "It is " + article(d) + " " + d + ".";
        }
        return s;
    }
}
