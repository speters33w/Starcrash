package game;

/*
 * Sample Java file by Huw Collingbourne
 *
 * This code (and other sample code) accompanies the book
 * "The Little Book of Adventure Game Programming In Java"
 * Source code can be downloaded from:
 * http://www.bitwisebooks.com
 *
 */
import globals.Debug;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Starcrash {

    static Game game;
    static BufferedReader in;
    static String file_ext = "adv";

    // read input from prompt - return as trimmed string
    public static String getInput() {
        String s = "";

        try {
            s = in.readLine().trim();
        } catch (IOException ex) {
            System.out.print(ex.getClass() + ": " + ex.getMessage());
        }
        return s;
    }

    // --- File checking methods
    public static boolean overwriteFile(String fn) {
        boolean ok;
        String s;

        System.out.print(fn + " exists. Overwrite (Y/N)? ");
        s = getInput().toLowerCase();
        ok = (s.equals("yes")) || (s.equals("y"));
        return ok;
    }

    public static boolean fileExists(String fn) {
        boolean exists;
        File f;

        f = new File(fn);
        exists = f.exists();
        return exists;
    }

    public static String getFileExtension(String fn) {
        String ext = "";

        if (fn.contains(".") && fn.lastIndexOf(".") != 0) {
            ext = fn.substring(fn.lastIndexOf(".") + 1);
        }
        return ext;
    }

    private static String getFileName() {
        String fn = "";
        String ext;

        System.out.print("Enter file name: ");
        fn = getInput();
        if (!fn.isEmpty()) {
            ext = getFileExtension(fn);
            if (!ext.equals(file_ext)) {
                System.out.println("Error: File must have extension: " + file_ext);
                fn = "";
            }
        }
        return fn;
    }

    // --- prompt to save game
    private static void saveGame() {
        String fn;
        boolean doSave;

        doSave = true;
        fn = getFileName();
        if (fn.isEmpty()) {
            doSave = false;
        } else if (fileExists(fn)) {
            if (!overwriteFile(fn)) {
                doSave = false;
            }
        }
        if (doSave) {
            try {
                FileOutputStream fos = new FileOutputStream(fn);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(game); // game
                oos.flush(); // write out any buffered bytes
                oos.close();
                System.out.print("Game saved\n");
            } catch (Exception e) {
                System.out.print("Serialization Error! Can't save data.\n"
                        + e.getClass() + ": " + e.getMessage());
            }
        } else {
            System.out.println("File not saved");
        }
    }

    // --- prompt to load game
    private static void loadGame() {
        String fn;

        fn = getFileName();
        if (fn.isEmpty()) {
            System.out.println("File loading failed");
        } else {
            try {
                FileInputStream fis = new FileInputStream(fn);
                ObjectInputStream ois = new ObjectInputStream(fis);
                game = (Game) ois.readObject();
                ois.close();
                System.out.print("\n---Game loaded---\n");
            } catch (Exception e) {
                System.out.print("Serialization Error! Can't load data.\n");
                System.out.print(e.getClass() + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String input;
        String output = "";

        game = new Game();
        in = new BufferedReader(new InputStreamReader(System.in));
        game.showIntro();      
        do {
            System.out.print("> ");            
            input = getInput().toLowerCase();
            switch (input) {
                case "save":
                    output = "";
                    saveGame();
                    break;
                case "load":
                    output = "";
                    loadGame();
                    break;
                default:
                    output = game.runCommand(input);
                    break;
            }
            if (!output.trim().isEmpty()) {
                game.showStr(output);
            }
        } while (!"q".equals(input) && !"quit".equals(input) );
    }

}
