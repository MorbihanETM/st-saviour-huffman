public class Leaf extends Node {
    private final char character;

    // Constructor that takes a character and frequency, calling the parent constructor
    public Leaf(char character, int frequency) {
        super(frequency); // Call to Node's constructor with frequency
        this.character = character;
    }

    // Getter method for character
    public char getCharacter() {
        return character;
    }
}
