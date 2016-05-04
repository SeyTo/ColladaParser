package colladaParser.colladaComponents;

import colladaParser.colladaComponents.nodeComponent.cNode;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rj on 3/25/2016.
 */
public final class cScene extends cComponent{

    public String name;
    public List<cNode> nodes;

    public cScene() {
        nodes = new ArrayList<>();
    }

    public void collect(Element element, Namespace ns) {
        id = element.getAttributeValue("id");
        name = element.getAttributeValue("name");
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
