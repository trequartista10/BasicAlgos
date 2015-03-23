package codility;

/**
 *
 * 100 % score
 Task description
 A zero-indexed array A consisting of N integers is given. An inversion is a pair of indexes (P, Q) such that P < Q and A[Q] < A[P].
 Write a function:
 class Solution { public int solution(int[] A); }
 that computes the number of inversions in A, or returns −1 if it exceeds 1,000,000,000.
 Assume that:
 N is an integer within the range [0..100,000];
 each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 For example, in the following array:
 A[0] = -1 A[1] = 6 A[2] = 3
 A[3] =  4 A[4] = 7 A[5] = 4
 there are four inversions:
 (1,2)  (1,3)  (1,5)  (4,5)
 so the function should return 4.
 Complexity:
 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 *
 *
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 3/30/14
 * Time: 11:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayInversions {
    // you can also use imports, for example:
// import java.math.*;


        public int solution(int[] A) {
            int[] B = new int[A.length]; // Make a copy of A
            int inversions =0;
            inversions = mergeSort(A,0,A.length-1,B);
            return inversions;

        }


        int mergeSort(int[] A, int start, int end, int[] B) {

            if(A== null || A.length <= 1)
                return 0;

            if((end - start) == 1) {
                if(A[start] <= A[end]) {

                    B[start] = A[start];
                    B[end] = A[end];
                    return 0;
                }else {
                    B[end] = A[start];
                    B[start] = A[end];
                    A[end] = B[end];
                    A[start] = B[start];
                    return 1;
                }
            }else if(end == start)
                return 0;

            int inversions = 0;
            int mid = (end + start) /2;
            inversions+=mergeSort(A,start,mid, B);
            inversions+=mergeSort(A,mid+1, end, B);
            inversions += merge(start,end,mid,A, B);
            //System.out.println(inversions);
            return inversions;

        }

        int merge(int start, int end, int mid, int[] A, int[] B) {
            if(start == end )
                return 0;

            int multiplier =0;
            int cnt = 0;
            int sum =0;
            int i =start, j=mid+1;
            int k = start;
            for(; i <= mid && j<=end;k++) {
                if(A[i] <= A[j]) {


                    B[k] = A[i];
                    //sum = sum + (j - (mid));
                    i++;
                }else {

                    //sum= sum +(multiplier * cnt);

                    cnt =0;
                    B[k] = A[j];
                    sum = sum + (mid - i + 1);
                    j++;
                }

            }

            cnt =0;
            for(;i <= mid;k++,i++) {
                //multiplier = multiplier + 1;
                B[k] = A[i];

            }

            for(;j <=end;k++,j++) {
                //cnt = cnt + 1;
                // System.out.println(k + " " + j);
                B[k] = A[j];
                //sum = sum + (mid -start-1);

            }

            for(int t=start; t <=end; t++)
                A[t] = B[t];

            //sum = sum+(cnt * multiplier);
            return sum;
        }


}
