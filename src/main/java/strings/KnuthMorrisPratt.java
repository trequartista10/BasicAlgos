package strings;

/**
 * Created with IntelliJ IDEA.

 * Date: 4/7/14
 * Time: 9:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class KnuthMorrisPratt {

    /**
     * 1. KMP always avoids backup!
     * 2. Finite no of states
     * 3. Performance: Spends O(n) to build a state table. O(k) to search a pattern compared to a O(k.n) of naive algo
     * 4. n is pattern length and k is text length. Overall complexit O(n + k).
     *
     * Based on this https://www.youtube.com/watch?v=1k2KDhcO_uo
     * 1. Have two pointers i and j
     * 2. If there is a mismatch set i to 0 and compare a[i] and a[j] again. Set prev Cnt to 0 or one depending on the match
     * 3. If there is a match
     * @param ip
     * @return
     */
    int[] buildKMPTable(String ip) {
        char[] characters = ip.toCharArray();
        int[] matchTable = new int[characters.length];
        matchTable[0] = -1;
        int i=0,j=1, prevCnt = 0;
        for(; i < characters.length-1 && j < characters.length-1;) {
            if(characters[j] == characters[i]) {
                i++;
                j++;
                prevCnt++;
                matchTable[j] = prevCnt;
                continue;
            }else {
                i = 0;
                if(characters[i] == characters[j])
                    prevCnt = 1;
                else
                    prevCnt = 0;

                matchTable[j + 1] = prevCnt;
                j++;
                continue;
            }


        }
        //printTables(characters, matchTable);
        return matchTable;
    }

    /**
     * KMP does atmost 2n comparisons
     * @param pattern
     * @param text
     * @return
     */
    public boolean doKMP(String pattern, String text) {
        int[] matchTable = buildKMPTable(pattern);
        printTables(pattern.toCharArray(),matchTable);
        char[] s = text.toCharArray();
        char[] w = pattern.toCharArray();
        int m =0, i=0, j=0;
        /**
         *
         * m + i idx in text s
         * j current
         */
        for(; (m + i) < s.length;) {
            if(s[m+i] == w[i]) {
                i++;
                if(i >= w.length)
                    return true;
                continue;
            }else {
                System.out.println(m + " " + i + " " + matchTable[i]);
                m = m +  (i - matchTable[i]); //Find the new position of m.
                // No need to match patterns from the begining that are already matched. Goal is to not check matched characters in sequence not more than once.
                //If its a completely new character, -(-1) will become m + i + 1!!! Move on!
                if(matchTable[i] > -1)
                    i = matchTable[i]; // No need to match patterns from the begining that are alread matched
                    //Consider text: abcdaefabcabc and test pattern: abcdab. When 'e' and 'b' are checked,
                else
                    i = 0; //case where it is a completely new character which is not in the pattern


                System.out.println(m);
            }

        }

//        PriorityQueue pq = ne PriorityQueue();
  //      pq.poll()
        return false;

    }

    void printTables(char[] characters, int[] matchTable) {
        for(int i=0; i < characters.length; i++) {
            System.out.println(characters[i] + "  " + matchTable[i]);
        }
    }

    public static void main(String[] args) {
        //String str = "abaababaabc";
        //String str = "ATTATACA";
        String str = "ABC ABCDAB ABCDABCDABDE"; //wikipedia example
        KnuthMorrisPratt kmp = new KnuthMorrisPratt();
        kmp.buildKMPTable(str);
        System.out.println(kmp.doKMP("ABCDABD",str));

    }
}
