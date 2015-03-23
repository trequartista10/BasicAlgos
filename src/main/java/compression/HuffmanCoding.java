package compression;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * 1. For a stream of characters, build a set of unique character and their respective counts/frequency
 * 2. Sort the set from from step 1 by their counts in ascending order  
 * 3. Build a binary tree from bottom up by aggregating two nodes with the least count such that count at each node is
 * sum of counts of its two immediate child nodes.
 * 4. THe count of the root should be the same as the character count in the stream
 * 5. From the root, assign a "0" for the left edge and "1" for the right edge. Bits 0 and 1 
 * 6. The representative bits for each character can be found by starting from the root and traversing to the leaf.
 * 7. As you can see, the characters nearest to the root will be more frequent and have fewer bits. Bits are derived by 
 * * aggregating each edge value.
 *
 * 8 Example: For a stream of ABCAABAEA
 *  > Unique Character->Count->Bit Representation
 *  A->5->0
 *  B->2->10
 *  C->1->110
 *  E->1->111
 *  Tree representation:

                            ABCE[9]
                            /   \ 1
                           /   BCE[4]     
                        0 /    0/   \1
                         /     /     CE[2]
                        /     /     0/  \1
                       A[5]   B[2]  C[1]  E[1]


 * Created by kuldeep on 2/28/15.
 */



public class HuffmanCoding {

    private Map<Character,String> characterEncoding = new HashMap<>();


    static class HuffmanNode {
        //final Set<Character> characters = new HashSet<>();
        final char c;
        //Null for leaves
        final HuffmanNode left;
        final HuffmanNode right;

        //Edge value from parent
        //final BitSet bs = new BitSet(1);
        boolean isSet;
        final int frequency;

        HuffmanNode(char c, int frequency) {
            this.c = c;
            left = null;
            right = null;
            this.frequency = frequency;
            isSet = false;
        }

        HuffmanNode(HuffmanNode left, HuffmanNode right) {
            this.left = left;
            this.right = right;
            right.isSet = true;
            frequency = right.frequency + left.frequency;
            c=' ';
        }
    }

    static List<HuffmanNode> getCharacterNodes(String stream) {
        //Build frequency table
        Map<Character,Integer> charCount= new HashMap<>();
        for(int i=0; i < stream.length(); i++) {
            Integer count = charCount.get(stream.charAt(i));
            if(count == null)
                count = 0;

            charCount.put(stream.charAt(i),++count);
        }

        //Build leaves of the huffman tree
        List<HuffmanNode> leaves = new ArrayList<>();
        for(Map.Entry<Character,Integer> entry : charCount.entrySet())
            leaves.add(new HuffmanNode(entry.getKey(),entry.getValue()));

        //Sort the leaves
        leaves.sort(new Comparator<HuffmanNode>() {
            @Override
            public int compare(HuffmanNode o1, HuffmanNode o2) {
                return Integer.compare(o1.frequency, o2.frequency);
            }
        });

        return leaves;
    }


    Map<Character,String> createEncoding(String stream) {
        List<HuffmanNode> leaves = getCharacterNodes(stream);
        TreeSet<HuffmanNode> nodes = new TreeSet<>(new Comparator<HuffmanNode>() {
            @Override
            public int compare(HuffmanNode o1, HuffmanNode o2) {
                return (o1.frequency == o2.frequency) ? 1 : Integer.compare(o1.frequency, o2.frequency);
            }
        });

        nodes.addAll(leaves);
        while(nodes.size() > 1) {
            Iterator<HuffmanNode> iterator = nodes.iterator();
            HuffmanNode l1 = iterator.next(); // constant time
            iterator.remove();
            HuffmanNode l2 = iterator.next();                 // constant time
            iterator.remove();
            HuffmanNode parent = new HuffmanNode(l1,l2);
            nodes.add(parent);  // log n
        }
        HuffmanNode node = nodes.iterator().next();
        //Traverse and update the child nodes
        traverseAndUpdateEncoding(this.characterEncoding,node,"");
        return characterEncoding;
    }

    void traverseAndUpdateEncoding(Map<Character, String> bitmap, HuffmanNode node, String bitsSoFar) {
        //BitSet bs = new BitSet(bitsSoFar.size() + 1);
        //bs.or(bitsSoFar);
        //bs.set(bs.size() - 1, node.isSet);
        //bitsSoFar = bitsSoFar + (node.isSet ? "1" : "0");
        
        if (node.right == null && node.left == null) {
            //Leaf reached           
            bitmap.put(node.c,bitsSoFar);
        } else  {
            if(node.right != null)
                traverseAndUpdateEncoding(bitmap,node.right,bitsSoFar+"1");
            if(node.left != null)
                traverseAndUpdateEncoding(bitmap,node.left,bitsSoFar+"0");
        }

    }
    
    void printChracacterEncoding(Map<Character,String> encoding) {
        encoding.forEach(new BiConsumer<Character, String>() {
            @Override
            public void accept(Character character, String bitSet) {
                System.out.println(character + "->" + bitSet);
            }
        });
        
    }



    public static void main(String[] args) {
        HuffmanCoding coding = new HuffmanCoding();
        coding.printChracacterEncoding(coding.createEncoding("ABCAABAEA"));

    }
}
