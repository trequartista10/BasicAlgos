package dp;


import java.util.Arrays;

/**
 * Solving largest contiguous sub array sum problem
 * Created by kuldeep on 9/21/14.
 */
public class KadaneAlgo {

    static int[] getLargestSumSubArray(int[] arr) {
        int maxStart=0;
        int maxEnd =0;
        int maxTempStart=0;
        int maxSoFar=arr[0];
        int maxHere=arr[0];
        int max = arr[0];
        int maxElemIdx=0;

        for(int i=1; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
                maxElemIdx = i;
            }
            if((maxHere + arr[i])> arr[i]) {
                maxHere = (maxHere + arr[i]);
            }else {
                maxHere = arr[i];
                maxTempStart=i;
            }
            if(maxSoFar < maxHere) {
                maxSoFar = maxHere;
                maxEnd = i;
                maxStart = maxTempStart;
            }
        }


        if(maxEnd == -1) {
            maxStart=maxEnd=maxElemIdx;
        }

        return Arrays.copyOfRange(arr, maxStart, maxEnd + 1);
    }



    public static void main(String[] args) {
        int[] arr = new int[]{10,2,3,-20,2,-1,30,-1};
        int[] arr2 = new int[]{-10,-2,-3,-20,-2,-1,30,-1};
        int[] arr3 = new int[]{10};
        int[] arr4 = new int[]{10,-9,-6,5,-1,-9,-3,4};
        System.out.println(Arrays.toString(getLargestSumSubArray(arr)));
        System.out.println(Arrays.toString(getLargestSumSubArray(arr2)));
        System.out.println(Arrays.toString(getLargestSumSubArray(arr3)));
        System.out.println(Arrays.toString(getLargestSumSubArray(arr4)));


    }
}
