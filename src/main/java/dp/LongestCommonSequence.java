package dp;

/**
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/10/14
 * Time: 9:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class LongestCommonSequence {

    /**
     * The Simple case is NOT DP CASE. IT IS POLYNOMIAL TIME 2^N!!!! This is brute force
     * DP case is O(m * n)
     *
     * @param str1 String 1
     * @param str2 String 2
     * @param lidx1 Last Index of String 1
     * @param lidx2 Last Index of String 2
     * @return
     */
    static String simple_recurrence_lcs(char[] str1, char[] str2, int lidx1, int lidx2) {
        if(lidx1 < 0 || lidx2 < 0)
            return "";

        if(str1[lidx1] == str2[lidx2]) {
            //System.out.println(str1[lidx1]);
             return (str1[lidx1] + simple_recurrence_lcs(str1, str2, lidx1 - 1, lidx2 - 1));
        }

        //max of the below 2
       // if(str1.length > str2.length)
            String t1 = simple_recurrence_lcs(str1, str2, lidx1 - 1, lidx2);
       // else
            String t2 = simple_recurrence_lcs(str1, str2, lidx1, lidx2 - 1);

            String t3 = simple_recurrence_lcs(str1, str2, lidx1 - 1, lidx2 - 1);

        if(t1.length() > t2.length() && t1.length() > t3.length())
            return t1;
        else if(t2.length() > t1.length() && t2.length() > t3.length())
            return t2;
        else
            return t3;
        //if(t2.length() > t1.length())
            //return t2;


    }


    /**
     * This runs in polynomial time!!!!! 2^n
     * @param str1
     * @param str2
     */
    static void simple_reccurence_lcs(String str1, String str2) {
        System.out.println(simple_recurrence_lcs(str1.toCharArray(), str2.toCharArray(), str1.length() - 1, str2.length() - 1));
    }


    static void dp_lcs(String str1, String str2) {
        int[][] dp_stringArr = dp_lcs(str1.toCharArray(), str2.toCharArray());       //Print array
        printDPArray(str1,str2,dp_stringArr);
        printLCSString(str1,str2,dp_stringArr);
    }

    static void printDPArray(String str1, String str2,int[][] dp_stringArr) {
        System.out.print("   ");
        for(int k=1; k < str2.length() + 1; k++)
            System.out.print(" " + str2.charAt(k -1));

        System.out.println();
        for(int i=0; i < dp_stringArr.length; i++) {
            if(i > 0)
                System.out.print(str1.charAt(i -1) + " ");
            else
                System.out.print("  ");
            for(int j=0; j < dp_stringArr[i].length; j++) {
                System.out.print(dp_stringArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void printLCSString(String str1, String str2,int[][] dp_stringArr) {
        int m = str1.length() ; // dimensions of this arr is one more than string
        int n = str2.length() ;
        StringBuilder str = new StringBuilder();
        for(int i=m, j=n; i>0 && j > 0;) {
            //If along the diagonal it is
            //if(dp_stringArr[i][j] == (dp_stringArr[i-1][j-1] + 1) && dp_stringArr[i-1][j] == (dp_stringArr[i][j-1] + 1) && dp_stringArr[i][j] == (dp_stringArr[i][j-1] + 1) ) {
              if(str1.charAt(i-1) == str2.charAt(j-1)) {
                str.insert(0,str1.charAt(i-1));
                i = i-1;
                j = j-1;
            }else if(dp_stringArr[i-1][j] > dp_stringArr[i][j-1])
                i = i-1;
            else
                j=j-1;
        }


        System.out.println("\n"+str.toString());
    }

    static int[][] dp_lcs(char[] str1, char[] str2) {
        int m = str1.length + 1;
        int n = str2.length + 1;

        int[][] dpArr = new int[m][n];
        //let (i,0) and  (0,j) be 0
        for(int i=1; i < m; i++) {
            char c1 = str1[i - 1];
            for(int j=1; j < n; j++) {
                char c2 = str2[j - 1];
                if(c1 == c2) {
                    dpArr[i][j] = dpArr[i-1][j-1] + 1;
                } else
                    dpArr[i][j] = Math.max(dpArr[i-1][j],dpArr[i][j-1]);
            }
        }

        return dpArr;
    }

    public static void main(String[] args) {
        //String str2 = "abcd";//args[0];
        //String str1 = "afsdfsdfpbc";//args[1];
        String str2 = "HIEROGLYPHOLOGYH";//args[0];
        String str1 = "MICHAELANGELOH";//args[1];
        //simple_reccurence_lcs(str1, str2);

        //String str1 = "AGGTAB";//args[0];
        //String str2 = "GXTXAYB";//args[1];

        //String str1 = "thisisatest";
        //String str2 = "testing123testing";
        //String str1 = "HELLO";
        //String str2 = "HELLO";
        dp_lcs(str1,str2);
    }
}
