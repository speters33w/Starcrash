/*
 * Bitwise Books & Courses - sample Java code
 * http://www.bitwisebooks
 * http://www.bitwisecourses.com
 */
package gameobjects;

public class GameThing extends Thing implements java.io.Serializable {

    /*
     * This is a very simple example of a subclass of the Thing class.
     * Nothing much here at present. Even so, I'm keeping it in case
     * I may want to add special data or behaviour that is not shared
     * with the other descendents of Thing...
     *
     */
    public GameThing(String aName, String aDescription, int aMass) {
        super(aName, aDescription, aMass); // init superclass        
    }

    public GameThing(String aName, String aDescription,
            int aMass,
            boolean canTake, boolean canMove) {
        super(aName, aDescription, aMass, canTake, canMove);
    }

}
