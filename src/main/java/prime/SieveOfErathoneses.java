package prime;

import java.util.ArrayList;
import java.util.List;

/**
 * This program generates prime numbers from 1 to 100
 *
 * 1. Generate a 10x10 2d matrix from 1 to 100 *
 * 2. Between 1 to 10 cross out numbers if they have more that two factors
 * 3. 11 to 100 Cross out all multiples of 1 to 10.
 * 4. Un crossed numbers are prime numbers.
 * 5. 1 is neither prime nor composite number
 * 6. Inner loop starts at I * I as all prime numbers till I are already marked.
 *
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/9/14
 * Time: 12:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class SieveOfErathoneses {

    public static void main(String[] args) {
        //int n = Integer.valueOf(args[0]);
        int n = 300;
        boolean[] nonPrimes = new boolean[n + 1];
        List<Integer> primes = new ArrayList<Integer>();
        int cnt =0;
        //PriorityQueue
        for(int i=2; i<=n;i++) {
            if(nonPrimes[i] ==  false) {
               // primes.add(i);
                for(int j=i; j*i <=n; j++) { //Start j =i as everything till i * i is already marked
                    nonPrimes[j*i] = true;
                }


                if(i >= (n+1)/2)
                    break;

               // i = (int)Math.pow(i,2);
            }
        }

        for(Integer prime : primes) {
            System.out.println(prime);
            cnt++;
        }

        for(int k = 1; k <=n; k++)
            if(!nonPrimes[k]) {
                //System.out.println(k);
                cnt++;
            }

        System.out.println("PRinted : "+ cnt + " numbers");
        System.out.println("complexity is O(n * (loglog n))");
    }
}
