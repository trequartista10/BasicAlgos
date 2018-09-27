package LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/27/14
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReverseLinkedList<T> {

    public static class LinkedListNode<T> {
        public final T val;
        public LinkedListNode<T> next;
        public LinkedListNode<T> prev;

        LinkedListNode(T val) {
            this.val = val;
        }
    }


     LinkedListNode<T> head;
     LinkedListNode<T> tail;


    /**
     * Consider the case where the first three nodes are temp1, temp2 and temp3
     * temp1->temp2->temp3
     *
     * @param head
     */
     public LinkedListNode<T> reverseList(LinkedListNode<T> head ) {
         LinkedListNode temp = null;
         LinkedListNode a = head;
         LinkedListNode b = null;

         while(a != null) {
             b = a.next;
             a.next = temp;
             temp = a;
             a = b;
             //System.out.println(a.val);
         }
         head = temp;
         return head;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
       LinkedListNode<Integer> a = new LinkedListNode<Integer>(1);
       
        LinkedListNode<Integer> b = new LinkedListNode<Integer>(2);
        LinkedListNode<Integer> c = new LinkedListNode<Integer>(3);
        LinkedListNode<Integer> d = new LinkedListNode<Integer>(4);
        LinkedListNode<Integer> e = new LinkedListNode<Integer>(5);
        LinkedListNode<Integer> f = new LinkedListNode<Integer>(6);
        LinkedListNode<Integer> g = new LinkedListNode<Integer>(7);
        LinkedListNode<Integer> h = new LinkedListNode<Integer>(8);
        LinkedListNode<Integer> i = new LinkedListNode<Integer>(9);
        LinkedListNode<Integer> head = a;
        a.next = b;
        b.next =c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;
        h.next = i;
        i.next = null;

        ReverseLinkedList<Integer> rev = new ReverseLinkedList<Integer>();
        rev.traverseLinkedList(head);
        rev.traverseLinkedList(rev.reverseList(head));
    }


    void traverseLinkedList(LinkedListNode head) {
        System.out.println("\nHEAD: " +head.val + "\n");
        while(head != null) {
            System.out.print(head.val + "\t");
            head = head.next;
        }



    }
}
