package heap;

import java.util.Comparator;

/**
 * Generic implementation of priority queue based on heap with add and pop
 * Reverse is min heap
 * 	Average	Worst case
 Space	O(n)	O(n)
 Search	O(n)	O(n)
 Insert	O(1)	O(log n)
 Delete	O(log n)	O(log n)
 * Created by  on 9/7/14.
 */
public class BinaryHeap<T extends Comparable> {
    private final Comparable[] pq;
    private int size = 0;

    private final Comparator<Comparable> comparator;

    private final static Comparator<Comparable> comparatorMax = new Comparator<Comparable>() {
        @Override
        public int compare(Comparable o1, Comparable o2) {
            return o1.compareTo(o2);
        }
    };


    public BinaryHeap(int size) {
        this(size,false);
    }

    public BinaryHeap(int size, boolean reverse) {
        pq = new Comparable[size];

        if(reverse)
            comparator = comparatorMax.reversed();
        else
            comparator = comparatorMax;
    }


    public void add(T entry) throws Exception {
        if(size == pq.length)
            throw new Exception("Heap Overflow Exception: "+ entry);
        pq[size] = entry;
        size++;
        maxHeapify(pq, getNewPos(size - 1), true);
        print();
    }

    public Comparable pop() throws Exception {
        if(size == 0)
            return null;
        size--;
        swap(pq,0,size);
        Comparable entry = pq[size];
        pq[size] = null;
        maxHeapify(pq, 0, false);
        System.out.println("Popped: "+ entry);
        print();
        return entry;
    }

    public Comparable find(T entry) {
        for(int i=0; i < size; i++)
            if(comparator.compare(entry,pq[i]) == 0)
                return entry;

        return null;
    }

    private void maxHeapify(Comparable[] entries, int pos, boolean swimUp) throws Exception {
        int leftPos = 2 * pos + 1;
        int rightPos = 2 * pos + 2;

        Comparable parent = entries[pos];
        Comparable left = null;
        if(leftPos < size)
            left = entries[leftPos];

        Comparable right = null;

        if(rightPos < size)
            right = entries[rightPos];

        if(left == null && right == null)
            return; //For swim Down case

        if (parent == null)
            throw new Exception("Entry not found Exception: " + pos);

        //Find the largest of left and right for swaps
        int largest = pos;
        if (left != null && comparator.compare(parent,left) < 0) {
            largest = leftPos;
        }

        if (right != null && comparator.compare(parent,right) < 0) {
            if(largest == pos)
                largest = rightPos;
            else if(comparator.compare(right,entries[largest]) > 0) {
                largest = rightPos;
            }

        }

        if (largest != pos) {
            swap(entries, largest, pos);
            if (swimUp && pos == 0)
                return;

            maxHeapify(entries, swimUp ? getNewPos(pos) : largest, swimUp);
        }
    }


    private void swap(Comparable[] entries, int pos1, int pos2) {
        Comparable temp = entries[pos2];
        entries[pos2] = entries[pos1];
        entries[pos1] = temp;
    }

    private int getNewPos(int pos) {
        int newPos = pos / 2;//Takes the floor automatically because of int
        if (pos > 0 && pos % 2 == 0)
            newPos = (pos / 2) - 1;

        return newPos;
    }

    public void print() {
        System.out.print("[");
        int i=0;
        for(;i < size-1; i++)
            System.out.print(pq[i] + ",");

        System.out.print(pq[i]+"]");
        System.out.println();
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> pq = new BinaryHeap<Integer>(100);
        try {
            pq.add(5);

            pq.add(3);

            pq.add(9);

            pq.add(16);
            pq.add(2);
            pq.add(3);
            pq.add(19);
            pq.add(500);
            pq.add(90);
            pq.add(1);
            pq.add(91);
            pq.add(600);
            pq.pop();
            pq.pop();
            pq.pop();
            pq.pop();
            pq.pop();
            pq.pop();
            pq.pop();
            pq.pop();
            pq.pop();
            pq.pop();
            pq.pop();
            pq.pop();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}