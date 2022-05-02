/*
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package game.grammar;

public class GrammarUnit implements java.io.Serializable {

    private String word;

    public GrammarUnit(String aWord) {
        word = aWord;
    }

    public String getWord() {
        return word;
    }

}
