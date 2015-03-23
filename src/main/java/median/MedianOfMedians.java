package median;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created by kuldeep on 1/5/15.
 */
public class MedianOfMedians {

    private static final int SEGMENT_SIZE = 5;

    public static int bruteForce(int[] a) {
        Arrays.sort(a);
        return a[a.length/2];
    }

    //Acerage/Expected running time of o(n). Worst case is O(n^2) low probability
    public static int randomSelect(int[] a, int k, int start, int end) {
        //Two elements case
        if (end - start <= 1) {
            if(a[start] > a[end])
                swap(a,start,end);

            if(k == start || k == end)
                return a[k];
            else {
                System.out.println("Start: " + start + " End: " + end + " Kth element: " + k);
                return -1; //Something went terribly wrong

            }
        }
        //Pick a random element
        int idx = start + new SecureRandom().nextInt(end - start);
        int pivotIdx = partition(a,idx,start,end);


        if(pivotIdx == k)
            return a[pivotIdx];

        else if(pivotIdx <  k)
            return randomSelect(a,k,pivotIdx,end);
        else
            return randomSelect(a,k,start,pivotIdx-1);
    }

    //Deterministic algorithm
    //median of medians
    /*
    1. Split the elements into 5 element groups. so n/5 groups in total
    2. Find the median of each 5 element group
    3. Recursively find median of all median(n/5 elements) groups.
    4. Use this as pivot and partition to find the kth largest/or use it as pivot for quick sort

     */
    int medianOfMedians(int[] a, int k, int[] temp1, int[] temp2, int start, int end) {

        int totalLen = end-start + 1;
        //if(totalLen <)

        int tempCounter =0;
        //int[] b = new int[totalLen/5];
        //Step 1. Sort 5 element groups
        int len = 5;
        for(int i=0;i<end;i=len) {
            if(i+10 >= end)
                len = end;
            else 
                len = i+5;
            Arrays.sort(a,i,len);
            temp1[tempCounter++]=a[(i+len)/2];
        }

        int medianOfMedian;
        if(tempCounter <= 5) {
            Arrays.sort(temp1,0, tempCounter);
            medianOfMedian = temp1[tempCounter/3];
        }  else {
            medianOfMedian = medianOfMedians(temp1,0,tempCounter);
        }
        
        len = 5;
        for(int i=0;i<end;i=len) {
            if(i+10 >= end)
                len = end;
            else
                len = i+5;
            int median = a[(i+len)/2];
            
        }



        //Partition the elements Worst case 70% left or right. Best case 50% left on either side
        int idx1=0,idx2=0;
        for(int i=0;i < a.length;i=i+5) {
            len = i+5;
            if(i+10 > a.length)
                len = a.length;
            int median = a[(i+len)/2] ;
            int limit = len;
            /* We will check all elements
            if(median >= medianOfMedian) {
              limit = ((i+len)/2) -1;
            }*/

            for(int j=i; j < limit; j++) {
                if(a[j] <= medianOfMedian) {
                    //In 30% case its certain to be less
                    temp1[idx1] = a[j];
                    idx1++;
                }else {
                    temp2[idx2] = a[j];
                    idx2++;
                }

            }
        }
        

        if(idx1 == k-1) {
            return medianOfMedian;
        } else {
            //If idx > k and if idx < k
            if(idx1 > k)
                medianOfMedians(temp1, k, a, temp2,start,idx1);
            else {
                medianOfMedians(temp2, k-idx1, a, temp2,idx1+1,idx2);
            }
            
        }
        return -1;
    }





    public static int partition(int[] a, int idx, int start, int end) {
        //swap with first element
        swap(a, start, idx);

        int ptr1 = start + 1;
        int ptr2 = end;
        int pivotValue = a[start];
        int pivotIdx = start;

        for(; ptr1 < ptr2;) {
            if(a[ptr1] <= pivotValue)
                ptr1++;
            else {
                swap(a, ptr1, ptr2);
                ptr2--;
            }
        }

        if(ptr2 == start + 1)
            return start;

        if(a[ptr1] <= pivotValue)
            swap(a,start,ptr1);
        else {
            swap(a,start,ptr1-1);
            ptr1 = ptr1 -1;
        }

        return ptr1;
    }

   static  void swap(int[] a, int i, int idx) {
        int temp = a[idx];
        a[idx] = a[i];
        a[i] = temp;
    }


    public static int medianOfMedians(int[] a, int start, int end)  {
        for(int i =start; i < (end/SEGMENT_SIZE) - 1; i++) {
            for(int j=0; j < SEGMENT_SIZE; j++) {

            }

        } return -1;

    }



    public static void main(String[] args) {
        int failed = 0;
        int total =1000;
        long t1 = System.currentTimeMillis() ;
        for(int i=1; i <=1;i++) {
            int[] a = util.Utils.getRandomArray(100000);
            int[] b = Arrays.copyOf(a, a.length);

            //Utils.printArray(a);
            int bruty = bruteForce(a);
            //int bruty = -1;
            System.out.println("Brute Force: " + bruty);
            //Arrays.sort(a);
            //Utils.printArray(a);

            int randSelect = randomSelect(b, b.length / 2, 0, b.length - 1);
            //int randSelect = -1;
            System.out.println("Random select: " + randSelect);
            //System.out.println(medianOfMedians(a));
            if (bruty != randSelect) {
                System.out.println("Difference: bruty vs randSelect");
                failed++;
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Failed|Passed|time: " + failed + "|" + total+"|"+(t2 -t1)/1000);
        //Utils.printArray(b);

    }
}
