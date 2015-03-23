package trees;

/**
 * Created by kuldeep on 3/15/15.
 */
public class SubTreeMatcher {
    TreeNode<Integer> root;



    void add(Integer val) {
        if(root == null) root = new TreeNode(val);
        else
            add(root, val);
    }
    

    void add(TreeNode current, int val) {
        if(current.val.compareTo(val) == 0)
            return ;
        if(current.val.compareTo(val) > 0) {
            if(current.left == null)
                current.left = new TreeNode(val);
            else
                add(current.left, val);
        } else {
            if(current.right == null)
                current.right = new TreeNode(val);
            else
                add(current.right, val);
        }
    }


    boolean match(TreeNode subTree) {
        return 	match(subTree, root);
    }

    boolean match(TreeNode subTree, TreeNode current) {
        if(current == null || subTree == null) return false;
        if(current.val == subTree.val) {
            return checkNodes(subTree, current);
        }else {
            return match(subTree, current.left)  || match(subTree, current.right);
        }
    }

    boolean checkNodes(TreeNode subTree, TreeNode  current) {
        if(subTree == null) return true; //Matched till leaves
        if(current == null) return false;

        if(subTree.val == current.val) {
            return checkNodes(subTree.left, current.left) && checkNodes(subTree.right, current.right);
        } else
            return false;
    }



    public static void main(String[] args) {
        TreePrinter printer = new TreePrinter();
        SubTreeMatcher treeMatcher = new SubTreeMatcher();
        treeMatcher.add(7);
        treeMatcher.add(2);
        treeMatcher.add(1);
        treeMatcher.add(3);
        treeMatcher.add(9);
        treeMatcher.add(11);
        treeMatcher.add(8);

        SubTreeMatcher subTree = new SubTreeMatcher();
        subTree.add(7);
        subTree.add(2);
        subTree.add(1);
        subTree.add(3);


        
        
        printer.printNode(treeMatcher.root);
        printer.printNode(subTree.root);
        
        System.out.println(treeMatcher.match(subTree.root));
        System.out.println(subTree.match(treeMatcher.root));

    }
}
