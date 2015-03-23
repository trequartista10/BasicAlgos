package trees;

/**
 * Created by kuldeep on 3/4/15.
 */
public class SortedArrayToBST {

    static TreeNode<Integer> getBalancedBST(int[] numbers, int start, int end) {
        if(start > end || start < 0 || end > numbers.length)
            return null;
            
        int mid = (start + end)/2;
        TreeNode<Integer> node = new TreeNode<>(numbers[mid]);
        
        node.left = getBalancedBST(numbers, start, mid -1);
        node.right = getBalancedBST(numbers, mid +1, end);
        return node;
    }
    
    public static void main(String[] args) {
        /*
        int[] numbers = new int[args.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(args[i]);            
        } */
        int[] numbers = {0,1,2,3,4,5,6,7,8,9};
        TreeNode<Integer> node = getBalancedBST(numbers, 0, numbers.length-1);
        System.out.println(node);
        TreePrinter.printNode(node);
                     
    }
}
