package graphs;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by kuldeep on 9/7/14.
 */
public class Graph {

    //All the nodes that have an indegree 0! May lead to O(n) lookups/adds/removals
    private HashSet<GraphNode> roots = new HashSet<>();
    private HashSet<GraphNode> nodeSet = new HashSet<>();

    /**
     * Add an edge from node a directed towards node b. Indegree of node b will be prev + 1
     *
     * @param a
     * @param b
     * @param cost
     */
    public void addDirectedEdge(GraphNode a, GraphNode b, Integer cost) {
        a.adjNodes.add(b);
        a.costs.add(cost);
        roots.remove(b);
        if(!nodeSet.contains(a))
            roots.add(a);

        nodeSet.add(a); //Overwrite
        nodeSet.add(b); //Overwrite
    }

    public void addUnDirectedEdge(GraphNode a, GraphNode b, Integer cost) {
        a.adjNodes.add(b);
        b.adjNodes.add(a);
        a.costs.add(cost);
        b.costs.add(cost);

        nodeSet.add(a);
        nodeSet.add(b);
    }


    public Collection<GraphNode> getIndegreeZeroList() {
        return roots;
    }

    public static Graph getSampleGraph() {
        //PriorityQueue<Integer> maxPQ= new PriorityQueue<Integer>(11, Collections.reverseOrder());
        //BellmanForgAlgo algo = new BellmanForgAlgo();
        Graph g = new Graph();
        GraphNode n0 = new GraphNode(0);
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3);
        GraphNode n4 = new GraphNode(4);
        //algo.connectNodes(n0,n1, 5);
        g.addDirectedEdge(n0,n2, 4);
        g.addDirectedEdge(n0, n3, -1);
        g.addDirectedEdge(n1, n3, 10);
        g.addDirectedEdge(n1, n4, 3);
        g.addDirectedEdge(n2, n1, 6);
        g.addDirectedEdge(n2, n4, 9);
        g.addDirectedEdge(n3, n2, 3);
        g.addDirectedEdge(n2, n3, 3);//Make a cycle

        return g;
    }




}
