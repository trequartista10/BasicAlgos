package trees;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/12/14
 * Time: 10:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>>{
    T val;
    TreeNode leftChild; //For Binary Tree
    TreeNode rightChild; //For Binary Tree

    TreeNode parent; //May not be required in most algorithms
    boolean isRed = false; //For Red Black type Trees
    Collection<TreeNode> children; //If it points to a collection of nodes like a heap or as a graph Node.

    /**
     * Insersts a tree node into a tree based on
     * @param insertNode
     */
    void insertBSTTreeNode(TreeNode<T> insertNode) {
        //Less than specified object ? Swim down left
        int dir = compareTo(insertNode);
        if(dir == 0)
            return; // No dupes allowed

        if(dir > 0) {
            if(leftChild == null)
                leftChild = insertNode;
            else
                leftChild.insertBSTTreeNode(insertNode);
        }else {
            if(rightChild == null)
                rightChild = insertNode;
            else
                rightChild.insertBSTTreeNode(insertNode);
        }
    }


    int[] getHeightAndDiameter() {
        int[] leftHeightAndDiameter ,rightHeightAndDiameter;
        if(val == null)
            return new int[] {0,0};

        if(leftChild != null)
            leftHeightAndDiameter = leftChild.getHeightAndDiameter();
        else
            leftHeightAndDiameter =  new int[]{0,0};

        if(rightChild != null)
            rightHeightAndDiameter = rightChild.getHeightAndDiameter();
        else
            rightHeightAndDiameter = new int[]{0,0};

        int[] currentHeightAndDiameter  = new int[]{0,0};

        //Tree height is max of (lHeight and rHeight) + 1
        if(leftHeightAndDiameter[0] >=rightHeightAndDiameter[0]) {
            currentHeightAndDiameter[0] = leftHeightAndDiameter[0] + 1;
        } else
            currentHeightAndDiameter[0] = rightHeightAndDiameter[0] + 1;

        //Max of (leftDiameter, rightDiameter, currentDiameter) where currentDiameter is lDia + rDia + 1
        currentHeightAndDiameter[1] = Math.max( Math.max(leftHeightAndDiameter[1],rightHeightAndDiameter[1]),
                leftHeightAndDiameter[1] + rightHeightAndDiameter[1] + 1);

        return currentHeightAndDiameter;
    }


    /**
     * Sample case     ==>
     *        A                     A           A is current Node
     *       /                     /
     *      B                     C            B is left Child
     *     / \                   / \            C is left left Child
     *    C   G                 D   B
     *   / \                       / \
     *  D   F                     F   G
     *
     */
    void rightRotate() {
        if(leftChild == null || leftChild.leftChild == null)
            return;

        TreeNode<T> temp = this.leftChild;
        this.leftChild = this.leftChild.leftChild;
        temp.leftChild = this.leftChild.rightChild;
        this.leftChild.rightChild = temp;
    }


    /**
     * Sample case        ==>
     *        A                     A           A is current Node
     *       /                     /
     *      B                     G            B is left Child
     *     / \                   / \            C is left left Child
     *    C   G                 B   F
     *       / \               / \
     *      D   F             C   D
     *
     */
    void leftRotate() {
        if(leftChild == null || leftChild.leftChild == null)
            return;

        TreeNode<T> temp = this.leftChild;
        this.leftChild = this.leftChild.leftChild;
        temp.leftChild = this.leftChild.rightChild;
        this.leftChild.rightChild = temp;
    }


    @Override
    public int compareTo(TreeNode<T> o) {
        return this.val.compareTo(o.val);
    }
}
