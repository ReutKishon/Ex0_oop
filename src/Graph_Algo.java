import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;

public class Graph_Algo implements graph_algorithms {
    private graph graph;

    @Override

    public void init(graph g) {
        this.graph = g;
    }

    @Override
    public graph copy() {
        graph copyGraph = new Graph_DS();
        //add nodes to the new graph
        for (node_data node : graph.getV()) {
            copyGraph.addNode(new NodeData(node.getKey(), node.getTag()));
        }
        //add edges to the new graph
        for (node_data node : graph.getV()) {

            if (node.getNi().size() != 0) {
                for (node_data neighbor : node.getNi()) {
                    copyGraph.connect(node.getKey(), neighbor.getKey());
                }

            }
        }

        return copyGraph;
    }


    @Override
    public boolean isConnected() {
        if (graph.nodeSize() == 0) {
            return true;
        }
        Iterator<node_data> it = graph.getV().iterator();
        //start the DFS from vertex 0
        BFS_Algo(it.next().getKey());

        //check if all the vertices are visited, if yes then graph is connected

        for (node_data nodeData : graph.getV()) {
            if (nodeData.getTag() != 1) {
                resetTags();
                return false;
            }

        }
        resetTags();
        return true;
    }

    public void resetTags() {
        for (node_data node : graph.getV()) {
            node.setTag(0);
        }
    }


    public void BFS_Algo(int node) {


        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        graph.getNode(node).setTag(1);
        queue.add(node);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            int tempNode = queue.poll();

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            for (node_data neighbor : graph.getNode(tempNode).getNi()) {
                if (neighbor.getTag() != 1) {
                    neighbor.setTag(1);

                    queue.add(neighbor.getKey());
                }
            }
        }
    }


    @Override
    public int shortestPathDist(int src, int dest) {
//        int res = shortestPathDistHelper(src, dest, new LinkedList<>());
//        if (res == Integer.MAX_VALUE) return -1;
//        return res;


        // Initialize distances as 0
        HashMap<Integer , Integer> distance  = new HashMap<>();

        for (node_data node : graph.getV()) {
            distance.put(node.getKey(),-1);
        }

        // queue to do BFS.
        Queue<node_data> Q = new LinkedList<>();
        distance.put(src,0);

        Q.add(graph.getNode(src));
        graph.getNode(src).setTag(1);
        while (!Q.isEmpty())
        {
            node_data curr = Q.peek();
            Q.poll();

            for (node_data neighbor : curr.getNi())
            {
                if (neighbor.getTag() == 1)
                    continue;

                // update distance for i
                distance.put(neighbor.getKey(),distance.get(curr.getKey()) + 1);
                Q.add(neighbor);
                neighbor.setTag(1);
            }
        }
        resetTags();
        return distance.get(dest);


    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        var route = shortestPathHelper(src, dest, new LinkedList<>());
        if (route != null) {
            route.add(dest);
        }

        return route != null ? route.stream().map(key -> graph.getNode(key)).collect(Collectors.toList()) : null;
    }

    /// helper methods
    public List<Integer> shortestPathHelper(Integer currNode, Integer destNode, List<Integer> route) {
        if (route.contains(currNode)) return null;

        if (currNode.equals(destNode)) {
            return route;
        }

        List<Integer> shortestRoute = null;
        route.add(currNode);

        for (var neighbor : graph.getNode(currNode).getNi()) {

            var shortestResult = shortestPathHelper(neighbor.getKey(), destNode, route);

            if (shortestResult != null && shortestRoute == null) {
                shortestRoute = new LinkedList<>(shortestResult);
            } else if (shortestResult != null && shortestResult.size() < shortestRoute.size()) {
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
