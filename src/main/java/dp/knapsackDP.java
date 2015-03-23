package dp;

/**
 * N = Total number of elements
 * Wi = Weight of ith element
 * Bi = Benefit of ith element
 * Xi = either 1 or 0 if element is chosen or not
 * C = Capacity .ie SumOf(WiXi) should be less than C
 * i goes from 1 to N
 *
 * Aim:Maximize SumOf(BiXi) such that SumOf(WiXi) < C
 *
 * Brute Force Method: Try al possible combinations.ie NC1 + NC2 +....+NCn = 2^N
 *
 * Dyanimic Programming:
 *
 *
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/12/14
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class knapsackDP {

    /**
     *
     * @param w
     * @param b
     * @param capacity
     */
    /*
    public static void doKnapSack(int[] w, int[] b, int capacity) {
        int m = w.length;

        int[] result = new int[capacity + 1];
        int[] previousElement = new int[capacity + 1];
        int[] keep = new int[capacity + 1];

        int[][] dp = new int[capacity + 1][m + 1];
        for(int i=0; i <capacity+1; i++) {
            //result[i] = result[i-1];
            for(int j=0; j<m; j++) {
                int max = 0;
                if(i==0 || j==0) {
                    dp[i][j] = 0;
                    continue;
                }

                if(w[j] < i) {
                    int curr = dp[i - w[j]][j-1] + b[j];
                    if(curr > max)
                        max = curr;
                } else if(w[j] == i) {
                    if(b[j] > max)
                        max = b[j];
                }else {
                    if(dp[i][j-1] > max)
                        max = dp[i][j-1];
                }

                dp[i][j] = max;
            }

        }

        for(int i=0;i <= capacity; i++) {
            for(int j=0; j<= m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

    } */

    static void doKnapSack(int[] w, int[] b, int capacity) {
        int[][] keep = new int[w.length + 1][capacity + 1];
        int[][] dp = new int[w.length + 1][capacity + 1];

        for(int i=0; i <= w.length; i++) {
            for(int j=0; j <= capacity; j++) {
                if(i==0 || j==0) {
                    continue;
                }
                //Run compares
                int max = dp[i-1][j];        //The previously set benefit for the capacity j flows down
                //System.out.println(i +" "+j);
                if(j >= w[i-1]) {
                    int temp = dp[i-1][j- w[i-1]] + b[i-1];//Case where the new benefit is better benefit with this element
                    if(b[i-1] > max) {
                        max = b[i-1];
                        keep[i][j] = 1;
                    }
                    if(temp > max) {            //Case where a single element fits in.
                        max = temp;
                        keep[i][j] = 1;
                    }
                    dp[i][j] = max;

                }
            }

        }

        // Printing the beautiful matrices
        for(int i=0;i <= w.length; i++) {
            for(int j=0; j<= capacity; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\n\n\n\n");
        for(int i=0;i <= w.length; i++) {
            for(int j=0; j<= capacity; j++) {
                System.out.print(keep[i][j] + " ");
            }
            System.out.println();
        }


        //Figure out what the elements are
        for(int i=w.length,j=capacity; ;) {
            if(i < 0 || j< 0)
                break;

            if(keep[i][j] == 1) {
                System.out.println(w[i-1]);
                j=j-w[i-1];
                i=i-1;
                continue;
            }
            i = i-1;
        }

    }





    public static void main(String[] args) {
        //int[] w = {2,3,4,5,6};
        //int[] b = {3,2,1,4,5};
        //int[] b = {60, 180, 120};
        //int[] w = {10, 40, 30};
        int[] w ={4,6,5,7,3,1,6};
        int[] b = {12,10,8,11,14,7,9};
        int capacity  = 18;
        doKnapSack(w,b,capacity);

    }
}
