import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Graph_Algo implements graph_algorithms {
    private Graph_DS graph;

    @Override
    public void init(graph g) {
        this.graph = (Graph_DS) g;
    }

    @Override
    public graph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {


        //created visited array
        boolean[] flag = new boolean[graph.nodeSize()];

        //start the DFS from vertex 0
        DFS_Algo(graph.getNode(0), graph.getEdges(), flag);

        //check if all the vertices are visited, if yes then graph is connected

        for (boolean b : flag) {
            if (!b) return false;

        }
        return true;
    }


    public void DFS_Algo(node_data node, HashMap<node_data, List<node_data>> edges, boolean[] flag) {

        if (!flag[node.getKey()]) {
            flag[node.getKey()] = true;
        }
        for (int i = 0; i < edges.get(node).size(); i++) {
            node_data neighbor = edges.get(node).get(i);
            if (!flag[neighbor.getKey()]) {
                DFS_Algo(neighbor, edges, flag);
            }
        }


    }

    @Override
    public int shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        return null;
    }

    public graph getGraph() {
        return graph;
    }
}
