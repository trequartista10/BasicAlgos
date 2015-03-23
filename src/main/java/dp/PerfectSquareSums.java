package dp;public class PerfectSquareSums {

    static int findMinSumPerfectSqares(int n) {
        if(n ==0) return -1;
        if(n == 1) return 1;

        int k = (int)Math.floor(Math.sqrt(n));
        int [] squares = new int[k+1];
        int[] cusums = new int[n + 1];

        //Initialize squares
        for(int i=1; i < squares.length; i++)
            squares[i] = (int)Math.pow(i,2);
        for(int i=0; i < cusums.length; i++)
            cusums[i] = i;

        for(int i=2; i < cusums.length; i++)
            for(int j=1; j < squares.length; j++) {
                if(i == squares[j]) {
                    cusums[i] = 1;
                    continue;
                }

                if(i < squares[j])
                    continue;
                cusums[i] = min(cusums[i], cusums[i - squares[j] ] + 1);
            }
        return cusums[n];
    }

    static int min(int a , int b) {
        if(a < b)
            return a;
        else
            return b;
    }

    public static void main(String[] args) {
        for(int i=0; i < 101; i++) {
            System.out.println(i + "->" +findMinSumPerfectSqares(i));
        }
    }
}