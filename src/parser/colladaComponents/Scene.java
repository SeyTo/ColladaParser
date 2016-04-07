package parser.colladaComponents;

import parser.colladaComponents.nodeComponent.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rj on 3/25/2016.
 */
public final class Scene {
    public String id;
    public String name;
    public List<Node> nodes;

    public Scene() {
        nodes = new ArrayList<>();
    }

    @Override
    public String toString() {
        String out = "Scene => id = " + id + " , name = " + name + "\nNodes :\n";
        for (Node node : nodes) {
            out += node.toString() + "\n";
        }
        return out;
    }
}
