/*
 * Bitwise Books & Courses - sample Java code
 * http://www.bitwisebooks
 * http://www.bitwisecourses.com
 */
package gameobjects;

import gameobjects.lists.ThingList;
import game.grammar.NounPhrase;
import gameobjects.lists.ThingAndThingHolderList;
import gameobjects.lists.tools.ListTool;
import gameobjects.special.Actions;
import globals.Debug;
import globals.Dir;
import globals.Mass;
import globals.ThingAndThingHolder;

public class Actor extends ThingHolder implements java.io.Serializable {

    final int MAXLOAD = Mass.MEDIUM + Mass.SMALL;

    private int load;

    public Actor(String aName, String aDescription, Room aRoom) {
        super(aName, aDescription, Mass.UNKNOWN, new ThingList()); // init super class  
        this.setContainer(aRoom);
        load = 0;
    }

    public void setLocation(Room aRoom) {
        setContainer(aRoom);
    }

    public Room getLocation() {
        return (Room) getContainer();
    }

    public String describeLocation(boolean useLongdescription) {
        return ((Room) getContainer()).describe(useLongdescription);
    }

    public String inventory() {
        String s;

        s = describeThings();
        if (s.isEmpty()) {
            s = "nothing";
        }
        return "You have " + s;
    }

    public ThingAndThingHolderList matchingThingsHere(NounPhrase np) {
        ThingAndThingHolderList things;

        things = matchingThingsInInventory(np);
        things.addAll(matchingThingsInRoom(np));
        return things;
    }

    public ThingAndThingHolderList matchingThingsInInventory(NounPhrase np) {
        ThingAndThingHolderList things;

        things = findThings(np);
        if (Debug.FULL) {
            if (things != null) {
                for (ThingAndThingHolder tth : things) {
                    System.out.println("FOUND in isThingInInventory(): " + tth.getThing().getName() + " : " + tth.getThing().getDescription());
                    System.out.println(tth.getThing().getAdjectives());
                }
            }
        }
        return things;
    }

    public ThingAndThingHolderList matchingThingsInRoom(NounPhrase np) {
        ThingHolder th;
        Room r;
        ThingAndThingHolderList things;

        th = getContainer();
        r = (Room) th;
        things = (r.findThings(np));
        if (Debug.FULL) {
            if (things != null) {
                for (ThingAndThingHolder tth : things) {
                    System.out.println("FOUND in isThingInRoom(): " + tth.getThing().getName() + " : " + tth.getThing().getDescription());
                    System.out.println(tth.getThing().getAdjectives());
                }
            }
        }
        return things;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public String openWith(NounPhrase np, NounPhrase np2) {
        String s;
        ThingAndThingHolderList thingToOpenList;
        ThingAndThingHolderList thingToUseList;

        thingToOpenList = matchingThingsHere(np);
        thingToUseList = matchingThingsInInventory(np2);
        s = ListTool.oneThingInList(thingToOpenList, np.phrase(), "open");
        if (s.isEmpty()) {
            s = ListTool.oneThingInList(thingToUseList, np2.phrase(), "use", true);
            if (s.isEmpty()) {
                s = "You can't open the " + np.phrase() + " with the " + np2.phrase();
            }
        }
        return s;
    }

    public String lockWith(NounPhrase np, NounPhrase np2) {
        String s;
        Thing t;
        Thing t2;
        ThingAndThingHolder t_th;
        ThingAndThingHolder t_th2;
        ThingAndThingHolderList thingToOpenList;
        ThingAndThingHolderList thingToUseList;

        thingToOpenList = matchingThingsHere(np);
        thingToUseList = matchingThingsInInventory(np2);
        s = ListTool.oneThingInList(thingToOpenList, np.phrase(), "lock");
        if (s.isEmpty()) {
            s = ListTool.oneThingInList(thingToUseList, np2.phrase(), "use", true);
            if (s.isEmpty()) {
                t_th = thingToOpenList.get(0);
                t_th2 = thingToUseList.get(0);
                t = t_th.getThing();
                t2 = t_th2.getThing();
                if (t instanceof LockableThing) {
                    s = ((LockableThing) t).trytolockWith(t2);
                } else {
                    s = "You can't lock the " + np.phrase() + " with the " + np2.phrase();
                }
            }
        }
        return s;
    }

    public String unlockWith(NounPhrase np, NounPhrase np2) {
        String s;
        Thing t;
        Thing t2;
        ThingAndThingHolder t_th;
        ThingAndThingHolder t_th2;
        ThingAndThingHolderList thingToOpenList;
        ThingAndThingHolderList thingToUseList;

        thingToOpenList = matchingThingsHere(np);
        thingToUseList = matchingThingsInInventory(np2);
        s = ListTool.oneThingInList(thingToOpenList, np.phrase(), "unlock");
        if (s.isEmpty()) {
            s = ListTool.oneThingInList(thingToUseList, np2.phrase(), "use", true);
            if (s.isEmpty()) {
                t_th = thingToOpenList.get(0);
                t_th2 = thingToUseList.get(0);
                t = t_th.getThing();
                t2 = t_th2.getThing();
                if (t instanceof LockableThing) {
                    s = ((LockableThing) t).trytounlockWith(t2);
                } else {
                    s = "You can't unlock the " + np.phrase() + " with the " + np2.phrase();
                }
            }
        }
        return s;
    }

    public String lockOb(NounPhrase np) {
        String s;
        ThingAndThingHolder t_th;
        ThingAndThingHolderList things;

        things = matchingThingsHere(np);
        s = ListTool.oneThingInList(things, np.phrase(), "lock");
        if (s.isEmpty()) {
            s = "You need to say what you want to lock it with!";
        }
        return s;
    }

    public String unlockOb(NounPhrase np) {
        String s;
        ThingAndThingHolder t_th;
        ThingAndThingHolderList things;

        things = matchingThingsHere(np);
        s = ListTool.oneThingInList(things, np.phrase(), "unlock");
        if (s.isEmpty()) {
            s = "You need to say what you want to unlock it with!";
        }
        return s;
    }

    public String openOb(NounPhrase np) {
        String s;
        ThingAndThingHolder t_th;
        ThingAndThingHolderList things;
        Thing t;

        things = matchingThingsHere(np);
        s = ListTool.oneThingInList(things, np.phrase(), "open");
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            s = t.open();
        }
        return s;
    }

