package codility;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/20/14
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindingLoopsLinkedList {

    public static class Node<T> {
        public Node next;
        public T val;

        Node(T val) {
            this.val = val;
        }

        public void addNext(Node<T> t) {
            this.next = t;
        }

    }

    public static void main(String[] args) {

        Node<Integer> node1 = new Node<Integer>(1);
        Node<Integer> head = node1;
        Node<Integer> node2 = new Node<Integer>(2);
        node1.next = node2;
        Node<Integer> node3 = new Node<Integer>(3);
        node2.next = node3;
        Node<Integer> node4 = new Node<Integer>(4);
        node3.next = node4;
        Node<Integer> node5 = new Node<Integer>(5);
        node4.next = node5;
        Node<Integer> node6 = new Node<Integer>(6);
        node5.next = node6;
        Node<Integer> node7 = new Node<Integer>(7);
        node6.next = node7;
        Node<Integer> node8 = new Node<Integer>(8);
        node7.next = node8;

        //Loop
        node8.next = node2;

        Node ptr1 = head;
        Node ptr2 = head.next;
        boolean hasloops = false;
        while(ptr1.next != null && ptr2.next!= null && ptr2.next.next != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next.next;
            if(ptr1.equals(ptr2)) {
                hasloops = true;
                System.out.println("Found loop at " + ptr1.val);
                break;
            }
        }

        if(!hasloops)
            return;
        //Find length of the loop
        int ctr =0;
        while (!ptr1.next.equals(ptr2)) {
            ptr1 = ptr1.next;
            ctr++;
        }

        System.out.println("Loop Length is " + ctr);
        ptr1 = head;
        ptr2 = head;
        for(int i=0; i < ctr ; i++) {
            ptr2 = ptr2.next;
        }

        while(!ptr2.next.equals(ptr1)) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        System.out.println("Loop Node is " + ptr1.val);


    }
}
