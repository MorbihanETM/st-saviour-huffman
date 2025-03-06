public class Node implements Comparable<Node> {
    private int frequency;
    private Node left;
    private Node right;


    public Node(int frequency) {
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    
    public Node(Node left, Node right) {
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }


    public Node getLeftNode() {
        return left;
    }

   
    public Node getRightNode() {
        return right;
    }

    @Override
    public int compareTo(Node n) {
        return Integer.compare(this.frequency, n.frequency);
    }
}
