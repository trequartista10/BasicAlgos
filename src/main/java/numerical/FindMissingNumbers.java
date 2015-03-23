package numerical;

import java.util.Arrays;

/**
 Find 2 missing numbers in a contiguous array
 */
class MissingNumbers {

    static 	int[] findNumbers(int[] arr, int n) {
        int sum = 0;
        int squareSum = 0;
        for(int i=0; i <  arr.length; i++) {
            sum+=arr[i];
            squareSum+=((arr[i]*arr[i]));
        }
        int missingSum = closedFormSum(n) - sum;
        int missingProd = ((squareSum + (missingSum * missingSum)) - (closedFormSquareSum(n))) /2;
        return findRoots(missingProd, missingSum);
    }

    static int[] findRoots(int missingProd, int missingSum) {
        int[] roots = new int[2];
//ax^2 + bx + c = 0
        int a = 1;
        int b = (-1) * missingSum;
        int c = missingProd;
        int d = (int)Math.sqrt((b*b) -(4*a*c));
        roots[0] =  (((-1)* b) + d)/(2*a);
        roots[1] = (((-1)* b) - d)/(2*a);
        return roots;

    }

    static int closedFormSum(int n) {
        return ((n/2)*((2) + ((n -1))));
    }

    static int closedFormSquareSum(int n) {
        return (n*((2*n) + 1)*(n + 1)) /6;
    }

    public static void main(String[] args) {
        int arr[]  = new int[] {1,2,4,6};
        System.out.println(Arrays.toString((findNumbers(arr, 6))));
    }
}