import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class Graph_DS implements graph {

    private HashMap<Integer, node_data> nodes;
    private int modifyCount;
    private int edgeSize;

    public Graph_DS() {
        this.nodes = new HashMap<>();
        modifyCount = 0;
        edgeSize = 0;
        NodeData.count = 0;

    }

    // get the node by key if it exists
    @Override
    public node_data getNode(int key) {
        if (!nodes.containsKey(key)) {
            return null;
        }
        return nodes.get(key);
    }

    @Override
    //check if one of the nodes is contained in the other node's adjacency list.
    public boolean hasEdge(int node1, int node2) {
        var nodeData = nodes.get(node1);
        return nodeData.getNi().contains(nodes.get(node2));
    }

    @Override
    //add node n to the map ,and increases the modifyCount - it changes the nodeSize.
    public void addNode(node_data n) {
        nodes.put(n.getKey(), n);
        modifyCount++;
    }

    @Override
    public void connect(int node1, int node2) {
        // edge from node to itself is forbidden.
        if (node1 == node2) return;
        // add node1 and node2 to each other's neighbors list **if this edge doesn't exist**
        //it increases the edgesSize ans so the modifyCount.
        if (!nodes.get(node1).getNi().contains(nodes.get(node2))) {

            nodes.get(node1).addNi(nodes.get(node2));
            nodes.get(node2).addNi(nodes.get(node1));
            edgeSize++;
            modifyCount++;
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
        // try to remove node that doesn't exist
        if (!nodes.containsKey(key)) {
            return null;
        }

        modifyCount++;
        //get the node
        var deletedData = getNode(key);
        // remove this node from all the neighbors lists of deleted_node and decrease the edgeSize respectively

        for (node_data neighbor : deletedData.getNi()) {
            neighbor.removeNode(deletedData);

            edgeSize--;
            modifyCount--;

        }
        nodes.remove(key);
        return deletedData;

    }

    @Override
    public void removeEdge(int node1, int node2) {
//  try to remove edge that doesn't exist
        if (!nodes.get(node1).getNi().contains(nodes.get(node2))) return;
        // remove node1 and node2 from each other's neighbors list and decrease the edgeSize in 1
        nodes.get(node1).removeNode(nodes.get(node2));
        nodes.get(node2).removeNode(nodes.get(node1));
        edgeSize--;
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
