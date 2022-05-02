/*
 * Bitwise Books & Courses - sample Java code
 * http://www.bitwisebooks
 * http://www.bitwisecourses.com
 */

package globals;

import gameobjects.Room;

public enum Dir {
    NORTH, 
    SOUTH, 
    EAST, 
    WEST,
    UP,
    DOWN;   
    public static final Room NOEXIT = null;
};