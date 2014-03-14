package dp;

/**
 * 1. Use dynamic prgramming to compute the levenshtein edit distance. Compute the minimum cost of editing two strings
 * such that the two strings are equal. Below for each cell, compute the min cost of 3,4 and 5 steps
 * 2. Initialize D(i,0) and D(0,j) as i and j respectively. ie Cost of removing i characters or j characters.
 * 2. D(i,j) = Insertion or deletion cost is prev cost + 1.
 * 3. Di,j) = Deleting both characters can be either 0 if the characters are equal or 2 if they are unequal
 * 4. D(m,n) = Final cost of editing
 *
 * 5. Brute force of simple recursive way, would be maybe 2^n. Need to check on that.
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/11/14
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditDistance {

    static int[][] computeEditDistanceMatrix(String str1, String str2) {
        int m = str1.length() + 1;
        int n = str2.length() + 1;
        int[][] d = new int[m][n];
        for(int i=1; i < m; i ++) {
            d[i][0] = i;
            for(int j=1; j < n; j++) {
                  d[0][j] = j; // This can be moved outside the loop!!! SO that it is set only once.
                 int minCost = 0; //Minimum cost of all possible edits
                 //cost if two characters are removed
                 if(str1.charAt(i-1) == str2.charAt(j-1)) { // If characters are equal
                    minCost = d[i-1][j-1];
                 } else { //If characters are unequal but two characters are equal
                    minCost = d[i-1][j-1] + 2;
                 }

                //Removing ith characer from string1
                if(minCost > (d[i-1][j] + 1))
                    minCost = d[i-1][j] + 1;

                //Removing jth character from string2
                if(minCost > (d[i][j-1] + 1))
                    minCost = d[i][j-1] + 1;

                d[i][j] = minCost;
            }
        }

        return d;
    }

    static void computeEditDistance(String str1, String str2) {
        int[][] d = computeEditDistanceMatrix(str1,str2);
        LongestCommonSequence.printDPArray(str1,str2,d);
        System.out.println();
        System.out.print("Edit distance is: "+ d[str1.length()][str2.length()]);
        //BackTrace can either be done by remembering the path or finding by finding the minimum
    }



    public static void main(String[] args) {

        String str4 = "EXECUTION";
        String str5 = "INTENTION";

        computeEditDistance(str4,str5);
    }
}
