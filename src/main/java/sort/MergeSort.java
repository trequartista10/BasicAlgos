package sort;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 3/23/14
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergeSort<T extends Comparable> {
    private Comparable [] tempItems;
    public void sort(T[] items) {
        tempItems = new Comparable[items.length];
        divideSortAndMerge(items,0,items.length-1);
        //items = (T[])tempItems;
        printItems(tempItems);
    }


    public void divideSortAndMerge(T[] items , int start, int end) {
        if(items == null || end-start == 0)
            return;
        int mid = (start + end) /2;
        if(end - start == 1) {
            mid = end;
        }


        divideSortAndMerge(items, start, mid -1);
        divideSortAndMerge(items, mid, end);

        merge(items,start,mid,end);
        //items = (T[])tempItems;
    }

    /* Incredibly hard to do in place merge in O(n) time and O(1) space
    void merge(T[] items, int start ,int mid, int end) {
        for(int i=mid-1,j=end; i >=start && j >= mid;) {

                if(items[i].compareTo(items[j]) >= 0) {
                    swap(items,i,j);
                    //i--;
                }else {
                    j--;
                }


                printItems(items);

        }

    } */

    //O(2n) space
    public void merge(T[] items, int start ,int mid, int end) {
        int i=start, j=mid, k=start;
        for(;i < mid && j <= end;) {
            if(items[i].compareTo(items[j]) < 0) {
                tempItems[k] = items[i];
                i++;
            }else {
                tempItems[k] = items[j];
                j++;

            }

            k++;
        }


        while(j<=end) {
            tempItems[k] =items[j];
            k++;
            j++;
        }

        while(i<mid) {
            tempItems[k] =items[i];
            k++;
            i++;
        }

        for(int t=start; t <=end; t++ ) {
            items[t] = (T)tempItems[t];
        }

    }

    public void swap(T[] items, int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;


    }

   public static <T extends Comparable> void printItems(T[] items) {
        for(T i : items) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeSort<Integer> sorter = new MergeSort<Integer>();
        Integer[] items = new Integer[] {-1, 6, 3, 4, 7, 4,15,2,9,12,13,0,1,5,8,10};
        //Integer[] items = new Integer[] {1,6,8,12,2,7,16,19};
        //sorter.merge(items,0,4,items.length-1);
        sorter.sort(items);



    }
}
