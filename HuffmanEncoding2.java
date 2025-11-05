import java.util.*;

// Node class representing each character and its frequency
class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    // Compare nodes by frequency (for priority queue)
    public int compareTo(Node other) {
        return this.freq - other.freq;
    }
}

public class HuffmanEncoding2 {

    // Recursive function to build Huffman codes map
    static void buildCodeMap(Node root, String code, Map<Character, String> codeMap) {
        if (root == null)
            return;

        // If it's a leaf node, store the code
        if (root.left == null && root.right == null) {
            codeMap.put(root.ch, code);
            return;
        }

        buildCodeMap(root.left, code + "0", codeMap);
        buildCodeMap(root.right, code + "1", codeMap);
    }

    // Main Huffman Encoding function
    static void huffmanEncode(String message) {
        // Step 1: Calculate frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : message.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Create a priority queue (min-heap)
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // Step 3: Create leaf nodes for each character
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Step 4: Build Huffman tree
        while (pq.size() > 1) {
            Node left = pq.poll();   // smallest freq
            Node right = pq.poll();  // next smallest freq

            Node newNode = new Node('-', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;

            pq.add(newNode);
        }

        // Step 5: Generate Huffman Codes
        Node root = pq.peek();
        Map<Character, String> codeMap = new HashMap<>();
        buildCodeMap(root, "", codeMap);

        // Step 6: Display codes
        System.out.println("\nHuffman Codes:");
        for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Step 7: Encode the message
        StringBuilder encoded = new StringBuilder();
        for (char ch : message.toCharArray()) {
            encoded.append(codeMap.get(ch));
        }

        System.out.println("\nOriginal Message: " + message);
        System.out.println("Encoded Message : " + encoded.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a message to encode: ");
        String message = sc.nextLine();
        sc.close();

        huffmanEncode(message);
    }
}
