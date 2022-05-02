/*
 * Bitwise Books & Courses - sample Java code
 * http://www.bitwisebooks
 * http://www.bitwisecourses.com
 */
package gameobjects.lists;

import gameobjects.Thing;
import java.util.ArrayList;

public class ThingList extends ArrayList<Thing> implements java.io.Serializable {

    private String name; // useful for debugging

    public ThingList() {
        super();
        name = "unnamed";
    }

    public ThingList(String aName) {
        super();
        name = aName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
