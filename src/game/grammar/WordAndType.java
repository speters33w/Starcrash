/*
 * Sample Java file by Huw Collingbourne
 * 
 * This code (and other sample code) accompanies the book 
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package game.grammar;

import globals.WT;

public class WordAndType implements java.io.Serializable {

    private String word;
    private WT wordtype;

    public WordAndType(String wd, WT wt) {
        word = wd;
        wordtype = wt;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WT getWordtype() {
        return wordtype;
    }

    public void setWordtype(WT wordtype) {
        this.wordtype = wordtype;
    }

}
