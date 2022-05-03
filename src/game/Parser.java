/*
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 *    "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 *     http://www.bitwisebooks.com
 */
package game;

import static game.Starcrash.game;
import static game.data.VocabularyData.vocab;
import game.grammar.WordAndType;
import game.grammar.GrammarUnit;
import game.grammar.NounPhrase;
import game.grammar.Preposition;
import game.grammar.SentenceAnalyzer;
import game.grammar.Verb;
import globals.WT;
import java.util.ArrayList;
import java.util.List;

public class Parser implements java.io.Serializable {

    private static String last_input; // save the complete line input by user

    // ---- Methods to process commands comprising the specified list
    // ---- of GrammarUnit objects
    static String processVerbNounPhrasePrepositionNounPhrase(List<GrammarUnit> command) {
        String msg = "";
        GrammarUnit gu1 = command.get(0);
        GrammarUnit gu2 = command.get(1);
        GrammarUnit gu3 = command.get(2);
        GrammarUnit gu4 = command.get(3);
        String verb_word = gu1.getWord();
        String noun_word = gu2.getWord();
        String preposition_word = gu3.getWord();
        String noun_word2 = gu4.getWord();

        Verb v = null;
        Preposition prep = null;
        NounPhrase np = null;
        NounPhrase np2 = null;

        if (gu1 instanceof Verb) {
            v = (Verb) gu1;
        }
        if (gu2 instanceof NounPhrase) {
            np = (NounPhrase) gu2;
        }
        if (gu3 instanceof Preposition) {
            prep = (Preposition) gu3;
        }
        if (gu4 instanceof NounPhrase) {
            np2 = (NounPhrase) gu4;
        }

        if ((v == null) || (prep == null)) {
            msg = "Can't do this because I don't understand how to '" + verb_word + " something " + preposition_word + "' something!";
        } else if (np == null) {
            msg = "Can't do this because '" + noun_word + "' is not an object!\r\n";
        } else if (np2 == null) {
            msg = "Can't do this because '" + noun_word2 + "' is not an object!\r\n";
        } else {
            switch (verb_word + preposition_word) {
                case "putin":
                case "putinto":
                case "putinside":
                case "placein":
                case "placeinto":
                case "placeinside":
                    msg = game.putObInContainer(np, np2);
                    break;
                case "openwith":
                    msg = game.openObWithSomething(np, np2);
                    break;
                case "lockwith":
                    msg = game.lockObWithSomething(np, np2);
                    break;
                case "unlockwith":
                    msg = game.unlockObWithSomething(np, np2);
                    break;
                default:
                    msg = "I don't know how to " + verb_word + " " + noun_word + " " + preposition_word + " " + noun_word2 + "!";
                    break;
            }
        }
        return msg;
    }

    static String processVerbPrepositionNounPhrase(List<GrammarUnit> command) {
        String msg = "";
        GrammarUnit gu1 = command.get(0);
        GrammarUnit gu2 = command.get(1);
        GrammarUnit gu3 = command.get(2);
        String verb_word = gu1.getWord();
        String preposition_word = gu2.getWord();
        String noun_word = gu3.getWord();
        Verb v = null;
        Preposition prep = null;
        NounPhrase np = null;

        if (gu1 instanceof Verb) {
            v = (Verb) gu1;
        }
        if (gu2 instanceof Preposition) {
            prep = (Preposition) gu2;
        }
        if (gu3 instanceof NounPhrase) {
            np = (NounPhrase) gu3;
        }

        if ((v == null) || (prep == null)) {
            msg = "Can't do this because I don't understand '" + last_input + "' !";
        } else if (np == null) {
            msg = "Can't do this because '" + noun_word + "' is not an object!\r\n";
        } else {
            switch (verb_word + preposition_word) {
                case "lookat":
                    msg = Starcrash.game.lookAtOb(np);
                    break;
                case "lookin":
                case "lookinto":
                case "lookinside":
                    msg = Starcrash.game.lookInOb(np);
                    break;
                default:
                    msg = "I don't know how to " + verb_word + " " + preposition_word + " " + noun_word + "!";
                    break;
            }
        }
        return msg;
    }

    static String processVerbNounPhrase(List<GrammarUnit> command) {
        String msg = "";
        GrammarUnit gu1 = command.get(0);
        GrammarUnit gu2 = command.get(1);
        String verb_word = gu1.getWord();
        String noun_word = gu2.getWord();
        Verb v = null;
        NounPhrase np = null;

        if (gu1 instanceof Verb) {
            v = (Verb) gu1;
        }
        if (gu2 instanceof NounPhrase) {
            np = (NounPhrase) gu2;
        }
        if (v == null) {
            msg = "Really? You want to " + verb_word + "? I don't think you can!";
        } else if (np == null) {
            msg = "You can't do this because '" + noun_word + "' isn't something you can " + verb_word;
        } else {
            switch (verb_word) {
                case "take":
                case "get":
                    msg = game.takeOb(np);
                    break;
                case "drop":
                    msg = game.dropOb(np);
                    break;
                case "open":
                    msg = game.openOb(np);
                    break;
                case "close":
                    msg = game.closeOb(np);
                    break;
                case "x":
                case "examine":
                    msg = Starcrash.game.lookAtOb(np);
                    break;
                case "lock":
                    msg = game.lockOb(np);
                    break;
                case "unlock":
                    msg = game.unlockOb(np);
                    break;
                default:
                    msg = verb_word + " is a word I should know ... but I don't.\n";
                    break;
            }
        }
        return msg;
    }

