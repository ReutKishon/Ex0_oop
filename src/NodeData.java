import java.util.ArrayList;
import java.util.Collection;

public class NodeData implements node_data {
    private static int count = 0;
    private int key;
    Collection<node_data> neighbors;
    Collection<Integer> neighbors_keys;
    String data;
    int tag;

    public NodeData() {
        key = count++;
        neighbors = new ArrayList<>();
        neighbors_keys = new ArrayList<>();
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public Collection<node_data> getNi() {
        return neighbors;
    }

    @Override
    public boolean hasNi(int key) {
        return neighbors_keys.contains(key);
    }

    @Override
    public void addNi(node_data t) {
        neighbors.add(t);
        neighbors_keys.add(t.getKey());
    }

    @Override
    public void removeNode(node_data node) {
        neighbors.remove(node);
        neighbors_keys.remove(node.getKey());
    }

    @Override
    public String getInfo() {
        return data;
    }

    @Override
    public void setInfo(String s) {
        this.data = s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