    public String closeOb(NounPhrase np) {
        String s;
        ThingAndThingHolder t_th;
        ThingAndThingHolderList things;
        Thing t;

        things = matchingThingsHere(np);
        s = ListTool.oneThingInList(things, np.phrase(), "close");
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            s = t.close();
        }
        return s;
    }

    public String lookIn(NounPhrase np) {
        String s = "";
        ThingAndThingHolder t_th;
        Thing t;
        ContainerThing container;
        ThingAndThingHolderList things;

        things = matchingThingsHere(np);
        s = ListTool.oneThingInList(things, np.phrase(), "look in");
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            container = toContainerThing(t);
            if (container == null) {
                s = "You can't look inside the " + t.getDescription() + ".";
            } else {
                if (container.isOpen()) {
                    s = container.describeThings();
                    if (s.isEmpty()) {
                        s = "There is nothing in the " + container.getDescription();
                    } else {
                        s = "The " + container.getDescription() + " contains:\n" + s;
                    }
                } else {
                    s += "The " + container.getDescription() + " isn't open.";
                }
            }
        }
        return s;
    }

    public String lookAt(NounPhrase np) {
        String s = "";
        ThingAndThingHolder t_th;
        Thing t;
        ThingHolder th;
        ThingAndThingHolderList things;

        things = matchingThingsHere(np);
        s = ListTool.oneThingInList(things, np.phrase(), "look at");
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            th = t_th.getThingHolder();
            if (th instanceof ContainerThing) {
                s = "[The " + t.getDescription() + " is inside " + th.getDescription() + "]\n";
            }
            s += t.describe();
        }
        return s;
    }

    private void doPutInto(Thing t, ThingHolder th, ContainerThing container) {
        boolean containerIsInRoom;
        containerIsInRoom = false;

        transferOb(t, th, container);
        // is Container visible in this room?
        if (this.getLocation().containsThing(container, true)) {
            containerIsInRoom = true;
        }
        if (containerIsInRoom) { // if putting into container in Room
            load -= t.getMass(); // decrease load  
        }

    }

    private String putInto_SanityCheck(Thing t, ContainerThing container,
            String thingStr, String containerStr) {
        String s = "";

        if ((container == null)) {      // container is not a ContainerThing
            s = "You can't put the " + thingStr + " into the " + containerStr + "!";
        } else if (t == container) {
            s = "You can't put the " + thingStr + " into itself!";
        } else if (container.containsThing(t, true)) {
            s = "The " + thingStr + " is already in the " + containerStr;
        } else if (!(container).isOpen()) {
            s = "You can't put the " + thingStr + " into a closed " + containerStr + "!";
        } else if (container.isIn(t)) {
            s = "You can't put the " + thingStr + " into the " + containerStr
                    + "\nbecause the " + containerStr + " is already in the " + thingStr + "!";
        } else if ((container.totalMass() + t.totalMass()) > container.volume()) {
            s = "The " + containerStr + " isn't big enough for the " + thingStr;
            if (container.numberOfThings() > 0) {
                s += "\nMaybe you could take something out of it and try again?";
            }
        }
        return s;
    }

    // TODO: Rewrite so that th is passed rather than th.getThing() etc.
    public String putInto(NounPhrase np, NounPhrase np2) {
        String s;
        ThingAndThingHolder t_th;
        Thing t;
        ThingAndThingHolder cont_th;
        ThingAndThingHolderList things;
        ThingAndThingHolderList container_things;
        ContainerThing container;

        things = matchingThingsInInventory(np);
        container_things = matchingThingsHere(np2);
        s = ListTool.oneThingAndOneContainerInLists(things, container_things, np.phrase(),
                np2.phrase(), "put", "into");
        if (s.isEmpty()) {
            // if Thing and Container are found                        
            cont_th = container_things.get(0);
            t_th = things.get(0);
            t = t_th.getThing();
            container = toContainerThing(cont_th.getThing());
            s = putInto_SanityCheck(t, container, np.phrase(), np2.phrase());
            if (s.isEmpty()) {
                // Are there any "special actions" defined when t is put
                // into container? If so, do them instead of default actions
                s = Actions.putIntoSpecial(t, t_th.getThingHolder(), container);
                if (s.isEmpty()) {
                    doPutInto(t, t_th.getThingHolder(), container);
                    s = "You put the " + np.phrase() + " into the " + np2.phrase() + ".";
                }
            }
        }
        return s;
    }

    private String debugTakeDrop(Thing t, int totalMass, boolean isContainer) {
        String s = "";

        s += "\nPlayer load=" + load + " MAXLOAD=" + MAXLOAD
                + ". Mass of " + t.getName() + "=" + t.getMass();
        if (isContainer) {
            s += " (This is a Container) total mass = " + totalMass;
        }
        return s;
    }

    private String doTake(Thing t, ThingHolder th) {
        String s;
        ContainerThing ct;
        int tmass;

        tmass = t.totalMass();
        if (t.isTakable()) {
            if (this.inTopLevelList(t)) {
                s = "You already have the " + t.getDescription();
            } else if ((load + tmass) > MAXLOAD) {
                s = "You are carrying too much. You need to drop\n"
                        + "something before taking the " + t.getDescription();
            } else {
                load += tmass;
                // special action?
                s = Actions.takeSpecial(t, th, this.getLocation(), tmass);
                if (s.isEmpty()) {
                    transferOb(t, th, this);
                    if (th instanceof ContainerThing) {
                        s = "You take the " + t.getDescription() + " from the " + th.getDescription();
                    } else {
                        s = t.getDescription() + " taken!";
                    }
                }
            }
        } else { // it's not Takable
            s = "You can't take the " + t.getDescription() + "!";
        }
        if (Debug.ON) {
            ct = toContainerThing(t);
            s += debugTakeDrop(t, tmass, ct != null);
        }
        return s;
    }

    public String take(NounPhrase np) {
        String s;
        ThingAndThingHolder t_th;
        Thing t;
        ThingHolder th;
        ThingAndThingHolderList things;

        things = matchingThingsHere(np);
        s = ListTool.oneThingInList(things, np.phrase(), "take");
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            th = t_th.getThingHolder();
            s = doTake(t, th);
        }
        return s;
    }

    public String drop(NounPhrase np) {
        String s;
        ThingAndThingHolder t_th;
        Thing t;
        ThingHolder th;
        ThingAndThingHolderList things;
        ContainerThing ct;
        int tmass;

        things = matchingThingsInInventory(np);
        s = ListTool.oneThingInList(things, np.phrase(), "drop", true);
        if (s.isEmpty()) {
            t_th = things.get(0);
            t = t_th.getThing();
            th = t_th.getThingHolder();
            tmass = t.totalMass();
            // special action?
            s = Actions.dropSpecial(t, th, this.getLocation(), tmass);
            if (s.isEmpty()) {
                transferOb(t, th, this.getLocation());
                load -= tmass;
                s = t.getDescription() + " dropped!";
                if (Debug.ON) {
                    ct = toContainerThing(t);
                    s += debugTakeDrop(t, tmass, ct != null);
                }
            }
        }
        return s;
    }

    public boolean moveTo(Dir dir) {
        Room r;
        Room exit;
        boolean moved;

        moved = false;
        r = getLocation();
        switch (dir) {
            case NORTH:
                exit = r.getN();
                break;
            case SOUTH:
                exit = r.getS();
                break;
            case EAST:
                exit = r.getE();
                break;
            case WEST:
                exit = r.getW();
                break;
            case UP:
                exit = r.getUp();
                break;
            case DOWN:
                exit = r.getDown();
                break;
            default:
                exit = null; // noexit - stay in same room
                break;
        }
        if (exit != null) {
            setLocation(exit);
            moved = true;
        }
        return moved;
    }

}