    static String processVerb(List<GrammarUnit> command) {
        String msg = "";
        GrammarUnit gu = command.get(0);
        String word = gu.getWord();
        Verb v = null;

        if (gu instanceof Verb) {
            v = (Verb) gu;
        }
        if (v == null) {
            msg = "Can't do this because '" + word + "' is not a command!\n";
        } else {
            switch (word) {
                case "n":
                case "north":
                    game.goN();
                    break;
                case "s":
                case "south":
                    game.goS();
                    break;
                case "w":
                case "west":
                    game.goW();
                    break;
                case "e":
                case "east":
                    game.goE();
                    break;
                case "u":
                case "up":
                    game.goUp();
                    break;
                case "d":
                case "down":
                    game.goDown();
                    break;
                case "engage":
                    msg = "OK, Jean-Luc. We'll engage. Really?";
                    break;
                case "examine":
                    msg = word + " what?\n"
                        +"OK, on further examination you find you aren't examining anything.";
                    break;
                case "exits":
                    msg = "How exciting"; //todo program for exits command
                    break;
                case "l":
                case "look":
                    game.look();
                    break;
                case "inventory":
                case "i":
                    game.showInventory();
                    break;
                case "test":
                    game.test();
                    break;
                default:
                    msg = word + " is a word I should know ... but I don't.\n";
                    break;
            }
        }
        return msg;
    }

    // Given a list of WordAndType objects (a string and a WT constant such as
    // NOUN or VERB), create a list of GrammarUnit objects and pass that list
    // to be processed by an appropriate method 
    static String processCommand(List<WordAndType> command) {
        String s = "";
        SentenceAnalyzer analyzer;
        List<GrammarUnit> grammarunits = new ArrayList<>(); // create Grammar Units

        analyzer = new SentenceAnalyzer(command); // Work Out Sentence Type
        grammarunits = analyzer.analyze();
        if (grammarunits.isEmpty()) {
            s = "You must write a command!";
        } else if (grammarunits.size() > 4) {
            s = "That command is too long!";
        } else if (analyzer.containsError()) {
            s = "How are you supposed to do that without a " + analyzer.getError();
        } else {
            switch (grammarunits.size()) {
                case 1:
                    s = processVerb(grammarunits);
                    break;
                case 2:
                    s = processVerbNounPhrase(grammarunits);
                    break;
                case 3:
                    s = processVerbPrepositionNounPhrase(grammarunits);
                    break;
                case 4:
                    s = processVerbNounPhrasePrepositionNounPhrase(grammarunits);
                    break;
                default:
                    s = "Unable to process command";
                    break;
            }
        }
        return s;
    }

    // Create a list of WordAndType objects by analysing a list of words
    static List<WordAndType> parseCommand(List<String> wordlist) {
        List<WordAndType> command = new ArrayList<>();
        WT wordtype;

        for (String k : wordlist) {
            if (vocab.containsKey(k)) {
                wordtype = vocab.get(k);
                if (wordtype == WT.ARTICLE) {       // ignore articles             
                } else {
                    command.add(new WordAndType(k, wordtype));
                }
            } else { // if word not found in vocab
                command.add(new WordAndType(k, WT.ERROR));
            }
        }
        return command;
    }

    // show error message if a word in the list is not understood
    static private String parseErrors(List<WordAndType> command) {
        String s = "";

        for (WordAndType wt : command) {
            if (wt.getWordtype() == WT.ERROR) {
                s += wt.getWord() + " isn't a word any reasonable person would understand.\n";
            }
        }
        return s;
    }

    // Split input into individual words.
    // e.g. input = "take small box"
    // return list: "take", "small", "box"
    static List<String> wordList(String input) {
        String delims = "[ \t,.:;?!\"']+";
        List<String> strlist = new ArrayList<>();
        String[] words = input.split(delims);

        for (String word : words) {
            strlist.add(word);
        }
        return strlist;
    }

    // main parser method. Take string input and calls methods to parse and
    // process it as a command
    static String runCommand(String input) {
        List<String> words;
        String s;
        String lowstr;
        List<WordAndType> command;

        s = "ok";
        lowstr = input.trim().toLowerCase();
        if (!lowstr.equals("q") && !lowstr.equals("quit")) {
            if (lowstr.equals("")) {
                s = "You must enter a command";
            } else {
                last_input = input; // save user input (for error messages)
                words = wordList(lowstr);
                command = parseCommand(words);
                s = parseErrors(command);
                if (s.isEmpty()) {
                    s = processCommand(command);
                } else {
                    s = "I have no idea what you are trying to say here: '" + input + ",' LOL!\n" + s;
                }
            }
        }
        return s;
    }
}
