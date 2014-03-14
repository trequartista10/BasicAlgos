package RBTree;

/**
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 10/5/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 *
 *
 *
 *     	These are binary trees with the following properties.

 1. Every node has a value.
 2. The value of any node is greater than the value of its left child and less than the value of its right child.
 3. Every node is colored either red or black.
 4. Every red node that is not a leaf has only black children.
 5. Every path from the root to a leaf contains the same number of black nodes.
 6. The root node is black.
 */
public class RBTree {


    RBTreeNode root = null;

    public static class RBTreeNode {
        boolean isRed = true; //Indicate if its a red node or a black node
        // Its better to have new node as red because it may(may violate red-red violatioin) not violate anything.
        // Black nodes will always violate height so will have to reblanace everytie
        RBTreeNode[] children = new RBTreeNode[2]; //Index 0 is left and Index 1 is right
        //int depth = -1;
        int val;

        public RBTreeNode(int val) {
            this.val = val;
        }

        public int getDirection(RBTreeNode other) {
            return (other.val < this.val) ? 0 : 1;
        }

        public void update(RBTreeNode node) {
            this.isRed = node.isRed;
            this.val = node.val;
            this.children = node.children;
        }

    }


    boolean isRedNode(RBTreeNode node) {
        return node != null && node.isRed;
    }

    boolean setRoot(RBTreeNode node) {
        if(root == null) {
            node.isRed = false;
            this.root = node;
            return true;
        }

        return false;
    }

    /**
     * Bottom up insertion: Recursive ur way up!!
     *
     * 1. Insert the node when the leaf is null
     * 2. Check if any of the children is red if curr is red.
     * NOTE: BOTH CHILDRED OF A RED PARENT WILL NEVER BE RED! This would already have a RED Violation!
     * 3. If Both Children are red and curr is red, flip colors... ie make the curr black and keep children red
    * 4.
     *
      * @param curr
     * @param node
     * @return
     */
    boolean insertNode(RBTreeNode curr, RBTreeNode node) {
       int dir1 = curr.getDirection(node);
       int dir2 = dir1 ==0 ? 1 : 0;

        if(curr.children[dir1] == null) {
            curr.children[dir1] = node;
            return true;
        }

        if(isRedNode(curr))  {
            //Case 1: Case where we can flip colors and black height remains unchanged
            if(isRedNode(curr.children[dir1]) ) {
                if(isRedNode(curr.children[dir2])) {
                curr.children[dir1].isRed = true;
                curr.children[dir2].isRed = true;
                curr.isRed = false;
                return true;
            } else if(isRedNode(curr.children[dir1]) && isRedNode(curr.children[dir1].children[dir1])) { //Case 2 and 3 //single rotation
                   //RBTreeNode temp = singleRotate(curr,dir1,dir1); //Java no pointers :( So need to copy the returned val
                    //curr.update(temp);
                   curr.update(singleRotate(curr,dir1,dir1));
            } else if(isRedNode(curr.children[dir1]) && isRedNode(curr.children[dir1].children[dir2])) { //Case 2 and 3 // double rotation
                   //doubleRotate

            }
        }  else {
            //do nothing as black height is unchanged and red red violation
        }
        }//not sure about braces

        return insertNode(curr.children[dir1],node);
    }


    public RBTreeNode singleRotate(RBTreeNode node, int dir1,int dir2) {
        RBTreeNode temp = node.children[dir1];

        temp.children[dir2] = node;
        node.children[dir1] = node.children[dir1].children[dir2];

        //update colors to maintain black height
        temp.isRed = false;
        //temp.children[dir1].isRed = true; //It should already be red!
        temp.children[dir2].isRed = true;

        return temp;

    }

    //Return the height of the tree
    int RBTreeTest(RBTreeNode testRoot) {
         if (testRoot == null) {
             return 1; //Leaf nodes
         }

        RBTreeNode lNode = testRoot.children[0];
        RBTreeNode rNode = testRoot.children[1];



        //Check that Red Nodes do not have red children
        if(isRedNode(testRoot)) {
            if(isRedNode(rNode) || isRedNode(lNode))  {
                System.out.println("Red-Red violation- Left: "+ isRedNode(lNode) +" Right: "+isRedNode(rNode));
                return 0;
            }



        }

        int lHeight = RBTreeTest(lNode);
        int rHeight = RBTreeTest(rNode);

        //Check for  Binary Tree. Should be done after the recursive call to handle the null case.
        if(lNode.val > rNode.val) {
            System.out.println("Binary tree violation Left: "+ lNode.val + " Right: "+ rNode.val);
            return 0;
        }

        if(lHeight !=0 && rHeight != 0 && lHeight != rHeight) {
            System.out.println("Height violation- left Height: "+rHeight+ " right Height: "+lHeight);
            return 0;
        }


        //Return current height incuding the current node.
        if (lHeight != 0 && rHeight != 0)
            return isRedNode(testRoot) ? lHeight : lHeight + 1;
        else
           return 0;

    }







    public static void main(String[] args) {

    }
}

