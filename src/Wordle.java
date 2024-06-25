import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Wordle {

    // standard length of a Wordle word
    public static final int WORD_LENGTH = 5;

    // the maximum number of guesses
    public static final int MAX_GUESSES = 5;

    // list of possible wordle words
    private List<String> wordList = new ArrayList<>();

    // list of guessed words
    private List<Letter>[] guessedWords = new List[MAX_GUESSES];

    // the Wordle word
    private String wordleWord;

    // the status of each letter in the guessed word
    private boolean[] status = new boolean[WORD_LENGTH];
    // the number of attempts
    private int attempts = 0;

    // determines if we're playing in hard mode
    private boolean hardMode = false;

    /**
     * Validates if the guess is a valid Wordle word
     * @param guess
     * @return True if valid guess, false otherwise
     */
    public boolean validateGuess(String guess) {

        // TO-DO

        return true;
    }

    /**
     * Validates if the guess satisfies the rules of "hard mode"
     * @param guess
     * @return True if the guess satisfies hard mode rules, false if not.
     */
    public boolean validateHardMode(String guess) {

        // TO-DO

        return true;
    }
    public void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Clear command for Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Clear command for Unix-like systems
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Handle exceptions
            System.out.println("Error while clearing console: " + e.getMessage());
        }
    }


    /**
     * Reads the Wordle word dictionary into the wordList ArrayList
     * @param fileName
     * @throws FileNotFoundException
     */
    public void readWords(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));

        while (s.hasNext())
            wordList.add(s.next());

        s.close();
    }

    /**
     * Returns a random Wordle word from the dictionary
     */
    public String getWordleWord() {
        Random r = new Random();

        return wordList.get(r.nextInt(wordList.size()));
    }

    /**
     * Resets the status array at the beginning of each guessed word to
     * false for each position
     */

    public void clearStatus() {

        // TO DO
    }

    /**
     * Checks the status to determine if the player has correctly guessed all letters
     *
     * @return True if all values of status are set to true, false otherwise
     */
    public boolean checkStatus() {

        // TO DO

        return true;
    }


    /**
     * The main logic of playing Wordle! This method compares the guess to the
     * Wordle word and colors each letter appropriately.
     *
     * This method runs in two steps:
     *
     * Step #1 - Looks for exact matches (green letters)
     * Step #2 - Looks for non-exact matches (yellow letters)
     *
     * @param guess The guess entered by the player.
     */
    public void checkGuess(char[] guess) {
        List<Character> wordleList = new ArrayList<>();

        // store Wordle word in an array list
        for (int i = 0; i < wordleWord.length(); i++) {
            wordleList.add(wordleWord.charAt(i));
        }

        for (int i = 0; i < WORD_LENGTH; i++) {
            guessedWords[attempts].add(new Letter(guess[i],Color.GRAY, i));
        }

        /**
         * Step #1 - Proceed through each character looking for exact (green) matches
         */

        // TO DO

        /**
         * Step #2 - Now look for matches elsewhere (yellow)
         */

        // TO DO
    }

    /**
     * Plays the Wordle game
     * @param mode This value is true if playing hard mode, false otherwise
     */
    public void playGame(boolean mode) {
        Scanner reader = new Scanner(System.in);
        char[] guess = new char[WORD_LENGTH];

        hardMode = mode;

        wordleWord = getWordleWord();

        // let's override the word for now!
        /*
        wordleWord = "SKATE";
        wordleWord = "STALE";
        wordleWord = "DIGIT";
        wordleWord = "ALIVE";
        wordleWord = "AGENT";
        */

        // It is recommended to keep this line while testing
        System.out.println("The word is " + wordleWord);

        while (true) {
            clearStatus();

            System.out.print("Enter your guess: ");

            String input = reader.nextLine();

            if (input.length() != WORD_LENGTH)
                continue;

            if (!validateGuess(input.toUpperCase())) {
                System.out.println(input.toUpperCase() + " is not in Wordle dictionary");
                continue;
            }

            if (hardMode) {
                if (!validateHardMode(input.toUpperCase())) {
                    System.out.println("You are playing hard mode");
                    continue;
                }
            }

            // convert each letter to upper case
            for (int i = 0; i < WORD_LENGTH; i++) {
                guess[i] = Character.toUpperCase(input.charAt(i));
            }

            guessedWords[attempts] = new ArrayList();

            checkGuess(guess);

            ++attempts;

            System.out.println();

            for (int i = 0; i < attempts; i++) {
                for (int j = 0; j < guessedWords[i].size(); j++) {
                    guessedWords[i].get(j).print();
                }
                System.out.println();
            }

            // TO-DO
            // Determine if the player has won or lost
        }

    }

    public static void main(String[] args) {
        Wordle game = new Wordle();

        try {
            game.readWords("wordle-words.txt");

            if (args.length == 1) {
                if (args[0].equals("H")) {
                    game.playGame(true);
                }
            }
            else {
                game.playGame(false);
            }
        }
        catch (FileNotFoundException fnfe) { System.err.println("Can't find wordle dictionary!");}
    }
}
