package main.java.dp;

import java.util.Arrays;

/**
 * Created by kmarathe on 5/15/16.
 */
public class BitCounter {

    public static int[] countBits(int num) {
        int[] bits = new int[num+1];
        if(num == 0)
            return bits;

        bits[1] = 1;
        int counter = 2;
        int bitcounter;

        for(int i=2;i<=num || counter <= num;) {
            bitcounter = 0;
            bits[counter++] = 1 ;
            int next = i*2;
            while(counter <next && counter <= num)
                bits[counter++] = 1 + bits[++bitcounter];

            i = next;
        }

        return bits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(32)));
    }
}
