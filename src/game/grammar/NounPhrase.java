/*
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package game.grammar;

import java.util.ArrayList;

public class NounPhrase extends GrammarUnit implements java.io.Serializable {

    private ArrayList<String> adjectives;

    NounPhrase(String aNoun, ArrayList<String> someAdjectives) {
        super(aNoun);
        adjectives = someAdjectives;
    }

    public ArrayList<String> getAdjectives() {
        return adjectives;
    }

    public void setAdjectives(ArrayList<String> adjectives) {
        this.adjectives = adjectives;
    }
    
    public String phrase(){
        String s="";
        for (String a: adjectives){
            s += a + " ";            
        }
        s += this.getWord();
        return s;
    }
}
