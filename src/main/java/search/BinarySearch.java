package search;

import sort.MergeSort;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 3/23/14
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinarySearch<T extends Comparable> {


    void findNextSmallestItem(T item, T[] items) {

    }


    /**
     * Complexit log n
     * @param item
     * @param items
     */
    boolean findItem(T item, T[] items) {
        MergeSort sorter = new MergeSort<T>();
        sorter.sort(items);
        int start =0;
        int end = items.length -1;
        int loc = findItem(item,items,start,end);
        System.out.println(loc);
        if(items[loc].compareTo(item) == 0)
            return true;
        else
            return false;
    }

    int findItem(T item, T[] items, int start, int end) {
        int mid = Math.round((start + end) /2);  //Watch the damn paranthesis
        //System.out.println(start+ "\t" + mid +"\t"+ end);
        int comp = item.compareTo(items[mid]);

        if(end == start && comp !=0)
            return mid;

        if(comp == 0 )
            return mid;
        else if(comp < 0)
            return findItem(item,items,start,mid -1);
        else {
            System.out.println(start+ "\t" + mid +"\t"+ end);
            return findItem(item,items,mid,end);
        }

    }

    void findNextLargestItem(T item, T[] items) {
        MergeSort sorter = new MergeSort<T>();
        sorter.sort(items);

        int start =0;
        int end = items.length -1;
        int loc = findItem(item,items,start,end);


    }

    public static void main(String[] args) {
        Integer[] items = new Integer[] {1,6,8,12,2,7,16,19};
        BinarySearch bs = new BinarySearch();
        bs.findItem(120,items);
        //MergeSort.printItems(items);

    }
}
