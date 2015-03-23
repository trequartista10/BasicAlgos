package arrays;

import util.Utils;

import java.util.Arrays;

public class MedianOfTwoArrays {

    static int findMedian(int[] a, int[] b, int startA, int endA, int startB, int endB) {


        if((endA - startA <= 1) || (endB - startB) <= 1) {
            if((endA - startA) <= 1)
                return getMedianEdgeCase(a,b,startA,endA,startB,endB);
            else
                return getMedianEdgeCase(b,a,startB,endB,startA,endA);

        }
        int medianIdxA = (startA + endA) / 2;
        int medianIdxB = (startB + endB) / 2;

        if(a[medianIdxA] == b[medianIdxB])
            return a[medianIdxA];



        if(a[medianIdxA] < b[medianIdxB])  {
            return findMedian(a,b, medianIdxA + 1, endA, startB, medianIdxB -1);

        } else {
            return findMedian(a,b,startA, medianIdxA -1,  medianIdxB + 1, endB);

        }



    }

    static int getMedianEdgeCase(int[] smaller, int[] larger, int startS, int endS, int startL, int endL) {
        int totalLen = endS - startS + 1 + 3;
        if((endL - startL) <=1)
            totalLen = endL - startL + 1 + endS - startS + 1;
        
        int[] arr = new int[totalLen];
        int j=0;
        
            for (int i = startS; i <= endS; j++, i++)
                arr[j] = smaller[j];


        if((endL - startL) <=1) {
            for (int i = startL; i <= endL; j++, i++)
                arr[j] = larger[j];
        }else {
            int medianIdx = (startL + endL) /2 ; 
            arr[j] = larger[medianIdx];
            arr[j++] = larger[medianIdx-1];
            arr[j++] = larger[medianIdx + 1];
        }

        
        Arrays.sort(arr);
        return arr[arr.length/2];
    }


    public static void main(String[] args) {
        int[] a = Utils.getRandomArray(10);
        int[] b = Utils.getRandomArray(10);
        Arrays.sort(a);
        Arrays.sort(b);

        System.out.println(findMedian(a, b, 0, a.length - 1, 0, b.length - 1));
        int [] s = new int[a.length+b.length];
        System.arraycopy(a,0,s,0,a.length);
        System.arraycopy(b,0,s,a.length,b.length);
        Arrays.sort(s);
        //System.out.println(Arrays.toString(s));
        System.out.println(s[(s.length/2) - 1] +":"+ s[s.length/2]+":" +s[(s.length/2) + 1]);

    }
}
