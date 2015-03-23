package tries;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/27/14
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class DictonarySuffixTrie {
    int size;
    public HashMap<Character, TrieNode> firstLevel;

    public List<String> getAllElements(String pattern) {
        return  null;
    }

    public void addNewSuffixTree(String word) {
        for(String subString : getAllSubstrings(word)) {
            TrieNode node = firstLevel.get(subString.charAt(0));

            if(node == null) {
                node = new TrieNode(subString.charAt(0));
                firstLevel.put(subString.charAt(0), node);
            }
            if(word.length() > 1)
                addWord(word.toCharArray(), 1, node);
            else
                node.isWord = true;

            PriorityQueue<Integer> pint = new PriorityQueue<>(10,Collections.reverseOrder());

        }
    }


    public List<String> getAllSubstrings(String word) {
        List<String> allSubstrings = new ArrayList<String>();
        for(int i=0; i < word.length(); i++) {
            allSubstrings.add(word.substring(i));
        }

        return allSubstrings;
    }

    void addWord(char[] arr, int index,TrieNode node) {
        for(TrieNode n : node.childNodes) {
            if(n.c == arr[index]) {
                if(index == arr.length -1) {
                    //Case where it is the last node

                }else {
                    index = index + 1;
                    addWord(arr, index, n);
                    return;
                }
            }
        }
        TrieNode temp = new TrieNode(arr[index]);
        node.addChildNode(temp);
        if(index == arr.length -1) {
            temp.isWord = true;
        } else {
            index = index + 1;
            addWord(arr, index, temp);
        }
    }

    List<String> getAllMatchingSubArray(String pattern ) {
        char[] p = pattern.toCharArray();
        TrieNode node = firstLevel.get(p[0]);
        List<String> values = new ArrayList<String>();
        StringBuilder soFar = new StringBuilder();
        if(node == null)
            return null;

        if(p.length <=1 ) {
            if(node.isWord)
                values.add(""+p[0]);
            return values;

        } else {
            getAllWordsBelow(p,node,1,values,"" +p[0]);
            return values;
        }
    }

    void getAllWordsBelow(char[] pattern, TrieNode node, int index, List<String> values, String soFar) {

        for(TrieNode child : node.childNodes) {
        //        if(child.c)
        }
    }





    public static void main(String[] args) {
        System.out.println(Character.MAX_VALUE);
        System.out.println(Character.MIN_VALUE);


    }

}
