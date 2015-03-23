package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by kuldeep on 9/7/14.
 */
public class CycleFinder {


    public List<GraphNode> getCycle(Graph g) {
        for(GraphNode node : g.getIndegreeZeroList()) {
            List<GraphNode> cycle = getCycle(node, new HashSet<>());
            if(cycle != null)
                return cycle;
        }

        return null;
    }

    public List<GraphNode> getCycle(GraphNode node, HashSet<GraphNode> onStack) {
        for(GraphNode n : node.adjNodes) {
            if(onStack.contains(n)) {
                return new ArrayList<GraphNode>(onStack);
            }

            onStack.add(n);
            getCycle(n,onStack);
        }
        onStack.remove(node);

        return null;
    }

    public static void main(String[] args) {
        Graph g = Graph.getSampleGraph();
        CycleFinder cf = new CycleFinder();
        List<GraphNode> cycle = cf.getCycle(g);

        if(cycle != null)
            for(GraphNode node : cycle)
                System.out.println(node.val);

    }

}
