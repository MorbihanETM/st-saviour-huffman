import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman {

    private Node root;
    private final String text;
    private Map<Character, Integer> frequencies;
    private final Map<Character, String> codes;

    public Huffman(String text) {
        this.text = text;
        populateFrequenciesMap();
        codes = new HashMap<>();
    }

    private void populateFrequenciesMap() {
        frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (frequencies.containsKey(c)) {
                frequencies.put(c, frequencies.get(c) + 1);
            } else {
                frequencies.put(c, 1);
            }
        }
    }

    public String encode() {
        Queue<Node> queue = new PriorityQueue<>();

        for (char c : frequencies.keySet()) {
            int frequency = frequencies.get(c);
            Node leaf = new Leaf(c, frequency);
            queue.add(leaf);
        }

        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            int combinedFrequency = left.getFrequency() + right.getFrequency();
            Node parent = new InternalNode(left, right, combinedFrequency);
            queue.add(parent);
        }

        root = queue.poll();
        generateCodes(root, "");
        return getEncodedText();
    }

    private void generateCodes(Node node, String code) {
        if (node instanceof Leaf) {
            Leaf leaf = (Leaf) node;
            codes.put(leaf.getCharacter(), code);
            return;
        }
        InternalNode internal = (InternalNode) node;
        generateCodes(internal.getLeft(), code + "0");
        generateCodes(internal.getRight(), code + "1");
    }

    private String getEncodedText() {
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray()) {
            builder.append(codes.get(c));
        }
        return builder.toString();
    }

    public String decode(String encoded) {
        StringBuilder builder = new StringBuilder();
        Node current = root;
        for (char bit : encoded.toCharArray()) {
            current = bit == '0' ? ((InternalNode) current).getLeft() : ((InternalNode) current).getRight();
            if (current instanceof Leaf) {
                builder.append(((Leaf) current).getCharacter());
                current = root;
            }
        }
        return builder.toString();
    }

    public void printCodes() {
        codes.forEach((character, code) ->
                System.out.println(character + ": " + code)
        );
    }
}

abstract class Node implements Comparable<Node> {
    protected int frequency;
    public Node(int frequency) { this.frequency = frequency; }
    public int getFrequency() { return frequency; }
    public int compareTo(Node other) { return this.frequency - other.frequency; }
}

class Leaf extends Node {
    private final char character;
    public Leaf(char character, int frequency) {
        super(frequency);
        this.character = character;
    }
    public char getCharacter() { return character; }
}

class InternalNode extends Node {
    private final Node left, right;
    public InternalNode(Node left, Node right, int frequency) {
        super(frequency);
        this.left = left;
        this.right = right;
    }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }
}
