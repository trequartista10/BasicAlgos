package dp;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/12/14
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class LongestIncreasingSubSeq {

    /**
     * Brute force is n^2
     * @param numbers
     */
    static void findLongestIncSubSeq(int[] numbers) {
        int[] seqCounts = new int[numbers.length];
        int allmax =0;
        for(int i=0; i < numbers.length; i++) {
            int max = numbers[i];
            int cnt =1;
            for(int j=i-1; j>=0; j--) {
                if(numbers[j] < max) {
                    max = numbers[j];
                    cnt++;
                }
            }

            seqCounts[i] = cnt;
            if(seqCounts[i] > allmax)
                allmax = seqCounts[i];
        }
        for(int i=0; i < numbers.length; i++)
            System.out.print(numbers[i]+ " ");
        System.out.println("\n" +allmax);
        for(int k=numbers.length -1; k>=0; k-- ) {
            if(seqCounts[k] == allmax) {
                System.out.print(numbers[k] + " ");
                allmax--;
            }

        }
    }

    /** Optimized lIs
     * Space Complexity is O(n)
     * Use active lists. If the element is less than all the end elements of the active list, then create a new active list
     *1. If A[i] is smallest among all end candidates of active lists, we will start new active list of length 1.

     2. If A[i] is largest among all end candidates of active lists, we will clone the largest active list, and extend it by A[i].

     3. If A[i] is in between, we will find a list with largest end element that is smaller than A[i]. Clone and extend this list by A[i]. We will discard all other lists of same length as that of this modified list.

     http://en.wikipedia.org/wiki/Patience_sorting
     Refer to : http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
     *
     *
     * @param args
     */
    static void findLongestIncSubSeqOpt(int[] numbers) {
        for(int i=0; i < numbers.length; i++) {
            int num = numbers[i];
        }

    }



    /**
     * Copy on write array and extend
     * @param arr
     * @param val
     * @return
     */
    static int[] cloneAndExtend(int[] arr, int val) {
        if(arr == null)
            return null;


        int[] newArr = Arrays.copyOf(arr,arr.length + 1);
        newArr[arr.length]=val;
        return newArr;
    }

    public static void main(String[] args) {
        int[] numbers = {0,4,1,2,8,11,12,6};
        findLongestIncSubSeq(numbers);

    }
}
