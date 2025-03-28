import org.junit.jupiter.api.Test;

public class EncodingTest {

    @Test
    public void testSimpleTextEncoding() {
        testEncodingAndDecoding("hello");
        testEncodingAndDecoding("aaaaabbbbcc");
        testEncodingAndDecoding("aaaaaaxx");
    }

    private void testEncodingAndDecoding(String text) {
        Huffman huffman = new Huffman(text);
        String encoded = huffman.encode();
        String decoded = huffman.decode(encoded);

        System.out.println("Original Text: " + text);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + decoded);
        System.out.println("Encoding Successful: " + text.equals(decoded));

        assert text.equals(decoded) : "Decoding failed for: " + text;
    }

    public static void main(String[] args) {
        EncodingTest test = new EncodingTest();
        test.testSimpleTextEncoding();
    }
}