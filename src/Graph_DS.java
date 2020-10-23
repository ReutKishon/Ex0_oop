import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Graph_DS implements graph {

    private HashMap<Integer, node_data> nodes;
    private int modifyCount;
    private int edgeSize;

    public Graph_DS() {
        this.nodes = new HashMap<>();
        modifyCount = 0;
        edgeSize = 0;
    }


    @Override
    public node_data getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        var nodeData = nodes.get(node1);
        return nodeData.getNi().contains(nodes.get(node2));
    }

    @Override
    public void addNode(node_data n) {
        nodes.put(n.getKey(), n);
        modifyCount++;
    }

    @Override
    public void connect(int node1, int node2) {
        nodes.get(node1).addNi(nodes.get(node2));
        nodes.get(node2).addNi(nodes.get(node1));
        edgeSize++;
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
        var deletedData = getNode(key);
        // remove this node from all the neighbors lists of deleted_node
        for (node_data node_data : getV()) {
            node_data.removeNode(node_data);
        }
        nodes.remove(key);
        return deletedData;

    }

    @Override
    public void removeEdge(int node1, int node2) {
        nodes.get(node1).removeNode(nodes.get(node2));
        nodes.get(node2).removeNode(nodes.get(node1));
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




}
