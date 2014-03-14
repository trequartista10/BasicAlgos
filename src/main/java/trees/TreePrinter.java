package trees;

/**
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/13/14
 * Time: 11:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreePrinter {

    public static <T extends Comparable<T>> void printTree(TreeNode<T> tree) {
        int height = tree.getHeightAndDiameter()[0];
        int totalPossibleNodes = (int)(Math.pow(2,height)) - 1;
        //For height h there are 2^N nodes    there are n/(2^(h + 1) nodes
        int noOfNodes = totalPossibleNodes / (int)Math.pow(2,height + 1) ;
    }

    public static void main(String[] args) {
        System.out.println("Prints a Tree top down");
    }

}
