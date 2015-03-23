package graphs;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/22/14
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class BellmanForgAlgo {

    HashMap<GraphNode, Integer> nodeSet = new HashMap<GraphNode, Integer>();
    ArrayList<GraphNode> nodeIdx = new ArrayList<GraphNode>();
    ArrayList<Integer> distFromSrc = new ArrayList<Integer>();
    GraphNode[] edgeTo;
    int count = 0;

    /**
     * Edge Edge from Node1 to Node2
     * @param node1
     * @param node2
     */
    void connectNodes(GraphNode node1, GraphNode node2, Integer cost) {
        if(!nodeSet.containsKey(node1)) {
            nodeSet.put(node1, count);
            nodeIdx.add(node1);
            distFromSrc.add(new Integer(Integer.MAX_VALUE));
            count++;
        }

        if(!nodeSet.containsKey(node2)) {
            nodeSet.put(node2, count);
            nodeIdx.add(node2);
            distFromSrc.add(new Integer(Integer.MAX_VALUE));
            count++;
        }

        //Assuming no duplictes nodes are added
        node1.adjNodes.add(node2);
        node1.costs.add(cost);
    }


    void buildShortestPath(GraphNode src) {
        //Initialize edgeTo
        edgeTo = new GraphNode[nodeSet.size()];
        System.out.println("Total nodes: " + nodeSet.size());

        //Let every thing other than the sourcce to infinity
        for(int i=0; i < nodeIdx.size(); i++) {
            if(nodeIdx.get(i).equals(src))
                distFromSrc.set(i,0);
        }

        //Update costs for immediate neighbors of src will be done automatically




        //Do v-1 iterations
        // Relax all edges.
        //To relax all edges, we need to iterate over every node and relax its edges O(VE)
        //Space O(V)
        for(int i=0; i < nodeSet.size() -1; i++) {

            for(int j=0; j <nodeSet.size(); j++) {
                int v = distFromSrc.get(j);
                for(int k=0; k < nodeIdx.get(j).adjNodes.size(); k++) {
                    GraphNode e = nodeIdx.get(j).adjNodes.get(k);
                    int ew = nodeIdx.get(j).costs.get(k);
                    int index = nodeSet.get(e);
                    //A->B->C
                    //If distance to C from A is greater than B to A + B to C,
                    //Then update new path C->B->A
                    if(v != Integer.MAX_VALUE && distFromSrc.get(index) > v +  ew) {
                        distFromSrc.set(index,v +  ew);
                        edgeTo[index] = nodeIdx.get(j);
                    }
                }
            }
        }

        //Finding negative cycle TODO
        //for each path, backtrack to the src and see if there is a negative edge and if a node is traversed twice!

        //Print distTo
        for(int i=0; i < nodeIdx.size();i++) {
            System.out.println(nodeIdx.get(i).val + " " + distFromSrc.get(i));
        }

    }


    public class GraphComparator implements Comparator<GraphNode> {

        @Override
        public  void finalize() throws  Throwable {

            super.finalize();
        }

        @Override
        public int compare(GraphNode o1, GraphNode o2) {
            return o1.val.equals(o2.val) ? 1 : -1;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> maxPQ= new PriorityQueue<Integer>(11,Collections.reverseOrder());
        BellmanForgAlgo algo = new BellmanForgAlgo();
        GraphNode n0 = new GraphNode(0);
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3);
        GraphNode n4 = new GraphNode(4);
        //algo.connectNodes(n0,n1, 5);
        algo.connectNodes(n0,n2, 4);
        algo.connectNodes(n0,n3, -1);
        algo.connectNodes(n1,n3,10);
        algo.connectNodes(n1,n4,3);
        algo.connectNodes(n2,n1,6);
        algo.connectNodes(n2,n4,9);
        algo.connectNodes(n3,n2,3);
        algo.buildShortestPath(n0);




    }

}
