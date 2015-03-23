package numerical;

/**
 * Created by kuldeep on 3/14/15.
 */
public class FindingPower {

    /**
     * Using recursioion
     * @param base
     * @param exp
     * @return
     */
    static long pow(int base, int exp) {
        if (exp == 1)
            return base;

        long result = pow(base, exp / 2);
        result *= result;

        if (exp % 2 != 0)
            return result * base;

        return result;
    }

    /**
     * Constant space and much more elegant
     * 1. If bit is set then accumulate base to result
     * 2. keep exponentiating base by 2 if its not 0
     * @param base
     * @param exp
     * @return
     */
    static long powItr(long base, long exp) {
        
            long result = 1;
            while (exp != 0)
            {
                if ((exp & 1) == 1)
                    result *= base;
                exp >>= 1;
                base *= base;
            }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(pow(5, 1));
        System.out.println(pow(5, 2));
        System.out.println(pow(5, 3));
        System.out.println(pow(5, 25));
        System.out.println(pow(5, 26));

        System.out.println(powItr(5, 1));
        System.out.println(powItr(5, 2));
        System.out.println(powItr(5, 3));
        System.out.println(powItr(5, 25));
        System.out.println(powItr(5, 26));
    }
}