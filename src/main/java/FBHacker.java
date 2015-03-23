import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

/**
 * Created by kuldeep on 1/11/15.
 */
public class FBHacker {



    void sortDigits(char[] digits) {
        Arrays.sort(digits);
    }

    static void swap (char[] digits,int idx1, int idx2)  {
        char temp = digits[idx1];
        digits[idx1] = digits[idx2];
        digits[idx2] = temp;
    }


    public static void main(String[] args) {
        String input= "/home/kuldeep/Downloads/cooking_the_books.txt";
        String output="/home/kuldeep/Downloads/cooking_the_books_output.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));

            String line = br.readLine();
            int val = Integer.valueOf(line);
            int counter = 1;
            while((line = br.readLine()) != null && val>0) {
                val--;
                String outputLine = "Case "+"#" +counter+": ";
                line = line.trim();
                if(line.isEmpty())
                    continue;


                char[] digits = line.toCharArray();
                char[] digitsCopy = Arrays.copyOf(digits,digits.length);
                char[] sorted = (Arrays.copyOf(digits,digits.length));
                Arrays.sort(sorted);
                //Max case
                System.out.println(digits);
                System.out.println(sorted);
                for(int j=0; j < sorted.length; j++) {

                    int k = sorted.length - 1-j;
                    if(sorted[k] == digits[j])
                        continue;

                    for(int t=j; t < digits.length;t++)
                        if(digits[t] == sorted[k]) {
                            swap(digits,t,j);
                            break;
                        }
                    break;
                }

                //Min case
                int idx = 0;
                for(int j=0,k=0; j < sorted.length && k < digitsCopy.length ; j++,k++) {
                    if(j==0) {
                        if(sorted[j] == '0') {
                            j--;
                            continue;
                        }

                        if(digitsCopy[k] == sorted[j]) {
                            continue;
                        }

                        for(int t=k; t < digitsCopy.length; t++) {
                            if(digitsCopy[t] == sorted[j]) {
                                swap(digitsCopy,t,j);
                                break;
                            }
                        }
                        break;

                    }
                }

                outputLine += new String(digitsCopy) + " "+new String(digits);
                System.out.println(outputLine);
                bw.write(outputLine + "\n");
                counter++;

            }
            br.close();
            bw.close();

        }   catch (Exception e) {
            e.printStackTrace();
        }
         /*
        Case #1: 13524 51324
        Case #2: 798 987
        Case #3: 123 321
        Case #4: 10 10
        Case #5: 5 5
        */
    }
}
