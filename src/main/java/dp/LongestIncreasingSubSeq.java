package dp;

/**
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/12/14
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class LongestIncreasingSubSeq {

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

    public static void main(String[] args) {
        int[] numbers = {0,4,1,2,8,11,12,6};
        findLongestIncSubSeq(numbers);

    }
}
