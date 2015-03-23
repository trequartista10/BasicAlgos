package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/12/14
 * Time: 10:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreeNode<T extends Comparable> implements Comparable<TreeNode<T>>{
    T val;
    TreeNode left; //For Binary Tree
    TreeNode right; //For Binary Tree

    TreeNode parent; //May not be required in most algorithms
    boolean isRed = false; //For Red Black type Trees
    public List<TreeNode<T>> children = new ArrayList<>(); //If it points to a collection of nodes like a heap or as a graph Node.

    TreeNode(T val) {
        this.val = val;
    }

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
            if(left == null)
                left = insertNode;
            else
                left.insertBSTTreeNode(insertNode);
        }else {
            if(right == null)
                right = insertNode;
            else
                right.insertBSTTreeNode(insertNode);
        }
    }

    boolean deleteBSTTreeNode(TreeNode<T> deleteNode) {
        if(this.compareTo(deleteNode) > 0) {
            if(left == null)
                return false; // No left Node

           if(left.compareTo(deleteNode) == 0) {
               TreeNode<T> rightNode = this.left.right;
               this.left = left.left;
               if(rightNode != null)
                this.left.insertBSTTreeNode(rightNode);

               return true;
           }else if(left.compareTo(deleteNode) > 0) {
               return this.left.deleteBSTTreeNode(deleteNode);

           } else
               return false; //No Match


        }else if(this.compareTo(deleteNode) < 0) {
            if(this.right == null)
                return  false;
            if(this.right.compareTo(deleteNode) == 0) {
                TreeNode<T> leftNode = this.right.left;
                this.right = this.right.right;
                if(leftNode != null)
                    this.right.insertBSTTreeNode(leftNode);

                return true;
            } else if(this.right.compareTo(deleteNode) < 0) {
                return this.right.deleteBSTTreeNode(deleteNode);

            }   else
                return false;//No match

        }else {
            //This is the root Node!
            // Do it outside since this is the root node
            return false;

        }

    }


    int[] getHeightAndDiameter() {
        int[] leftHeightAndDiameter ,rightHeightAndDiameter;
        if(val == null)
            return new int[] {0,0};

        if(left != null)
            leftHeightAndDiameter = left.getHeightAndDiameter();
        else
            leftHeightAndDiameter =  new int[]{0,0};

        if(right != null)
            rightHeightAndDiameter = right.getHeightAndDiameter();
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
        if(left == null || left.left == null)
            return;

        TreeNode<T> temp = this.left;
        this.left = this.left.left;
        temp.left = this.left.right;
        this.left.right = temp;
    }


    /**
     *
     *
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
        if(left == null || left.left == null)
            return;

        TreeNode<T> temp = this.left;
        this.left = this.left.left;
        temp.left = this.left.right;
        this.left.right = temp;
    }



    /** Revisit later
     *
     *        4
     *      /  \
     *     3    6
     *    / \  /  \
     *   1   2 5   9
     *            / \
     *           7   8
     *1. if you reach null, it means no match was found. SO just return
     * 2. If the left side matches then swim down left side.
     * 3 Else swim down right side.
     * 4. If a left or right match is found,
     *      a. If no childern for the match, then just point to null.
     *      b. If it has a match,
     *          (i) If its a left match, the left child is  m
     * @param node
     * @return
     */
    boolean deleteTreeNode(TreeNode node) {

        int side = this.compareTo(node);
        switch(side) {
            case 0:
                return true;

            case 1:
                if(this.left == null)
                    return true;

                if(left.compareTo(node) == 0) {
                    TreeNode temp = left.left;

                    if(this.left.right == null) {
                        this.left = temp;
                        return true;
                    }

                    this.left = left.right;
                    this.left.insertBSTTreeNode(temp);
                    return true;
                } else {
                    //Swim down left
                    this.left.deleteTreeNode(node);
                }



                break;
            case -1:
                //Insert a mirror of case 1 here
                return true;
            default:
                return false;

        }

        return  true;
    }

    /**
     *
     * @param node
     */
    void rb_insert(TreeNode<T> node) {
        if(this.compareTo(node) < 0) {
            if(this.left == null)
                this.left = node;
            else
                this.left.rb_insert(node);
        }else if(this.compareTo(node) > 0) {
            if(this.right == null)
                this.right = node;
            else
                this.right.rb_insert(node);
        }else {
            return;
        }


        //All rotation cases here
        if(this.compareTo(node) < 0) {
            if(isRed(left)) {
                if(isRed(left.left) && isRed(left.right)) {
                    left.left.isRed = false;
                    left.right.isRed = false;
                    return;
                }else {
                    //rotate
                }

            }else if(isRed(right)) {
                if(isRed(right.left) && isRed(right.right)) {
                    right.left.isRed = false;
                    right.right.isRed = false;
                }else {
                    //Rotate
                }
            }else {
                //Npthing to fix
            }
        }else {
            //No red red violation
        }



    }


    boolean isRed(TreeNode node) {
        if(node != null && node.isRed )
            return true;

        return false;
    }


    @Override
    public int compareTo(TreeNode<T> o) {
        return this.val.compareTo(o.val);
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(50);
        root.insertBSTTreeNode(new TreeNode<Integer>(20));
        root.insertBSTTreeNode(new TreeNode<Integer>(100));
        root.insertBSTTreeNode(new TreeNode<Integer>(10));
        root.insertBSTTreeNode(new TreeNode<Integer>(90));
        root.insertBSTTreeNode(new TreeNode<Integer>(66));
        root.insertBSTTreeNode(new TreeNode<Integer>(72));
        root.insertBSTTreeNode(new TreeNode<Integer>(24));
        root.insertBSTTreeNode(new TreeNode<Integer>(29));
        root.insertBSTTreeNode(new TreeNode<Integer>(36));
        root.insertBSTTreeNode(new TreeNode<Integer>(59));
        root.insertBSTTreeNode(new TreeNode<Integer>(95));
        root.insertBSTTreeNode(new TreeNode<Integer>(105));

        root.insertBSTTreeNode(new TreeNode<Integer>(23));
        root.insertBSTTreeNode(new TreeNode<Integer>(25));

        root.insertBSTTreeNode(new TreeNode<Integer>(8));
        root.insertBSTTreeNode(new TreeNode<Integer>(11));
        root.insertBSTTreeNode(new TreeNode<Integer>(15));
        root.insertBSTTreeNode(new TreeNode<Integer>(9));

        TreePrinter printer = new TreePrinter();
        printer.printNode(root);
        //Deletions
        //ToDO Handle deleting a root node here

        System.out.println();
        System.out.println(root.deleteBSTTreeNode(new TreeNode<Integer>(100)));
        printer.printNode(root);
    }
}


