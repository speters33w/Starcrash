/*
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package game.grammar;

public class GrammarError extends GrammarUnit implements java.io.Serializable {

    // Flags errors when parsing input
    public GrammarError(String aWord) {
        super(aWord);
    }
}
