package tries;

import java.util.Arrays;

/**
 * Created by kuldeep on 1/16/15.
 */
public class BitTrie<T> {

    private final Node<T> root = new Node<T>(null,null);

    BitTrie() {
        root.children = new Node[2];
    }

    public static class Node<T> {
        public static final int LEFT = 0;
        public static final int RIGHT  = 1;
        byte[] key;
        int startBit;
        int endBit;
        Node<T>[] children;
        T val;

        Node(Node<T> other) {
            this.key = other.key;
            this.startBit = other.startBit;
            this.endBit = other.endBit;
            this.children = other.children;
        }

        Node(byte[] key, T val, int startBit, int endBit, Node<T>[] children) {
            this(key, val, startBit, endBit);
            this.children = children;
        }

        Node(byte[] key, T val) {
            this.key = key;
            this.val = val;
            startBit = 0;
            endBit = 7;
        }

        Node(byte[] key, T val, int startBit, int endBit) {
            this.key = key;
            this.val = val;
            this.startBit = startBit;
            this.endBit = endBit;
        }

        void printNode() {
            StringBuffer sb = new StringBuffer();
            printNode(sb,1);
            System.out.println(sb.toString());
        }

        void printNode(StringBuffer buff, int spaces) {

            char[] c = new char[spaces];
            for(int i =0; i < spaces; i++) {
                c[i] = '-';
            }

            if(children != null) {
                for(int i=0; i < children.length; i++) {
                    if(children[i] != null) {
                        buff.append('\n').append(c).append(new String(children[i].key)).
                                append("_").append(children[i].startBit).
                                append("_").append(children[i].endBit).
                                append("_").append(children[i].val == null ? 'N' : 'Y');
                        children[i].printNode(buff, spaces + children[i].key.length + 6);
                    }
                }
            }
        }
    }

    public void add(byte[] key, T val) {
        if(key.length == 0 || key == null)
            root.val = val;
        int side = getBitValue(key[0], 0);
        if(root.children[side] == null)
            root.children[side] = getNewNode(key,val,0,0,7);
        else
            add(key, val, 0, root.children[side]);
    }

    public void add(byte[] key, T val, int keyIdx, Node<T> node) {
        boolean searchFurther = handleFirstByte(node,val,key,keyIdx);
        if(!searchFurther)
            return;

        //KeyIdx incremented inside handle* method
        searchFurther = handleMiddleBytes(node, val, key, keyIdx);
        if(!searchFurther)
            return;

        handleLastByte(node, val, key, keyIdx); //Recurse till everything is updated
    }

    int getBitValue(byte key, int pos) {
        return (key >> pos) & 1;
    }


    int getMismatchedBit(byte a,byte b,int startBit, int endBit) {
        for(int i=startBit; i <= endBit; i++)
            if(getBitValue(a,i) != getBitValue(b,i))
                return i;

        return -1;
    }

    Node<T> getNewNode(byte[] key, T val, int keyIdx, int startBit, int endBit) {
        if(keyIdx == 0)
            return new Node<T>(key, val, startBit, endBit);

        byte[] dest = new byte[key.length-keyIdx];
        System.arraycopy(key,keyIdx,dest,0,dest.length);
        return new Node<T>(dest,val, startBit, endBit);
    }

    /**
     * Based on the index on the given node, split the given node into two nodes.
     * Children of the new node are set to null and based on the start bit of the new node, new node is referenced
     * to parent node
     * @param node
     * @param keyIdx
     */
    void splitMultiByteNode(Node<T> node, int keyIdx,int newKeyIdx, int newEndBit) {
        Node<T> tempNode = new Node<T>(Arrays.copyOfRange(node.key,newKeyIdx,node.key.length),node.val,
                newEndBit == 7 ? 0 : newEndBit + 1, node.endBit,node.children);


        node.key = Arrays.copyOfRange(node.key, 0, keyIdx+1);
        node.endBit = newEndBit;
        resetChildrenAndValue(node,tempNode);
    }

    void splitByteNode(Node<T> node, int newEndBit) {
        Node<T> tempNode = new Node<T>(Arrays.copyOfRange(node.key,0,node.key.length),node.val,
                newEndBit + 1,node.endBit,node.children);

        node.endBit = newEndBit;
        resetChildrenAndValue(node,tempNode);
    }

    void resetChildrenAndValue(Node<T> node, Node<T> tempNode) {
        node.val = null;
        /*
        if(node.children != null)
            for(int i=0; i < node.children.length ;i++)
                node.children[i] = null;
         */
        node.children = new Node[2];
        node.children[getBitValue(tempNode.key[0],tempNode.startBit)] = tempNode;
    }

