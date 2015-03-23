package trees;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/27/14
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BSTChcker {
    TreeNode<Integer> root = null;

    <T extends Comparable> boolean checkBST(TreeNode<T>  root, T upper, T lower ) {
        if(root == null)
            return true; //If the root node is null, check somewhere outside this piece of code.

        if(root.val.compareTo(upper) < 0 && root.val.compareTo(lower) > 0)
            return checkBST(root.left, root, lower) && checkBST(root.right, lower, root);
        else
            return false;
    }

    public static void main(String[] args) {

    }


}
