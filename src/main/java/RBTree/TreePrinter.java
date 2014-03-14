package RBTree;

import RBTree.RBTree.RBTreeNode;
/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 10/21/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */


public class TreePrinter {
   final RBTreeNode root;

   TreePrinter(RBTreeNode root) {
       this.root = root;
    }

    void print(){

    }

    private void printChildren(RBTreeNode node) {
        int h = getMaxDepth(node);
        int noOfLeaves = (2^(h-1));

        int rooLoc =  ((noOfLeaves * 3)/2) + 1;

        String[] lines = new String[h];

    }


    void printChildren(RBTreeNode node,int idx, int start, int end )  {

    }


    static int getMaxDepth(RBTreeNode node) {
          if(node == null)
              return 0;

          int t1 = getMaxDepth(node.children[0]);
          int t2 = getMaxDepth(node.children[1]);

          return t2 > t1 ? t2 + 1 : t1 + 1;

    }
}
