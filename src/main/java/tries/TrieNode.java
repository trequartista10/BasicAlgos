package tries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/11/14
 * Time: 8:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrieNode {
    public char c;
    public boolean isWord = false;

    public List<TrieNode> childNodes = new ArrayList<TrieNode>();

    public TrieNode(char c) { this.c = c;}


    /**
     * Look at each child node and if its a word add it to the list or recursively call the child trie nodes/
     * @param node
     */
    void addChildNode(TrieNode node) {
        this.childNodes.add(node);
    }

    List<String> getAllChildWords(String chars) {
        List<String> words = new ArrayList<String>();
        String currStrng = chars + c;
        if(this.isWord)
            words.add(currStrng);

        for(TrieNode node : childNodes) {
            words.addAll(node.getAllChildWords(currStrng));
        }
        return  words;
    }

    boolean isExists(char[] str, int index) {
        if(index == str.length -1) {
            if(this.c == str[index])
                return true;
            else
                return false;
        }

        for(TrieNode node : childNodes) {
            if(node.c == str[index + 1])
                return isExists(str, index + 1);
        }

        return  false;

    }

    /**
     * If the next character is found in one of the children then set that trie node as a word
     * @param word
     * @param index
     */
    void addWord(char[] word, int index) {
        if(index == word.length -1)
            if(this.c == word[index])
                this.isWord = true;


        for(TrieNode node : childNodes)
            if(node.c == word[index + 1]) {
                node.isWord = true;
                return;
            }
        if(index == word.length -2) {
            for(TrieNode node : childNodes)
                if(node.c == word[index + 1]) {
                    node.isWord = true;
                    return;
                }
        }




//Need to do
//        if()
//            this.childNodes.add(new TrieNode());


    }

}
