/*
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package gameobjects;

public class BaseThing implements java.io.Serializable {
    // BaseThing type that defines all objects in the Adventure
    // This is not intended to be used directly.
    // One of its descendents should be used for actual Game Objects 

    private String name;
    private String description;

    public BaseThing(String aName, String aDescription) {
        name = aName;
        description = aDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }

}
