package codility;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/20/14
 * Time: 1:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class PrintAllPermutationsOfSomething {

    public static void main(String[] args) {
        String[][] posibilities = new String[][] {
                {"R", "G", "B"},
                {"M", "XL" , "L"},
                {"Checks" , "Stripes"},
                //{"store1", "store2"}

        };

        int allSize = 1;
        for(int i=0; i < posibilities.length; i++) {
            allSize = allSize * posibilities[i].length;
        }

        StringBuilder[] result = new StringBuilder[allSize];
        int repeat = 1;
        for(int i=0; i < posibilities.length; i++) {
            repeat = repeat * posibilities[i].length;
            int p =0;
            while (p < allSize) {
            for(int k=0;k < posibilities[i].length; k++) {

                    int t =0;
                    for(int j=0 ; j < allSize/repeat; j++ ) {
                        if(result[p] == null) {
                            result[p] = new StringBuilder();
                        }

                        //k = k %posibilities[i].length;
                        result[p].append("\t").append(posibilities[i][k]);
                        p ++;
                        //k = k + 1;
                    }


                }

            }

        }

        int ctr = 0;
        for(int i=0; i < result.length; i++)
            System.out.println(++ctr + "\t" +result[i].toString());

        System.out.println("\n" + allSize);

    }
}
/** Output
 * 1		R	M	Checks
 2		R	M	Stripes
 3		R	XL	Checks
 4		R	XL	Stripes
 5		R	L	Checks
 6		R	L	Stripes
 7		G	M	Checks
 8		G	M	Stripes
 9		G	XL	Checks
 10		G	XL	Stripes
 11		G	L	Checks
 12		G	L	Stripes
 13		B	M	Checks
 14		B	M	Stripes
 15		B	XL	Checks
 16		B	XL	Stripes
 17		B	L	Checks
 18		B	L	Stripes

 18
 */
