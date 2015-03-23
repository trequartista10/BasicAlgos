package trees;

/**
 * Created by kuldeep on 3/19/15.
 */
public class IntervalSearchTree {

    TreeNode<Interval> root;

    public static class Interval implements Comparable {
        int lo;
        int hi;
        int max;

        Interval(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            max = this.hi;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(lo,((Interval)o).lo);
        }
        
        @Override
        public String toString() {
            return "["+lo+","+max+","+hi+"]";
        }
    }

    public static class Node extends TreeNode<Interval>{
        int h =1;

        Node(Interval interval) {
            super(interval);
            this.val = interval;
        }
    }


    void add(int lo, int hi) {
        if(root == null) {
            root = new Node(new Interval(lo, hi));
            return;
        }

        //int max = add(root,lo, hi) ;
        //root.interval.max = max;
        root = add(root, lo, hi);
    }

    TreeNode<Interval> add(TreeNode<Interval> node, int lo, int hi) {
        if (lo <= node.val.lo) {
            if (node.left == null)
                node.left = new Node(new Interval(lo, hi));
            else
                node.left = add(node.left, lo, hi);

        } else {
            if (node.right == null)
                node.right = new Node(new Interval(lo, hi));
            else
                node.right = add(node.right, lo, hi);

        }


        updateHeight((Node)node);
        int hdiff = heightDiff((Node)node);
        if(hdiff <= -2) { //right is greater than left by 2
            if(node.right.right != null) {
                node = leftLeftRotation(node);
            } else if(node.right.left != null) {
                node = leftRightRotation(node);                
            } else {
                System.out.print("Something is wrong!!!!");
            }

        }else if(hdiff >= 2) { //left is greater than right by 2
            //Right rotate or zig zag pattern   
            if(node.left.left != null ) {
                node = rightRightRotation(node);
            } else if(node.left.right != null) {
                node = rightLeftRotation(node);
            } else {
                System.out.print("Something is wrong!!!!");
            }
        }

        updateMaxAndHeight(node);
        return node;
    }

    TreeNode<Interval> leftLeftRotation(TreeNode<Interval> node) {

        TreeNode<Interval> r = node.right;
        TreeNode<Interval> rr = node.right.right;
        TreeNode<Interval> temp = r.left;

        r.left = node;

        node.right = temp;

        updateMaxAndHeight(temp);
        updateMaxAndHeight(rr);
        updateMaxAndHeight(node);
        updateMaxAndHeight(r);
        return r;
    }

    TreeNode<Interval> rightRightRotation(TreeNode<Interval> node) {

        TreeNode<Interval> l = node.left;
        TreeNode<Interval> ll = node.left.left;
        TreeNode<Interval> temp = l.right;

        l.right = node;
        node.left = temp;

        updateMaxAndHeight(temp);
        updateMaxAndHeight(ll);
        updateMaxAndHeight(node);
        updateMaxAndHeight(l);
        return l;
    }

    TreeNode<Interval> leftRightRotation(TreeNode<Interval> node) {
        TreeNode<Interval> r = node.right;
        TreeNode<Interval> rl = node.right.left;

        TreeNode<Interval> tempL = rl.left;
        TreeNode<Interval> tempR = rl.right;

        rl.right = r;
        rl.left = node;
        r.left = tempR;
        node.right = tempL;


        updateMaxAndHeight(tempL);
        updateMaxAndHeight(tempR);
        updateMaxAndHeight(r);
        updateMaxAndHeight(rl);
        return rl;
    }


    TreeNode<Interval> rightLeftRotation(TreeNode<Interval> node) {
        TreeNode<Interval> l = node.left;
        TreeNode<Interval> lr = node.left.right;

        TreeNode<Interval> tempL = l.left;
        TreeNode<Interval> tempR = l.right;
        
        lr.left = l;
        lr.right = node;
        node.left = tempR;
        l.right = tempL;

        updateMaxAndHeight(tempL);
        updateMaxAndHeight(tempR);
        updateMaxAndHeight(l);
        updateMaxAndHeight(lr);      
        return lr;
    }



    void updateMaxAndHeight(TreeNode<Interval> node) {
        if(node == null)
            return;

        node.val.max = node.val.hi;

        updateHeight((Node)node);
        updateMax((Node)node);
    }

    void updateHeight(Node node) {
        int lh = node.left == null ? 0 : ((Node)(node.left)).h;
        int rh = node.right == null ? 0 : ((Node)(node.right)).h;
        node.h =(lh > rh ? lh : rh) + 1;
    }

    int heightDiff(Node node) {
        int lh = node.left == null ? 0 : ((Node)(node.left)).h;
        int rh = node.right == null ? 0 : ((Node)(node.right)).h;

        return lh - rh;
    }

    void updateMax(Node node) {
        int max = node.val.hi;
        max = node.left == null ? max : Math.max(((Node)(node.left)).val.hi,max);
        max = node.right == null ? max : Math.max(((Node)(node.right)).val.hi,max);

        node.val.max = max;
    }

    Node intsects(int lo, int hi) {
        
        if(root == null)        
            return null;

        Interval interval = new Interval(lo,hi);
        System.out.println("Matching: "+ interval);
        return intersects((Node)root, interval, Integer.MAX_VALUE);
    }

    Node intersects(Node node, Interval interval, int maxSoFar) {
        if(node == null)
            return null;
        Node returnVal = null;
        Node returnVal2;
        System.out.println("Entering " + node.val);
        int l1 = (getL1Norm(node.val, interval));
        if(interval.lo >= node.val.lo && interval.hi <= node.val.hi && maxSoFar >= l1) {
            System.out.println("Matched "+ node.val);
            returnVal = node;
            maxSoFar = l1;
        }
        
        if(interval.lo <= node.val.lo || (node.left != null && interval.lo <= ((Node)(node.left)).val.max)) {
            returnVal2 = intersects((Node) node.left, interval, maxSoFar);
        } else {            
            if(node.right == null) {
                return returnVal;
            }
            
            returnVal2 = intersects((Node) node.right, interval, maxSoFar);
        }
        
        if(returnVal2 != null)
            returnVal = returnVal2;
        
        return returnVal;
    }
    
    
    int getL1Norm(Interval outer, Interval inner) {
        return (inner.lo - outer.lo + outer.hi - inner.hi);                
    }
    
    
    

    public static void main(String[] args) {
        IntervalSearchTree ist = new IntervalSearchTree() ;
        TreePrinter tp = new TreePrinter();
        ist.add(4,5);
        //tp.printNode(ist.root);
        ist.add(10,13);
        //tp.printNode(ist.root);
        ist.add(19,20);
        //tp.printNode(ist.root);
        ist.add(1,3);
        //tp.printNode(ist.root);
        ist.add(25,90);
        //tp.printNode(ist.root);
        ist.add(7,15);
       // tp.printNode(ist.root);
       // ist.add(19,21);
        
        
        
        //tp.printNode(ist.root);
        ist.add(27,40);
        //tp.printNode(ist.root);
        ist.add(31,35);
        ist.add(4,7);
        
        ist.add(40,60);
        tp.printNode(ist.root);
        //tp.printNode(ist.root);

        System.out.println(ist.intsects(50,52).val);
        System.out.println(ist.intsects(19,20).val);
        System.out.println(ist.intsects(1,2).val);
        System.out.println(ist.intsects(4,6).val);
        System.out.println(ist.intsects(4,5).val);
        System.out.println(ist.intsects(5,6).val);

        
    }


}
