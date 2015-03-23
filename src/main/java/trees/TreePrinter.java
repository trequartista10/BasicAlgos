package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: treq
 * Date: 3/13/14
 * Time: 11:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreePrinter {
   /*
    public static <T extends Comparable<T>> void printTree(TreeTreeTreeNodT> tree) {
        int height = tree.getHeightAndDiameter()[0];
        int totalPossibleNodes = (int)(Math.pow(2,height)) - 1;
        //For height h there are 2^N nodes    there are n/(2^(h + 1) nodes
        int noOfNodes = totalPossibleNodes / (int)Math.pow(2,height + 1) ;
    }*/

    public static <T extends  Comparable> void printNode(TreeNode<T> root) {
        int maxLevel = TreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends  Comparable> void printNodeInternal(List<TreeNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || TreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level+3;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 5;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 2;

        TreePrinter.printWhitespaces(firstSpaces);

        List<TreeNode<T>> newNodes = new ArrayList<TreeNode<T>>();
        for (TreeNode<T> node : nodes) {
            if (node != null) {
                System.out.print(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print("      ");
            }

            TreePrinter.printWhitespaces(betweenSpaces-7);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                TreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    TreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null) {
                    System.out.print("/");
                }else
                    TreePrinter.printWhitespaces(1);

                TreePrinter.printWhitespaces(i + i);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    TreePrinter.printWhitespaces(1);

                TreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends  Comparable> int maxLevel(TreeNode<T> node) {
        if (node == null)
            return 0;

        return Math.max(TreePrinter.maxLevel(node.left), TreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T extends  Comparable> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }



    public static void main(String[] args) {
        System.out.println("Prints a Tree top down");
    }

}
