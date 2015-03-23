package stack;

/**
 * Created by kuldeep on 9/7/14.
 */

public class SimpleStack{

    private final Object[] entries;
    private final int overflowSize;
    private int size = 0;

    public SimpleStack(int overflowSize) {
        entries = new Object[overflowSize];
        this.overflowSize = overflowSize;
    }

    public void push(Object entry) {
        if(size == overflowSize)
            throw new StackOverflowError();

        entries[size] = entry;
        size++;
    }

    public Object pop() {
        if(size == 0)
            throw new Error("Stack under flow");
        size--;
        Object entry = entries[size];
        entries[size] = null;
        return entry;
    }

    public int getSize() {
        return size;
    }

    public boolean isOnStack(Object element) {
        for(int i=0; i < size; i++)
            if(entries[i].equals(element))
                return true;

        return false;
    }

    public static void main(String[] args) {


    }

}
