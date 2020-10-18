import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Graph_DS implements graph {

    private HashMap<Integer, node_data> nodes = new HashMap<>();
    private HashMap<node_data, List<node_data>> edges = new HashMap<>();
    private int modifyCount = 0;


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

        edges.get(node_1).add(node_2);
        edges.get(node_2).add(node_1);

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
        node_data deleted_node = nodes.get(key);

        // remove this node from all the neighbors of deleted_node
        for (int i = 0; i < edges.get(deleted_node).size(); i++) {
            node_data neighbor = edges.get(deleted_node).get(i);
            edges.get(neighbor).remove(deleted_node);
        }
        nodes.remove(key);
        edges.remove(deleted_node);
        return deleted_node;

    }

    @Override
    public void removeEdge(int node1, int node2) {


        modifyCount++;
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return 0;
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
