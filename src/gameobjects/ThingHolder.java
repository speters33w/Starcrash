/*
 * Bitwise Books & Courses - sample Java code
 * http://www.bitwisebooks
 * http://www.bitwisecourses.com
 */
package gameobjects;

import gameobjects.lists.ThingList;
import game.grammar.NounPhrase;
import gameobjects.lists.ThingAndThingHolderList;
import globals.ThingAndThingHolder;
import java.util.ArrayList;

public class ThingHolder extends Thing implements java.io.Serializable {

    private ThingList things;
    private ThingAndThingHolder t_and_th = null;
    private ThingAndThingHolderList thingsfound = new ThingAndThingHolderList();
    private String thingStr = "";
    ThingList flatlist = new ThingList(); // temp: used by flatten() to return flat list

    public ThingHolder(String aName, String aDescription, int aSize, ThingList tl) {
        super(aName, aDescription, aSize);
        things = tl;
    }

    public ThingHolder(String aName, String aDescription, int aSize, boolean canTake,
            boolean canMove, ThingList tl) {
        super(aName, aDescription, aSize, canTake, canMove);
        things = tl;
    }

    public ArrayList<ThingAndThingHolder> getThingsfound() {
        return thingsfound;
    }

    public static ContainerThing toContainerThing(Thing t) {
        ContainerThing ct = null;

        if (t instanceof ContainerThing) {
            ct = (ContainerThing) t;
        }
        return ct;
    }

    public int numberOfThings() {
        return things.size();
    }

    /*
     * Thing is 'here' if it is visible (e.g. it's in a Room if it is an object
     * in that room or an object in an open container in that room).
     *
     * Recursively look for a Thing called obname in the list maintained by the
     * ThingHolder th. If found in that list any list 'branching off' it, the
     * object variable t_and_th is initialized with the Thing and the
     * ThingHolder which 'contains' it.
     */
    // 'unnest' things (things inside containers) and return as a sequential list
    private ThingList thingsToFlatList(ThingHolder th) {

        for (Thing t : th.getThings()) {
            flatlist.add(t);
            if (t instanceof ContainerThing) {
                thingsToFlatList((ContainerThing) t);
            }
        }
        return flatlist;
    }

    public ThingList flatten() {
        flatlist.clear();
        return thingsToFlatList(this);
    }

    private void findThingInAnyList(ThingHolder th, NounPhrase np) {
        ContainerThing container;

        for (Thing t : th.getThings()) {
            if (t.matchThing(np)) {
                t_and_th = new ThingAndThingHolder(t, th);
                thingsfound.add(t_and_th);
            }
            container = toContainerThing(t);
            if ((container != null) && (container.isOpen())) {
                findThingInAnyList(container, np);
            }
        }
    }

    private void doDescribeThings(ThingHolder th, boolean describeAll) {
        ThingList tlist = th.getThings();
        ContainerThing container;

        for (Thing t : tlist) {
            String inContainer = "";
            if (t.getContainer() instanceof ContainerThing) {
                inContainer = " (in the " + t.getContainer().getDescription() + ")";
            }
            // don't show non-showable things (e.g. GenericThing objects such as lights)
            if (t.show()) {
                thingStr += t.getDescription() + inContainer + "\n";
            }
            if (describeAll) {
                container = toContainerThing(t);
                if ((container != null) && (container.isOpen())) {
                    if (container.numberOfThings() > 0) {
                        doDescribeThings(container, describeAll);
                    }
                }
            }
        }
    }

    // Describe all things - even those that are "in" other things
    public String describeThings() {
        thingStr = "";
        doDescribeThings(this, true);
        return thingStr;
    }

    // Don't describe things that are "in" other things
    public String describeTopLevelThings() {
        thingStr = "";
        doDescribeThings(this, false);
        return thingStr;
    }

    public boolean containsThing(Thing t, boolean searchAllContainers) {
        boolean yes;

        if (searchAllContainers) {
            yes = containsThingInNestedLists(t);
        } else {
            yes = getThings().contains(t);
        }
        return yes;
    }

    // Is Thing t in list of objects owned by ThingHolder
    // including Things that may be inside other things
    // such as boxes and bags etc.?
    private boolean containsThingInNestedLists(Thing t) {
        // searches for t in all lists (e.g. inside containers in containers)
        ThingList tl;
        boolean yes;

        tl = flatten();
        if (tl.contains(t)) {
            yes = true;
        } else {
            yes = false;
        }
        return yes;
    }

    public ThingAndThingHolderList findThings(NounPhrase np) {
        t_and_th = null;
        thingsfound = new ThingAndThingHolderList();
        findThingInAnyList(this, np);
        return thingsfound;
    }

    // Is Thing t in list of objects directly owned by ThingHolder
    // e.g. the top-level list of Room or Actor (not counting Things that
    // may be inside boxes and bags etc.)?
    public boolean inTopLevelList(Thing t) {
        boolean yes;

        if (things.contains(t)) {
            yes = true;
        } else {
            yes = false;
        }
        return yes;
    }

    public ThingList getThings() {
        return things;
    }

    public void setThings(ThingList things) {
        this.things = things;
    }

    public void remove(Thing t) {
        things.remove(t);
    }

    public void addThing(Thing t) {
        things.add(t);
        t.setContainer(this);
    }

    private void add(Thing t) {
        things.add(t);
    }

    protected void transferOb(Thing t, ThingHolder from_TH, ThingHolder to_TH) {
        from_TH.remove(t);
        to_TH.add(t);
        t.setContainer(to_TH);
    }

}
