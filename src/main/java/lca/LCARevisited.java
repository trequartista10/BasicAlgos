package lca;


import trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic implementation for find
 * Created by kuldeep on 8/23/14.
 */
public class LCARevisited<T extends Comparable> {

    private final TreeNode<T> root;

    /**
     * Tree needs to be built before passing it to this
     * @param root
     */
    public LCARevisited(TreeNode<T> root) {
        this.root = root;
    }

    void dfs(TreeNode<T> node, int level, List<TreeNode> eulerList,List<Integer> levelList) {
        eulerList.add(node);
        levelList.add(level);

        if(node.children == null || node.children.size() == 0)
            return;

        for(TreeNode<T> child : node.children) {
            dfs(child ,level+1, eulerList, levelList);
            eulerList.add(node);
            levelList.add(level);
        }

    }

    /**
     * Index of the first occurence of something in the euler list
     * @param eulerList
     */
     public List<Integer> getRepresentative(List<TreeNode> eulerList, List<Integer> levelList, List<TreeNode> nodes) {
         int last = 0;
         List<Integer> represenative = new ArrayList<>();
         for(int i=0;i < nodes.size(); i++) {
             for(int j=0;j < eulerList.size(); j++)
             if(nodes.get(i).equals(eulerList.get(j))) {
                 represenative.add(j);
                 break;
             }
         }

         return represenative;
     }


    List<Integer> preprocess() {

        return null;
    }

    /**
     * Find the min node between two nodes p1 and p2
     * @param p1
     * @param p2
     * @return
     */
    TreeNode<T> queryRMQ(TreeNode<T> p1, TreeNode<T> p2) {

        return null;
    }

    /**
     * Find the LCA of two nodes p1 and p2. Work on Level List to find the LCA
     * @param p1
     * @param p2
     * @return
     */
    TreeNode<T> queryLCA(TreeNode<T> p1, TreeNode<T> p2) {
        return null;
    }


    static Comparable[][] naiveRMQPreprocesss(List<Comparable> nodes) {
        nodes.size();
        Comparable[][] dp = new Comparable[nodes.size() + 1][nodes.size() + 1];
        //Initialize dp
        for(int i=0; i <= nodes.size(); i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[0][i] = Integer.MAX_VALUE;
        }
        for(int i=1; i <= nodes.size(); i++)  {
            dp[i][i] = nodes.get(i-1);
            for(int j=i+1;j <= nodes.size(); j++) {
                if(nodes.get(j-1).compareTo( dp[i][j-1]) < 0)
                    dp[i][j] = nodes.get(j-1);
                else
                    dp[i][j] = dp[i][j-1];

            }
        }

        return dp;
    }

    static Comparable queryNaiveRMQ(Comparable[][] dp, int i, int j) {
        return  dp[i+1][j+1];
    }

    static void printDP(Comparable[][] dp) {
        for(int i=0; i < dp.length; i++) {
            for(int j=0; j < dp[i].length; j++)
                System.out.print(dp[i][j] + " ");

            System.out.println();
        }

    }



    public static void main(String[] args) {
        List<Comparable> nodes = new ArrayList<>();
        nodes.add(99);
        nodes.add(70);
        nodes.add(85);
        nodes.add(2);
        nodes.add(101);
        nodes.add(3);

        Comparable[][] dp = naiveRMQPreprocesss(nodes);

        System.out.println(queryNaiveRMQ(dp, 0, 4));
        System.out.println(queryNaiveRMQ(dp, 0, 1));
        System.out.println(queryNaiveRMQ(dp, 2, 2));
        System.out.println(queryNaiveRMQ(dp, 2, 4));

        printDP(dp);

    }

}
