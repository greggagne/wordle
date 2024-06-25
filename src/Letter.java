/**
 * Representation of a letter in a Wordle guess
 *
 * Each letter has:
 *
 * 1. The character
 * 2. The color (green, yellow, or gray)
 * 3. The position of the letter in the guessed word
 */

import java.awt.*;
public class Letter {
    public static final String RESET_COLOR = "\u001B[0m";
    public static final String YELLOW_CHARACTER = "\u001B[33m";
    public static final String GREEN_CHARACTER = "\u001B[32m";
    private char ch;        // the letter
    private Color color;    // the color of the letter
    private int position;   // the position of the letter in the guess

    public Letter(char ch, Color color, int position) {
        this.ch = ch;
        this.color = color;
        this.position = position;
    }
    public void print() {
        if (color == Color.GREEN) {
            System.out.print(GREEN_CHARACTER + ch + RESET_COLOR);
        }
        else if (color == Color.YELLOW) {
            System.out.print(YELLOW_CHARACTER + ch + RESET_COLOR);
        }
        else { // the color is gray
            System.out.print(ch);
        }
        System.out.print(" ");
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }

    public int getPosition() {
        return position;
    }

    public char getCharacter() {
        return ch;
    }
}
