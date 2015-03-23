package strings;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/6/14
 * Time: 9:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class BoyerMooreSearch {

    /**
     * Complexity O(m + n)
     *
     * 1. Build a bad match table. Rules for bad match:
     *  a. If no match in character set or if it is a last character match, return length of pattern
     *  b. Choose the lower cost of (length - index -1)
     * 2. Move the pointer by ptr + no of steps from step 1.
     * 3. Match from right to left.
     *
     *
     * @param str
     * @param pattern
     * @return
     */
    boolean searchString(String str, String pattern) {

        return false;
    }

    public static void main(String[] args) {

    }
}
