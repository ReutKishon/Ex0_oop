import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Graph_Algo implements graph_algorithms {
    private graph graph;

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
//        DFS_Algo(0, graph.getEdges(), flag);

        //check if all the vertices are visited, if yes then graph is connected

        for (boolean b : flag) {
            if (!b) return false;

        }
        return true;
    }


    public void DFS_Algo(int node, HashMap<Integer, List<Integer>> edges, boolean[] flag) {

        if (!flag[node]) {
            flag[node] = true;
        }
        for (int i = 0; i < edges.get(node).size(); i++) {
            var neighbor = edges.get(node).get(i);
            if (!flag[neighbor]) {
                DFS_Algo(neighbor, edges, flag);
            }
        }


    }

    @Override
    public int shortestPathDist(int src, int dest) {
        return shortestPathDistHelper(src, dest, new LinkedList<>());
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        return shortestPathHelper(src, dest, new LinkedList<>()).stream().map(key -> graph.getNode(key)).collect(Collectors.toList());
    }

    public List<Integer> shortestPathHelper(Integer currNode, Integer destNode, List<Integer> route) {
        if (route.contains(currNode)) return null;

        if (currNode.equals(destNode)) {
            route.add(currNode);
            return route;
        }

        List<Integer> shortestRoute = null;
        route.add(currNode);

        for (var neighbor : graph.getNode(currNode).getNi()) {

            var shortestResult = shortestPathHelper(neighbor.getKey(), destNode, route);

            if (shortestResult != null && shortestRoute == null) {
                shortestRoute = new LinkedList<>(shortestResult);
            }
            else if (shortestResult != null && shortestResult.size() < shortestRoute.size()) {
                shortestRoute = new LinkedList<>(shortestResult);
            }
        }
        route.remove(currNode);

        return shortestRoute;
    }

    public int shortestPathDistHelper(int currNode, int destNode, List<Integer> route) {
        if (currNode == destNode) return 0;
        if (route.contains(currNode)) return Integer.MAX_VALUE;

        route.add(currNode);
        var minDist = Integer.MAX_VALUE;
        for (var neighbor : graph.getNode(currNode).getNi()) {
            var shortestResult = shortestPathDistHelper(neighbor.getKey(), destNode, route);

            if (shortestResult != Integer.MAX_VALUE) {
                minDist = Math.min(minDist, 1 + shortestResult);
            }
        }
        route.removeIf(v -> v == currNode);

        return minDist;
    }

}
