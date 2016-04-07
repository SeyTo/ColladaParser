package parser.colladaComponents;

import parser.colladaComponents.nodeComponent.cNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rj on 3/25/2016.
 */
public final class cScene {
    public String id;
    public String name;
    public List<cNode> nodes;

    public cScene() {
        nodes = new ArrayList<>();
    }

    @Override
    public String toString() {
        String out = "cScene => id = " + id + " , name = " + name + "\nNodes :\n";
        for (cNode node : nodes) {
            out += node.toString() + "\n";
        }
        return out;
    }
}
