package util;
import java.security.SecureRandom;

/**
 * Created by kuldeep on 1/5/15.
 */
public class Utils {
    public static int[] getRandomArray(int len) {
        int[] a = new int[len];
        for(int i=0; i < len; i++)
            a[i] = new SecureRandom().nextInt();

        return a;

    }

    public static void printArray(int[] a) {
        System.out.print("{");
        for(int i : a)
            System.out.print(i+",");

        System.out.println("}");
    }


}
