package heap;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/13/14
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArrToMaxHeap {

    ArrayList<Integer> heap = new ArrayList<Integer>();
    /**
     * https://www.youtube.com/watch?v=YJa3GpNUrNs
     * Rules for building a heap from a array
     * Everything after a(n/2) are leaves. i.e a(n/2 +1) , a(n/2 +2) .... a(n)
     * For any node i,
     *  left(i) = 2i
     *  Right(i) =2i + 1
     *  parent(i) = |i/2|
     *
     * @param a
     */
    void heapify(Integer[] a) {
        int[] heap = new int[a.length];
        for(int i=(a.length - 1)/2; i >= 0; i--) {
            maxHeapify(a,i);
        }

    }

    /**
     * Check each parent and its children from bottom up and make the the largest of the three as the parent by swapping.
     * If the swap results in breaks , go down the tree to make more swaps and then move up the tree till root.
     *
     * Running time of the entire algo is O(nlogn). Each swap may involve logn more swaps in worst case.
     * Tighter bound would based on swap of node at height h.O(2n/3).
     * http://stackoverflow.com/questions/9099110/worst-case-in-max-heapify-how-do-you-get-2n-3
     * @param a
     * @param i
     */
    void maxHeapify(Integer[] a, int i) {

        if(a[i] == null)
            return;
        int k = 2*i + 1;
        int l = 2*i + 2;
        if(2*i >= a.length)
            return;
        Integer max = a[i];
        int loc = i;

        if(k < a.length && max < a[k]) {
            loc = k;
        }

        if(l < a.length && max < a[l] && a[l] > a[k]) {
            loc =l;
        }

        if(loc != i) {
            max = a[loc];
            a[loc] = a[i];
            a[i] = max;
            maxHeapify(a,loc);
        }

    }

    /***
     * Deleting and inserting is O(n) for heaps
     * 1
     * @param element
     * @return
     */
    void deleteElement(int element) {

    }

    Integer getMaxElement() {

        Integer max = this.heap.get(0);
        Integer last = this.heap.get(this.heap.get(this.heap.size() -1));
        //this.heap.remove(this.heap.size() -1);
        int i = 0;
        int k = -1;

        this.heap.set(0,last);
        this.heap.remove(this.heap.size() -1);
        do {
            k = swim(i);
            printArrayList(heap);
        }while( i != k && k != -1 );


        return  max;
    }

    void addElement(int element) {
        this.heap.add(element);
        int root = this.heap.size();
        int t = root;
        do {

            root = (root/2);
            t = swim(root);
            printArrayList(heap);
        }while(t != root && root != 0 && root >=3);

        if( t < 3) {
            if(t == 1)
                if(heap.get(1) > heap.get(0))
                    swap(1,0);
                else  {
                    int max = heap.get(0);
                }
        }

    }



    int swim(int i) {
        if(i > heap.size())
            return -1;
        int k = (2*i + 1);
        int l = (2*i + 2);
        if(heap.size() <= k ) {
            return i;
        }

        if(heap.size() <= l ) {
            if(heap.get(i) >= heap.get(k))
                return  i;
            else {
                swap(i,k);
                return k;
            }
        }

        if( heap.get(i) > heap.get(k) && heap.get(i) > heap.get(l)) {
            return i;
        }else if( heap.get(i) < heap.get(2*i) && heap.get(i) > heap.get(l)) {
            swap(k , i);
            return k;
        } else if( heap.get(i) < heap.get(2*(i+1)) && heap.get(i) > heap.get(k)) {
            swap(l, i);
            return l;
        }else {
            return i;
        }

    }




    <T> void printArrayList(ArrayList<T> values) {
        for(T value : values) {
            System.out.print(" " + value);
        }
        System.out.println();
    }

    void swap(int i, int j) {
        int temp = this.heap.get(i);
        this.heap.set(i,this.heap.get(j));
        this.heap.set(j, temp);
    }

    /**
     * A heap implemenation using an arraylist instead of array. Use sink and swim methods
     * @param args
     */
    public static void main(String[] args) {
        ArrToMaxHeap heap = new ArrToMaxHeap();
        heap.addElement(1);
        heap.addElement(2);
        heap.addElement(3);
        heap.addElement(4);
        heap.addElement(5);
        heap.addElement(6);

    }
    /*
    public static void main(String[] args) {
        //Integer[] a = new Integer[] {5,3,17,10,84,19,6,22,9};
        Integer[] a = new Integer[]{ 1, 2, 3, 4, 7, 8, 9, 10, 14, 16 };
        ArrToMaxHeap maxHeap = new ArrToMaxHeap();
        maxHeap.heapify(a);
        for(int i=0; i < a.length; i++)
            System.out.println(a[i]);
    } */
}
