package main.java.bitwise;

/**
 * Consider a case where an Array contains triplets of each unique number in the array except for one number which has a single copy.
 * Find this single number
 *Example An array of {10,10,9,8,9,10,9}
 * Result: 8
 *
 * Solution:
 * Key things to remember
 * A xor A = 0
 * A xor 0 = A # identity for bitwise xor
 *
 * A & ~A = 0 #how to get rid of duplicate numbers
 * A & 1 = A #identity for bitwise and
 */
public class TripletsAndSingles {
    final static int findSingleFromTriplets(int[] a) {
       int ones =0, twos=0;

        for(int i=0; i < a.length; i++) {
            ones = (a[i] ^ ones) & ~twos;
            twos = (a[i] ^ twos) & ~ones;
        }

        return ones;
    }

    public static void main(String[] args) {
        System.out.println(findSingleFromTriplets(new int[]{10,10,9,8,9,10,9}));
    }

}


