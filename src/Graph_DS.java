import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph_DS implements graph {

    private HashMap<Integer, node_data> nodes;
    private HashMap<node_data, List<node_data>> edges;
    private int modifyCount;
    private int edgeSize;

    public Graph_DS() {
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
        modifyCount = 0;
        edgeSize = 0;
    }


    @Override
    public node_data getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        node_data node_1 = getNode(node1);
        node_data node_2 = getNode(node2);

        return edges.containsKey(node_1) && edges.get(node_1).contains(node_2);
    }

    @Override
    public void addNode(node_data n) {
        nodes.put(n.getKey(), n);
        modifyCount++;
    }

    @Override
    public void connect(int node1, int node2) {
        node_data node_1 = getNode(node1);
        node_data node_2 = getNode(node2);

        // node_1 isn't in the edges map ( doesn't connect to any nodes)
        if (!edges.containsKey(node_1)) {
            List<node_data> neighbors = new LinkedList<>();
            neighbors.add(node_2);
            edges.put(node_1, neighbors);

        } else if (!edges.get(node_1).contains(node_2)) {
            edges.get(node_1).add(node_2);
        }

        // node_2 isn't in the edges map ( doesn't connect to any nodes)
        if (!edges.containsKey(node_2)) {
            List<node_data> neighbors = new LinkedList<>();
            neighbors.add(node_1);
            edges.put(node_2, neighbors);
            //connection between two nodes creates an edge
            edgeSize++;
        } else if (!edges.get(node_2).contains(node_1)) {
            edges.get(node_2).add(node_1);
            //connection between two nodes creates an edge
            edgeSize++;
        }


    }

    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }

    @Override
    public Collection<node_data> getV(int node_id) {
        return nodes.get(node_id).getNi();
    }

    @Override
    public node_data removeNode(int key) {

        if (!nodes.containsKey(key)) {
            return null;
        }

        modifyCount++;
        //get the node
        node_data deleted_node = getNode(key);

        // remove this node from all the neighbors lists of deleted_node
        for (int i = 0; i < edges.get(deleted_node).size(); i++) {
            node_data neighbor = edges.get(deleted_node).get(i);
            edges.get(neighbor).remove(deleted_node);
            edgeSize--;
        }
        nodes.remove(key);
        edges.remove(deleted_node);
        return deleted_node;

    }

    @Override
    public void removeEdge(int node1, int node2) {
        node_data node_1 = getNode(node1);
        node_data node_2 = getNode(node2);

        if (edges.containsKey(node_1)) {
            edges.get(node_1).remove(node_2);
        }

        if (edges.containsKey(node_2)) {
            edges.get(node_2).remove(node_1);
        }

        modifyCount++;
        if (edgeSize > 0) {
            edgeSize--;
        }
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edgeSize;
    }

    @Override
    public int getMC() {
        return modifyCount;
    }

    public HashMap<Integer, node_data> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<Integer, node_data> nodes) {
        this.nodes = nodes;
    }

    public HashMap<node_data, List<node_data>> getEdges() {
        return edges;
    }

    public void setEdges(HashMap<node_data, List<node_data>> edges) {
        this.edges = edges;
    }
}