    /**
     *
     * @return Boolean value indicating if the original node was split
     */
    boolean handleFirstByte(Node<T> node, T val, byte[] key, int keyIdx) {
        byte first = node.key[0];
        int mismatch = getMismatchedBit(first, key[keyIdx], node.startBit, node.key.length == 1 ? node.endBit: 7);
        if(mismatch < 0)   //Perfect Match
            return true;

        //Split node into two and then add two new children(one from existing split node and the other from remaining bytes of the new key)
        splitMultiByteNode(node, 0, 0, mismatch - 1);
        node.children[getBitValue(key[keyIdx],mismatch)] = getNewNode(key,val,keyIdx,mismatch,7);
        return false;
    }

    boolean handleMiddleBytes(Node<T> node, T val, byte[] key, int keyIdx) {
        if(node.key.length <= 2)
            return true;
        for(int i =1; i < node.key.length - 1; i++) {
            int idx = keyIdx + i;
            if(idx >= key.length ) {
                splitMultiByteNode(node,i-1,i,7);
                node.val = val;
                return false;
            } else if(key[idx] !=  node.key[i]){
                int mismatch = getMismatchedBit(key[idx], node.key[i],0,7);
                if(mismatch == 0)
                    splitMultiByteNode(node, i-1,i,7);
                else
                    splitMultiByteNode(node, i-1,i,mismatch-1);

                node.children[getBitValue(key[idx],mismatch)] = getNewNode(key,val,idx,mismatch,7);
                return false;
            }
        }
        return true;
    }

    boolean handleLastByte(Node<T> node, T val, byte[] key, int keyIdx) {
        keyIdx = keyIdx + node.key.length - 1;
        if(node.key.length > 1)  {
            if(keyIdx >= key.length ) {
                splitMultiByteNode(node,node.key.length -2,node.key.length - 1,7);
                node.val = val;
                return false;
            }else {
                byte last = node.key[node.key.length - 1];
                int mismatch = getMismatchedBit(last, key[keyIdx],0,node.endBit);
                if(mismatch != -1) {
                    if (mismatch == 0)
                        splitMultiByteNode(node, node.key.length - 2, node.key.length - 1, 7);
                    else
                        splitMultiByteNode(node, node.key.length - 1, node.key.length - 1, mismatch);

                    node.children[getBitValue(key[keyIdx], mismatch)] = getNewNode(key, val, keyIdx, mismatch, 7);
                    return false;
                }

            }
        }


        int nextStartBit = 0;
        if(node.endBit == 7)
            keyIdx++;
        else
            nextStartBit = node.endBit + 1;

        if(keyIdx == key.length) {
            node.val = val; //set or overwrite value
            return false;
        }else {

            int side = getBitValue(key[keyIdx],nextStartBit);
            if(node.children != null && node.children[side] != null)
                add(key,val,keyIdx,node.children[side]);
            else {
                if(node.children == null)
                    node.children = new Node[2];

                node.children[side] = getNewNode(key,val,keyIdx,nextStartBit,7);

            }
        }

        return true;
    }



    public static void main(String[] args) {
       // BitTrie<String> trie = new BitTrie<>();

        BitTrie<String> trie = new BitTrie<>();
        trie.add("ABC".getBytes(),"ABC");
        trie.add("ABD".getBytes(),"ABD");

        trie.add("ABCD".getBytes(),"ABCD");
        trie.add("ABDD".getBytes(),"ABDD");

        trie.add("ABE".getBytes(),"ABE");

        trie.add("ABF".getBytes(),"ABF");
        trie.add("ABG".getBytes(),"ABG");
        trie.add("ABH".getBytes(),"ABH");
        trie.add("ABI".getBytes(),"ABI");
        trie.add("ABJ".getBytes(),"ABJ");


        trie.add("ABCE".getBytes(),"ABCE");
        trie.add("ABDE".getBytes(),"ABDE");

        trie.add("C".getBytes(),"C");
        trie.add("Z".getBytes(),"Z");
        trie.add("B".getBytes(),"B");
        trie.add("X".getBytes(),"X");
        trie.add("P".getBytes(),"P");

        trie.add("PQRSTUVWXYZ".getBytes(),"PQRSTUVWXYZ");
        trie.add("PQRSTUVXZ".getBytes(),"PQRSTUVXZ");
        trie.add("PQLSTUVWXMZ".getBytes(),"PQLSTUVWXMZ");


        trie.root.printNode();
    }
}
