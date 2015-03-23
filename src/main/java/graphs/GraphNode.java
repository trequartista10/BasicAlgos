package graphs;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: kuldeep
 * Date: 4/22/14
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class GraphNode {

    GraphNode(Object val) {
        this.val = val;
    }

    final Object val;
    public ArrayList<GraphNode> adjNodes = new ArrayList<GraphNode>();
    public ArrayList<Integer> costs = new ArrayList<Integer>();
    //For SP
    public GraphNode from;
    public double costToThis;
    public int lowLink;
    public int index;
}