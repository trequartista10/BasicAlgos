package permutations;

/**
 * Below Prints all permutation of a given String
 *
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/8/14
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TruthTable {
    static int ctr =0;

    static void permuteString(int len, String str, String prefix) {
        if(prefix.length() == len)
            System.out.println((++ctr) + " " +prefix);

        for(int i=0; i<str.length(); i++) {
            permuteString(len, str.substring(0, i) + str.substring(i + 1, str.length()), prefix + str.charAt(i));
        }

    }


    //More efficient by using character arrays instead of creating strings
    static void permuteString(int len, char[] prefix, char[] str) {
        if(len == prefix.length)
            System.out.print(prefix);
        //for(int i=0; i < str.length; i++)
        //permuteString(prefix,null,null);

    }

    public static void perm2(String s) {
        int N = s.length();
        char[] a = new char[N];
        for (int i = 0; i < N; i++)
            a[i] = s.charAt(i);
        perm2(a, N);
    }

    private static void perm2(char[] a, int n) {
        if (n == 1) {
            System.out.println(a);
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n-1);
            perm2(a, n-1);
            swap(a, i, n-1);
        }
    }

    // swap the characters at indices i and j
    private static void swap(char[] a, int i, int j) {
        char c;
        c = a[i]; a[i] = a[j]; a[j] = c;
    }


    private static void permInOrder(char[] a, int start) {
        if(start == a.length-1) {
            System.out.print(++ctr + " ");
            System.out.println(a);
        }


        for(int i=start; i < a.length; i++) {
            //swap out ith element with start
            char temp = a[start];
            a[start] = a[i];
            a[i] = temp;
            permInOrder(a, start + 1);

            //swap back the two
            temp = a[start];
            a[start] = a[i];
            a[i] = temp;
        }
    }


    //public static void perm2(String prefix, String str) {}

    public static void main(String[] args) {
        //perm1("","abcde");
        permInOrder(new char[]{'1', '2', '3', '4', '5'}, 0);

        //permuteString(7,"abcdefg","");
        /*
        String input = args[0];
        List<StringBuilder> output = new ArrayList<StringBuilder>();

        char[][] op= new char[1][input.length()];

        for(int i=0; i < input.length(); i++) {
            char c =input.charAt(i);

            for(int j=0; j < input.length();j++) {

            }
            //int j = i%input.length();
            //StringBuilder sb = new StringBuilder();
            //char c =input.charAt(j);
            //sb.append(c);

            //output.add(new StringBuilder());

            //recurseResult(output, c);
        }

        for(int i=0; i < input.length(); i++) {

        } */
    }
}
